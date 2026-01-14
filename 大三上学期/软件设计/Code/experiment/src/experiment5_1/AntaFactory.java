package experiment5_1;

public class AntaFactory implements AbstractFactory{
    @Override
    public Clothing createClothing() {
        return new AntaClothing();
    }

    @Override
    public Shoe createShoe() {
        return new AntaShoe();
    }
}
