package com.elbicon.coderscampus;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class ScannerService extends Menu {
    private static final String SU_ROLE = "super_user";
    private static final String NU_ROLE = "normal_user";
    private static String email;
    private static String password;
    private static String name;
    private static String role;

    static Scanner scanner = new Scanner(System.in);
    public static String promptEmail(){
        try{
            System.out.println("Enter your email:");
            email = scanner.nextLine();
            while (email.equals("")) {
                System.out.println("Enter your email:");
                email = scanner.nextLine();
            }
            return email;
        }catch (Exception e){
            System.out.println("Exception Caught: " + e.toString());
        }
        return null;
    }
    public static String promptPassword(){
        try{
            System.out.println("Enter your password:");
            password = scanner.nextLine();
            while (password.equals("")) {
                System.out.println("Enter your password:");
                password = scanner.nextLine();
            }
            return password;
        } catch (Exception e){
            System.out.println("Exception Caught: " + e.toString());
        }
        return null;
    }
    public static String promptName(){
        try{
            System.out.println("Enter your Name:");
            name = scanner.nextLine();
            while (name.equals("")) {
                System.out.println("Enter your Name:");
                name = scanner.nextLine();
            }
            return name;
        } catch (Exception e){
            System.out.println("Exception Caught: " + e.toString());
        }
        return null;
    }
    public static String promptRole(){
        try{
            System.out.println("Enter your Role:");
            role = scanner.nextLine();
            while (role.equals("") || (!role.equals(SU_ROLE) || (!role.equals(NU_ROLE)))) {
                System.out.println("Enter your Role:");
                role = scanner.nextLine();
            }
            return role;
        } catch (Exception e){
            System.out.println("Exception Caught: " + e.toString());
        }
        return null;
    }
    public static int promptMenu(String user, String role){
        if (role.equals(SU_ROLE)){
            System.out.println(Menu.welcomeMessage + " " +  user);
            System.out.println(Menu.lineSeparator);
            superMenu();

            try{
                int menuChoice = Integer.parseInt(scanner.nextLine());
                return menuChoice;

            } catch (Exception e){
                System.out.println("Exception Caught: " + e.toString());
            }
        } else{
            System.out.println(Menu.welcomeMessage + " " + user);
            System.out.println(Menu.lineSeparator);
            normalMenu();

            try{
                int menuChoice = Integer.parseInt(scanner.nextLine());
                return menuChoice;
            } catch (Exception e){
                System.out.println("Exception Caught: " + e.toString());
            }
        }
        return -1;
    }
    private static void superMenu(){
            System.out.println(Menu.listItemZero);
            normalMenu();
    }
    private static void normalMenu() {
        System.out.println(Menu.listItemOne);
        System.out.println(Menu.listItemTwo);
        System.out.println(Menu.listItemThree);
        System.out.println(Menu.listItemFour);
    }
    public static void lockoutMessage(){
        System.out.println(Menu.lockoutMessage);
    }
    public static Person promptUserCredential() throws IOException {
        Person user = null;
        int i = 0;
        try{
            while ( i < 5)  {
                if (Objects.isNull(user)) {
                    String email = promptEmail();
                    String password = promptPassword();
                    user = UserValidator.validate(email, password);
                    i++;
                } else {
                    break;
                }
            }

            if (Objects.isNull(user) && i == 5){
                System.out.println(Menu.lockoutMessage);
            } else {
                return user;
            }
        } catch (Exception e){
            System.out.println("Exception Caught: " + e.toString());
        }
        return null;
    }
}
