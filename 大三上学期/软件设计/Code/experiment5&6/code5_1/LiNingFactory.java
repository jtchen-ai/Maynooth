package experiment5_1;

public class LiNingFactory implements AbstractFactory{
    @Override
    public Clothing createClothing() {
        return new LiNingClothing();
    }

    @Override
    public Shoe createShoe() {
        return new LiNingShoe();
    }
}
