package experiment13_2;

class Squat implements Visitor{
    @Override
    public void visit(Lion lion) {
        System.out.println(lion.name + " is squatting");
    }

    @Override
    public void visit(Tiger tiger) {
        System.out.println(tiger.name + " is squatting");
    }

    @Override
    public void visit(Horse horse) {
        System.out.println(horse.name + " is squatting");
    }
}
