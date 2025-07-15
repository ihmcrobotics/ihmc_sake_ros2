package us.ihmc.sakeros2;

/**
 * Manages higher-level control of an EZGripper, including calibration, position control, error resets, and automatic cooldowns.
 */
public class EZGripperManager
{
   public enum OperationMode
   {
      POSITION_CONTROL, CALIBRATION, ERROR_RESET, COOLDOWN;

      public static final OperationMode[] values = values();

      public static OperationMode fromByte(byte ordinal)
      {
         return values[ordinal];
      }

      public byte toByte()
      {
         return (byte) this.ordinal();
      }
   }

   public static final byte DISABLE_AUTO_COOLDOWN = (byte) 255;

   private final EZGripperInterface gripper;

   private OperationMode desiredOperationMode = OperationMode.POSITION_CONTROL;
   private OperationMode operationMode = desiredOperationMode;

   private float goalPosition = 0.1f;
   private float maxEffort = 0.3f;
   private boolean torqueOn = false;
   private byte temperatureLimit = 75;

   private boolean isCalibrated = false;

   private boolean resettingError = false;

   private boolean isCoolingDown = false;
   private byte cooldownGoalTemp = (byte) (temperatureLimit - temperatureLimit / 10);

   public EZGripperManager(EZGripperInterface gripper)
   {
      this.gripper = gripper;
   }

   public EZGripperInterface getGripper()
   {
      return gripper;
   }

   /**
    * Updates the gripper commands based on operation mode. Should be called periodically.
    */
   public void update()
   {
      if (getGripper().getErrorCode() != 0)
      {
         // If the error needs to be reset, do so
         if (desiredOperationMode == OperationMode.ERROR_RESET)
         {
            operationMode = OperationMode.ERROR_RESET;
            updateErrorReset();
         }
         else
         {
            setMaxEffort(0.0f);
            setTorqueOn(false);
            return;
         }
      }

      // Check whether the gripper needs to cooldown if the auto cooldown isn't disabled
      if (temperatureLimit != DISABLE_AUTO_COOLDOWN && checkCooldown())
         return;

      // If calibration is requested or gripper is currently calibrating, update the calibration
      if (desiredOperationMode == OperationMode.CALIBRATION || operationMode == OperationMode.CALIBRATION)
      {
         operationMode = OperationMode.CALIBRATION;
         updateCalibration();
         return;
      }

      operationMode = desiredOperationMode;
      switch (operationMode)
      {
         case POSITION_CONTROL -> updatePositionControl();
         case COOLDOWN -> checkCooldown();
      }
   }

   private boolean checkCooldown()
   {
      // Check if gripper needs to start cooling down
      if (!isCoolingDown && Byte.compareUnsigned(temperatureLimit, gripper.getTemperature()) < 0)
      {
         cooldownGoalTemp = (byte) (temperatureLimit - temperatureLimit / 10);
         operationMode = OperationMode.COOLDOWN;
         isCoolingDown = true;
      }

      // Check if gripper has cooled down sufficiently
      if (isCoolingDown && Byte.compareUnsigned(cooldownGoalTemp, gripper.getTemperature()) >= 0)
      {
         setOperationMode(OperationMode.POSITION_CONTROL);
         return isCoolingDown = false;
      }

      // Cooling down
      if (isCoolingDown)
      {
         gripper.setMaxEffort(0.0f);
         gripper.setGoalPosition(0.1f);
         gripper.setTorqueOn(false);
      }

      return isCoolingDown;
   }

   private void updatePositionControl()
   {
      gripper.setGoalPosition(goalPosition);
      gripper.setMaxEffort(maxEffort);
      gripper.setTorqueOn(torqueOn);
   }

   private void updateCalibration()
   {
      if (gripper.updateCalibration())
      {
         isCalibrated = true;
         operationMode = OperationMode.POSITION_CONTROL;
         setOperationMode(OperationMode.POSITION_CONTROL);
      }
   }

   private void updateErrorReset()
   {
      if (!resettingError)
      {
         gripper.setMaxEffort(0.0f);
         gripper.setTorqueOn(false);
         resettingError = true;
      }
      else
      {
         gripper.setMaxEffort(0.05f);
         gripper.setTorqueOn(true);
         resettingError = false;
      }
   }

   /**
    * Set the goal position. 0.0 = fully closed, 1.0 = fully open.
    *
    * @param goalPosition The desired position.
    */
   public void setGoalPosition(float goalPosition)
   {
      this.goalPosition = goalPosition;
   }

   /**
    * <p>Set the maximum effort to be used to achieve the goal position. 0.0 = no effort, 1.0 = max effort.</p>
    * <p>0.0 = no effort (fingers will not move), 1.0 = maximum effort (can quickly overheat actuator).
    * 0.3 is a reasonable normal value, and it is recommended not to exceed 0.8.</p>
    *
    * @param maxEffort
    */
   public void setMaxEffort(float maxEffort)
   {
      this.maxEffort = maxEffort;
   }

   /**
    * Set whether the gripper should have its torque on. Keeping the torque off when not needed can help keep the gripper's temperature down
    *
    * @param torqueOn Whether to have the torque on.
    */
   public void setTorqueOn(boolean torqueOn)
   {
      this.torqueOn = torqueOn;
   }

   /**
    * Set the desired operation mode.
    *
    * @param operationMode The desired operation mode.
    */
   public void setOperationMode(OperationMode operationMode)
   {
      this.desiredOperationMode = operationMode;
   }

   /**
    * Get the current operation mode. Depending on the state of the manager,
    * the current operation mode may not be the desired operation mode.
    *
    * @return The current operation mode.
    */
   public OperationMode getOperationMode()
   {
      return operationMode;
   }

   /**
    * Whether the gripper has been calibrated.
    *
    * @return {@code true} if the gripper has been calibrated. {@code false} otherwise.
    */
   public boolean isCalibrated()
   {
      return isCalibrated;
   }

   /**
    * <p>Set the temperature limit.</p>
    * <p>The manager will automatically go into cooldown mode if the gripper exceed this temperature.
    * Cooldown will continue until the gripper's temperature drops by 10% of the temperature limit.</p>
    * <p>To override or disable automatic cooldown, set the temperature limit to {@link #DISABLE_AUTO_COOLDOWN}</p>
    *
    * @param temperatureLimit The temperature limit, in Celsius.
    */
   public void setTemperatureLimit(byte temperatureLimit)
   {
      this.temperatureLimit = temperatureLimit;
   }
}
