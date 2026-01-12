package experiment17_2;

class LightOffCommand implements Command{
    private Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void executeCommand() {
        light.turnOff();
    }
}
