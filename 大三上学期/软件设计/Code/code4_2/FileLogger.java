package experiment4_2;

public class FileLogger extends Logger{
    @Override
    public void log(String message) {
        System.out.println("Save the log to a file: "+message);
    }
}
