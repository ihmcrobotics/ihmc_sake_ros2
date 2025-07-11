package us.ihmc.sakeros2;

import ihmc_sake_ros2.msg.dds.EZGripperCommand;
import ihmc_sake_ros2.msg.dds.EZGripperState;
import us.ihmc.communication.packets.Packet;
import us.ihmc.concurrent.ConcurrentRingBuffer;
import us.ihmc.pubsub.subscriber.Subscriber;
import us.ihmc.robotics.robotSide.RobotSide;
import us.ihmc.ros2.NewMessageListener;

import java.util.function.Supplier;

public class EZGripperMessageListener<T extends Packet<T>> implements NewMessageListener<T>
{
   private static final int QUEUE_SIZE = 5;

   private final T message;
   private final ConcurrentRingBuffer<T> leftMessageQueue;
   private final ConcurrentRingBuffer<T> rightMessageQueue;

   public EZGripperMessageListener(Supplier<T> newMessageInstantiator)
   {
      message = newMessageInstantiator.get();

      if (!(message instanceof EZGripperCommand || message instanceof EZGripperState))
         throw new IllegalArgumentException("Only EZGripperCommand or EZGripperState message types allowed.");

      leftMessageQueue = new ConcurrentRingBuffer<>(newMessageInstantiator::get, QUEUE_SIZE);
      rightMessageQueue = new ConcurrentRingBuffer<>(newMessageInstantiator::get, QUEUE_SIZE);
   }

   public boolean poll(T data, RobotSide robotSide)
   {
      ConcurrentRingBuffer<T> commandQueue = getMessageQueue(robotSide);
      if (commandQueue.poll())
      {
         T next = commandQueue.read();
         data.set(next);
         commandQueue.flush();
         return true;
      }
      else
      {
         return false;
      }
   }

   public boolean peek(T data, RobotSide robotSide)
   {
      ConcurrentRingBuffer<T> commandQueue = getMessageQueue(robotSide);
      if(commandQueue.poll())
      {
         T next = commandQueue.peek();
         data.set(next);
         return true;
      }
      else
      {
         return false;
      }
   }

   public boolean flushAndGetLatest(T data, RobotSide robotSide)
   {
      ConcurrentRingBuffer<T> commandQueue = getMessageQueue(robotSide);
      if (commandQueue.poll())
      {
         T latest = commandQueue.read();
         T next;
         while ((next = commandQueue.read()) != null)
         {
            latest = next;
         }
         data.set(latest);
         commandQueue.flush();
         return true;
      }
      else
      {
         return false;
      }
   }

   @Override
   public void onNewDataMessage(@SuppressWarnings("deprecation") Subscriber<T> subscriber)
   {
      subscriber.takeNextData(message, null);

      ConcurrentRingBuffer<T> commandQueue= getMessageQueue(readHandSide(message));
      T messageToPack = commandQueue.next();
      if (messageToPack != null)
      {
         messageToPack.set(message);
         commandQueue.commit();
      }
   }

   private ConcurrentRingBuffer<T> getMessageQueue(RobotSide robotSide)
   {
      if (RobotSide.LEFT.equals(robotSide))
      {
         return leftMessageQueue;
      }
      else if (RobotSide.RIGHT.equals(robotSide))
      {
         return rightMessageQueue;
      }

      throw new IllegalArgumentException("Only LEFT or RIGHT gripper side can be accepted.");
   }

   private RobotSide readHandSide(T message)
   {
      if (message instanceof EZGripperCommand commandMessage)
         return RobotSide.fromByte(commandMessage.getRobotSide());
      else if (message instanceof EZGripperState stateMessage)
         return RobotSide.fromByte(stateMessage.getRobotSide());
      else
         throw new IllegalArgumentException("Only EZGripperCommand or EZGripperState message types allowed.");

   }
}
