package com.elbicon.coderscampus;

import java.io.File;
import java.io.IOException;

public interface FileService {
    static final String SUPER_USER = null;
    Person[] readLine (File file) throws IOException;
    void writeLine (File file, String username, String changeValue, int changeType) throws IOException;
}
