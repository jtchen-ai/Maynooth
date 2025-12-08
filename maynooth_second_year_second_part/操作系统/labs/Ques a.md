**What is the difference between the string processing function `scanf()` and the system call function `read()`?**

- `scanf()`:
  - Is a C standard library function (part of `stdio.h`).
  - It's used for formatted input from `stdin` (or other streams specified by `fscanf`).
  - It parses input based on a format string (e.g., `%d`, `%s`, `%f`) and can convert input into different data types.
  - It is typically buffered, meaning it might read a block of data into a buffer and then process it.
  - It often stops reading for certain format specifiers (like `%s`) when it encounters whitespace.
- `read()`:
  - Is a POSIX standard system call (part of `unistd.h`).
  - It's used to read a raw stream of bytes from a file descriptor.
  - It does not interpret the data's format; it simply reads a specified number of bytes from the kernel into a user-supplied buffer.
  - It is generally unbuffered (or more accurately, it interacts directly with the kernel's buffers, and user-level buffering isn't directly controlled unless using standard I/O wrappers).
  - It returns the number of bytes actually read.

**What does the numeric value returned by the `read()` function represent?**

- The read()

   function returns an integer value that represents:

  - **A positive number**: The number of bytes successfully read. This value might be less than the number of bytes requested (e.g., if the end of the file was reached before reading the requested count, or if reading from a pipe with insufficient data).
  - **Zero (0)**: Indicates that the end of the file (EOF) has been reached. For pipes or sockets, this usually means the writing end has been closed.
  - **Minus one (-1)**: Indicates that an error occurred. The specific error code is stored in the global variable `errno`, and `perror()` can be used to print a human-readable error message.

**What is a process descriptor table?**

- A process descriptor table (more commonly referred to as a file descriptor table) is a data structure maintained by the operating system kernel for each process.
- This table keeps track of all the files, pipes, sockets, and other I/O resources that the process has opened.
- Each entry in the table is a file descriptor (a small, non-negative integer, like 0, 1, 2, ...).
- Each file descriptor points to an entry in a system-wide open file table, which contains information about the open file (like the current file offset, access modes, etc.) and a pointer to the actual file (i-node) in the file system.
- For example, file descriptor 0 is typically standard input (stdin), 1 is standard output (stdout), and 2 is standard error (stderr).

**What is the purpose of the `open()` system call?**

- The primary purpose of the `open()` system call is to open or create a file (or device) and return a file descriptor for that file.
- This file descriptor can then be used by other I/O system calls (like `read()`, `write()`, `close()`, `lseek()`, etc.) to perform operations on the file.
- The `open()` call allows specifying the file name, the mode in which to open it (e.g., read-only, write-only, read-write, append), and permissions if a new file is being created.

**Why do you need to use the `close()` system call?**

- Using the close()

   system call is important for several reasons:

  - **Resource Release**: Each process has a limit on the number of file descriptors it can have open. Closing a file descriptor when it's no longer needed releases it back to the process's pool of available descriptors, allowing it to be reused by subsequent `open()` calls.
  - **Ensuring Data Integrity**: For output files, `close()` often ensures that any data buffered in memory is flushed (written) to the disk. If a file is not closed, some data might be lost.
  - **Updating File Metadata**: Closing a file allows the operating system to update file metadata, such as the last modification time.
  - **Allowing File Deletion/Unmounting**: On some systems, a file cannot be deleted, or its filesystem cannot be unmounted if it is still open by a process.
  - **Pipe and FIFO Communication**: When using pipes for inter-process communication, properly closing the unused ends of the pipe (e.g., the writer closes its read end, the reader closes its write end) is crucial for correctly detecting EOF (when the write end is closed) or avoiding deadlocks.





## Ques a

cc p4a.c -o p4a
./p4a



![image-20250516144235331](C:\Users\LENOVO\AppData\Roaming\Typora\typora-user-images\image-20250516144235331.png)

## Ques b

cc p4b.c -o p4b
./p4b

![image-20250516144409411](C:\Users\LENOVO\AppData\Roaming\Typora\typora-user-images\image-20250516144409411.png)

## Ques c

cc p4c.c -o p4c
./p4c![image-20250516144442145](C:\Users\LENOVO\AppData\Roaming\Typora\typora-user-images\image-20250516144442145.png)

## Ques d

cc p4d.c -o p4d
./p4d![image-20250516144521778](C:\Users\LENOVO\AppData\Roaming\Typora\typora-user-images\image-20250516144521778.png)

## Ques e

cc p4e.c -o p4e
./p4e![image-20250516144605987](C:\Users\LENOVO\AppData\Roaming\Typora\typora-user-images\image-20250516144605987.png)

## Ques f

cc p4f.c -o p4f
./p4f![image-20250516144626714](C:\Users\LENOVO\AppData\Roaming\Typora\typora-user-images\image-20250516144626714.png)

## Ques g 

cc p4g.c -o p4g
./p4g![image-20250516144655128](C:\Users\LENOVO\AppData\Roaming\Typora\typora-user-images\image-20250516144655128.png)

## Ques h 

cc p4h.c -o p4h
./p4h![image-20250516144720381](C:\Users\LENOVO\AppData\Roaming\Typora\typora-user-images\image-20250516144720381.png)