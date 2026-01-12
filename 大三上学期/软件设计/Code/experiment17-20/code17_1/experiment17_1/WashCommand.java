package experiment17_1;

class WashCommand implements Command{
    private WashingMachine washingMachine;
    public WashCommand(WashingMachine washingMachine){
        this.washingMachine = washingMachine;
    }

    @Override
    public void executeCommand() {
        washingMachine.performWash();
    }
}
