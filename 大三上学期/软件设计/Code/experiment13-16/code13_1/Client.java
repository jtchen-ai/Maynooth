package experiment13_1;

public class Client {
    public static void main(String[] args) {
        Visitor visitor_ps = new PS();
        Visitor visitor_test = new Test();
        Programmer programmer = new Programmer();
        programmer.acceptVisitor(visitor_ps);
        programmer.acceptVisitor(visitor_test);
    }
}
