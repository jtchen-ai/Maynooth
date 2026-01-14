package experiment23_1;

public class Test {
    public static void main(String[] args) {
        Expression complexConstant1 = new ComplexConstant(3,2);
        Expression complexConstant2 = new ComplexConstant(1,7);

        // 3 + 2i + 1 + 7i
        Expression addition = new AdditionExpression(complexConstant1, complexConstant2);
        System.out.println("Addition Result: " + addition.interpret());

        // 3 + 2i - (1 + 7i)
        Expression subtraction = new SubtractionExpression(complexConstant1, complexConstant2);
        System.out.println("Subtraction Result: " + subtraction.interpret());
    }
}
