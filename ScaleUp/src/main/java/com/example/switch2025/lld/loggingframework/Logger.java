package com.example.switch2025.lld.loggingframework;

import com.example.switch2025.lld.loggingframework.appenders.ILogAppender;
import com.example.switch2025.lld.loggingframework.enums.LogLevel;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private LogLevel maxLogLevel;
    private final List<ILogAppender> appenders;

    public Logger(Builder builder) {
        this.maxLogLevel = builder.maxLogLevel;
        this.appenders = builder.appenders;
    }

    public void log(LogLevel logLevel, String message) {
        // need to check the minLevel till we can log
        if (!shouldLog(logLevel)) {
            return;
        }
        LogMessage logMessage = new LogMessage(logLevel, message);
        appenders.forEach(appender -> appender.append(logMessage));
    }

    public void setMaxLogLevel(LogLevel maxLogLevel) {
        this.maxLogLevel = maxLogLevel;
    }

    private boolean shouldLog(LogLevel logLevel) {
        return logLevel.ordinal() <= maxLogLevel.ordinal();
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    public static final class Builder {
        private LogLevel maxLogLevel;
        private List<ILogAppender> appenders = new ArrayList<>();

        public Builder withAppender(ILogAppender appender) {
            appenders.add(appender);
            return this;
        }

        public Builder withMinLevel(LogLevel minLogLevel) {
            this.maxLogLevel = minLogLevel;
            return this;
        }

        public Logger build() {
            return new Logger(this);
        }
    }
}
