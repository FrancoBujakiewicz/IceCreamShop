
 package org.logic.sessions;

 import org.logic.sessions.enums.CommonAction;
 import org.logic.sessions.enums.UserAction;

 import java.util.Scanner;

 public class UserSession

 {

  static Scanner userInput = new Scanner(System.in);

  public static <T extends Enum<T> & UserAction> void userOptions(Class<T> rolEnum)

  {

   T[] actEnum = rolEnum.getEnumConstants();

   System.out.println(" ");

   for (T act : actEnum)

   { System.out.println((act.ordinal() + 1) + ") " + act.getAction() + " "); }

   System.out.println(" ");
   System.out.print("Enter you want operation number:");
   selectOption(rolEnum);

  }

  public static <T extends Enum<T>> void selectOption(Class<T> rolEnum)

  {

   boolean validChoice;

   validChoice = false;

   String userChoice = userInput.nextLine().trim();
   int option = 0;

   if (!userChoice.isEmpty())

   {

     try { option = Integer.parseInt(userChoice); validChoice = true; }

     finally
     {

      if(validChoice){ getOption(CommonAction.class,(option-1)).userAction(); }

      else { System.out.println("Invalid option! Try again."); selectOption(rolEnum); }

     }

    }

  }

  public static <T extends Enum<T>> T getOption(Class<T> enumClass, int ordinal)

  {

   T[] values = enumClass.getEnumConstants();

   if (ordinal < 0 || ordinal >= values.length)

   { throw new IllegalArgumentException("Invalid ordinal!: " + ordinal); }

   return values[ordinal];

  }

 }
