

# QA L8（作者原版）



## 问题 1：在 Java 中怎么实现类适配器设计模式？

回答：你可以继承一个现有的类并实现想要的接口。举个例子，如果你想在修改后的实现里用类适配器而不是对象适配器，你可以使用下面的代码。

Java

```
class TriangleClassAdapter extends Triangle implements 
RectInterface
{
    public TriangleClassAdapter(double base, double height) {
        super(base, height);
    }
    @Override
    public void aboutRectangle()
    {
        aboutTriangle();
    }
    @Override
    public double calculateAreaOfRectangle()
    {
        return calculateAreaOfTriangle();
    }
}
```

但是要注意，这种方法并不总是行得通。比如，如果 Triangle 类是一个 final 类，你就没法继承它了。除了这种情况，当你发现需要适配一个接口里没有定义的方法时，你也会卡住。所以在这些情况下，对象适配器更有用。



## 问题 2：“除了这种情况，当你发现需要适配一个接口里没有定义的方法时，你也会卡住。”这是什么意思？

回答：在修改后的实现中，你使用了 aboutRectangle 和 aboutTriangle 方法。这些方法实际上是在描述 Rectangle 和 Triangle 类的对象。现在假设 Triangle 类里没有 aboutTriangle 方法，而是有一个叫 aboutMe 的方法，它的作用是一样的，但是 RectInterface 接口里并没有这个方法。那么，要想适配 Triangle 类里的 aboutMe 方法，并写出类似下面这样的代码，就会是个很有挑战的任务：

Java

```
for(RectInterface rectObjects:rectangleObjects)
{
    rectObjects.aboutMe();
}
```

## 问题 3：类适配器和对象适配器，你更喜欢哪一个？

回答：在大多数情况下，相比于继承，我更喜欢组合。对象适配器使用的是组合，所以更灵活。另外，在很多情况下，你可能没法实现真正的类适配器。关于这一点，你可以再回头看看前面几个问题的回答。

## 问题 4：这个模式有什么缺点？

回答：我没看到什么大的挑战。我相信你可以把适配器的工作做得简单直接，但你可能需要写一些额外的代码。不过回报也是巨大的，特别是对于那些不能修改但为了稳定性又必须使用的遗留系统来说。

同时，专家建议不要在适配器里使用不同类型的验证或者添加新的行为。

理想情况下，适配器的工作应该仅限于执行简单的接口转换。











# QA L8（AI 润色）

1.如何在Java中实现类适配器设计模式

在Java中实现类适配器模式需要让适配器类同时继承被适配类和实现目标接口。

```
// 目标接口
public interface Target {
    void request();
}

// 被适配的类
public class Adaptee {
    public void specificRequest() {
        System.out.println("特殊请求");
    }
}

// 类适配器
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest(); // 调用父类方法
    }
}
```

具体做法是创建一个新类，让它继承自需要被适配的类，同时实现客户端期望的目标接口。在这个适配器类中，实现目标接口的方法，并在这些方法内部调用被继承类的相关方法，完成接口的转换。由于Java只支持单继承，类适配器的使用场景受到限制，只能适配一个父类。当需要适配多个不兼容的类时，这种方式就不适用了。



2.除了这种情况，当你注意到需要适配一个接口中未指定的方法时，你将再次受阻。这是什么意思

这句话指的是类适配器模式的一个重要限制。当你使用类适配器时，适配器只能提供目标接口中定义的方法。如果客户端需要**访问被适配类中存在但目标接口中没有定义的方法**，类适配器就无法提供这种访问。这是因为类适配器必须严格遵循目标接口的契约，不能随意添加额外方法。如果需要使用这些额外方法，就必须修改目标接口，或者采用其他设计模式，这就造成了实现上的障碍。

**看这个例子**

```
Target adapter = new ClassAdapter(); // 使用接口类型引用
adapter.request(); // 可以调用，因为Target接口有这个方法
adapter.additionalMethod(); // 编译错误！不是因为ClassAdapter没有实现它，而是因为Target接口没有声明它
```



**要访问额外方法，必须进行强制类型转换：**

```
((ClassAdapter)adapter).additionalMethod();
```

**而对象适配器可以通过提供获取内部对象的方法来解决这个问题**

```
((ObjectAdapter)adapter).getAdaptee().additionalMethod();
```



3.你更喜欢哪一种———  类适配器还是对象适配器

我更倾向于使用对象适配器。对象适配器通过组合而非继承来实现，它持有被适配对象的实例而不是继承它。

```
interface Target {
    void request();
}

// 被适配类
class Adaptee {
    void specificRequest() {
        System.out.println("Adaptee's specific request");
    }
}

// 对象适配器
class ObjectAdapter implements Target {
    private Adaptee adaptee;
    
    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void request() {
        // 委派给被适配类的实例
        adaptee.specificRequest();
    }
}
```

这种方式更灵活，不受**Java单继承**的限制，可以**适配多个类**。对象适配器也更符合面向对象设计原则中的"组合优于继承"原则，降低了代码的耦合度。此外，对象适配器可以在运行时动态地改变被适配的对象，而类适配器在编译时就确定了适配关系。对象适配器也能更好地处理被适配类中额外方法的问题，因为它可以自由地暴露这些方法。



4.这种模式有什么缺点

适配器模式的主要缺点是增加了系统的复杂性。引入适配器类意味着添加了额外的代码和类，使系统结构变得更加复杂。当需要适配的接口很多时，可能会产生大量适配器类，增加维护成本。适配器模式还可能导致性能问题，因为每次调用都需要经过适配器的转换。此外，如果原始类或目标接口经常变化，适配器也需要频繁修改，增加了维护负担。在某些情况下，过度使用适配器模式可能会掩盖设计上的问题，而不是从根本上解决接口不兼容的问题