package experiment5_1;

public class Test {
    public static void main(String[] args) {
        System.out.println("***Anta Factory***");
        AbstractFactory abstractFactory1 = new AntaFactory();
        Clothing clothing1 = abstractFactory1.createClothing();
        Shoe shoe1 = abstractFactory1.createShoe();
        clothing1.show();
        shoe1.show();

        System.out.println("***Nike Factory***");
        AbstractFactory abstractFactory2 = new NikeFactory();
        Clothing clothing2 = abstractFactory2.createClothing();
        Shoe shoe2 = abstractFactory2.createShoe();
        clothing2.show();
        shoe2.show();

        System.out.println("***LiNing Factory***");
        AbstractFactory abstractFactory3 = new LiNingFactory();
        Clothing clothing3 = abstractFactory3.createClothing();
        Shoe shoe3 = abstractFactory3.createShoe();
        clothing3.show();
        shoe3.show();

    }
}
