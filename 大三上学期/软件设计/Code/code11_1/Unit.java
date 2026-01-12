package experiment11_1;

abstract class Unit {
    protected String name;
    public Unit(String name){
        this.name = name;
    }

    public abstract void add(Unit unit);
    public abstract void remove(Unit unit);
    public abstract void inform(int level);
}
