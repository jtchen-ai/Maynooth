
public class BoundedBufferSimulation {
	public static void main (String args[]) {
		Buffer buffer = new Buffer();

		// Create one producer and one consumer process
		Thread producer1 = new Thread(new Producer(buffer));
		Thread consumer1 = new Thread(new Consumer(buffer));
	
		producer1.start();
		consumer1.start();
	}

}
