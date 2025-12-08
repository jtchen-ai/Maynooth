import java.util.Iterator;
import java.util.NoSuchElementException;

public class ShapeIterator implements Iterator<Shape> {

	private Shape[] shapes;
	int pos;

	public ShapeIterator(Shape[] shapes) {
		this.shapes = shapes;
	}

	@Override
	public boolean hasNext() {
		if (pos >= shapes.length || shapes[pos] == null)
			return false;
		return true;
	}

	@Override
	public Shape next() {
		return shapes[pos++];
	}

	@Override
	public void remove() {
		if (pos <= 0)
			throw new IllegalStateException("Illegal position");
		if (shapes[pos - 1] != null) {
			for (int i = pos - 1; i < (shapes.length - 1); i++) {
				shapes[i] = shapes[i + 1];
			}
			shapes[shapes.length - 1] = null;
		}
	}

	public boolean contains(String shapeName) {
		for (Shape shape : shapes) {
			if (shape != null && shape.getName().equals(shapeName)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasPrev() {
		return pos > 0;
	}

	public Shape prev() {
		if (!hasPrev()) {
			throw new NoSuchElementException("No previous element exists.");
		}
		return shapes[--pos];
	}
}