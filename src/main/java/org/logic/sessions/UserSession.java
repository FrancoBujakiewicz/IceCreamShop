
 package org.logic.sessions;

 import org.logic.LogicController;

 public class UserSession

 {

  LogicController LogicCtl = new LogicController();

  public static void printOptions()

  {

    System.out.println(" ");
    System.out.println("Enter you want operation number:");
    for(UserAction act : UserAction.values()) { System.out.print("["+(act.ordinal()+1)+"]"+ act.getValue()+ " "); }

  }

  public static void selectOption(char userChoice)

  {



  }

 }
