package com.example.switch2025.lld.loggingframework;

import com.example.switch2025.lld.loggingframework.enums.LogLevel;

public class LogMessage {
    private final LogLevel logLevel;
    private final long timestamp;
    private final String message;

    public LogMessage(LogLevel logLevel, String message) {
        this.message = message;
        this.logLevel = logLevel;
        this.timestamp = System.currentTimeMillis();
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getThreadName() {
        return Thread.currentThread().getName();
    }

    @Override
    public String toString() {
        return "[" + timestamp + "]" + logLevel + message;
    }
}
