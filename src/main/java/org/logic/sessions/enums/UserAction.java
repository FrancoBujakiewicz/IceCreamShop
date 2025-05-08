
 package org.logic.sessions.enums;

 public interface UserAction

 {

   String getAction();
   void userAction();

     static  <T extends Enum<T> & UserAction> T getOption(Class<T> enumClass, int ordinal)

     {
         T[] values = enumClass.getEnumConstants();

         if (ordinal < 0 || ordinal >= values.length)

         { throw new IllegalArgumentException("Invalid ordinal: " + ordinal); }

         return values[ordinal];

     }

 }
