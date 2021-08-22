package com.elbicon.coderscampus;

import java.io.File;
import java.io.IOException;

public class MenuValidatorService {
    static final File file = new File("./sources/users.txt");
    static final FileImpl userFile = new FileImpl();

    public static void menuSelection(Integer menuChoice, String originalUsername) throws IOException {
        Person user = null;
        String changeUser = originalUsername;
        Integer menuId = menuChoice;
        while (menuId != 4) {
            switch (menuId) {
                case 0 -> {
                    user = ScannerService.promptUserCredential(false);
                    menuId = ScannerService.promptMenu(user.getName(), user.getRole());
                    changeUser = user.getUsername();
                }
                case 1 -> {
                    String username = ScannerService.promptEmail();
                    user = userFile.writeLine(file, changeUser, username, 1);
                    menuId = ScannerService.promptMenu(user.getName(), user.getRole());
                    changeUser = user.getUsername();
                }
                case 2 -> {
                    String password = ScannerService.promptPassword();
                    user = userFile.writeLine(file, changeUser, password, 2);
                    menuId = ScannerService.promptMenu(user.getName(), user.getRole());
                }
                case 3 -> {
                    String name = ScannerService.promptName();
                    user = userFile.writeLine(file, changeUser, name, 3);
                    menuId = ScannerService.promptMenu(user.getName(), user.getRole());
                }
            }
        }
    }
}
