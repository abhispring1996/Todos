package com.example.switch2025.lld.loggingframework.appenders.impl;

import com.example.switch2025.lld.loggingframework.LogMessage;
import com.example.switch2025.lld.loggingframework.appenders.ILogAppender;
import com.example.switch2025.lld.loggingframework.formatters.ILogFormatter;

public class ConsoleLogAppender implements ILogAppender {
    private final ILogFormatter formatter;

    public ConsoleLogAppender(ILogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }
}
