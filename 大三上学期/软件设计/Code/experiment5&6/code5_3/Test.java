package experiment5_3;

public class Test {
    public static void main(String[] args) {
        ComputerFactory pro = new ProComputerFactory();
        ComputerFactory standard = new StandardComputerFactory();
        printDetails(pro);
        System.out.println();
        printDetails(standard);


    }
    public static void printDetails(ComputerFactory computerFactory){
        CPU cpu = computerFactory.createCPU();
        GraphicsCard graphicsCard = computerFactory.createGraphicsCard();
        HardDisk hardDisk = computerFactory.createHardDisk();
        Memory memory = computerFactory.createMemory();
        Motherboard motherboard = computerFactory.createMotherboard();

        cpu.produceCPU();
        System.out.print(" => The price of CPU is: "+cpu.getPrice()+"\n");
        graphicsCard.produceGraphicsCard();
        System.out.print(" => The price of graphics card is: "+graphicsCard.getPrice()+"\n");
        hardDisk.produceHardDisk();
        System.out.print(" => The price of hard Disk is: "+hardDisk.getPrice()+"\n");
        memory.produceMemory();
        System.out.print(" => The price of memory is: "+memory.getPrice()+"\n");
        motherboard.produceMotherboard();
        System.out.print(" => The price of motherboard is: "+motherboard.getPrice()+"\n");

        double totalPrice = cpu.getPrice()+graphicsCard.getPrice()+hardDisk.getPrice()+memory.getPrice()+motherboard.getPrice();
        System.out.println("The total price is "+totalPrice);


    }
}
