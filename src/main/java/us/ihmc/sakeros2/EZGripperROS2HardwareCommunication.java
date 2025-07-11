package us.ihmc.sakeros2;

import ihmc_sake_ros2.msg.dds.EZGripperCommand;
import ihmc_sake_ros2.msg.dds.EZGripperState;
import us.ihmc.ros2.ROS2NodeBuilder;
import us.ihmc.ros2.ROS2Publisher;
import us.ihmc.ros2.ROS2Subscription;
import us.ihmc.ros2.RealtimeROS2Node;

/**
 * <p>Controller side ROS 2 communication for the {@link EZGripperInterface}. Communicates with low-level hardware control process.</p>
 * <p>Subscribes to {@link EZGripperState} messages and publishes {@link EZGripperCommand} messages.</p>
 */
public class EZGripperROS2HardwareCommunication
{
   private final RealtimeROS2Node node;

   private final EZGripperState stateMessage;
   private final EZGripperMessageListener<EZGripperState> stateListener;
   private final ROS2Subscription<EZGripperState> stateSubscription;
   private boolean receivedFirstState = false;

   private final EZGripperCommand commandMessage;
   private final ROS2Publisher<EZGripperCommand> commandPublisher;

   public EZGripperROS2HardwareCommunication(String nodeName)
   {
      node = new ROS2NodeBuilder().buildRealtime(nodeName);

      stateMessage = new EZGripperState();
      stateListener = new EZGripperMessageListener<>(EZGripperState::new);
      stateSubscription = node.createSubscription(EZGripperROS2API.STATE_TOPIC, stateListener);

      commandMessage = new EZGripperCommand();
      commandPublisher = node.createPublisher(EZGripperROS2API.COMMAND_TOPIC);
   }

   /**
    * Read the latest state into the hand object.
    *
    * @param handToPack Hand object to pack with the latest state.
    */
   public void readState(EZGripperInterface handToPack)
   {
      if (stateListener.flushAndGetLatest(stateMessage, handToPack.getRobotSide()))
      {
         handToPack.setCurrentPosition(stateMessage.getCurrentPosition());
         handToPack.setCurrentEffort(stateMessage.getCurrentEffort());
         handToPack.setCurrentTemperature(stateMessage.getTemperature());
         handToPack.setRealtimeTick(stateMessage.getRealtimeTick());
         handToPack.setErrorCode(stateMessage.getErrorCodes());
         handToPack.setCalibrated(stateMessage.getIsCalibrated());
         handToPack.setCalibrating(stateMessage.getIsCalibrating());
         handToPack.setCoolingDown(stateMessage.getIsCoolingDown());
         handToPack.setAutoCooldownEnabledStatus(stateMessage.getAutomaticCooldownEnabled());
         receivedFirstState = true;
      }
   }

   /**
    * Publish the hand's command.
    *
    * @param handToPublish The hand to publish.
    */
   public void publishCommand(EZGripperInterface handToPublish)
   {
      commandMessage.setRobotSide(handToPublish.getRobotSide().toByte());
      commandMessage.setCalibrate(handToPublish.pollCalibrate());
      commandMessage.setResetErrors(handToPublish.pollResetErrors());
      commandMessage.setEnableAutoCooldown(handToPublish.autoCooldownEnabled());
      commandMessage.setOverrideCooldown(handToPublish.pollOverrideCooldown());
      commandMessage.setGoalPosition(handToPublish.getGoalPosition());
      commandMessage.setMaxEffort(handToPublish.getMaxEffort());
      commandMessage.setTorqueOn(handToPublish.getTorqueOnCommand());

      commandPublisher.publish(commandMessage);
   }

   /**
    * Whether this object has received a state message.
    *
    * @return {@code true} if a state message has been received.
    */
   public boolean hasReceivedFirstState()
   {
      return receivedFirstState;
   }

   /**
    * Start the communication.
    */
   public void start()
   {
      node.spin();
   }

   /**
    * Stop the communication. {@link #start()} can be called again after this method to re-start communication.
    */
   public void stop()
   {
      node.stopSpinning();
   }

   /**
    * Shut the communication down. {@link #start()} cannot be called again after this method.
    */
   public void shutdown()
   {
      stop();

      commandPublisher.remove();
      stateSubscription.remove();

      node.destroy();
   }
}
