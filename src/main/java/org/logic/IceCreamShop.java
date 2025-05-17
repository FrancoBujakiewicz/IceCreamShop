
 package org.logic;

 import java.util.Date;
 import java.util.Scanner;
 import org.logic.domain.User;
 import org.logic.sessions.UserSession;
 import org.logic.sessions.enums.AdminAction;
 import org.logic.sessions.enums.CommonAction;
 import org.logic.sessions.enums.UserAction;

 public class IceCreamShop

 {

    public static Scanner userInput = new Scanner(System.in);


    public static void main (String[] args) throws Exception

    {

        // Using Console class to censor passwords inputs and similar
        // Comment this line if not works in your terminal or IDE
        // Console console = System.console(); if(console == null) { System.exit(1); }

        LogicController LogicCtl = new LogicController();
        String username;
        User user;
        String password;
        Long phoneNumber;

        //Admin
        //1a2b3c4d5e6f7g8h9

        System.out.println("--------------------------------------------[ IceCreamShop ]-------------------------------------------");
        System.out.println(" ");

        System.out.println("                                          Have you an account?                                         ");
        System.out.println(" ");

        System.out.print("[Yes -> y] [No -> enter any]:");
        String userChoice = userInput.nextLine().trim();

        if(!userChoice.equalsIgnoreCase("y"))

        {

            System.out.println("Create an account.");

          do {

              System.out.println("Username:");
              username = userInput.nextLine().trim();

              user = LogicCtl.findUserByUsername(username);

              if(username.equals(user.getUsername()))
              { System.out.println("Username already taken! Try again."); } else { break; }

          }
          while(true);

          /*
          do {

              System.out.println("Phone number:");


          }
          while(true);
          */

          String passwdConfirm;

          do {

              System.out.println("Password (at least 8 characters):");
              password = userInput.nextLine().trim();

              System.out.println("Confirm the password:");
              passwdConfirm = userInput.nextLine().trim();

              if(!passwdConfirm.equals(password) || !(password.length() > 6))
              { System.out.println("Invalid password! Try again."); } else { break; }

          }
          while(true);

          System.out.println("Confirm the user creation? [Yes -> y] [No -> enter any]: ");
          userChoice = userInput.nextLine().trim();

          if((!userChoice.equalsIgnoreCase("y"))){ }
          user = new User(username, phoneNumber, password, new Date(), "client");

        }

        do {

            System.out.print("Username:");
            username = userInput.nextLine().trim();

            user = LogicCtl.findUserByUsername(username);

        }
        while (user == null);

        do {

            // To censor password use this
            // char[] passwordChars = console.readPassword("Password: ");
            // password = new String(passwordChars);

            System.out.print("Password:");
            password = userInput.nextLine().trim();

        }
        while (!user.verifiedPassword(password, user.getPassword()));

        System.out.println(" ");
        System.out.println("                                              [ Welcome! ]                                             ");

        String userRol = user.getRole();

        userInput.close();

        switch(userRol)

        {

          case "admin" -> UserSession.userOptions(AdminAction.class);

        }

    }
    
 }
