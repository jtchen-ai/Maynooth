package experiment17_2;

public class Client {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(lightOnCommand);
        remoteControl.invokeCommand();

        remoteControl.setCommand(lightOffCommand);
        remoteControl.invokeCommand();
    }
}
