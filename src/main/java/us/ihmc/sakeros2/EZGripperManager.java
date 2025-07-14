package us.ihmc.sakeros2;

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

   private static final byte DISABLE_AUTO_COOLDOWN = (byte) 255;

   private final EZGripperInterface gripper;

   private OperationMode desiredOperationMode = OperationMode.POSITION_CONTROL;
   private OperationMode operationMode = desiredOperationMode;

   private float goalPosition;
   private float maxEffort;
   private boolean torqueOn;
   private byte temperatureLimit;

   private boolean isCalibrated;

   private boolean isCoolingDown;
   private byte cooldownGoalTemp;

   public EZGripperManager(EZGripperInterface gripper)
   {
      this.gripper = gripper;
   }

   public EZGripperInterface getGripper()
   {
      return gripper;
   }

   public void update()
   {
      // Check whether the gripper needs to cooldown if the auto cooldown isn't disabled
      if (temperatureLimit != DISABLE_AUTO_COOLDOWN && checkCooldown())
         return;

      operationMode = desiredOperationMode;
      switch (operationMode)
      {
         case POSITION_CONTROL -> updatePositionControl();
         case CALIBRATION -> updateCalibration();
         case ERROR_RESET -> updateErrorReset();
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
         setOperationMode(OperationMode.POSITION_CONTROL);
      }
   }

   private void updateErrorReset()
   {
      // If no error, go to position control
      if (gripper.getErrorCode() == 0)
      {
         setOperationMode(OperationMode.POSITION_CONTROL);
         return;
      }

      gripper.setMaxEffort(0.0f);
      gripper.setTorqueOn(false);
   }

   public void setGoalPosition(float goalPosition)
   {
      this.goalPosition = goalPosition;
   }

   public void setMaxEffort(float maxEffort)
   {
      this.maxEffort = maxEffort;
   }

   public void setTorqueOn(boolean torqueOn)
   {
      this.torqueOn = torqueOn;
   }

   public void setOperationMode(OperationMode operationMode)
   {
      this.desiredOperationMode = operationMode;
   }

   public OperationMode getOperationMode()
   {
      return operationMode;
   }

   public boolean isCalibrated()
   {
      return isCalibrated;
   }

   public void setTemperatureLimit(byte temperatureLimit)
   {
      this.temperatureLimit = temperatureLimit;
   }
}
