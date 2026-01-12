package experiment13_2;

interface Visitor {
    void visit(Lion lion);
    void visit(Tiger tiger);
    void visit(Horse horse);
}
