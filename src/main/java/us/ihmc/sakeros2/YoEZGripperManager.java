package us.ihmc.sakeros2;

import us.ihmc.yoVariables.registry.YoRegistry;
import us.ihmc.yoVariables.variable.YoBoolean;
import us.ihmc.yoVariables.variable.YoDouble;
import us.ihmc.yoVariables.variable.YoEnum;
import us.ihmc.yoVariables.variable.YoInteger;

/**
 * A YoVariable-ized version of the {@link EZGripperManager}.
 */
public class YoEZGripperManager extends EZGripperManager
{
   private final YoEnum<OperationMode> desiredOperationMode;
   private final YoEnum<OperationMode> operationMode;

   private final YoDouble goalPosition;
   private final YoDouble maxEffort;
   private final YoBoolean torqueOn;
   private final YoInteger temperatureLimit;

   private final YoBoolean isCalibrated;

   public YoEZGripperManager(YoRegistry registry, EZGripperInterface gripper)
   {
      super(gripper);

      String prefix = gripper.getRobotSide().name() + "EZGripperManager";
      desiredOperationMode = new YoEnum<>(prefix + "DesiredOperationMode", registry, OperationMode.class);
      operationMode = new YoEnum<>(prefix + "operationMode", registry, OperationMode.class);

      goalPosition = new YoDouble(prefix + "GoalPosition", registry);
      maxEffort = new YoDouble(prefix + "MaxEffort", registry);
      torqueOn = new YoBoolean(prefix + "TorqueOn", registry);
      temperatureLimit = new YoInteger(prefix + "TemperatureLimit", registry);

      isCalibrated = new YoBoolean(prefix + "IsCalibrated", registry);
   }

   @Override
   public void update()
   {
      super.setOperationMode(desiredOperationMode.getValue());
      super.setGoalPosition((float) goalPosition.getValue());
      super.setMaxEffort((float) maxEffort.getValue());
      super.setTorqueOn(torqueOn.getValue());
      super.setTemperatureLimit((byte) temperatureLimit.getValue());

      super.update();

      operationMode.set(super.getOperationMode());
      isCalibrated.set(super.isCalibrated());
   }

   @Override
   public void setGoalPosition(float goalPosition)
   {
      this.goalPosition.set(goalPosition);
   }

   @Override
   public void setMaxEffort(float maxEffort)
   {
      this.maxEffort.set(maxEffort);
   }

   @Override
   public void setTorqueOn(boolean torqueOn)
   {
      this.torqueOn.set(torqueOn);
   }

   @Override
   public void setOperationMode(OperationMode operationMode)
   {
      this.desiredOperationMode.set(operationMode.ordinal());
   }

   @Override
   public void setTemperatureLimit(byte temperatureLimit)
   {
      this.temperatureLimit.set(Byte.toUnsignedInt(temperatureLimit));
   }
}
