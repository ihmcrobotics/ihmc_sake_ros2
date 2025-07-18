package ihmc_sake_ros2.msg.dds;

/**
* 
* Topic data type of the struct "EZGripperCommand" defined in "EZGripperCommand_.idl". Use this class to provide the TopicDataType to a Participant. 
*
* This file was automatically generated from EZGripperCommand_.idl by us.ihmc.idl.generator.IDLGenerator. 
* Do not update this file directly, edit EZGripperCommand_.idl instead.
*
*/
public class EZGripperCommandPubSubType implements us.ihmc.pubsub.TopicDataType<ihmc_sake_ros2.msg.dds.EZGripperCommand>
{
   public static final java.lang.String name = "ihmc_sake_ros2::msg::dds_::EZGripperCommand_";
   
   @Override
   public final java.lang.String getDefinitionChecksum()
   {
   		return "fb3cc1de1fab78cf86a5cc088d8bbbb49f2aaa6dbea5a154fa24c703e8e3c166";
   }
   
   @Override
   public final java.lang.String getDefinitionVersion()
   {
   		return "local";
   }

   private final us.ihmc.idl.CDR serializeCDR = new us.ihmc.idl.CDR();
   private final us.ihmc.idl.CDR deserializeCDR = new us.ihmc.idl.CDR();

   @Override
   public void serialize(ihmc_sake_ros2.msg.dds.EZGripperCommand data, us.ihmc.pubsub.common.SerializedPayload serializedPayload) throws java.io.IOException
   {
      serializeCDR.serialize(serializedPayload);
      write(data, serializeCDR);
      serializeCDR.finishSerialize();
   }

   @Override
   public void deserialize(us.ihmc.pubsub.common.SerializedPayload serializedPayload, ihmc_sake_ros2.msg.dds.EZGripperCommand data) throws java.io.IOException
   {
      deserializeCDR.deserialize(serializedPayload);
      read(data, deserializeCDR);
      deserializeCDR.finishDeserialize();
   }

   public static int getMaxCdrSerializedSize()
   {
      return getMaxCdrSerializedSize(0);
   }

   public static int getMaxCdrSerializedSize(int current_alignment)
   {
      int initial_alignment = current_alignment;

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);

      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);

      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      return current_alignment - initial_alignment;
   }

   public final static int getCdrSerializedSize(ihmc_sake_ros2.msg.dds.EZGripperCommand data)
   {
      return getCdrSerializedSize(data, 0);
   }

   public final static int getCdrSerializedSize(ihmc_sake_ros2.msg.dds.EZGripperCommand data, int current_alignment)
   {
      int initial_alignment = current_alignment;

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);


      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);


      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);



      return current_alignment - initial_alignment;
   }

   public static void write(ihmc_sake_ros2.msg.dds.EZGripperCommand data, us.ihmc.idl.CDR cdr)
   {
      cdr.write_type_9(data.getRobotSide());

      cdr.write_type_9(data.getOperationMode());

      cdr.write_type_9(data.getTemperatureLimit());

      cdr.write_type_5(data.getGoalPosition());

      cdr.write_type_5(data.getMaxEffort());

      cdr.write_type_7(data.getTorqueOn());

   }

   public static void read(ihmc_sake_ros2.msg.dds.EZGripperCommand data, us.ihmc.idl.CDR cdr)
   {
      data.setRobotSide(cdr.read_type_9());
      	
      data.setOperationMode(cdr.read_type_9());
      	
      data.setTemperatureLimit(cdr.read_type_9());
      	
      data.setGoalPosition(cdr.read_type_5());
      	
      data.setMaxEffort(cdr.read_type_5());
      	
      data.setTorqueOn(cdr.read_type_7());
      	

   }

   @Override
   public final void serialize(ihmc_sake_ros2.msg.dds.EZGripperCommand data, us.ihmc.idl.InterchangeSerializer ser)
   {
      ser.write_type_9("robot_side", data.getRobotSide());
      ser.write_type_9("operation_mode", data.getOperationMode());
      ser.write_type_9("temperature_limit", data.getTemperatureLimit());
      ser.write_type_5("goal_position", data.getGoalPosition());
      ser.write_type_5("max_effort", data.getMaxEffort());
      ser.write_type_7("torque_on", data.getTorqueOn());
   }

   @Override
   public final void deserialize(us.ihmc.idl.InterchangeSerializer ser, ihmc_sake_ros2.msg.dds.EZGripperCommand data)
   {
      data.setRobotSide(ser.read_type_9("robot_side"));
      data.setOperationMode(ser.read_type_9("operation_mode"));
      data.setTemperatureLimit(ser.read_type_9("temperature_limit"));
      data.setGoalPosition(ser.read_type_5("goal_position"));
      data.setMaxEffort(ser.read_type_5("max_effort"));
      data.setTorqueOn(ser.read_type_7("torque_on"));
   }

   public static void staticCopy(ihmc_sake_ros2.msg.dds.EZGripperCommand src, ihmc_sake_ros2.msg.dds.EZGripperCommand dest)
   {
      dest.set(src);
   }

   @Override
   public ihmc_sake_ros2.msg.dds.EZGripperCommand createData()
   {
      return new ihmc_sake_ros2.msg.dds.EZGripperCommand();
   }
   @Override
   public int getTypeSize()
   {
      return us.ihmc.idl.CDR.getTypeSize(getMaxCdrSerializedSize());
   }

   @Override
   public java.lang.String getName()
   {
      return name;
   }
   
   public void serialize(ihmc_sake_ros2.msg.dds.EZGripperCommand data, us.ihmc.idl.CDR cdr)
   {
      write(data, cdr);
   }

   public void deserialize(ihmc_sake_ros2.msg.dds.EZGripperCommand data, us.ihmc.idl.CDR cdr)
   {
      read(data, cdr);
   }
   
   public void copy(ihmc_sake_ros2.msg.dds.EZGripperCommand src, ihmc_sake_ros2.msg.dds.EZGripperCommand dest)
   {
      staticCopy(src, dest);
   }

   @Override
   public EZGripperCommandPubSubType newInstance()
   {
      return new EZGripperCommandPubSubType();
   }
}
