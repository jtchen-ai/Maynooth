#include <fcntl.h> 
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

main(int argc, char *argv[]) { 
   int namedpipe; 
   int max_size = 20;
   char message[max_size+1]; 
   char buffer[max_size+1];
   bool paramerror= false;

/* Check for existence of named pipe and create it if it doesn't exist */


/* Check command line parameter count argc, it should be equal to 2.
   By convention, the command itself is counted as a parameter and placed in argv[0], the first argument string. 
   The first command line parameter supplied with the command is the second argument string, in argv[1].
   Check if argv[1] parameter is either "reader" or "writer" and open the pipe accordingly. */

/* if there are no command line errors or pipe opening errors then do the reader or writer actions */

 
}

