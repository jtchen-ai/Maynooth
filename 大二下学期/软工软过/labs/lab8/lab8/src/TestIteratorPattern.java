public class TestIteratorPattern {

	public static void main(String[] args) {
		ShapeStorage storage = new ShapeStorage();
		storage.addShape("Polygon");
		storage.addShape("Hexagon");
		storage.addShape("Circle");
		storage.addShape("Rectangle");
		storage.addShape("Square");

		ShapeIterator iterator = new ShapeIterator(storage.getShapes());

		// Test contains() method
		System.out.println("--- Testing contains() method ---");
		System.out.println("Collection contains 'Circle': " + iterator.contains("Circle"));
		System.out.println("Collection contains 'Triangle': " + iterator.contains("Triangle"));
		System.out.println();


		// Test prev() and hasPrev() methods
		System.out.println("--- Iterating forward to the end ---");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println();

		System.out.println("--- Iterating backward with prev() ---");
		// Now iterator is at the end, we can use prev() to iterate backward
		while (iterator.hasPrev()) {
			System.out.println(iterator.prev());
		}
	}
}