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

