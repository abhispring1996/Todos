package com.example.switch2025.lld.loggingframework;

import com.example.switch2025.lld.loggingframework.appenders.impl.ConsoleLogAppender;
import com.example.switch2025.lld.loggingframework.enums.LogLevel;
import com.example.switch2025.lld.loggingframework.formatters.SimpleFormatter;

public class LoggerManager {
    // inner static classes are loaded at runtime
    private static class LoggerHolder {
        private final static Logger INSTANCE = new Logger.Builder()
                .withAppender(new ConsoleLogAppender(new SimpleFormatter()))
                .withMinLevel(LogLevel.DEBUG)
                .build();
    }

    public static Logger getLogger() {
        return LoggerHolder.INSTANCE;
    }
}
