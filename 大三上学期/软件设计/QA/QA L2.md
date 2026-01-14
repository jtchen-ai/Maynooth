# QA L2



## **问题 1：使用原型设计模式有什么优势？**

回答：当创建一个类的实例过程很复杂或者很乏味时，能带来方便。

你可以在程序运行时加入或者丢弃产品。

```
import java.util.HashMap;
import java.util.Map;

// 1. 定义原型（实现了 Cloneable 的产品）
abstract class Shape implements Cloneable {
    public String type;
    
    // 核心：克隆方法
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

class Circle extends Shape {
    public Circle() { type = "Circle"; }
}

class Square extends Shape {
    public Square() { type = "Square"; }
}

// 2. 原型管理器（这就是那个“柜子”）
class ShapeCache {
    // 这个 Map 就是用来存样品的
    private static Map<String, Shape> shapeMap = new HashMap<>();

    // 【核心功能 1：加入产品】
    // 只要调用这个方法，工厂就学会了造这类新东西
    public static void loadCache(String shapeId, Shape newShape) {
        shapeMap.put(shapeId, newShape);
    }

    // 【核心功能 2：丢弃产品】
    // 只要调用这个方法，工厂就停止生产这类东西
    public static void removeCache(String shapeId) {
        shapeMap.remove(shapeId);
    }

    // 获取产品（通过克隆样品）
    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        // 如果柜子里有，就克隆一份给客户；如果没有，就返回空
        return (cachedShape != null) ? (Shape) cachedShape.clone() : null;
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        // 程序刚启动，柜子是空的，或者只有基本款
        ShapeCache.loadCache("1", new Circle());
        System.out.println("初始状态：我们要造一个圆形 -> " + ShapeCache.getShape("1").type);

        // --- 演示：运行时加入产品 ---
        // 假设程序跑了一半，用户突然定义了一个“方形”，我们直接把它注册进去
        // 注意：我们没有去修改 ShapeCache 类的源码，也没有重新编译
        System.out.println(">>> 正在动态引入新产品：方形...");
        ShapeCache.loadCache("2", new Square());

        // 现在工厂立刻就能造方形了
        Shape s2 = ShapeCache.getShape("2");
        System.out.println("成功造出了新加入的产品 -> " + s2.type);

        // --- 演示：运行时丢弃产品 ---
        // 假设圆形过时了，我们把它下架
        System.out.println(">>> 正在下架产品：圆形...");
        ShapeCache.removeCache("1");

        // 再尝试获取圆形
        Shape s1 = ShapeCache.getShape("1");
        if (s1 == null) {
            System.out.println("获取圆形失败，因为它已经被丢弃了。");
        }
    }
}
```

另外，你可以以更低的成本创建新实例。

**问题 2：使用原型设计模式有什么挑战？**

回答：每一个子类**都需要实现克隆或复制机制**。如果涉及的对象不支持复制或克隆，或者存在循环引用，实现克隆机制就会很有挑战性。例如在 Java 中，带有 clone 方法的类需要实现 Cloneable 标记接口，否则它会抛出 CloneNotSupportedException 异常。在这个例子中，我使用了执行浅拷贝的 clone 方法。按照惯例，我是通过调用 super.clone 来获取返回对象的。然后  文中提到了“循环引用”。 想象一下，对象 A 里面有个属性指向对象 B，而对象 B 里面又有个属性指向对象 A

## 问题 3：能详细说明一下浅拷贝和深拷贝的区别吗？

回答：浅拷贝会创建一个新对象，然后把原对象的各个字段值复制到新对象里。所以，它也被称为逐字段复制。如果原对象的字段里包含指向其他对象的引用，那么复制到新对象里的只是那些对象的引用，也就是说，你并没有创建那些被引用对象的副本。

让我们试着用一个简单的逻辑图来理解这个机制。假设我们要复制对象 X1，它有一个引用指向对象 Y1。再假设对象 Y1 有一个引用指向对象 Z1。

现在，对 X1 进行浅拷贝，创建了一个新对象 X2，它里面包含的引用仍然指向 Y1。

你已经在我们的实现中见过 clone 方法的用法了，它执行的就是浅拷贝。

对于 X1 的深拷贝，会创建一个新对象 X3。X3 有一个引用指向新对象 Y3，而 Y3 实际上是 Y1 的副本。同样，Y3 进而有一个引用指向另一个新对象 Z3，它是 Z1 的副本。

在深拷贝中，新对象和原对象是完全分离的。在一个对象里做的任何修改都不应该反应到另一个对象上。要在 Java 里创建深拷贝，你可能需要重写 clone 方法然后继续处理。另外，深拷贝成本比较高，因为你需要创建额外的对象。关于深拷贝的完整实现，本书第 19 章备忘录模式的问答环节会有介绍。

## 问题 4：什么时候该选择浅拷贝而不是深拷贝，反之亦然？

回答：浅拷贝速度更快，成本更低。如果你的目标对象只包含基本类型的字段，那通常最好用浅拷贝。深拷贝成本高且速度慢。但如果你的目标对象包含很多指向其他对象的引用字段，那深拷贝就很有用了。

## 问题 5：当我在 Java 中复制对象时，必须使用 clone 方法。这个理解对吗？

回答：不对。还有其他替代方案，其中之一是使用序列化机制。而且你总是可以定义自己的拷贝构造函数来使用。

## 问题 6：能给一个简单的例子演示用户自定义的拷贝构造函数吗？

回答：Java 不支持默认的拷贝构造函数。你可能需要自己编写。可以看看下面这个程序，它演示了这种用法。

演示

这里是演示代码。

Java

```
package jdp2e.prototype.questions_answers;
class Student
{
    int rollNo;
    String name;
    //Instance Constructor
    public Student(int rollNo, String name)
    {
        this.rollNo = rollNo;
        this.name = name;
    }
    //Copy Constructor
    public Student( Student student)
    {
        this.name = student.name;
        this.rollNo = student.rollNo;
    }
    public void displayDetails()
    {
        System.out.println(" Student name: " + name + ",Roll no: "+rollNo);
    }
}
class UserDefinedCopyConstructorExample {
    public static void main(String[] args) {
         System.out.println("***User defined copy constructor example in Java***\n");
        Student student1 = new Student(1, "John");
        System.out.println(" The details of Student1 is as follows:");
        student1.displayDetails();
        System.out.println("\n Copying student1 to student2 now");
        //Invoking the user-defined copy constructor
        Student student2 = new Student (student1);
        System.out.println(" The details of Student2 is as follows:");
        student2.displayDetails();
    }
}
```

输出

这里是输出结果。

User defined copy constructor example in Java

The details of Student1 is as follows:

Student name: John,Roll no: 1

Copying student1 to student2 now

The details of Student2 is as follows:

Student name: John,Roll no: 1









# QA L2

1. 使用原型模式的主要优势?

结论：使用原型模式的主要优势在于能够高效地创建新对象并节约资源。

原因：在常规操作中，从头开始创建一个全新的实例往往是昂贵的操作 1。根据原型模式的概念，可以通过复制或克隆一个现有的实例来生成新对象。这种方法避免了初始化的繁琐过程，从而节省了创建新实例所需的时间和金钱 。

1. 使用原型模式的主要挑战?

结论：每个子类都需要实现克隆或复制机制，浅拷贝。



1. 浅拷贝和深拷贝的区别?

结论：浅拷贝仅复制对象本身及基本数据类型变量，而深拷贝会递归复制对象所引用的所有依赖对象。

原因：在文档提及的 Java 环境中 ，浅拷贝创建的新对象与原对象共享引用类型的成员变量，这意味着修改其中一个对象的引用成员可能会影响另一个。深拷贝则确保新对象在内存中拥有完全独立的引用对象副本，这通常需要在 clone 方法内部进行显式的代码实现。

1. 什么时候深拷贝，什么时候浅拷贝 (and vice versa)?

结论：当对象**属性完全不可变**或**需要共享状态**时选择浅拷贝，当需要对象**完全独立且互不干扰**时选择深拷贝。

原因：原型模式的核心是从现有实例进行复制 。如果业务场景类似于文档中提到的复印文件并进行修改 ，为了确保修改副本时不影响原始文件，通常需要深拷贝。反之，如果仅仅是为了快速读取或对象内部结构简单且不含可变引用，浅拷贝则是更高效的选择。





```
// 辅助类：发动机 (引用类型，属于可变对象)
class Engine implements Cloneable {
    public String type;

    public Engine(String type) {
        this.type = type;
    }

    // 为了实现深拷贝，Engine 也需要支持克隆
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

// 主类：汽车
class Car implements Cloneable {
    public String name;    // String 是不可变引用，表现类似基本类型
    public Engine engine;  // 可变引用类型，这是区分深浅拷贝的关键

    public Car(String name, String engineType) {
        this.name = name;
        this.engine = new Engine(engineType);
    }

    // 【浅拷贝实现】
    public Object shallowCopy() throws CloneNotSupportedException {
        // 结论：直接调用 Java 默认的 clone
        // 原因：默认的 clone() 只复制对象本身和字段的值（引用地址），不处理内部引用的对象 [cite: 92, 94]
        return super.clone();
    }

    // 【深拷贝实现】
    public Object deepCopy() throws CloneNotSupportedException {
        // 步骤1：先获得浅拷贝对象
        Car deepClonedCar = (Car) super.clone();
        
        // 结论：手动处理内部的引用对象
        // 原因：必须显式地创建引用成员的新副本，确保新旧对象在内存中完全独立 [cite: 92]
        deepClonedCar.engine = (Engine) this.engine.clone();
        
        return deepClonedCar;
    }

    @Override
    public String toString() {
        return "Car: " + name + " | Engine: " + engine.type + " | Engine地址: " + engine.hashCode();
    }
}

public class CopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 1. 初始化原型对象
        Car originalCar = new Car("Ford", "V8");
        System.out.println("原始对象: " + originalCar);

        System.out.println("\n--- 测试浅拷贝 (Shallow Copy) ---");
        Car shallowCar = (Car) originalCar.shallowCopy();
        // 修改浅拷贝对象的发动机类型
        shallowCar.engine.type = "Electric"; 
        
        System.out.println("副本(浅): " + shallowCar);
        System.out.println("原始(浅): " + originalCar); 
        // 观察结果：原始对象的发动机也变成了 "Electric"，说明两者共享同一个 Engine 对象

        // 重置原始对象
        originalCar.engine.type = "V8"; 
        
        System.out.println("\n--- 测试深拷贝 (Deep Copy) ---");
        Car deepCar = (Car) originalCar.deepCopy();
        // 修改深拷贝对象的发动机类型
        deepCar.engine.type = "Hydrogen"; 
        
        System.out.println("副本(深): " + deepCar);
        System.out.println("原始(深): " + originalCar);
        // 观察结果：原始对象保持 "V8"，互不影响
    }
}
```



1. When I copy an object in Java, I need to use the clone() method. Is this understanding correct?

结论：这种理解是不准确的，clone 方法只是复制对象的途径之一。

在 Java 编程中，还可以利用拷贝构造函数、序列化与反序列化等技术手段来实现对象复制，并非只能局限于 clone 方法。



Can you give a simple example that demonstrates a user-defined copy constructor?

结论：用户定义的拷贝构造函数 是一种接收同类对象作为参数并将其属性值赋给新对象的特殊构造方法。

原因：针对文档中提出的关于拷贝构造函数示例的问题 ，其代码逻辑通常表现为在类中定义一个参数为该类本身的构造器。例如在 BasicCar 类中，可以编写一个构造器，接收另一个 BasicCar 对象，并将传入对象的 modelName 和 basePrice 等字段值直接赋值给当前新创建的对象，从而替代 clone 方法的功能。



```
// 1. 定义一个简单的汽车类
class BasicCar {
    public String modelName;
    public int basePrice;

    // 普通构造函数：用于创建原始对象
    public BasicCar(String modelName, int basePrice) {
        this.modelName = modelName;
        this.basePrice = basePrice;
    }

    // [核心部分] 用户定义的拷贝构造函数
    // 结论：接收一个同类型的对象作为参数
    public BasicCar(BasicCar source) {
        // 原因：手动将原对象的字段值赋给新对象，实现“复制”
        if (source != null) {
            this.modelName = source.modelName; // 复制名称
            this.basePrice = source.basePrice; // 复制价格
        }
    }

    @Override
    public String toString() {
        return "Model: " + modelName + ", Price: " + basePrice;
    }
}

public class CopyConstructorDemo {
    public static void main(String[] args) {
        // 步骤1：创建一个原型对象（原始对象）
        BasicCar originalCar = new BasicCar("Nano", 2000);
        System.out.println("原始对象: " + originalCar);

        // 步骤2：使用拷贝构造函数创建一个新对象
        // 此时不需要调用 clone() 方法，而是直接用 new 关键字
        BasicCar copiedCar = new BasicCar(originalCar);
        System.out.println("复制对象: " + copiedCar);

        // 验证独立性：修改副本不会影响原件
        copiedCar.basePrice = 5000;
        System.out.println("\n修改副本价格后...");
        System.out.println("原始对象: " + originalCar); // 保持 2000
        System.out.println("复制对象: " + copiedCar);   // 变为 5000
    }
}
```

