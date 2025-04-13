package org.logic;

import org.logic.logic_classes.Rol;
import org.logic.logic_classes.User;
import java.util.Scanner;

public class Main {

    public static Scanner userInput = new Scanner(System.in);
    public static String password;

    public static void main (String[] args) throws Exception {

        LogicController LogicCtl = new LogicController();

        //Admin
        //1a2b3c4d5e6f7g8h9

        System.out.println("--------------------------------------------[ IceCreamShop ]-------------------------------------------");
        System.out.println(" ");
        System.out.println("                                                Welcome!                                               ");

        User user;

        do {

            System.out.print("Username:");
            String username = userInput.nextLine();

            user = LogicCtl.findUserByUsername(username);

        }
        while (user == null);

        System.out.println(" ");

        do {

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
        
    }
    
 }
