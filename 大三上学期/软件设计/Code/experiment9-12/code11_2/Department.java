package experiment11_2;

class Department implements OrganizationComponent{
    private String name;
    private String description;

    public Department(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(OrganizationComponent component) {
        throw new UnsupportedOperationException("Cannot add to a department");
    }

    @Override
    public void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a department");
    }

    @Override
    public void print() {
        System.out.println(name + ": " + description);
    }
}
