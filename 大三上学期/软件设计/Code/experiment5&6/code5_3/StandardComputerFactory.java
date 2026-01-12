package experiment5_3;

public class StandardComputerFactory implements ComputerFactory{
    @Override
    public CPU createCPU() {
        return new StandardCPU();
    }

    @Override
    public GraphicsCard createGraphicsCard() {
        return new StandardGraphicsCard();
    }

    @Override
    public HardDisk createHardDisk() {
        return new StandardHardDisk();
    }

    @Override
    public Memory createMemory() {
        return new StandardMemory();
    }

    @Override
    public Motherboard createMotherboard() {
        return new StandardMotherboard();
    }


}
