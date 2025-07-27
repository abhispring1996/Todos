package com.example.switch2025.lld.loggingframework.appenders;

import com.example.switch2025.lld.loggingframework.LogMessage;

public interface ILogAppender {
    void append(LogMessage message);

    void setNext(ILogAppender appender);
}
