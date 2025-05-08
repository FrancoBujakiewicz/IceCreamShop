
 package org.logic.sessions;

 import org.logic.LogicController;
 import org.logic.sessions.enums.CommonAction;
 import org.logic.sessions.enums.UserAction;

 import java.util.Scanner;

 public class UserSession

 {

  static Scanner userInput = new Scanner(System.in);
  LogicController LogicCtl = new LogicController();

  public static <T extends Enum<T> & UserAction> void printOptions(Class<T> rolEnum)

  {

   T[] actEnum = rolEnum.getEnumConstants();

   System.out.println(" ");

   for (T act : actEnum)

   { System.out.println((act.ordinal() + 1) + ") " + act.getAction() + " "); }

   System.out.println(" ");
   System.out.print("Enter you want operation number:");

   boolean validChoice;

   do
   {

    validChoice = true;

    String userChoice = userInput.nextLine();


   }
   while(!validChoice);

  }

  }
