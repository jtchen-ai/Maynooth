package experiment13_2;

import java.util.LinkedList;

class Lion implements Animal{
    String name;
    public Lion(){
        this.name = "Lion";
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}
