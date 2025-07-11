package us.ihmc.sakeros2;

import us.ihmc.robotics.robotSide.RobotSide;

class TestEZGripper implements EZGripperInterface
{
   private final RobotSide side;

   private boolean calibrate = false;
   private boolean resetErrors = false;
   private boolean enableAutoCooldown = true;
   private boolean overrideCooldown = false;
   private float goalPosition = 0.1f;
   private float maxEffort = 0.3f;
   private boolean torqueOn = false;

   private float currentPosition = 0.0f;
   private float currentEffort = 0.0f;
   private byte temperature = 0;
   private int realtimeTick = 0;
   private byte errorCode = 0;
   private boolean isCalibrated = false;
   private boolean isCalibrating = false;
   private boolean isCoolingDown = false;

   public TestEZGripper(RobotSide side)
   {
      this.side = side;
   }

   @Override
   public RobotSide getRobotSide()
   {
      return side;
   }

   @Override
   public void calibrate()
   {
      calibrate = true;
   }

   @Override
   public boolean pollCalibrate()
   {
      boolean value = calibrate;
      calibrate = false;
      return value;
   }

   @Override
   public void resetErrors()
   {
      resetErrors = true;
   }

   @Override
   public boolean pollResetErrors()
   {
      boolean value = resetErrors;
      resetErrors = false;
      return value;
   }

   @Override
   public void enableAutoCooldown(boolean enable)
   {

   }

   @Override
   public boolean autoCooldownEnabled()
   {
      return false;
   }

   @Override
   public void overrideCooldown()
   {

   }

   @Override
   public boolean pollOverrideCooldown()
   {
      return false;
   }

   @Override
   public void setGoalPosition(float goalPosition)
   {

   }

   @Override
   public float getGoalPosition()
   {
      return 0;
   }

   @Override
   public void setMaxEffort(float maxEffort)
   {

   }

   @Override
   public float getMaxEffort()
   {
      return 0;
   }

   @Override
   public void setTorqueOn(boolean on)
   {

   }

   @Override
   public boolean getTorqueOnCommand()
   {
      return false;
   }

   @Override
   public float getCurrentPosition()
   {
      return 0;
   }

   @Override
   public void setCurrentPosition(float currentPosition)
   {

   }

   @Override
   public float getCurrentEffort()
   {
      return 0;
   }

   @Override
   public void setCurrentEffort(float currentEffort)
   {

   }

   @Override
   public byte getTemperature()
   {
      return 0;
   }

   @Override
   public void setCurrentTemperature(byte currentTemperature)
   {

   }

   @Override
   public int getRealtimeTick()
   {
      return 0;
   }

   @Override
   public void setRealtimeTick(int realtimeTick)
   {

   }

   @Override
   public byte getErrorCode()
   {
      return 0;
   }

   @Override
   public void setErrorCode(byte errorCode)
   {

   }

   @Override
   public boolean isCalibrated()
   {
      return false;
   }

   @Override
   public void setCalibrated(boolean isCalibrated)
   {

   }

   @Override
   public boolean isCalibrating()
   {
      return false;
   }

   @Override
   public void setCalibrating(boolean isCalibrating)
   {

   }

   @Override
   public boolean isCoolingDown()
   {
      return false;
   }

   @Override
   public void setCoolingDown(boolean isCoolingDown)
   {

   }

   @Override
   public boolean autoCooldownEnabledStatus()
   {
      return false;
   }

   @Override
   public void setAutoCooldownEnabledStatus(boolean autoCooldownEnabled)
   {

   }
}
