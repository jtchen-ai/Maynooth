package experiment7_1;

abstract class Decorator extends Machine{
    protected Machine machine;

    public void setMachine(Machine machine){
        this.machine = machine;
    }

    @Override
    public void run() {
        machine.run();
    }
}
