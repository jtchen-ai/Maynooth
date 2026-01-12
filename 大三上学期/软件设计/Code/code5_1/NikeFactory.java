package experiment5_1;

public class NikeFactory implements AbstractFactory{
    @Override
    public Clothing createClothing() {
        return new NikeClothing();
    }

    @Override
    public Shoe createShoe() {
        return new NikeShoe();
    }
}
