# memento

### 第一部分：现实困境与模式的提出

在软件开发中，我们经常遇到需要“后悔药”的场景，比如撤销（Undo）、事务回滚（Rollback）或者游戏存档。

#### 1. 原文解析：核心矛盾

> 原文引用
>
> \* "In your application, you may need to support "undo" operations. To achieve this, you need to record the internal state of an object." 1* "But in general, objects encapsulate their states, and those states are inaccessible to the outer world. So, if you expose the state information, then you violate encapsulation." 2

> **完整翻译**
>
> - “在你的应用程序中，你可能需要支持‘撤销’操作。为了实现这一点，你需要记录对象的内部状态。”
> - “但是通常情况下，对象封装了它们的状态，而这些状态对外部世界是不可访问的。因此，如果你暴露了这些状态信息，你就破坏了封装性。”

#### 2. 资深学者讲解：这是为了解决什么现实问题？

现实开发场景：

想象你在开发一个 绘图应用程序（参考课件提及的 Computer Example 3）。用户正在画布上移动一个图形。

- **状态 $S_t$**：图形当前坐标 $(x=100, y=100)$。
- **操作**：用户将其移动到 $(200, 200)$，状态变为 $S_{t+1}$。
- **需求**：用户按下了 `Ctrl+Z`（撤销），系统需要将图形恢复到 $S_t$。

**面临的“两难”困境（The Dilemma）：**

1. **方案 A（破坏封装）：** 为了保存 $S_t$，外部的“撤销管理器”需要读取图形对象的私有变量（如 `private int x, y`）。为了让管理器能读到，你必须把这些变量改为 `public`，或者提供全局的 getter/setter。
   - *后果*：对象的安全性丧失了。任何其他代码都可以随意修改图形的坐标，导致代码变得脆弱、难以维护。这违反了面向对象设计的核心原则——**封装（Encapsulation）**。
2. **方案 B（保持封装）：** 你坚持将 `x, y` 设为 `private`。
   - *后果*：外部的“撤销管理器”无法读取当前状态，也就无法保存快照。当用户想撤销时，系统无能为力。

备忘录模式的解决方案：

备忘录模式（Memento）就是为了解决这个矛盾而生的。它通过引入一个第三方的“胶囊”（Memento 对象），允许原对象（Originator）将自己的秘密装进胶囊里交给外部保管，但外部拿着这个胶囊却无法看到里面的秘密，只有原对象自己能打开它。

------

### 第二部分：结合 UML 图细讲解决方案

这部分我们将深入分析课件中的 Figure 19-1 类图 4444，这是理解该模式如何工作的数学模型。

![image-20251209145329042](D:\Typora\img\image-20251209145329042.png)

#### 1. UML 结构分析与专业描述

课件中展示了一个简化的 UML 图，包含三个核心角色：

1. **Originator (发起人)**

   - **对应类**：`Originator` 5

     

     

   - **职责**：这是拥有内部状态（`stateId`）的对象，也是我们需要“存档”的对象。

   - **关键方法**：

     - `saveMemento(int): Memento` 6：创建一个包含当前状态快照的备忘录。

       

       

     - `revertMemento(Memento): void` 7：接收一个备忘录，并使用其中的数据恢复自身状态。

       

       

2. **Memento (备忘录)**

   - **对应类**：`Memento` 8

     

     

   - **职责**：这是一个“黑箱”，负责存储 `Originator` 的内部状态。

   - 

     **关键属性**：`stateId: int` 9。注意，在理想设计中，这个属性对外部世界应该是不可见的。

     

     

3. **Caretaker (负责人/管理者)**

   - 

     **对应类**：`MementoPatternExample` (在课件示例中充当了 Client/Caretaker 的角色) 10

     

     

   - **职责**：负责保存好 `Memento`，但**不能**对 `Memento` 的内容进行操作或检查。

#### 2. 流程与逻辑讲解（形象化理解）

让我们把这个 UML 映射到上述的“绘图应用”场景：

- **Step 1: 创建快照 (save)**
  - **代码行为**：`MementoPatternExample` (用户/主程序) 想要存档。它调用 `Originator.saveMemento()`。
  - **内部逻辑**：`Originator` 创建一个新的 `Memento` 实例，把自己私有的 `stateId` 塞进去。
  - **形象比喻**：你（Originator）把你的日记（State）锁进一个保险箱（Memento），然后把保险箱交给保管员（Caretaker）。保管员拿着保险箱，但他没有钥匙，看不了你的日记。
- **Step 2: 恢复状态 (revert)**
  - **代码行为**：用户后悔了。`MementoPatternExample` 调用 `Originator.revertMemento(memento)`，把之前保存的那个对象传回去。
  - **内部逻辑**：`Originator` 拿到 `Memento`，它（且只有它）知道如何从里面读取 `stateId`，并将自己的状态恢复。
  - **形象比喻**：保管员把保险箱还给你。你有钥匙，你打开保险箱，读到了以前的日记，恢复了记忆。

#### 3. 为什么这个 UML 结构能解决问题？

请注意 UML 中的继承/依赖关系：

- 

  **依赖关系 (Dependency)**：`Originator` $\dashrightarrow$ `Memento` 11。这表示 `Originator` 依赖于 `Memento` 来存储状态。

  

  

- **封装的艺术**：

  - 在标准的备忘录模式实现中，`Memento` 类通常被设计为 **窄接口 (Narrow Interface)** 给 Caretaker 看（只能传递，不能读取），而提供 **宽接口 (Wide Interface)** 给 Originator 看（可以读取内部数据）。
  - 课件中的 Q&A 提到了这一点，我们在下文详细分析。

------

### 第三部分：Q&A 环节深度剖析

课件最后的 Q&A 环节非常有价值，它触及了这个模式的本质和实现细节。我将针对其中几个关键问题进行解读，帮助你理解为什么这个模块能生效。

#### 问题 1：Getter 方法是否破坏封装？

> 原文引用
>
> "But you are still using the getter method getStateld(). Does it not violate the encapsulation?" 12

解析：

这是一个非常敏锐的问题。在课件的简单示例 13 中，Memento 类有一个 public 的 getStateId()。

- **如果** `MementoPatternExample` (Caretaker) 也能调用 `getStateId()`，那么封装确实被破坏了！因为保管员能偷看日记了。

- 

  **解决方案**（参考 14）：

  

  

  - **包级私有 (Package-Private)**：在 Java 中，如果将 `Memento` 和 `Originator` 放在同一个包下，并将 `stateId` 或其 getter 设为默认访问权限（即不加 `public`），那么只有同包的 `Originator` 能访问，而包外的 Caretaker 无法访问。
  - **内部类 (Inner Class)**：更高级的做法是将 `Memento` 定义为 `Originator` 的私有内部类。这样，`Memento` 对外部世界完全不可见，只有 `Originator` 能实例化和访问它。这是最完美的封装。

#### 问题 2：多重状态与多线程 (Multiple Restore Points & Multithreading)

> 原文引用
>
> "I can restore the previous snapshot/restore point. But in a real life scenario, I may have multiple restore points... Can you observe any impact due to multithreading?" 15

**解析**：

- **多重快照**：现实中我们通常用 `Stack` (栈) 数据结构来存储一系列的 `Memento` 对象。`push` 用于保存新状态，`pop` 用于撤销。这正是 `Ctrl+Z` 多次撤销的原理。
- **多线程隐患**：如果多个线程同时操作同一个 `Originator`，可能会导致状态保存的不一致（脏读/脏写）。虽然 Memento 对象本身一旦创建通常是不可变的（Immutable），但创建 Memento 的那一瞬间必须保证 `Originator` 的状态是稳定的（通常需要加锁）。

#### 问题 3：深拷贝 (Deep Copy) 的必要性

> 原文引用
>
> "You talked about deep copy after the first implementation. Why do I need that?" 16

**解析**：

- 如果 `Originator` 的状态不仅仅是一个简单的 `int stateId`，而是一个引用对象（比如 `List<String> text`），仅仅复制引用（浅拷贝）是不够的。
- 如果不做深拷贝，`Memento` 里存的只是指向内存中同一个 List 的指针。当 `Originator` 修改 List 内容时，`Memento` 里的内容也会跟着变（因为它指向同一个东西）。这样“存档”就失去了意义。
- **结论**：在生成 Memento 时，必须对引用类型的状态进行**深拷贝**，确保快照与当前状态彻底解耦。

------

### 总结

这份课件通过 Memento Pattern 解决了一个极其具体的软件工程难题：**如何在不暴露对象私有细节的情况下实现“撤销”功能。**

它通过将状态管理的责任分离：

1. **Originator** 负责生产数据。
2. **Memento** 负责封装数据（像一个加密的信封）。
3. **Caretaker** 负责保管信封，但绝不拆信。

下一步建议：

如果你需要，我可以为你生成一段符合课件描述的、使用了内部类（解决封装问题）和栈结构（解决多步撤销问题）的 Java 代码示例。你想看这个具体的实现吗？