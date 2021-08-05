package com.elbicon.coderscampus;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class menuValidatorService {
    private static Integer menuId;
    private static String username;
    private static String password;
    private static String name;
    static File file = new File("./sources/users.txt");
    static FileImpl userFile = new FileImpl();
    public static void menuSelection(Integer menuChoice, String originalUsername ) throws IOException {
        menuId = menuChoice;

        switch(menuId){
            case 1:
                username = ScannerService.promptEmail();
                userFile.writeLine(file, originalUsername, username, 1);
                break;
            case 2:
                password = ScannerService.promptPassword();
                userFile.writeLine(file, originalUsername, password, 2);
                break;
            case 3:
                name = ScannerService.promptName();
                userFile.writeLine(file, originalUsername, name, 3);
                break;
        }


    }
}
