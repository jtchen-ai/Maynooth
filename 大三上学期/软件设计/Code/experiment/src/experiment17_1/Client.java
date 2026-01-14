package experiment17_1;

public class Client {
    public static void main(String[] args) {
        WashingMachine washingMachine = new WashingMachine();
        Command washCommand = new WashCommand(washingMachine);
        Command dehydrateCommand = new DehydrateCommand(washingMachine);

        Invoker invoker = new Invoker();

        invoker.setCommand(washCommand);
        invoker.invokeCommand();

        invoker.setCommand(dehydrateCommand);
        invoker.invokeCommand();
    }
}
