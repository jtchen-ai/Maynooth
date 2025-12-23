/**
 * 
 */

/**
 * @author dkelly
 *
 */
import java.util.Date;
public class Producer implements Runnable {
	private Buffer buffer;

	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		Date message;
		
		while (true) {
			message = new Date(); // produce an item
			try {
				Thread.sleep(3000); // Sleep for 1000 ms
			} catch (InterruptedException e){}
			buffer.insert(message);
			System.out.println("inserted:"+message.toString());
		}
	}
}