package experiment17_2;

class LightOnCommand implements Command{
    private Light light;
    public LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void executeCommand() {
        light.turnOn();
    }
}
