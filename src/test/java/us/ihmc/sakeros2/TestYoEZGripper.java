package us.ihmc.sakeros2;

import us.ihmc.robotics.robotSide.RobotSide;
import us.ihmc.yoVariables.registry.YoRegistry;

public class TestYoEZGripper extends YoEZGripper
{
   public TestYoEZGripper(YoRegistry registry, RobotSide robotSide)
   {
      super(registry, robotSide);
   }

   @Override
   public boolean updateCalibration()
   {
      return true;
   }
}
