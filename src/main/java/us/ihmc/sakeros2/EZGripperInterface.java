package us.ihmc.sakeros2;

import us.ihmc.robotics.robotSide.RobotSide;

public interface EZGripperInterface
{
   /**
    * Get the robot's side this gripper is attached to.
    *
    * @return The robot's side this gripper is attached to.
    */
   RobotSide getRobotSide();

   // COMMAND METHODS //

   /**
    * Command the gripper to calibrate.
    */
   void calibrate();
   boolean pollCalibrate();

   /**
    * Reset the errors reported by the gripper.
    */
   void resetErrors();
   boolean pollResetErrors();

   /**
    * Set whether to enable automatic cooldown.
    *
    * @param enable Whether to enable automatic cooldown.
    */
   void enableAutoCooldown(boolean enable);
   boolean autoCooldownEnabled();

   /**
    * Use to control the gripper while it's cooling down.
    * Not recommended unless strictly necessary.
    */
   void overrideCooldown();
   boolean pollOverrideCooldown();

   /**
    * Set the goal position.
    * 0.0 = closed, 1.0 = open.
    *
    * @param goalPosition The goal position.
    */
   void setGoalPosition(float goalPosition);
   float getGoalPosition();

   /**
    * Set the maximum effort to put into achieving the goal position.
    * 0.0 = no effort (fingers will not move), 1.0 = maximum effort (can quickly overheat actuator).
    * 0.3 is a reasonable normal value, and it is recommended not to exceed 0.8.
    *
    * @param maxEffort Maximum effort to put into achieving the goal position.
    */
   void setMaxEffort(float maxEffort);
   float getMaxEffort();

   /**
    * Set whether to turn torque on.
    * Keeping the torque off when not needed can hlp keep the gripper's temperature down.
    *
    * @param on Whether to turn the torque on.
    */
   void setTorqueOn(boolean on);
   boolean getTorqueOnCommand();

   // STATE METHODS //
   float getCurrentPosition();
   void setCurrentPosition(float currentPosition);

   float getCurrentEffort();
   void setCurrentEffort(float currentEffort);

   byte getTemperature();
   void setCurrentTemperature(byte currentTemperature);

   int getRealtimeTick();
   void setRealtimeTick(int realtimeTick);

   byte getErrorCode();
   void setErrorCode(byte errorCode);

   boolean isCalibrated();
   void setCalibrated(boolean isCalibrated);

   boolean isCalibrating();
   void setCalibrating(boolean isCalibrating);

   boolean isCoolingDown();
   void setCoolingDown(boolean isCoolingDown);

   boolean autoCooldownEnabledStatus();
   void setAutoCooldownEnabledStatus(boolean autoCooldownEnabled);
}
