package experiment5_3;

public class ProGraphicsCard implements GraphicsCard{
    @Override
    public void produceGraphicsCard() {
        System.out.print("Graphics Card: Pro Graphics Card");
    }

    @Override
    public double getPrice() {
        return 1200;
    }
}
