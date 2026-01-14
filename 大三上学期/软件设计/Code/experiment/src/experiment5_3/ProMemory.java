package experiment5_3;

public class ProMemory implements Memory{
    @Override
    public void produceMemory() {
        System.out.print("Memory: Pro Memory");
    }

    @Override
    public double getPrice() {
        return 1000;
    }
}
