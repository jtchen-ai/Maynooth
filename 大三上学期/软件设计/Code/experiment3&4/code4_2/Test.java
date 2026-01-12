package experiment4_2;

public class Test {
    public static void main(String[] args) {
        LoggerFactory fileLoggerFactory = new FileLoggerFactory();
        LoggerFactory databaseLoggerFactory = new DatabaseLoggerFactory();

        Logger fileLogger = fileLoggerFactory.createLogger();
        Logger databaseLogger = databaseLoggerFactory.createLogger();

        fileLogger.log("This is a file log");
        databaseLogger.log("This is a database log");
    }
}
