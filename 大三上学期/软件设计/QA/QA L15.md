# QA L15

**1. 为什么要避免简单的子类化行为，把例子搞得这么复杂？**

在面向对象编程里，你可能更倾向于利用多态的概念，这样你的代码就能在运行的时候自动选对需要的对象，而不用改动代码本身。当你熟悉了设计模式，通常会发现组合比继承更好用。策略模式能帮你把组合跟多态结合起来。让我们来看看这背后的原因。

我们假设你在编写任何应用程序时都会尝试遵循以下准则：把变化很大的代码和不怎么变化的代码分离开。尽量让变化的部分保持独立，以便于维护。尽量复用它们。

遵循这些准则，我使用了组合来提取和封装代码中不稳定或变化的部分，这样整个任务处理起来就很容易，你也能复用它们。但是当你使用继承时，父类会提供一个默认实现，然后子类会修改它，也就是 Java 里的重写。下一个子类可能会进一步修改实现，所以你基本上是把任务分散到了不同的层级上，这在将来可能会导致严重的维护和扩展性问题。我们要来看一个这样的例子。



假设你的 Vehicle 类有如下结构 ：

```
abstract class Vehicle
{
    // 默认实现
    public void showTransportMedium()
    {
        System.out.println("I am transporting in air.");
    }
    // 不变的代码
    public void commonJob()
    {
        System.out.println("We all can be used to transport");
    }
    public abstract void showMe();
}
```

做一个 Vehicle 的具体实现，比如 Aeroplane 类 2：



Java

```
class Aeroplane extends Vehicle
{
    @Override
    public void showMe() {
        System.out.println("I am an aeroplane.");
    }
}
```

在客户端类里使用下面的代码 3：



Java

```
Aeroplane aeroplane=new Aeroplane();
aeroplane.showMe();
aeroplane.showTransportMedium();
```

你会得到下面的输出：

I am an aeroplane.

I am transporting in air.

到目前为止看起来还不错。现在假设你引入了另一个类 Boat，像下面这样 4：



Java

```
class Boat extends Vehicle
{
    @Override
    public void showMe() {
        System.out.println("I am a boat.");
    }
}
```

在客户端类里使用下面的代码 5：



Java

```
Aeroplane aeroplane=new Aeroplane();
aeroplane.showMe();
aeroplane.showTransportMedium();

Boat boat=new Boat();
boat.showMe();
boat.showTransportMedium();
```

你会收到下面的输出 6：

I am an aeroplane.

I am transporting in air.

I am a boat.

I am transporting in air.

你可以看到你的船现在正在空中移动。为了防止这种糟糕的情况，你需要正确地重写它。现在进一步假设你需要引入另一个类 SpeedBoat，它也能在水中高速运输。你需要像下面这样防范各种情况 7：



Java

```
class Boat extends Vehicle
{
    @Override
    public void showMe()
    {
        System.out.println("I am a boat.");
    }
    @Override
    public void showTransportMedium() {        
        System.out.println("I am transporting in water.");    
    }
}

class SpeedBoat extends Vehicle
{
    @Override
    public void showMe() {
        System.out.println("I am a speedboat.");
    }

    @Override
    public void showTransportMedium() {         
        System.out.println("I am transporting in water with high speed.");    
    }
}
```

你可以看到，如果你把变化的认为分散到不同的类及其子类中，从长远来看，维护成本会非常高。如果你想经常适应类似的变化，你会感到很痛苦，因为在每种情况下你需要不断更新 showTransportMedium 方法 8。



**2. 如果是这种情况，你可以创建一个单独的接口 TransportInterface，并把 showTransportMedium() 方法放在那个接口里。现在任何想要获得该方法的类都可以实现那个接口。这种理解正确吗？**

没错，你可以这么做。但是代码会变成这样 9：



Java

```
abstract class Vehicle
{
    // 不变的代码
    public void commonJob()
    {
        System.out.println("We all can be used to transport");
    }
    public abstract void showMe();
}

interface TransportInterface
{
    void showTransportMedium();
}

class Aeroplane extends Vehicle implements TransportInterface
{
    @Override
    public void showMe() {
        System.out.println("I am an aeroplane.");
    }
    @Override
    public void showTransportMedium() {
        System.out.println("I am transporting in air.");
    }
}

class Boat extends Vehicle implements TransportInterface
{
    @Override
    public void showMe()
    {
        System.out.println("I am a boat.");
    }
    @Override
    public void showTransportMedium() {
        System.out.println("I am transporting in water.");
    }
}
```

你会发现每个类和它的子类都得自己去实现那个 showTransportMedium 方法。这样你就没法复用代码了，这种情况下这跟用继承一样糟糕 10。



**3. 你能在你的实现中在运行时修改默认行为吗？**

可以。让我们引入一个特殊的交通工具，它既可以在水中运输也可以在空中运输，如下所示 11：



Java

```
public class SpecialVehicle extends Vehicle
{
    public SpecialVehicle()
    {
        // 初始化为 AirTransport
        transportMedium= new AirTransport();
    }
    @Override
    public void showMe()
    {
         System.out.println("I am a special vehicle who can transport both in air and water.");
    }
}
```

然后在 Vehicle 类里加个 setter 方法 12：



Java

```
// 上下文类
public abstract class Vehicle
{
    // 上下文对象包含策略对象接口类型的引用变量
    TransportMedium transportMedium;
    
    public Vehicle()
    {
    }
    
    public void showTransportMedium()
    {
        // 把任务委托给相应的行为类
        transportMedium.transport();
    }
    
    // 不变的代码
    public void commonJob()
    {
        System.out.println("We all can be used to transport");
    }
    
    public abstract void showMe();
    
    // 解释第3个问题所需的额外代码
    public void setTransportMedium(TransportMedium transportMedium)
    {        
        this.transportMedium=transportMedium;    
    }
}
```

为了测试这个，在客户端类里也加几行代码 13：



Java

```
// 客户端代码
public class StrategyPatternExample {
    public static void main(String[] args) {
        System.out.println("***Strategy Pattern Demo***");
        Vehicle vehicleContext=new Boat();
        vehicleContext.showMe();
        vehicleContext.showTransportMedium();
        System.out.println("________");
        
        vehicleContext=new Aeroplane();
        vehicleContext.showMe();
        vehicleContext.showTransportMedium();
        System.out.println("________");
        
        // 解释第3个问题所需的额外代码
        vehicleContext=new SpecialVehicle();
        vehicleContext.showMe();        
        vehicleContext.showTransportMedium();        
        System.out.println("- - - - -");        
        
        // 修改 Special vehicle 的行为
        vehicleContext.setTransportMedium(new WaterTransport());        
        vehicleContext.showTransportMedium();    
    }
}
```

现在如果你运行这个修改后的程序，你会得到下面的输出 14：

Strategy Pattern Demo

I am a boat.

I am transporting in water.

------

I am an aeroplane.

I am transporting in air.

------

I am a special vehicle who can transport both in air and water.

I am transporting in air.

------

I am transporting in water.

初始的行为在后面的阶段被动态修改了。

**4. 你可以用抽象类来代替接口吗？**

可以。在某些情况下这很合适，比如你想把通用的行为放在抽象类里。我在建造者模式的问答环节详细讨论过这一点 15。



## **5. 使用策略设计模式的主要优势是什么？**

这个模式**让类跟算法独立开来**。在这里，类**在运行的时候把算法委托给策略对象去处理**。所以你可以说算法的选择不是在编译的时候定死的。

代码库维护起来更容易。

它也很容易扩展



## **6. 与策略设计模式相关的主要挑战是什么？**

增加上下文类会导致应用程序里的对象变多。

应用程序的用户必须知道有哪些不同的策略。所以客户端代码和不同策略的实现之间存在紧密的联系。

当你引入一个新的行为或者算法时，你可能也需要去修改客户端的代码 。