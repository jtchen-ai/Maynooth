package experiment13_1;

class PS implements Visitor{
    @Override
    public void visit(Programmer programmer) {
        System.out.println(programmer.name + " is working on PS related tasks.");
    }
}
