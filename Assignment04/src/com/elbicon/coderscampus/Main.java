package com.elbicon.coderscampus;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        final String ROLE = "super_user";
        int menuOption;
        Person user = ScannerService.promptUserCredential();
        if (Objects.isNull(user) ) {
              System.exit(0);
        } else if (!Objects.isNull(user)){
            menuOption = ScannerService.promptMenu(user.getName(), user.getRole());

            switch(menuOption){
                case 0:
                    Person userContextChange = ScannerService.promptUserCredential();
                    if (!Objects.isNull(userContextChange)){
                        menuOption = (Integer) ScannerService.promptMenu(userContextChange.getUsername(), userContextChange.getRole());
                        menuValidatorService.menuSelection(menuOption, userContextChange.getUsername());
                    }
                    break;
                case 4:
                    System.exit(0);
                default:
                    menuValidatorService.menuSelection(menuOption, user.getUsername());
                    break;
            }
        }
    }
}
