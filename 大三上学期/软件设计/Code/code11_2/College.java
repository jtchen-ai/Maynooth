package experiment11_2;

import java.util.ArrayList;
import java.util.List;

class College implements OrganizationComponent{
    private String name;
    private String description;
    private List<OrganizationComponent> departments;

    public College(String name, String description){
        this.name = name;
        this.description = description;
        departments = new ArrayList<OrganizationComponent>();
    }

    @Override
    public void add(OrganizationComponent component) {
        departments.add(component);
    }

    @Override
    public void remove(OrganizationComponent component) {
        departments.remove(component);
    }

    @Override
    public void print() {
        System.out.println(name + ": " + description);
        for(OrganizationComponent department: departments){
            department.print();
        }
    }
}
