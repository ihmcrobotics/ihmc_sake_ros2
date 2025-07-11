package us.ihmc.sakeros2;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum EZGripperError
{
   // Values based off of Dynamixel Protocol 1.0 Error codes (https://emanual.robotis.com/docs/en/dxl/protocol1/#error)
   NONE                 ((byte) 0b00000000),
   OVERLOAD_ERROR       ((byte) 0b00100000),
   CHECKSUM_ERROR       ((byte) 0b00010000),
   RANGE_ERROR          ((byte) 0b00001000),
   OVER_HEATING_ERROR   ((byte) 0b00000100),
   ANGLE_LIMIT_ERROR    ((byte) 0b00000010),
   INPUT_VOLTAGE_ERROR  ((byte) 0b00000001);

   public static final EZGripperError[] values = values();
   public static final EnumSet<EZGripperError> set = EnumSet.allOf(EZGripperError.class);

   public final byte errorCode;

   EZGripperError(byte errorCode)
   {
      this.errorCode = errorCode;
   }

   @Override
   public String toString()
   {
      return name().replace("_", " ");
   }

   public static List<String> getErrorNames(byte errorCode)
   {
      return getErrorList(errorCode).stream().map(EZGripperError::toString).toList();
   }

   public static List<EZGripperError> getErrorList(byte errorCode)
   {
      List<EZGripperError> errorList = new ArrayList<>();
      for (EZGripperError error : values())
         if ((errorCode & error.errorCode) != 0)
            errorList.add(error);

      return errorList;
   }
}
