package com.elbicon.coderscampus;

import java.io.File;
import java.io.IOException;

public class UserValidator {
    static final File file = new File("./sources/users.txt");
    static final FileImpl userFile = new FileImpl();

    public static Person validate(String username, String password) throws IOException {
        Person[] users = null;
        users = userFile.readLine(file);
        for (Person user : users) {
            if (password.equals("")) {
                if (user.getUsername().equals(username.trim())) {
                    return user;
                }
            } else {
                if (user.getUsername().equals(username.trim()) && user.getPassword().equals(password.trim())) {
                    return user;
                }

            }
        }
        return null;
    }
}
