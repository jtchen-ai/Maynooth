package experiment13_2;

class Jump implements Visitor{
    @Override
    public void visit(Lion lion) {
        System.out.println(lion.name + " is jumping");
    }

    @Override
    public void visit(Tiger tiger) {
        System.out.println(tiger.name + " is jumping");
    }

    @Override
    public void visit(Horse horse) {
        System.out.println(horse.name + " is jumping");
    }
}
