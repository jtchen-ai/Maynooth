package experiment5_3;

public class StandardMotherboard implements Motherboard{
    @Override
    public void produceMotherboard() {
        System.out.print("Motherboard: Standard Motherboard");
    }

    @Override
    public double getPrice() {
        return 700;
    }
}
