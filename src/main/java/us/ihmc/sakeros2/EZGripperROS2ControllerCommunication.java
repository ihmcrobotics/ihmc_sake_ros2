package us.ihmc.sakeros2;

import ihmc_sake_ros2.msg.dds.EZGripperCommand;
import ihmc_sake_ros2.msg.dds.EZGripperState;
import us.ihmc.ros2.ROS2NodeBuilder;
import us.ihmc.ros2.ROS2Publisher;
import us.ihmc.ros2.ROS2Subscription;
import us.ihmc.ros2.RealtimeROS2Node;

/**
 * <p>Hardware side ROS 2 communication for the {@link EZGripperInterface}. Communicates with external controller.</p>
 * <p>Subscribes to {@link EZGripperCommand} messages and publishes {@link EZGripperState} messages.</p>
 */
public class EZGripperROS2ControllerCommunication
{
   private final RealtimeROS2Node node;

   private final EZGripperState stateMessage;
   private final ROS2Publisher<EZGripperState> statePublisher;

   private final EZGripperCommand commandMessage;
   private final EZGripperMessageListener<EZGripperCommand> commandListener;
   private final ROS2Subscription<EZGripperCommand> commandSubscription;

   public EZGripperROS2ControllerCommunication(String nodeName)
   {
      node = new ROS2NodeBuilder().buildRealtime(nodeName);

      stateMessage = new EZGripperState();
      statePublisher = node.createPublisher(EZGripperROS2API.STATE_TOPIC);

      commandMessage = new EZGripperCommand();
      commandListener = new EZGripperMessageListener<>(EZGripperCommand::new);
      commandSubscription = node.createSubscription(EZGripperROS2API.COMMAND_TOPIC, commandListener);
   }

   /**
    * Read the latest command into the hand object.
    *
    * @param handToPack Hand object to pack with the latest command.
    */
   public void readCommand(EZGripperInterface handToPack)
   {
      if (commandListener.flushAndGetLatest(commandMessage, handToPack.getRobotSide()))
      {
         if (commandMessage.getCalibrate())
            handToPack.calibrate();
         if (commandMessage.getResetErrors())
            handToPack.resetErrors();
         if (commandMessage.getOverrideCooldown())
            handToPack.overrideCooldown();

         handToPack.enableAutoCooldown(commandMessage.getEnableAutoCooldown());
         handToPack.setGoalPosition(commandMessage.getGoalPosition());
         handToPack.setMaxEffort(commandMessage.getMaxEffort());
         handToPack.setTorqueOn(commandMessage.getTorqueOn());
      }
   }

   /**
    * Publish the hand's state.
    *
    * @param handToPublish The hand to publish.
    */
   public void publishState(EZGripperInterface handToPublish)
   {
      stateMessage.setRobotSide(handToPublish.getRobotSide().toByte());
      stateMessage.setCurrentPosition(handToPublish.getCurrentPosition());
      stateMessage.setCurrentEffort(handToPublish.getCurrentEffort());
      stateMessage.setTemperature(handToPublish.getTemperature());
      stateMessage.setRealtimeTick(handToPublish.getRealtimeTick());
      stateMessage.setErrorCodes(handToPublish.getErrorCode());
      stateMessage.setIsCalibrated(handToPublish.isCalibrated());
      stateMessage.setIsCalibrating(handToPublish.isCalibrating());
      stateMessage.setIsCoolingDown(handToPublish.isCoolingDown());
      stateMessage.setAutomaticCooldownEnabled(handToPublish.autoCooldownEnabledStatus());

      statePublisher.publish(stateMessage);
   }

   /**
    * Initialize the communication. No messages will be received or published until this method is called.
    */
   public void start()
   {
      node.spin();
   }

   /**
    * Shut the communications down. Messages will no longer be received or published.
    */
   public void shutdown()
   {
      node.stopSpinning();

      statePublisher.remove();
      commandSubscription.remove();

      node.destroy();
   }
}
