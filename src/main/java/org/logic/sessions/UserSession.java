
 package org.logic.sessions;

 import org.logic.LogicController;
 import java.util.Scanner;

 public class UserSession

 {

  static Scanner userInput = new Scanner(System.in);
  LogicController LogicCtl = new LogicController();

  public static void userOptions()

  {

   boolean validChoice;

    do
    {

     validChoice = true;

     // Printing the options
     System.out.println(" ");
     for(UserAction act : UserAction.values()) { System.out.print("["+(act.ordinal()+1)+"]"+ act.getValue()+ " "); }

     System.out.println(" ");
     System.out.print("Enter you want operation number:");
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
