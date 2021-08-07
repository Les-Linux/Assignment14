package com.elbicon.coderscampus;

import java.io.IOException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        final String ROLE = "super_user";
        Integer menuOption = null;
        Person user = ScannerService.promptUserCredential(true);
        if (Objects.isNull(user)) {
            System.exit(0);
        } else if (!Objects.isNull(user)) {
            menuOption = ScannerService.promptMenu(user.getName(), user.getRole());
            switch (menuOption) {
                case 0 -> {
                    Person userContextChange = ScannerService.promptUserCredential(false);
                    if (!Objects.isNull(userContextChange)) {
                        menuOption = ScannerService.promptMenu(userContextChange.getName(), userContextChange.getRole());
                        MenuValidatorService.menuSelection(menuOption, userContextChange.getUsername());
                    }
                }
                case 4 -> System.exit(0);
                default -> MenuValidatorService.menuSelection(menuOption, user.getUsername());
            }
        }
    }
}
