package experiment15_1;

public class Strategypatterndemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 20 + 30 = " + context.showOperation(10, 20, 30));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 20 - 30 = " + context.showOperation(10, 20, 30));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 20 * 30 = " + context.showOperation(10, 20, 30));

    }
}
