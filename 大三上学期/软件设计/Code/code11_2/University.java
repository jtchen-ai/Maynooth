package experiment11_2;

import java.util.ArrayList;
import java.util.List;

class University implements OrganizationComponent{
    private String name;
    private String ranking;
    private List<OrganizationComponent> colleges;

    public University(String name, String ranking){
        this.name = name;
        this.ranking = ranking;
        colleges = new ArrayList<OrganizationComponent>();
    }

    @Override
    public void add(OrganizationComponent component) {
        colleges.add(component);
    }

    @Override
    public void remove(OrganizationComponent component) {
        colleges.remove(component);
    }

    @Override
    public void print() {
        System.out.println(name + " - Ranking: " + ranking);
        for(OrganizationComponent college: colleges){
            college.print();
        }
    }
}
