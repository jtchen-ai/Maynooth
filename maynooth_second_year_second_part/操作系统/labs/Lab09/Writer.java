public class Writer extends Thread {

// All threads which use the data being synchronised should 
// share the same DataAccessPolicyManager object to 
// coordinate access. The instance could be passed in to the 
// constructor for the Reader class.

private DataAccessPolicyManager accessManager;


   public Writer (DataAccessPolicyManager accessManager) {
   	this.accessManager = accessManager;
   }

   public void run() {
	while (true) {

	   // Acquire writelock
	
           // Print message "writer acquired write lock"

	   // Simulate writing with sleep

	   // Print message "writer finished, releasing write lock

	   // Release writelock

	   // Sleep before repeating the loop
	}

   }
}
