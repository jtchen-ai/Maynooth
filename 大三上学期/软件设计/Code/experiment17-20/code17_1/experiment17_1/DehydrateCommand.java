package experiment17_1;

class DehydrateCommand implements Command{
    private WashingMachine washingMachine;
    public DehydrateCommand(WashingMachine washingMachine){
        this.washingMachine = washingMachine;
    }

    @Override
    public void executeCommand() {
        washingMachine.performDehydrate();
    }
}
