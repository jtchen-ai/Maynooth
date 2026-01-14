package experiment5_3;

public class StandardGraphicsCard implements GraphicsCard{
    @Override
    public void produceGraphicsCard() {
        System.out.print("GraphicsCard: Standard Graphics Card");
    }

    @Override
    public double getPrice() {
        return 900;
    }
}
