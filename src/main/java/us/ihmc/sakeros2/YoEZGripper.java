package us.ihmc.sakeros2;

import us.ihmc.robotics.robotSide.RobotSide;
import us.ihmc.yoVariables.registry.YoRegistry;
import us.ihmc.yoVariables.variable.YoBoolean;
import us.ihmc.yoVariables.variable.YoDouble;
import us.ihmc.yoVariables.variable.YoInteger;

public class YoEZGripper implements EZGripperInterface
{
   private final RobotSide robotSide;

   // Java control
   private final YoBoolean calibrate;
   private final YoBoolean resetErrors;
   private final YoBoolean enableAutoCooldown;
   private final YoBoolean overrideCooldown;

   // Low level control
   private final YoDouble goalPosition;
   private final YoDouble maxEffort;
   private final YoBoolean torqueOn;

   // State
   private final YoDouble currentPosition;
   private final YoDouble currentEffort;
   private final YoInteger temperature;
   private final YoInteger realtimeTick;
   private final YoInteger errorCode;

   private final YoBoolean isCalibrated;
   private final YoBoolean isCalibrating;
   private final YoBoolean isCoolingDown;
   private final YoBoolean autoCooldownEnabledStatus;

   public YoEZGripper(YoRegistry registry, RobotSide robotSide)
   {
      this.robotSide = robotSide;

      String prefix = robotSide.name() + "EZGripper";

      calibrate = new YoBoolean(prefix + "Calibrate", registry);
      resetErrors = new YoBoolean(prefix + "ResetErrors", registry);
      enableAutoCooldown = new YoBoolean(prefix + "EnableAutoCooldown", registry);
      overrideCooldown = new YoBoolean(prefix + "OverrideCooldown", registry);

      goalPosition = new YoDouble(prefix + "GoalPosition", registry);
      maxEffort = new YoDouble(prefix + "MaxEffort", registry);
      torqueOn = new YoBoolean(prefix + "TorqueOn", registry);

      currentPosition = new YoDouble(prefix + "CurrentPosition", registry);
      currentEffort = new YoDouble(prefix + "CurrentEffort", registry);
      temperature = new YoInteger(prefix + "Temperature", registry);
      realtimeTick = new YoInteger(prefix + "RealtimeTick", registry);
      errorCode = new YoInteger(prefix + "ErrorCode", registry);

      isCalibrated = new YoBoolean(prefix + "IsCalibrated", registry);
      isCalibrating = new YoBoolean(prefix + "IsCalibrating", registry);
      isCoolingDown = new YoBoolean(prefix + "IsCoolingDown", registry);
      autoCooldownEnabledStatus = new YoBoolean(prefix + "AutoCooldownEnabledStatus", registry);
   }

   @Override
   public RobotSide getRobotSide()
   {
      return robotSide;
   }

   @Override
   public void calibrate()
   {
      calibrate.set(true);
   }

   @Override
   public boolean pollCalibrate()
   {
      return calibrate.set(false);
   }

   @Override
   public void resetErrors()
   {
      resetErrors.set(true);
   }

   @Override
   public boolean pollResetErrors()
   {
      return resetErrors.set(false);
   }

   @Override
   public void enableAutoCooldown(boolean enable)
   {
      enableAutoCooldown.set(enable);
   }

   @Override
   public boolean autoCooldownEnabled()
   {
      return enableAutoCooldown.getValue();
   }

   @Override
   public void overrideCooldown()
   {
      overrideCooldown.set(true);
   }

   @Override
   public boolean pollOverrideCooldown()
   {
      return overrideCooldown.set(false);
   }

   @Override
   public void setGoalPosition(float goalPosition)
   {
      this.goalPosition.set(goalPosition);
   }

   @Override
   public float getGoalPosition()
   {
      return (float) goalPosition.getValue();
   }

   @Override
   public void setMaxEffort(float maxEffort)
   {
      this.maxEffort.set(maxEffort);
   }

   @Override
   public float getMaxEffort()
   {
      return (float) maxEffort.getValue();
   }

   @Override
   public void setTorqueOn(boolean on)
   {
      torqueOn.set(on);
   }

   @Override
   public boolean getTorqueOnCommand()
   {
      return torqueOn.getValue();
   }

   @Override
   public float getCurrentPosition()
   {
      return (float) currentPosition.getValue();
   }

   @Override
   public void setCurrentPosition(float currentPosition)
   {
      this.currentPosition.set(currentPosition);
   }

   @Override
   public float getCurrentEffort()
   {
      return (float) currentEffort.getValue();
   }

   @Override
   public void setCurrentEffort(float currentEffort)
   {
      this.currentEffort.set(currentEffort);
   }

   @Override
   public byte getTemperature()
   {
      return (byte) temperature.getValue();
   }

   @Override
   public void setCurrentTemperature(byte currentTemperature)
   {
      temperature.set(Byte.toUnsignedInt(currentTemperature));
   }

   @Override
   public int getRealtimeTick()
   {
      return realtimeTick.getValue();
   }

   @Override
   public void setRealtimeTick(int realtimeTick)
   {
      this.realtimeTick.set(realtimeTick);
   }

   @Override
   public byte getErrorCode()
   {
      return (byte) errorCode.getValue();
   }

   @Override
   public void setErrorCode(byte errorCode)
   {
      this.errorCode.set(Byte.toUnsignedInt(errorCode));
   }

   @Override
   public boolean isCalibrated()
   {
      return isCalibrated.getValue();
   }

   @Override
   public void setCalibrated(boolean isCalibrated)
   {
      this.isCalibrated.set(isCalibrated);
   }

   @Override
   public boolean isCalibrating()
   {
      return isCalibrating.getValue();
   }

   @Override
   public void setCalibrating(boolean isCalibrating)
   {
      this.isCalibrating.set(isCalibrating);
   }

   @Override
   public boolean isCoolingDown()
   {
      return isCoolingDown.getValue();
   }

   @Override
   public void setCoolingDown(boolean isCoolingDown)
   {
      this.isCoolingDown.set(isCoolingDown);
   }

   @Override
   public boolean autoCooldownEnabledStatus()
   {
      return autoCooldownEnabledStatus.getValue();
   }

   @Override
   public void setAutoCooldownEnabledStatus(boolean autoCooldownEnabled)
   {
      autoCooldownEnabledStatus.set(autoCooldownEnabled);
   }
}
