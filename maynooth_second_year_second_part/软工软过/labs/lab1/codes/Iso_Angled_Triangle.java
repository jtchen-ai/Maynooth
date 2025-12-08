package shape;

/**
 * The type Iso angled triangle.
 */
public class Iso_Angled_Triangle {


    /**
     * Area double.
     *
     * @param base   the base
     * @param height the height
     * @return the double
     */
    public static double area(double base, double height) {
        return (base * height) / 2;
    }


    /**
     * Perimeter double.
     *
     * @param base the base
     * @param side the side
     * @return the double
     */
    public static double perimeter(double base, double side) {
        return base + 2 * side;
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        double base = 10.0;
        double height = 6.0;
        double side = 8.0;

        double triangleArea = area(base, height);
        double trianglePerimeter = perimeter(base, side);

        System.out.println("Isosceles Triangle:");
        System.out.println("Base = " + base + ", Height = " + height + ", Side = " + side);
        System.out.println("Area = " + triangleArea);
        System.out.println("Perimeter = " + trianglePerimeter);
    }
}