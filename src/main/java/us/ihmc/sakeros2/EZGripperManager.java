package us.ihmc.sakeros2;

import us.ihmc.robotics.stateMachine.core.State;
import us.ihmc.robotics.stateMachine.core.StateMachine;
import us.ihmc.robotics.stateMachine.factories.StateMachineFactory;

import java.util.List;

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
   private final StateMachine<OperationMode, State> stateMachine;

   private float goalPosition = 0.1f;
   private float maxEffort = 0.0f;
   private boolean torqueOn = false;
   private byte temperatureLimit = 75;

   private boolean isCalibrated = false;

   public EZGripperManager(EZGripperInterface gripper)
   {
      this.gripper = gripper;

      StateMachineFactory<OperationMode, State> factory = new StateMachineFactory<>(OperationMode.class);
      factory.buildClock(System::nanoTime);

      // Add position control state
      factory.addState(OperationMode.POSITION_CONTROL, new PositionControlState());

      // Position control goes to calibration or error reset if demanded
      factory.addTransition(OperationMode.POSITION_CONTROL, OperationMode.CALIBRATION, nanoTime -> desiredOperationMode == OperationMode.CALIBRATION);
      factory.addTransition(OperationMode.POSITION_CONTROL, OperationMode.ERROR_RESET, nanoTime -> desiredOperationMode == OperationMode.ERROR_RESET);

      // Add calibration state. Goes to position control once done.
      factory.addStateAndDoneTransition(OperationMode.CALIBRATION, new CalibrationState(), OperationMode.POSITION_CONTROL);

      // Add error reset state. Goes to position control once done.
      factory.addStateAndDoneTransition(OperationMode.ERROR_RESET, new ErrorResetState(), OperationMode.POSITION_CONTROL);

      // Add cooldown state. Goes to position control once done.
      factory.addStateAndDoneTransition(OperationMode.COOLDOWN, new CooldownState(), OperationMode.POSITION_CONTROL);

      // All other states go to cooldown if gripper overheats.
      factory.addTransition(List.of(OperationMode.POSITION_CONTROL, OperationMode.CALIBRATION, OperationMode.ERROR_RESET),
                            OperationMode.COOLDOWN,
                            nanoTime -> temperatureLimit != DISABLE_AUTO_COOLDOWN && Byte.compareUnsigned(gripper.getTemperature(), temperatureLimit) > 0);

      // Build the state machine
      stateMachine = factory.build(OperationMode.POSITION_CONTROL);
   }

   private class PositionControlState implements State
   {
      @Override
      public void onEntry() {}

      @Override
      public void doAction(double timeInState)
      {
         gripper.setGoalPosition(goalPosition);
         gripper.setMaxEffort(maxEffort);
         gripper.setTorqueOn(torqueOn);
      }

      @Override
      public void onExit(double timeInState) {}
   }

   private class CalibrationState implements State
   {
      private boolean done = false;

      @Override
      public void onEntry() {}

      @Override
      public void doAction(double timeInState)
      {
         done = gripper.updateCalibration();
      }

      @Override
      public void onExit(double timeInState)
      {
         isCalibrated = done;
         setOperationMode(OperationMode.POSITION_CONTROL);
      }

      @Override
      public boolean isDone(double timeInState)
      {
         return done;
      }
   }

   private class ErrorResetState implements State
   {
      private boolean powerOff;

      @Override
      public void onEntry()
      {
         powerOff = true;
      }

      @Override
      public void doAction(double timeInState)
      {
         if (powerOff)
         {
            gripper.setTorqueOn(false);
            gripper.setMaxEffort(0.0f);
            powerOff = false;
         }
         else
         {
            gripper.setTorqueOn(true);
            gripper.setMaxEffort(0.1f);
            powerOff = true;
         }
      }

      @Override
      public void onExit(double timeInState)
      {
         gripper.setTorqueOn(false);
         gripper.setMaxEffort(0.0f);
         setOperationMode(OperationMode.POSITION_CONTROL);
      }

      @Override
      public boolean isDone(double timeInState)
      {
         return gripper.getErrorCode() == EZGripperError.NONE.errorCode;
      }
   }

   private class CooldownState implements State
   {
      private byte goalTemp = (byte) 255;

      @Override
      public void onEntry()
      {
         goalTemp = (byte) (temperatureLimit - temperatureLimit / 10);
      }

      @Override
      public void doAction(double timeInState)
      {
         gripper.setTorqueOn(false);
         gripper.setMaxEffort(0.0f);
      }

      @Override
      public void onExit(double timeInState)
      {
         setOperationMode(OperationMode.POSITION_CONTROL);
      }

      @Override
      public boolean isDone(double timeInState)
      {
         return temperatureLimit == DISABLE_AUTO_COOLDOWN || Byte.compareUnsigned(gripper.getTemperature(), goalTemp) <= 0;
      }
   }

   public EZGripperInterface getGripper()
   {
      return gripper;
   }

   public void update()
   {
      stateMachine.doActionAndTransition();
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
    * @param maxEffort The maximum effort to use to read the goal position.
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
      OperationMode currentMode = stateMachine.getCurrentStateKey();
      return currentMode == null ? stateMachine.getInitialStateKey() : currentMode;
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
