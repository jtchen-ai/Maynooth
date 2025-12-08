#### **先前互斥解决方案的局限性 (Limitations of Previous Mutual Exclusion Solutions)**

- **软件解决方案 (Software Solutions - e.g., Bakery Algorithm)**

  - 笨重且易错 (Unwieldy and Prone to Error):

     程序员使用这些算法来保护临界区时，代码复杂、笨重且容易出错 。

  - 多处理器失效 (Failure on Multiprocessors):

     在多处理器系统中，由于每个处理器都有本地缓存，可能导致各线程对共享数据结构的视图不一致（非线程安全），从而使算法失效 。

- **硬件自旋锁方案 (Hardware Spinlock Solutions - e.g., Test-and-Set)**

  - 不保证公平性 (No Fairness Guarantees):

    虽然能实现互斥，但不能保证“前进性”或“有限等待” （These provide a simple solution to mutual exclusion but do not 
    make any guarantees about progress or bounded waiting. ）。

    哪个线程能获取锁是任意的，取决于调度 （As the competing threads spin the lock, it is arbitrary (based on 
    scheduling) as to which thread may acquire it. ）。

  - 效率问题 (Inefficiency):

     硬件原子指令（如TestAndSet）在多处理器系统上执行效率低于可以使用本地缓存的指令 （Hardware TestandSet operations are also less efficient for multiple  processor systems to execute than instructions which can use the 
  local caches and it is desirable to minimise the need for their execution.）。
  
- **忙等待 (Busy Waiting)**

  - 定义:

     两种方案都存在忙等待问题，即线程在一个循环中持续测试锁条件，直到条件满足 （）。

  - 缺点 (Disadvantages):

    - 浪费处理器时间 (Wastage of processor time):

       导致系统性能下降，尤其是在等待时间较长的情况下  System performance degrades due to a wastage of processor time  reevaluating conditions repeatedly.。

    - 优先级反转 (Priority Inversion):

       可能发生低优先级任务持有锁，导致高优先级任务被迫等待的情况（Timed waiting - It is not possible to implement a Bounded  Waiting primitive for a lock where a thread might want to wait for a certain period of time only.） 。

    - 无法实现定时等待 (Cannot implement Timed Waiting):

       无法实现一个让线程只等待特定时间的锁 。（Priority inversion can occur where a lower priority task in the 
critical section may hold a lock which causes a higher priority task to wait.）

#### **2. 信号量简介 (Introduction to Semaphores)**

- 目的 (Purpose):

   信号量是一种更方便、更高效的同步结构，旨在解决代码笨重、访问不公和忙等待的问题 。

- 定义 (Definition):

   信号量是一个通用的同步工具，由一个共享整数变量和两个专门的原子操作

  ```
P
  ```
  
  和

  ```
V
  ```
  
  组成 

  。

- 

  命名 (Naming):

   由Dijkstra在1965年引入，

  ```
  P
  ```

  来自荷兰语

  ```
  proberen
  ```

  （测试），

  ```
  V
  ```

  来自

  ```
  verhogen
  ```

  （增加）

  。

#### **3. 信号量操作与类型 (Semaphore Operations and Types)**

- **经典操作 (Classical Operations):**

  - 

    ```
    P(S)
    ```

    : 

    ```
    while (S <= 0) { } S = S - 1;
    ```

     (等待，直到S > 0，然后将其减一) 

    。

  - 

    ```
    V(S)
    ```

    : 

    ```
    S = S + 1;
    ```

     (将S加一) 

    。

  - 

    关键要求 (Key Requirement):

     

    ```
    P
    ```

    和

    ```
    V
    ```

    操作都必须是

    不可分割的（原子的）

    

    。

  - 

    注意:

     这个经典定义仍然包含

    忙等待

    循环 

    。

- **信号量类型 (Types of Semaphores):**

  - 二进制信号量 (Binary Semaphore):

     值只能是0或1。**用作互斥锁（Mutex）**。

  - 计数信号量 (Counting Semaphore):

     值可以是一个非负整数。用于允许多个（例如n个）线程同时进入某个区域（速率限制），或作为线程间的信令机制 。


#### **4. 信号量的实现 (Implementation of Semaphores)**

- **保证原子性 (Ensuring Atomicity):**

  - 单处理器:

     可以将

    ```
P和V
    ```
    
    实现为操作系统调用，并在其执行期间关闭中断 

    。

  - 
  
    多处理器 可以使用硬件自旋锁（如TestAndSet）来保护

    P和V

    代码的执行。因为P/V代码很短，所以这种方式的性能影响被认为是最小的 
  
    。

- **无忙等待的实际实现 (Practical Implementation without Busy Waiting):**

  - 为了克服忙等待，可以将信号量与一个等待队列关联。

  - `P(S)`操作:

    1. 将信号量的值减一 。

    2. 如果结果值小于0，则将当前线程加入等待队列并

       阻塞

       （挂起）该线程 。

  - `V(S)`操作:

    1. 将信号量的值加一 。

    2. 如果结果值小于或等于0（意味着有线程在等待），则从等待队列中

       唤醒

       一个线程 。

  - 关键要求:

     这种改进的

    ```
    P
    ```
    
    和

    ```
    V
    ```
    
    操作本身也必须是
  
    原子的

     

    。

#### **5. 信号量的使用 (Usage of Semaphores)**

- **实现互斥 (Mutual Exclusion):**

  - 使用一个二进制信号量

    ```
    mutex
    ```

    ，并将其初始值设为

    ```
    1
    ```

    （表示锁可用）

    。

  - 代码结构:

    ```
    P(mutex); /* 临界区 */; V(mutex);
    ```

     

    。

- **实现通用同步 (General Synchronization):**

  - 用于使一个线程（如线程2）等待另一个线程（如线程1）完成某个动作 

    。

  - 使用一个信号量

    ```
    S
    ```

    ，并将其初始值设为

    ```
    0
    ```

    （表示初始时需要等待）

    。

  - 代码结构:

    - 线程1: 

      ```
      ...动作S1...; V(S);
      ```

       

      。

    - 线程2: 

      ```
      P(S); ...动作S2...;
      ```

       

      。

#### **6. 可用信号量解决的经典问题 (Classical Problems Solved by Semaphores)**

信号量可用于解决多种经典的协调问题 ，例如：



- 生产者-消费者问题 (Producer Consumer Problem) 

  。

- 哲学家就餐问题 (Dining Philosophers Problem) 

  。

- 读者-写者问题 (Readers Writers Problem)