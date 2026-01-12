package experiment4_2;

public class DatabaseLoggerFactory extends LoggerFactory{
    @Override
    public Logger createLogger() {
        return new DatabaseLogger();
    }
}
