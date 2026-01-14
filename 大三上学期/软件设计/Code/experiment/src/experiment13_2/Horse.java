package experiment13_2;

class Horse implements Animal{
    String name;
    public Horse(){
        this.name = "Horse";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}
