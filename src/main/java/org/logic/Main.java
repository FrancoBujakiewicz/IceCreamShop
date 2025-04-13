package org.logic;

import org.logic.logic_classes.Rol;
import org.logic.logic_classes.User;

import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);
    public static Rol userRol;
    public static String password;
    static LogicController LogicCtl = new LogicController();

    public static void main (String[] args) throws Exception {

        //Admin
        //1a2b3c4d5e6f7g8h9

        System.out.println("--------------------------------------------[ IceCreamShop ]-------------------------------------------");
        System.out.println(" ");
        System.out.println("                                                Welcome!                                               ");
        System.out.println(" ");

       // User user = null;

        LogIn();

        switch(userRol.getRol())

        {

            case "admin" -> System.out.println("admin");

            case "client" -> System.out.println("client");

        }

    }

    public static void LogIn()

    {

        User user;

        do {

            System.out.println("Username:");
            String username = input.next();

            user = LogicCtl.findUserByUsername(username);
        }
        while (user == null);

        do {

            System.out.println("Password:");
            password = input.next();

        }
        while (!user.verifiedPassword(password, user.getPassword()));

        Rol userRol = user.getRole();

    }

}
