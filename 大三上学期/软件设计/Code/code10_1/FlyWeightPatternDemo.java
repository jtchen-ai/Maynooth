package experiment10_1;

import java.util.Random;

public class FlyWeightPatternDemo {
    private static final String[] colors = {"red", "green", "blue", "black", "white"};

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 20; i++){
            Circle cicle = (Circle)ShapeFactory.getCircle(getRandomColor());
            cicle.setX(getRandomX());
            cicle.setY(getRandomY());
            cicle.setRadius(100);
            cicle.draw();
        }
    }
    private static String getRandomColor(){
        Random random = new Random();
        int index = random.nextInt(colors.length);
        return colors[index];
    }
    private static int getRandomX(){
        return (int) (Math.random() * 100);
    }
    private static int getRandomY(){
        return (int) (Math.random() * 100);
    }
}
