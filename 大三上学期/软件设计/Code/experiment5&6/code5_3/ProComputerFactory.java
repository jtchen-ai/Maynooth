package experiment5_3;

public class ProComputerFactory implements ComputerFactory{
    @Override
    public CPU createCPU() {
        return new ProCPU();
    }

    @Override
    public GraphicsCard createGraphicsCard() {
        return new ProGraphicsCard();
    }

    @Override
    public HardDisk createHardDisk() {
        return new ProHardDisk();
    }

    @Override
    public Memory createMemory() {
        return new ProMemory();
    }

    @Override
    public Motherboard createMotherboard() {
        return new ProMotherboard();
    }

}
