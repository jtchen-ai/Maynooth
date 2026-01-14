package experiment11_1;

import java.util.ArrayList;
import java.util.List;

class Headquarters extends Unit{
    private List<Unit> unitList;
    public Headquarters(String name){
        super(name);
        unitList = new ArrayList<>();
    }

    @Override
    public void add(Unit unit) {
        unitList.add(unit);
    }

    @Override
    public void remove(Unit unit) {
        unitList.remove(unit);
    }

    @Override
    public void inform(int level) {
        System.out.println(" ".repeat(level * 2) + "Informing Headquarters: " + name);
        for(Unit unit: unitList){
            unit.inform(level + 1);
        }
    }
}
