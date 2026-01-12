package experiment4_2;

public class FileLoggerFactory extends LoggerFactory{
    @Override
    public Logger createLogger() {
        return new FileLogger();
    }
}
