

**1. 我注意到单例模式和享元模式之间有一些相似之处。你能强调它们之间的主要区别吗？**

**答：** 单例模式帮助你在系统中只维护一个**必需的**对象。换句话说，一旦创建了所需的对象，就不能再创建更多。你需要重用现有的对象。

享元模式通常关注**大量**相似的（可能是重量级的）对象，因为它们可能占用大量内存。所以，你尝试创建一组较小的模板对象，可以在运行时配置它们以完成重量级对象的创建。这些较小的、可配置的对象被称为享元（flyweights）。你可以在应用程序中重用它们，使其看起来像是有许多大对象。这种方法有助于减少大块内存的消耗。基本上，享元模式让一个对象看起来像许多个。

这就是为什么 GoF（设计模式四人帮）告诉我们：享元是一个共享对象，可以在多个上下文中同时使用。享元在每个上下文中充当一个独立的对象——它与未共享对象的实例没有区别。



**2. 你能观察到多线程带来的任何影响吗？**

**答：** 如果你在多线程环境中使用 `new` 运算符创建对象，你可能会最终得到多个不需要的对象（类似于单例模式中的情况）。补救措施类似于你在单例模式中处理多线程环境的方式。



```
import java.util.HashMap;
import java.util.Map;

// 享元工厂
class UnsafeRobotFactory {
    // 缓存池
    private Map<String, Robot> robotCache = new HashMap<>();

    // ❌ 这是一个不安全的方法
    public Robot getRobot(String color) {
        // 1. 检查缓存中是否已有该颜色的机器人
        if (robotCache.get(color) == null) {
            
            // --- 假设线程 A 执行到这里被挂起（CPU切换） ---
            // --- 线程 B 此时进来了，它也发现 cache.get(color) 是 null ---
            
            // 2. 如果没有，就创建一个新的（使用 new 运算符）
            System.out.println("创建新的机器人: " + color);
            Robot newRobot = new ConcreteRobot(color);
            
            // 3. 放入缓存
            robotCache.put(color, newRobot);
        }
        
        return robotCache.get(color);
    }
}
```



**3. 使用享元设计模式的优点是什么？**

**答：**

- 你可以减少那些可以被相同控制的重量级对象的内存消耗。
- 你可以减少系统中“完整但相似对象”的总数。
- 你可以提供一种集中机制来控制许多“虚拟”对象的状态。

**4. 使用享元设计模式相关的挑战是什么？**

**答：**

- 在这种模式中，你需要花时间来配置这些享元。配置时间可能会影响应用程序的整体性能。
  - 解释：享元对象（type）本身是不完整的，它只有颜色和名字，没有坐标。每次你想用它，必须把外部状态（x, y）传给它。这就像你有一把公用的枪（享元），每次开枪前，你都得手动给它装子弹（配置外部状态）。
- 为了创建享元，你需要从现有对象中提取一个通用的模板类。这一层额外的编程可能会很棘手，有时难以调试和维护。
- 你会发现一个类的逻辑实例无法表现得与其他实例不同（注：指共享的内部状态部分）。
  - 你无法做到“只让 tree1 掉叶子，而 tree2 保持茂盛”，除非你把“掉叶子”这个状态移出享元，变成外部状态。这限制了单个对象的独立性。
- 享元模式通常与单例工厂实现结合使用，为了守护单一性，需要额外的成本（例如，你可能会选择同步方法或双重检查锁定，但它们每一个都是昂贵的操作）。



```
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

// ---------------------------------------------------------
// 1. 享元对象 (Flyweight / 模板)
// 提取出的“通用模板类”，只包含内部状态（共享数据）
// ---------------------------------------------------------
class TreeType {
    private String name;  // 内部状态：名字
    private Color color;  // 内部状态：颜色
    // 假设这里还有很重的 3D 模型数据...

    public TreeType(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    // 核心业务方法
    // 注意：这里需要传入外部状态 (x, y) 才能工作
    public void draw(int x, int y) {
        System.out.println("在坐标 (" + x + "," + y + ") 绘制一棵 " + color + " 的 " + name);
    }
    
    // 为了演示第3点，假设我们允许修改颜色
    public void setColor(Color color) {
        this.color = color;
    }
}

// ---------------------------------------------------------
// 2. 享元工厂 (Factory)
// ---------------------------------------------------------
class TreeFactory {
    private static Map<String, TreeType> cache = new HashMap<>();

    // 【对应挑战 4】：为了守护单一性，这里必须加锁
    // synchronized 导致每次获取对象都要排队，这是一个昂贵的操作
    public static synchronized TreeType getTreeType(String name, Color color) {
        if (!cache.containsKey(name)) {
            cache.put(name, new TreeType(name, color));
        }
        return cache.get(name);
    }
}

// ---------------------------------------------------------
// 3. 逻辑对象 (Client Context)
// 实际上这是我们在代码里操作的“虚拟”树
// ---------------------------------------------------------
class Tree {
    private int x; // 外部状态
    private int y; // 外部状态
    private TreeType type; // 引用享元对象

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // 【对应挑战 1】：运行时配置
    // 每次绘制，都需要把自己的外部状态 (x,y) "组装" 进享元里
    public void draw() {
        type.draw(x, y); 
    }
}

// ---------------------------------------------------------
// 主程序演示
// ---------------------------------------------------------
public class FlyweightChallengesDemo {
    public static void main(String[] args) {
        // 创建大量树
        // 【对应挑战 2】：编程复杂性。
        // 如果不用模式，直接 new Tree(x,y,name,color) 就行了。
        // 现在由于拆分了类，我得先找工厂拿类型，再组装对象，代码量增加了。
        TreeType oakType = TreeFactory.getTreeType("橡树", Color.GREEN);
        
        Tree tree1 = new Tree(10, 20, oakType);
        Tree tree2 = new Tree(30, 40, oakType);
        
        // --- 演示挑战 1：配置时间 ---
        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            // 每次调用 draw()，CPU 都要做一次“参数传递”和“状态结合”
            // 如果不使用享元，对象自身就包含数据，可能直接读取即可，不用跨对象调用
            tree1.draw(); 
        }
        long end = System.nanoTime();
        System.out.println("运行时配置耗时...");

        // --- 演示挑战 3：逻辑实例无法表现得不同 ---
        System.out.println("\n--- 修改前 ---");
        tree1.draw(); // 绿色的橡树
        tree2.draw(); // 绿色的橡树
        
        // 假设我想把 tree1 这棵树变成枯黄色的（秋天到了），
        // 但我不小心直接改了 type（共享部分）
        System.out.println("\n--- 修改共享状态后 ---");
        oakType.setColor(Color.YELLOW); 
        
        tree1.draw(); // tree1 变黄了
        tree2.draw(); // 糟糕！！tree2 也变黄了！
        // 结论：tree1 无法拥有“自己独特的”颜色属性，因为它没有自己的颜色字段。
        // 它只是共享了 oakType 的颜色。要改都得改，要么就得新建一个 Type。
    }
}
```



**5. 我可以拥有不可共享的享元接口吗？**

**答： 是的。享元接口并不强制要求它必须总是可共享的。在某些情况下，你可能有不可共享的享元，其子项是具体的享元对象。在我们的例子中，你看到了使用固定大小机器人（fixed-size robots）的不可共享享元。**

**6. 既然享元的内部数据是相同的，我可以共享它们。这正确吗？**

**答：** 是的。

**7. 客户端如何处理这些享元的外部数据？**

**答：** 它们需要将信息（状态）传递给享元。客户端要么管理这些数据，要么在运行时计算它们。

**8. 外部数据是不可共享的。这正确吗？**

**答：** 是的。

**9. 你说我应该尝试使内部状态不可变。我该如何实现这一点？**

答： 是的，为了线程安全和安全性，专家建议你实现这一点。在这个案例中，它已经实现了。在 Java 中，你必须记住 String 对象本质上是不可变的。

此外，你可能会注意到在具体的享元（SmallRobot, LargeRobot, FixedSizeRobot）中，没有 setter 方法来设置/修改 robotTypeCreated 的值。当你仅通过构造函数提供数据且没有 setter 方法时，你就在遵循一种促进不可变性的方法。

**10. 你在内部状态 `robotTypeCreated` 上标记了 `final` 关键字以实现不可变性。这正确吗？**

答： 你需要记住 final 和不可变性不是同义词。在设计模式的背景下，不可变性这个词通常意味着一旦创建，你就不能改变对象的状态。虽然关键字 final 可以应用于类、方法或字段，但目的不同。

final 字段可以帮助你构建一个**无需同步的线程安全不可变对象**，并且它在多线程环境中提供了安全性。所以，我在这个例子中使用了它。



```
import java.util.ArrayList;
import java.util.List;

class UnsafeRobot {
    // 这里使用了 final，很多人以为这就“不可变”了
    private final List<String> parts = new ArrayList<>();

    public UnsafeRobot() {
        parts.add("左手");
    }

    public List<String> getParts() {
        return parts;
    }
}

public class Demo {
    public static void main(String[] args) {
        UnsafeRobot robot = new UnsafeRobot();
        
        // 1. 尝试修改引用：报错，这是 final 保证的
        // robot.parts = new ArrayList<>(); // 编译错误！❌
        
        // 2. 尝试修改内部数据：成功，这是 final 管不了的
        System.out.println("修改前: " + robot.getParts()); 
        
        // 既然获取到了引用，我就可以修改里面的内容
        robot.getParts().add("非法武器"); 
        
        System.out.println("修改后: " + robot.getParts()); 
        // 结果：对象的状态被改变了！所以它不是“不可变对象”。
    }
}
```



```
// 享元对象
class ConcreteRobot {
    // 1. 使用 final 确保初始化后引用不乱跑
    // 2. String 本身是不可变的，这很关键
    private final String robotTypeCreated;

    // 构造函数：唯一一次赋值的机会
    public ConcreteRobot(String type) {
        this.robotTypeCreated = type;
    }

    // 只提供“读”方法，不提供“写”方法
    public String getType() {
        return robotTypeCreated;
    }

    // 业务方法
    public void showMe(String externalState) {
        System.out.println("我是 " + robotTypeCreated + "，当前颜色: " + externalState);
    }
}

public class FlyweightDemo {
    public static void main(String[] args) {
        ConcreteRobot sharedRobot = new ConcreteRobot("高达");

        // 1. 无法修改引用
        // sharedRobot.robotTypeCreated = "扎古"; // 编译错误

        // 2. 无法修改内部数据
        // String 没有提供任何修改自身内容的方法（比如没有 append）
        // sharedRobot.getType().??? // 做不到
        
        // 结论：无论多少个线程同时拿到 sharedRobot，
        // 它们看到的永远都是一个状态稳定的“高达”。
    }
}
```





**11. 这里的 `getRobotFromFactory()` 方法是同步的，以提供线程安全。这种理解正确吗？**

**答：** 完全正确。在单线程环境中，这是不需要的。

**12. 这里的 `getRobotFromFactory()` 方法是静态的。这是强制性的吗？**

**答：** 不。你也可以实现非静态工厂方法。你可能会经常注意到单例工厂与享元模式实现同时存在。

**13. 在这个实现中，“RobotFactory”的角色是什么？**

**答：** 它**缓存享元并提供获取它们的方法**。在这个例子中，有许多对象可以共享。所以，将它们存储在一个中心位置总是一个好主意。