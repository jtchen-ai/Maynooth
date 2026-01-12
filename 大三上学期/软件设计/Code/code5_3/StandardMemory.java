package experiment5_3;

public class StandardMemory implements Memory{
    @Override
    public void produceMemory() {
        System.out.print("Memory: Standard Memory");
    }

    @Override
    public double getPrice() {
        return 600;
    }
}
