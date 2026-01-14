package experiment18_1;

import java.util.List;

abstract class AbstractObjectList {
    protected List<Object> objects;

    public void addObject(Object obj){
        objects.add(obj);
    };
    public void removeObject(Object obj){
        objects.remove(obj);
    };
    public List<Object> getObjects(){
        return objects;
    };
    public abstract AbstractIterator createIterator();
}
