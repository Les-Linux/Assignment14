package com.elbicon.coderscampus;

import java.io.File;
import java.io.IOException;

public interface FileService {
    String SUPER_USER = null;

    Person[] readLine(File file) throws IOException;

    Person writeLine(File file, String username, String changeValue, int changeType) throws IOException;
}
