package com.example.switch2025.lld.loggingframework.appenders.impl;

import com.example.switch2025.lld.loggingframework.LogMessage;
import com.example.switch2025.lld.loggingframework.appenders.ILogAppender;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogAppender implements ILogAppender {
    private final String filePath;
    private FileWriter fileWriter;

    public FileLogAppender(String filePath) {
        this.filePath = filePath;
        try {
            fileWriter = new FileWriter(filePath, true);
        } catch (IOException e) {
            // handle exception
        }
    }

    @Override
    public void append(LogMessage message) {
        try {
            fileWriter.append(String.valueOf(message)).append("\n");
            fileWriter.flush();
        } catch (IOException e) {
            // handle exception
        }
    }
}
