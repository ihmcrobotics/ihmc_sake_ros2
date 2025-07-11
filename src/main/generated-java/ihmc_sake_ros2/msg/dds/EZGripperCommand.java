package ihmc_sake_ros2.msg.dds;

import us.ihmc.communication.packets.Packet;
import us.ihmc.euclid.interfaces.Settable;
import us.ihmc.euclid.interfaces.EpsilonComparable;
import java.util.function.Supplier;
import us.ihmc.pubsub.TopicDataType;

public class EZGripperCommand extends Packet<EZGripperCommand> implements Settable<EZGripperCommand>, EpsilonComparable<EZGripperCommand>
{
   public static final byte LEFT = (byte) 0;
   public static final byte RIGHT = (byte) 1;
   /**
            * Specifies the side of the robot of the gripper being referred to
            */
   public byte robot_side_ = (byte) 255;
   /**
            * Request the gripper to perform a calibration sequence
            */
   public boolean calibrate_;
   /**
            * Request to reset the gripper error state after overheating
            */
   public boolean reset_errors_;
   /**
            * Set to true to enable automatic cooldown
            */
   public boolean enable_auto_cooldown_;
   /**
            * Set to true to override the cooldown
            */
   public boolean override_cooldown_;
   /**
            * The goal position
            * 0.0 = fully closed, 1.0 = fully open
            */
   public float goal_position_;
   /**
            * Maximum effort to put into achieving the goal position
            * 0.0 = no effort (fingers will not move), 1.0 = maximum effort (can quickly overheat actuator)
            * 0.3 is a reasonable normal value, and it is recommended not to exceed 0.8.
            */
   public float max_effort_;
   /**
            * Keeping the torque off when not needed can help keep the gripper's temperature down
            */
   public boolean torque_on_;

   public EZGripperCommand()
   {
   }

   public EZGripperCommand(EZGripperCommand other)
   {
      this();
      set(other);
   }

   public void set(EZGripperCommand other)
   {
      robot_side_ = other.robot_side_;

      calibrate_ = other.calibrate_;

      reset_errors_ = other.reset_errors_;

      enable_auto_cooldown_ = other.enable_auto_cooldown_;

      override_cooldown_ = other.override_cooldown_;

      goal_position_ = other.goal_position_;

      max_effort_ = other.max_effort_;

      torque_on_ = other.torque_on_;

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
            * Request the gripper to perform a calibration sequence
            */
   public void setCalibrate(boolean calibrate)
   {
      calibrate_ = calibrate;
   }
   /**
            * Request the gripper to perform a calibration sequence
            */
   public boolean getCalibrate()
   {
      return calibrate_;
   }

   /**
            * Request to reset the gripper error state after overheating
            */
   public void setResetErrors(boolean reset_errors)
   {
      reset_errors_ = reset_errors;
   }
   /**
            * Request to reset the gripper error state after overheating
            */
   public boolean getResetErrors()
   {
      return reset_errors_;
   }

   /**
            * Set to true to enable automatic cooldown
            */
   public void setEnableAutoCooldown(boolean enable_auto_cooldown)
   {
      enable_auto_cooldown_ = enable_auto_cooldown;
   }
   /**
            * Set to true to enable automatic cooldown
            */
   public boolean getEnableAutoCooldown()
   {
      return enable_auto_cooldown_;
   }

   /**
            * Set to true to override the cooldown
            */
   public void setOverrideCooldown(boolean override_cooldown)
   {
      override_cooldown_ = override_cooldown;
   }
   /**
            * Set to true to override the cooldown
            */
   public boolean getOverrideCooldown()
   {
      return override_cooldown_;
   }

   /**
            * The goal position
            * 0.0 = fully closed, 1.0 = fully open
            */
   public void setGoalPosition(float goal_position)
   {
      goal_position_ = goal_position;
   }
   /**
            * The goal position
            * 0.0 = fully closed, 1.0 = fully open
            */
   public float getGoalPosition()
   {
      return goal_position_;
   }

   /**
            * Maximum effort to put into achieving the goal position
            * 0.0 = no effort (fingers will not move), 1.0 = maximum effort (can quickly overheat actuator)
            * 0.3 is a reasonable normal value, and it is recommended not to exceed 0.8.
            */
   public void setMaxEffort(float max_effort)
   {
      max_effort_ = max_effort;
   }
   /**
            * Maximum effort to put into achieving the goal position
            * 0.0 = no effort (fingers will not move), 1.0 = maximum effort (can quickly overheat actuator)
            * 0.3 is a reasonable normal value, and it is recommended not to exceed 0.8.
            */
   public float getMaxEffort()
   {
      return max_effort_;
   }

   /**
            * Keeping the torque off when not needed can help keep the gripper's temperature down
            */
   public void setTorqueOn(boolean torque_on)
   {
      torque_on_ = torque_on;
   }
   /**
            * Keeping the torque off when not needed can help keep the gripper's temperature down
            */
   public boolean getTorqueOn()
   {
      return torque_on_;
   }


   public static Supplier<EZGripperCommandPubSubType> getPubSubType()
   {
      return EZGripperCommandPubSubType::new;
   }

   @Override
   public Supplier<TopicDataType> getPubSubTypePacket()
   {
      return EZGripperCommandPubSubType::new;
   }

   @Override
   public boolean epsilonEquals(EZGripperCommand other, double epsilon)
   {
      if(other == null) return false;
      if(other == this) return true;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsPrimitive(this.robot_side_, other.robot_side_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsBoolean(this.calibrate_, other.calibrate_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsBoolean(this.reset_errors_, other.reset_errors_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsBoolean(this.enable_auto_cooldown_, other.enable_auto_cooldown_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsBoolean(this.override_cooldown_, other.override_cooldown_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsPrimitive(this.goal_position_, other.goal_position_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsPrimitive(this.max_effort_, other.max_effort_, epsilon)) return false;

      if (!us.ihmc.idl.IDLTools.epsilonEqualsBoolean(this.torque_on_, other.torque_on_, epsilon)) return false;


      return true;
   }

   @Override
   public boolean equals(Object other)
   {
      if(other == null) return false;
      if(other == this) return true;
      if(!(other instanceof EZGripperCommand)) return false;

      EZGripperCommand otherMyClass = (EZGripperCommand) other;

      if(this.robot_side_ != otherMyClass.robot_side_) return false;

      if(this.calibrate_ != otherMyClass.calibrate_) return false;

      if(this.reset_errors_ != otherMyClass.reset_errors_) return false;

      if(this.enable_auto_cooldown_ != otherMyClass.enable_auto_cooldown_) return false;

      if(this.override_cooldown_ != otherMyClass.override_cooldown_) return false;

      if(this.goal_position_ != otherMyClass.goal_position_) return false;

      if(this.max_effort_ != otherMyClass.max_effort_) return false;

      if(this.torque_on_ != otherMyClass.torque_on_) return false;


      return true;
   }

   @Override
   public java.lang.String toString()
   {
      StringBuilder builder = new StringBuilder();

      builder.append("EZGripperCommand {");
      builder.append("robot_side=");
      builder.append(this.robot_side_);      builder.append(", ");
      builder.append("calibrate=");
      builder.append(this.calibrate_);      builder.append(", ");
      builder.append("reset_errors=");
      builder.append(this.reset_errors_);      builder.append(", ");
      builder.append("enable_auto_cooldown=");
      builder.append(this.enable_auto_cooldown_);      builder.append(", ");
      builder.append("override_cooldown=");
      builder.append(this.override_cooldown_);      builder.append(", ");
      builder.append("goal_position=");
      builder.append(this.goal_position_);      builder.append(", ");
      builder.append("max_effort=");
      builder.append(this.max_effort_);      builder.append(", ");
      builder.append("torque_on=");
      builder.append(this.torque_on_);
      builder.append("}");
      return builder.toString();
   }
}
