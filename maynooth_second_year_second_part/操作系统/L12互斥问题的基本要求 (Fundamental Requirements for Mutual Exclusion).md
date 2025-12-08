####  **互斥问题的基本要求 (Fundamental Requirements for Mutual Exclusion)**

一个正确的互斥解决方案必须满足以下三个条件 ： A correct solution to the mutual exclusion problem must satisfy the following three conditions:



- 互斥性 (Mutual Exclusion):

   任何时候都不能有两个或以上的线程同时在临界区内执行 

  。No two threads can be able to execute simultaneously in the critical code section.

- 前进性 (Progress):

   任何在临界区之外运行的线程，都不能阻止其他希望进入临界区的线程进入 

  。A thread operating outside the critical section cannot prevent another thread from entering the critical section.

- 有限等待 (Bounded Waiting):

   一旦一个线程表示希望进入临界区，必须保证它能在有限的时间内成功进入 

  。Once a thread has indicated its desire to enter a critical section, it is guaranteed that it may do so in a finite time

  .

#### **2. 软件算法解决方案 (Software Algorithmic Solutions)**

##### **双线程解决方案 (Two-Thread Solution)**

- 思想 (Idea):

   结合了“轮转法”（使用

  ```
  turn
  ```

  变量）和“标记法”（使用

  ```
  flag
  ```

  数组）来避免死锁和锁步执行问题 

  。It's a combination of the "turn" variable approach and the "flag" array approach to avoid deadlock and lockstep execution

  .

- 实现 (Implementation):

  

  ```
  // 线程i的进入区代码
  flag[i] = true;      // 表示自己想进入
  turn = j;            // 谦让，让对方先请
  while (flag[j] && (turn == j)); // 如果对方也想进，并且轮到对方，则等待
  ```

- 保证 (Guarantees):

  - 互斥性:

     如果双方都想进入，

    ```
    turn
    ```

    变量的值是唯一的，能决定谁先进入 。

  - 前进性:

     决策只涉及那些希望进入临界区的线程 。

  - 有限等待:

     线程通过设置

    ```
    turn=j
    ```

    来让步，确保等待的线程不会被无限期推迟 。
  

##### **N线程解决方案：面包店算法 (N-Thread Solution: The Bakery Algorithm)**

- 思想 (Idea):

   模拟面包店排队叫号。每个想进入临界区的线程获取一个号码牌，号码最小的线程优先进入 

  。 It mimics a bakery's queuing system. 

  Each thread wanting to enter the critical section gets a ticket number, and the thread with the lowest number goes first

  .

- 号码牌规则 (Ticket Rules):

  - 线程选择一个比当前所有号码都大的号码 。

  - 如果号码牌冲突，则线程ID（进程ID）小的优先 。

- 性质 (Properties):

   这种机制保证了服务顺序，因此满足“有限等待” 。

  This mechanism guarantees a service order, thus satisfying "Bounded Waiting".


#### **3. 软件方案在现代硬件上的挑战 (Challenges for Software Solutions on Modern Hardware)**

- 缓存一致性问题 (Cache Coherence Problem):

  - 在多处理器系统中，每个处理器都有自己的缓存（Cache），这会导致共享变量（如 

    ```
    flag
    ```

    , 

    ```
    turn
    ```

    ）存在多个副本 

    。

  - 一个处理器对缓存中数据的修改，可能不会立即对其他处理器可见 

    。

  - 这会导致其他线程基于过时的（obsolete）数据做出错误判断，从而破坏互斥性 

    。

  - 要使算法正确工作，所有线程必须对共享数据结构有“一致的视图” 

    。

#### **4. 硬件辅助解决方案 (Hardware-Assisted Solutions)**

由于纯软件算法在现代硬件上的局限性，需要硬件提供支持。

- **禁用中断 (Disabling Interrupts):**

  - 在单处理器系统上，通过禁用中断可以阻止当前线程被切换，从而使其“测试-设置”操作变为原子操作 

    。

  - 但在多处理器系统上，同时禁用所有处理器的中断是低效且不切实际的 

    。

- **原子指令 (Atomic Instructions):**

  - 更安全的方式是使用特殊的处理器指令，这些指令可以原子地（不可中断地）完成“读-修改-写”操作 

    。

  - Test-and-Set:

     一条指令完成“读取一个内存地址的旧值，并写入一个新值” 

    。在执行期间，该内存位置会被锁定，防止其他处理器访问 

    。

    - 用法:

       

      ```
    while (test-and-set(lock, true)) { /* 循环等待，直到获得锁 */ }
      ```
      
       

      。

  - 

    Swap (or xchg):

     一条指令原子地交换两个内存地址的内容 

    。

    - 用法:

      ```
      do { swap(lock, key); } while (key == true);
      ```
  
       

      。

#### **5. 忙等待问题 (The Problem of Busy Waiting)**

- 定义 (Definition):

   上述许多解决方案都采用了“忙等待”（也称“自旋锁” Spinlock）。即当一个线程无法进入临界区时，它会在一个循环中持续不断地检查锁的状态，而不是被挂起 

  。 Many of the above solutions involve "Busy Waiting" (also called "Spinlock"). 

  A thread, unable to enter the critical section, continuously re-evaluates the lock condition in a loop instead of being suspended

  .

- 缺点 (Drawbacks):

  - 浪费CPU周期:

     忙等待会消耗宝贵的CPU时间，特别是当等待时间较长时非常低效 。

  - 不保证有限等待:

     基于原子指令的简单自旋锁通常不保证“有限等待”。哪个线程能抢到锁纯粹是“运气”问题，可能导致某个线程饥饿 

    。 The simple spinlock solutions do not guarantee "Bounded Waiting". 

    It is "pure luck" which thread manages to acquire the lock, potentially leading to **starvation**

    .

 Sources 