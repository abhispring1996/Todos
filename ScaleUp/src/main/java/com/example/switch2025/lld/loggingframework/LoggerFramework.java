package com.example.switch2025.lld.loggingframework;

import com.example.switch2025.lld.loggingframework.enums.LogLevel;

public class LoggerFramework {

    public static void main(String[] args) {
        Logger logger = LoggerManager.getLogger();
        logger.info("Info logging");
        logger.setMaxLogLevel(LogLevel.FATAL);
        logger.fatal("Fatal logging");
    }
}
