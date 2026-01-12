package experiment4_2;

public class DatabaseLogger extends Logger{
    @Override
    public void log(String message) {
        System.out.println("Save the log to a database: "+message);
    }
}
