package experiment5_3;

interface ComputerFactory {
    CPU createCPU();
    GraphicsCard createGraphicsCard();
    HardDisk createHardDisk();
    Memory createMemory();
    Motherboard createMotherboard();
}
