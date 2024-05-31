class FileLogger {
    public void log(String message) {

    }
}

// 의존성 역전 원칙은 상위 계층이 하위 계층의 변화에 독립돼야 함
// LogManger 는 직접적으로 FileLogger 에 의존하기 때문에 위반한다.
class LogManager {
    private final FileLogger fileLogger;

    public LogManager() {
        this.fileLogger = new FileLogger();
    }

    public void logMessage(String message) {
        fileLogger.log(message);
    }
}

// Logger 인터페이스를 통해 추상화를 도입해서
// LogManager 는 의존성 역전 원칙을 준수하게 됨
interface Logger {
    void log(String message);
}

class NiceFileLogger implements Logger {
    @Override
    public void log(String message) {

    }
}

class NiceLogManager {
    private final Logger logger;

    public NiceLogManager(Logger logger) {
        this.logger = logger;
    }

    public void logMessage(String message) {
        logger.log(message);
    }
}