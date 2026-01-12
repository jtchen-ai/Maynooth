package experiment13_1;

class Test implements Visitor{
    @Override
    public void visit(Programmer programmer) {
        System.out.println(programmer.name + " is working on test related tasks.");
    }
}
