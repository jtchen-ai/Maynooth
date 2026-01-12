package experiment13_2;

class Tiger implements Animal{
    String name;
    public Tiger(){
        this.name = "Tiger";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}
