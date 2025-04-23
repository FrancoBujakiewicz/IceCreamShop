package org.logic;

import org.logic.domain.Rol;
import org.logic.domain.User;
import org.logic.sessions.UserSession;

import java.io.Console;
import java.util.Scanner;

public class IceCreamShop {

    public static Scanner userInput = new Scanner(System.in);
    public static String password;

    public static void main (String[] args) throws Exception {

        // Using Console class to censor passwords inputs and similar
        // Comment this line if not works in your terminal or IDE
        // Console console = System.console(); if(console == null) { System.exit(1); }

        LogicController LogicCtl = new LogicController();

        //Admin
        //1a2b3c4d5e6f7g8h9

        System.out.println("--------------------------------------------[ IceCreamShop ]-------------------------------------------");
        System.out.println(" ");
        System.out.println("                                                Welcome!                                               ");

        User user;
        /*
        do {

            System.out.print("Username:");
            String username = userInput.nextLine();

            user = LogicCtl.findUserByUsername(username);

        }
        while (user == null);

        System.out.println(" ");

        do {

            // To censor password use this
            // char[] passwordChars = console.readPassword("Password: ");
            // password = new String(passwordChars);

            System.out.print("Password:");
            password = userInput.nextLine();

        }
        while (!user.verifiedPassword(password, user.getPassword()));

        Rol userRol = user.getRole();

        switch(userRol.getRol())

        {

            case "admin" -> System.out.println("admin");

            case "client" -> System.out.println("client");

        }

        userInput.close();
        */

        UserSession.printOptions();

    }
    
 }
