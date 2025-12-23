public class TestBuilderPattern {

	public static void main(String[] args) {
		//
		// CarBuilder carBuilder = new SedanCarBuilder();

		//
		CarBuilder carBuilder = new SportCarBuilder();

		CarDirector director = new CarDirector(carBuilder); // CarDirector
		director.build(); //  SportCarBuilder
		Car car = carBuilder.getCar(); // 从 SportCarBuilder
		System.out.println(car); //
	}
}