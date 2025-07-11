package ihmc_sake_ros2.msg.dds;

/**
* 
* Topic data type of the struct "EZGripperState" defined in "EZGripperState_.idl". Use this class to provide the TopicDataType to a Participant. 
*
* This file was automatically generated from EZGripperState_.idl by us.ihmc.idl.generator.IDLGenerator. 
* Do not update this file directly, edit EZGripperState_.idl instead.
*
*/
public class EZGripperStatePubSubType implements us.ihmc.pubsub.TopicDataType<ihmc_sake_ros2.msg.dds.EZGripperState>
{
   public static final java.lang.String name = "ihmc_sake_ros2::msg::dds_::EZGripperState_";
   
   @Override
   public final java.lang.String getDefinitionChecksum()
   {
   		return "40fbda5fdc0be7d12f9b88d402e90b5199aea5f4c4402df45de30e45acf496f6";
   }
   
   @Override
   public final java.lang.String getDefinitionVersion()
   {
   		return "local";
   }

   private final us.ihmc.idl.CDR serializeCDR = new us.ihmc.idl.CDR();
   private final us.ihmc.idl.CDR deserializeCDR = new us.ihmc.idl.CDR();

   @Override
   public void serialize(ihmc_sake_ros2.msg.dds.EZGripperState data, us.ihmc.pubsub.common.SerializedPayload serializedPayload) throws java.io.IOException
   {
      serializeCDR.serialize(serializedPayload);
      write(data, serializeCDR);
      serializeCDR.finishSerialize();
   }

   @Override
   public void deserialize(us.ihmc.pubsub.common.SerializedPayload serializedPayload, ihmc_sake_ros2.msg.dds.EZGripperState data) throws java.io.IOException
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

      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);

      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);

      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      return current_alignment - initial_alignment;
   }

   public final static int getCdrSerializedSize(ihmc_sake_ros2.msg.dds.EZGripperState data)
   {
      return getCdrSerializedSize(data, 0);
   }

   public final static int getCdrSerializedSize(ihmc_sake_ros2.msg.dds.EZGripperState data, int current_alignment)
   {
      int initial_alignment = current_alignment;

      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);


      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);


      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      current_alignment += 4 + us.ihmc.idl.CDR.alignment(current_alignment, 4);


      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);


      current_alignment += 1 + us.ihmc.idl.CDR.alignment(current_alignment, 1);



      return current_alignment - initial_alignment;
   }

   public static void write(ihmc_sake_ros2.msg.dds.EZGripperState data, us.ihmc.idl.CDR cdr)
   {
      cdr.write_type_9(data.getRobotSide());

      cdr.write_type_9(data.getTemperature());

      cdr.write_type_5(data.getCurrentPosition());

      cdr.write_type_5(data.getCurrentEffort());

      cdr.write_type_9(data.getErrorCodes());

      cdr.write_type_2(data.getRealtimeTick());

      cdr.write_type_7(data.getIsCalibrated());

      cdr.write_type_7(data.getIsCalibrating());

      cdr.write_type_7(data.getIsCoolingDown());

      cdr.write_type_7(data.getAutomaticCooldownEnabled());

   }

   public static void read(ihmc_sake_ros2.msg.dds.EZGripperState data, us.ihmc.idl.CDR cdr)
   {
      data.setRobotSide(cdr.read_type_9());
      	
      data.setTemperature(cdr.read_type_9());
      	
      data.setCurrentPosition(cdr.read_type_5());
      	
      data.setCurrentEffort(cdr.read_type_5());
      	
      data.setErrorCodes(cdr.read_type_9());
      	
      data.setRealtimeTick(cdr.read_type_2());
      	
      data.setIsCalibrated(cdr.read_type_7());
      	
      data.setIsCalibrating(cdr.read_type_7());
      	
      data.setIsCoolingDown(cdr.read_type_7());
      	
      data.setAutomaticCooldownEnabled(cdr.read_type_7());
      	

   }

   @Override
   public final void serialize(ihmc_sake_ros2.msg.dds.EZGripperState data, us.ihmc.idl.InterchangeSerializer ser)
   {
      ser.write_type_9("robot_side", data.getRobotSide());
      ser.write_type_9("temperature", data.getTemperature());
      ser.write_type_5("current_position", data.getCurrentPosition());
      ser.write_type_5("current_effort", data.getCurrentEffort());
      ser.write_type_9("error_codes", data.getErrorCodes());
      ser.write_type_2("realtime_tick", data.getRealtimeTick());
      ser.write_type_7("is_calibrated", data.getIsCalibrated());
      ser.write_type_7("is_calibrating", data.getIsCalibrating());
      ser.write_type_7("is_cooling_down", data.getIsCoolingDown());
      ser.write_type_7("automatic_cooldown_enabled", data.getAutomaticCooldownEnabled());
   }

   @Override
   public final void deserialize(us.ihmc.idl.InterchangeSerializer ser, ihmc_sake_ros2.msg.dds.EZGripperState data)
   {
      data.setRobotSide(ser.read_type_9("robot_side"));
      data.setTemperature(ser.read_type_9("temperature"));
      data.setCurrentPosition(ser.read_type_5("current_position"));
      data.setCurrentEffort(ser.read_type_5("current_effort"));
      data.setErrorCodes(ser.read_type_9("error_codes"));
      data.setRealtimeTick(ser.read_type_2("realtime_tick"));
      data.setIsCalibrated(ser.read_type_7("is_calibrated"));
      data.setIsCalibrating(ser.read_type_7("is_calibrating"));
      data.setIsCoolingDown(ser.read_type_7("is_cooling_down"));
      data.setAutomaticCooldownEnabled(ser.read_type_7("automatic_cooldown_enabled"));
   }

   public static void staticCopy(ihmc_sake_ros2.msg.dds.EZGripperState src, ihmc_sake_ros2.msg.dds.EZGripperState dest)
   {
      dest.set(src);
   }

   @Override
   public ihmc_sake_ros2.msg.dds.EZGripperState createData()
   {
      return new ihmc_sake_ros2.msg.dds.EZGripperState();
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
   
   public void serialize(ihmc_sake_ros2.msg.dds.EZGripperState data, us.ihmc.idl.CDR cdr)
   {
      write(data, cdr);
   }

   public void deserialize(ihmc_sake_ros2.msg.dds.EZGripperState data, us.ihmc.idl.CDR cdr)
   {
      read(data, cdr);
   }
   
   public void copy(ihmc_sake_ros2.msg.dds.EZGripperState src, ihmc_sake_ros2.msg.dds.EZGripperState dest)
   {
      staticCopy(src, dest);
   }

   @Override
   public EZGripperStatePubSubType newInstance()
   {
      return new EZGripperStatePubSubType();
   }
}
