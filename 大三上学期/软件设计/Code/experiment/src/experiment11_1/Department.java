package experiment11_1;

class Department extends Unit{
    public Department(String name){
        super(name);
    }

    @Override
    public void add(Unit unit) {
        throw new UnsupportedOperationException("Cannot add a unit to a department.");
    }

    @Override
    public void remove(Unit unit) {
        throw new UnsupportedOperationException("Cannot remove a unit from a department.");
    }

    @Override
    public void inform(int level) {
        System.out.println(" ".repeat(level * 2) + "Informing Department: " + name);
    }
}
