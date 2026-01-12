package experiment10_1;

import java.util.HashMap;
import java.util.Map;

class ShapeFactory {
    static Map<String, Shape> circleMap = new HashMap<String, Shape>();
    public static synchronized Shape getCircle(String color)throws Exception{
        Shape shape = circleMap.get(color);
        if(shape == null){
            shape = new Circle(color);
            circleMap.put(color, shape);
        }else{
            System.out.println("Using existing "+ color +" circle and coloring it");
        }
        return shape;
    }
}
