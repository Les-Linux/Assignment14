package com.elbicon.coderscampus;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileImpl implements FileService {
    static final String SUPER_USER = "super_user";

    @Override
    public Person[] readLine(File file) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            long userCount = numberOfUsers(file);
            String line = "";
            String[] userType = null;
            Person[] users = new Person[(int) userCount];
            int i = 0;
            while ((line = fileReader.readLine()) != null) {
                userType = line.split(",");
                if (userType != null) {
                    users[i] = userTypeValidator(userType);
                    i++;
                }
            }
            return users;
        }
    }

    @Override
    public Person writeLine(File file, String username, String changeValue, int changeType) throws IOException {
        Person[] users = readLine(file);
        Person changedUserCredential = null;
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {
            for (Person user : users) {
                if (user.getUsername().equals(username)) {
                    switch (changeType) {
                        case 1 -> {
                            user.setUsername(changeValue);
                            changedUserCredential = user;
                        }
                        case 2 -> {
                            user.setPassword(changeValue);
                            changedUserCredential = user;
                        }
                        case 3 -> {
                            user.setName(changeValue);
                            changedUserCredential = user;
                        }
                    }
                }
            }
            Arrays.sort(users);

            for (Person user : users) {
                fileWriter.write(user.getUsername() + ", " + user.getPassword() + ", " + user.getName() + ", " + user.getRole() + "\r\n");
            }
        }
        return changedUserCredential;
    }

    private Person userTypeValidator(String[] user) {
        try {
            if ((user[user.length - 1] != null) && user[user.length - 1].trim().equals(SUPER_USER)) {
                SuperUserImpl superUser = new SuperUserImpl();
                superUser.setUsername(user[0].trim());
                superUser.setPassword(user[1].trim());
                superUser.setName(user[2].trim());
                superUser.setRole(user[3].trim());
                return superUser;
            } else {
                Person normalUser = new NormalUserImpl();
                normalUser.setUsername(user[0].trim());
                normalUser.setPassword(user[1].trim());
                normalUser.setName(user[2].trim());
                normalUser.setRole(user[3].trim());
                return normalUser;
            }
        } catch (Exception e) {
            System.out.println("Exception Caught: " + e);
        }
        return null;
    }

    private long numberOfUsers(File file) throws IOException {
        long lineCount;

        // try with resources
        try (Stream<String> fileStream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            lineCount = fileStream.count();
        }
        return lineCount;
    }
}
