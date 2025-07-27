1. It should be log levels like DEBUG,WARN,INFO
2. It can write to different outputs like file, console and database.
3. It needs to be thread safe as it will be accessed concurrently
4. Allows the user to provide config like logLevel and output destinations


// LogMessage
1. Format of the timestamp
2. Log Level
3. String message

// LogLevelEnum
1. INFO
2. DEBUG
3. WARN

// LoggingConfig

1. To hold config for the logger to be used

// Different types of appender implementation

// LogAppender Interface
1. append(LogMessage message)

// Logging singleton






