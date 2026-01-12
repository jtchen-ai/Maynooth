package experiment5_3;

public class ProMotherboard implements Motherboard{
    @Override
    public void produceMotherboard() {
        System.out.print("Motherboard: Pro Motherboard");
    }

    @Override
    public double getPrice() {
        return 1100;
    }
}
