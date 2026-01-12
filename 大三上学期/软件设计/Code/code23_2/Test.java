package experiment23_2;

public class Test {
    public static void main(String[] args) {
        Expression five = new Number(5);
        Expression two = new Number(2);
        Expression nine = new Number(9);

        Expression subtraction = new SubtractionExpression(five, two);
        Expression addition = new AdditionExpression(subtraction, nine);
        System.out.println("Expression Result (5 - 2 + 9): " + addition.interpret());

    }
}
