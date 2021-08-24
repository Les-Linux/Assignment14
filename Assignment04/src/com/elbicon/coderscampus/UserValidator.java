package com.elbicon.coderscampus;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class UserValidator {
    static final File file = new File("./sources/users.txt");
    static final FileImpl userFile = new FileImpl();

    public static Person validate(String username, String password) throws IOException {
        Person[] users = null;
        users = userFile.readLine(file);
        for (Person user : users) {
            if (password.equals("")) {
                if (user.getUsername().equalsIgnoreCase(username.trim())) {
                    return user;
                }
            } else {
                if (user.getUsername().equalsIgnoreCase(username.trim()) && user.getPassword().equals(password.trim())) {
                    return user;
                }
            }
        }
        return null;
    }
}
