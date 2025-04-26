
 package org.logic.sessions;

 import org.logic.LogicController;
 import org.logic.sessions.enums.ActionToPrint;
 import java.util.Scanner;

 public class UserSession

 {

  static Scanner userInput = new Scanner(System.in);
  LogicController LogicCtl = new LogicController();

  public static <T extends Enum<T> & ActionToPrint> void printOptions(T[] actions)

  {

   System.out.println(" ");
   for (T act : actions) { System.out.println((act.ordinal() + 1) + ") " + act.getAction() + " "); }

   System.out.println(" ");
   System.out.print("Enter you want operation number:");

  }

  public static void userOptions(String userRole)

  {

   boolean validChoice;

    do
    {

     validChoice = true;

     String userChoice = userInput.nextLine();

     switch(userChoice)

     {

      case "1" -> System.out.println("You will change the username");

      case "2" -> System.out.println("You will change the password");

      case "3" -> System.out.println("You will log out. Bye!");

      default -> { System.out.println("Invalid option!"); validChoice = false; }

     }

    }
    while(!validChoice);

  }

 }
