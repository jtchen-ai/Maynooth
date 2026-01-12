package experiment16_2;

public class DrinkDemo {
    public static void main(String[] args) {
        DivdrinkTemplate coffee = new Coffee();
        DivdrinkTemplate lemonTea = new LemonTea();
        coffee.div();
        System.out.println();
        lemonTea.div();
    }
}
