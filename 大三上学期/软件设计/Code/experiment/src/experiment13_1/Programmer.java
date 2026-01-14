package experiment13_1;

class Programmer implements Staff{
    String name;

    public Programmer(){
        this.name = "Lee";
    }


    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}
