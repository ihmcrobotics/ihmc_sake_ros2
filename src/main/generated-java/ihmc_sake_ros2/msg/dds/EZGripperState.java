package ihmc_sake_ros2.msg.dds;

import us.ihmc.communication.packets.Packet;
import us.ihmc.euclid.interfaces.Settable;
import us.ihmc.euclid.interfaces.EpsilonComparable;
import java.util.function.Supplier;
import us.ihmc.pubsub.TopicDataType;

public class EZGripperState extends Packet<EZGripperState> implements Settable<EZGripperState>, EpsilonComparable<EZGripperState>
{
   public static final byte LEFT = (byte) 0;
   public static final byte RIGHT = (byte) 1;
   /**
            * Specifies the side of the robot of the gripper being referred to
            */
   public byte robot_side_ = (byte) 255;
   /**
            * Temperature of the Dynamixel in Celsius
            */
   public byte temperature_;
   /**
            * The current Dynamixel position
            * 0.0 = fully closed, 1.0 = fully open
            */
   public float current_position_;
   /**
            * The current amount of effort being used
            * 0.0 = no effort, 1.0 = maximum effort
            */
   public float current_effort_;
   /**
            * Dynamixel's error codes
            * See: https://emanual.robotis.com/docs/en/dxl/protocol1/#error
            */
   public byte error_codes_;
   /**
            * Realtime tick of the Dynamixel
            * If this value isn't changing, communication with the gripper is broken
            */
   public int realtime_tick_;
   public boolean is_calibrated_;
   public boolean is_calibrating_;
   public boolean is_cooling_down_;
   public boolean automatic_cooldown_enabled_;

   public EZGripperState()
   {
   }

   public EZGripperState(EZGripperState other)
   {
      this();
      set(other);
   }

   public void set(EZGripperState other)
   {
      robot_side_ = other.robot_side_;

      temperature_ = other.temperature_;

      current_position_ = other.current_position_;

      current_effort_ = other.current_effort_;

      error_codes_ = other.error_codes_;

      realtime_tick_ = other.realtime_tick_;

      is_calibrated_ = other.is_calibrated_;

      is_calibrating_ = other.is_calibrating_;

      is_cooling_down_ = other.is_cooling_down_;

      automatic_cooldown_enabled_ = other.automatic_cooldown_enabled_;

   }

   /**
            * Specifies the side of the robot of the gripper being referred to
            */
   public void setRobotSide(byte robot_side)
   {
      robot_side_ = robot_side;
   }
   /**
            * Specifies the side of the robot of the gripper being referred to
            */
   public byte getRobotSide()
   {
      return robot_side_;
   }

   /**
            * Temperature of the Dynamixel in Celsius
            */
   public void setTemperature(byte temperature)
   {
      temperature_ = temperature;
   }
   /**
            * Temperature of the Dynamixel in Celsius
            */
   public byte getTemperature()
   {
      return temperature_;
   }

   /**
            * The current Dynamixel position
            * 0.0 = fully closed, 1.0 = fully open
            */
   public void setCurrentPosition(float current_position)
   {
      current_position_ = current_position;
   }
   /**
            * The current Dynamixel position
            * 0.0 = fully closed, 1.0 = fully open
            */
   public float getCurrentPosition()
   {
      return current_position_;
   }

   /**
            * The current amount of effort being used
            * 0.0 = no effort, 1.0 = maximum effort
            */
   public void setCurrentEffort(float current_effort)
   {
      current_effort_ = current_effort;
   }
   /**
            * The current amount of effort being used
            * 0.0 = no effort, 1.0 = maximum effort
            */
   public float getCurrentEffort()
   {
      return current_effort_;
   }

   /**
            * Dynamixel's error codes
            * See: https://emanual.robotis.com/docs/en/dxl/protocol1/#error
            */
   public void setErrorCodes(byte error_codes)
   {
      error_codes_ = error_codes;
   }
   /**
            * Dynamixel's error codes
            * See: https://emanual.robotis.com/docs/en/dxl/protocol1/#error
            */
   public byte getErrorCodes()
   {
      return error_codes_;
   }

   /**
            * Realtime tick of the Dynamixel
            * If this value isn't changing, communication with the gripper is broken
            */
   public void setRealtimeTick(int realtime_tick)
   {
      realtime_tick_ = realtime_tick;
   }
   /**
            * Realtime tick of the Dynamixel
            * If this value isn't changing, communication with the gripper is broken
            */
   public int getRealtimeTick()
   {
      return realtime_tick_;
   }

   public void setIsCalibrated(boolean is_calibrated)
   {
      is_calibrated_ = is_calibrated;
   }
   public boolean getIsCalibrated()
   {
      return is_calibrated_;
   }

   public void setIsCalibrating(boolean is_calibrating)
   {
      is_calibrating_ = is_calibrating;
   }
   public boolean getIsCalibrating()
   {
      return is_calibrating_;
   }

   public void setIsCoolingDown(boolean is_cooling_down)
   {
      is_cooling_down_ = is_cooling_down;
   }
   public boolean getIsCoolingDown()
   {
      return is_cooling_down_;
   }

   public void setAutomaticCooldownEnabled(boolean automatic_cooldown_enabled)
   {
      automatic_cooldown_enabled_ = automatic_cooldown_enabled;
   }
   public boolean getAutomaticCooldownEnabled()
   {
      return automatic_cooldown_enabled_;
   }


   public static Supplier<EZGripperStatePubSubType> getPubSubType()
   {
      return EZGripperStatePubSubType::new;
   }

   @Override
   public Supplier<TopicDataType> getPubSubTypePacket()
   {
      return EZGripperStatePubSubType::new;
   }

   @Override
   public boolean epsilonEquals(EZGripperState other, double epsilon)
   {
      if(other == null) return false;
      if(other == this) return true;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsPrimitive(this.robot_side_, other.robot_side_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsPrimitive(this.temperature_, other.temperature_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsPrimitive(this.current_position_, other.current_position_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsPrimitive(this.current_effort_, other.current_effort_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsPrimitive(this.error_codes_, other.error_codes_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsPrimitive(this.realtime_tick_, other.realtime_tick_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsBoolean(this.is_calibrated_, other.is_calibrated_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsBoolean(this.is_calibrating_, other.is_calibrating_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsBoolean(this.is_cooling_down_, other.is_cooling_down_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsBoolean(this.automatic_cooldown_enabled_, other.automatic_cooldown_enabled_, epsilon)) return false;


      return true;
   }

   @Override
   public boolean equals(Object other)
   {
      if(other == null) return false;
      if(other == this) return true;
      if(!(other instanceof EZGripperState)) return false;

      EZGripperState otherMyClass = (EZGripperState) other;

      if(this.robot_side_ != otherMyClass.robot_side_) return false;

      if(this.temperature_ != otherMyClass.temperature_) return false;

      if(this.current_position_ != otherMyClass.current_position_) return false;

      if(this.current_effort_ != otherMyClass.current_effort_) return false;

      if(this.error_codes_ != otherMyClass.error_codes_) return false;

      if(this.realtime_tick_ != otherMyClass.realtime_tick_) return false;

      if(this.is_calibrated_ != otherMyClass.is_calibrated_) return false;

      if(this.is_calibrating_ != otherMyClass.is_calibrating_) return false;

      if(this.is_cooling_down_ != otherMyClass.is_cooling_down_) return false;

      if(this.automatic_cooldown_enabled_ != otherMyClass.automatic_cooldown_enabled_) return false;


      return true;
   }

   @Override
   public java.lang.String toString()
   {
      StringBuilder builder = new StringBuilder();

      builder.append("EZGripperState {");
      builder.append("robot_side=");
      builder.append(this.robot_side_);      builder.append(", ");
      builder.append("temperature=");
      builder.append(this.temperature_);      builder.append(", ");
      builder.append("current_position=");
      builder.append(this.current_position_);      builder.append(", ");
      builder.append("current_effort=");
      builder.append(this.current_effort_);      builder.append(", ");
      builder.append("error_codes=");
      builder.append(this.error_codes_);      builder.append(", ");
      builder.append("realtime_tick=");
      builder.append(this.realtime_tick_);      builder.append(", ");
      builder.append("is_calibrated=");
      builder.append(this.is_calibrated_);      builder.append(", ");
      builder.append("is_calibrating=");
      builder.append(this.is_calibrating_);      builder.append(", ");
      builder.append("is_cooling_down=");
      builder.append(this.is_cooling_down_);      builder.append(", ");
      builder.append("automatic_cooldown_enabled=");
      builder.append(this.automatic_cooldown_enabled_);
      builder.append("}");
      return builder.toString();
   }
}
