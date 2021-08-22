package com.elbicon.coderscampus;

import java.util.Objects;
import java.util.Scanner;

public class ScannerService extends Menu {
    private static final String SU_ROLE = "super_user";
    private static final String NU_ROLE = "normal_user";

    static final Scanner scanner = new Scanner(System.in);

    public static String promptEmail() {
        boolean validEmail = false;
        try {
            System.out.println("Enter your email:");
            String email = scanner.nextLine();
            while (email.equals("")) {
                System.out.println("Enter your email:");
                email = scanner.nextLine();
            }
            return email;
        } catch (Exception e) {
            System.out.println("Exception Caught: " + e);
        }
        return null;
    }

    public static String promptPassword() {
        try {
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            while (password.equals("")) {
                System.out.println("Enter your password:");
                password = scanner.nextLine();
            }
            return password;
        } catch (Exception e) {
            System.out.println("Exception Caught: " + e);
        }
        return null;
    }

    public static String promptName() {
        try {
            System.out.println("Enter your Name:");
            String name = scanner.nextLine();
            while (name.equals("")) {
                System.out.println("Enter your Name:");
                name = scanner.nextLine();
            }
            return name;
        } catch (Exception e) {
            System.out.println("Exception Caught: " + e);
        }
        return null;
    }

    public static String promptRole() {
        try {
            System.out.println("Enter your Role:");
            String role = scanner.nextLine();
            while (role.equals("") || (!role.equals(SU_ROLE) || (!role.equals(NU_ROLE)))) {
                System.out.println("Enter your Role:");
                role = scanner.nextLine();
            }
            return role;
        } catch (Exception e) {
            System.out.println("Exception Caught: " + e);
        }
        return null;
    }

    public static Integer promptMenu(String user, String role) {
        String line = null;
        Integer menuChoice = -1;
        System.out.println(Menu.welcomeMessage + " " + user);
        System.out.println(Menu.lineSeparator);
        if (role.equals(SU_ROLE)) {
            superMenu();
        } else {
            normalMenu();
        }
        try {
            while (menuChoice == -1) {
                line = scanner.nextLine();
                if (!(line.isEmpty()) & line.matches("[0-4]")) {
                    menuChoice = Integer.parseInt(line);
                    System.out.println("You selected menu item: " + menuChoice);
                    return menuChoice;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception Caught: " + e);
        }
        return -1;
    }

    private static void superMenu() {
        System.out.println(Menu.listItemZero);
        normalMenu();
    }

    private static void normalMenu() {
        System.out.println(Menu.listItemOne);
        System.out.println(Menu.listItemTwo);
        System.out.println(Menu.listItemThree);
        System.out.println(Menu.listItemFour);
    }

    public static void lockoutMessage() {
        System.out.println(Menu.lockoutMessage);
    }
    private static void invalidLoginMessage(){
        System.out.println(Menu.invalidLoginMessage);
    }
    public static Person promptUserCredential(boolean passwordRequired)  {
        Person user = null;
        int i = 0;
        try {
            while (i < 5) {
                if (Objects.isNull(user)) {
                    String email = promptEmail();
                    if (passwordRequired) {
                        String password = promptPassword();
                        user = UserValidator.validate(email, password);
                    } else {
                        user = UserValidator.validate(email, "");
                    }
                    if(Objects.isNull(user) && i != 4){
                        invalidLoginMessage();
                    }
                    i++;
                } else {
                    break;
                }
            }

            if (Objects.isNull(user) && i == 5) {
                System.out.println(Menu.lockoutMessage);
            } else{
                return user;
            }
        } catch (Exception e) {
            System.out.println("Exception Caught: " + e);
        }
        return null;
    }
}
