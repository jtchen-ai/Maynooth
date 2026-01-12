package experiment14_1;

public class Test {
    public static void main(String[] args) {
        IntegerSubject integerSubject = new IntegerSubject();
        Observer binaryObserver = new BinaryObserver("Binary string:");
        Observer hexaObserver = new HexaObserver("Hexadecimal string:");
        integerSubject.register(binaryObserver);
        integerSubject.register(hexaObserver);
        System.out.println("Setting num = 12");
        integerSubject.setNum(12);
        System.out.println();
        System.out.println("Unregistering the binary observer, then setting num = 13");
        integerSubject.unRegister(binaryObserver);
        integerSubject.setNum(13);
    }
}
