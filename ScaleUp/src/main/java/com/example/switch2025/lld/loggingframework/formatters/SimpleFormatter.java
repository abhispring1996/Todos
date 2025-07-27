package com.example.switch2025.lld.loggingframework.formatters;

import com.example.switch2025.lld.loggingframework.LogMessage;

public class SimpleFormatter implements ILogFormatter {
    @Override
    public String format(LogMessage message) {
        return String.format("[%s] [%s] [%s]: %s",
                message.getTimestamp(),
                message.getThreadName(),
                message.getLogLevel(),
                message.getMessage());
    }
}
