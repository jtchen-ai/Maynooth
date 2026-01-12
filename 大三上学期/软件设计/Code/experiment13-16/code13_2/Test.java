package experiment13_2;

public class Test {
    public static void main(String[] args) {
        Visitor j = new Jump();
        Visitor s = new Squat();
        Lion lion = new Lion();
        Tiger tiger = new Tiger();
        Horse horse = new Horse();
        lion.acceptVisitor(j);
        tiger.acceptVisitor(s);
        horse.acceptVisitor(s);
        lion.acceptVisitor(j);
        tiger.acceptVisitor(s);
        horse.acceptVisitor(j);
    }
}
