# QA L1





## **问题 1：为什么要搞得那么复杂？你可以简单地把 Singleton 类写成下面这样。**

Java

```
class Captain
{
   private static Captain captain;
    //We make the constructor private to prevent the use of "new"
   private Captain() { }
   public static Captain getCaptain()
   {
             // Lazy initialization
             if (captain == null)
             {
                captain = new Captain();
                 System.out.println("New captain is elected for your team.");
             }
             else
             {
                  System.out.print("You already have a captain for your team.");
                 System.out.println("Send him for the toss.");
             }
             return captain;
      }
}
```

**这个理解对吗？**

回答：这只能在单线程环境下工作，在多线程环境下不能算是线程安全的实现。考虑这种情况：假设在多线程环境下，两个或更多线程试图执行 if (captain == null) 这行代码，如果它们发现实例还没创建，每个线程都会尝试创建一个新实例。结果就是，你最后会有这个类的多个实例。

## **问题 2：为什么你在代码里用了延迟初始化这个词？**

回答：因为在这里，直到调用 getCaptain 方法时，单例的实例才会被创建。

## **问题 3：延迟初始化是什么意思？**

回答：简单来说，延迟初始化是一种推迟对象创建过程的技术。意思是只有在需要的时候才创建对象。当你处理创建对象需要很大开销的过程时，这个方法很有用。

## **问题 4：为什么要让类变成 final 的？你已经有私有构造函数了，这就能防止继承。对吗？**

回答：防止子类化有很多方法。是的，在这个例子里，因为构造函数已经标记为 private 了，所以不是非得加 final。但是如果你像例子里那样把 Captain 类设为 final，这被认为是更好的做法。当你考虑到嵌套类时，这就很有效了。比如，让我们修改私有构造函数的主体来检查 Captain 类创建的实例数量。再假设在前面的例子里，你有一个非静态嵌套类，也就是 Java 里的内部类，像下面这样。所有修改都用粗体显示。

Java

```
//final class Captain
class Captain
{
    private static Captain captain;
    //We make the constructor private to prevent the use of "new"
    static int numberOfInstance=0;    private Captain()
    {
        numberOfInstance++;        System.out.println("Number of instances at this moment="+ 
numberOfInstance);    }
    public static synchronized Captain getCaptain()
    {
        // Lazy initialization
        if (captain == null)
        {
            captain = new Captain();
             System.out.println("New captain is elected for your team.");
        }
        else
        {
             System.out.print("You already have a captain for your team.");
            System.out.println("Send him for the toss.");
        }
        return captain;
    }
     //A non-static nested class (inner class)   public class CaptainDerived extends Captain   {     //Some code   }}
```

现在在 main 方法里加另一行代码，像这样。

Java

```
public class SingletonPatternExample {
    public static void main(String[] args) {
        System.out.println("***Singleton Pattern Demo***\n");
         System.out.println("Trying to make a captain for your team:");
        //Constructor is private.We cannot use "new" here.
        //Captain c3 = new Captain();//error
        Captain captain1 = Captain.getCaptain();
         System.out.println("Trying to make another captain for your team:");
        Captain captain2 = Captain.getCaptain();
        if (captain1 == captain2)
        {
             System.out.println("captain1 and captain2 are same instance.");
        }
         Captain.CaptainDerived derived=captain1.new CaptainDerived();    }
}
```

现在注意输出。你可以看到程序违反了关键目标，因为我根本没想创建超过一个实例。

**输出**

Plaintext

```
***Singleton Pattern Demo***
Trying to make a captain for your team:
Number of instances at this moment=1
New captain is elected for your team.
Trying to make another captain for your team:
You already have a captain for your team.Send him for the toss.
captain1 and captain2 are same instance.Number of instances at this moment=2
```

## **问题 5：有没有什么替代方法来构建单例设计模式？**

回答：有很多方法。每种都有优缺点。你已经见过其中两种了。让我们讨论一些替代方法。

急切初始化

这里是急切初始化的一个简单实现。

Java

```
class Captain
{
    //Early initialization
    private static final Captain captain = new Captain();
    //We make the constructor private to prevent the use of "new"
    private Captain()
    {
        System.out.println("A captain is elected for your team.");
    }
     /* Global point of access.The method getCaptain() is a public static 
method*/
    public static Captain getCaptain()
    {
        System.out.println("You have a captain for your team.");
        return captain;
    }
}
```

讨论

急切初始化方法有以下优缺点。它的优点是直截了当且更干净。它和延迟初始化相反，但仍然是线程安全的。应用程序执行时有一点点延迟，因为所有东西都已经加载到内存里了。缺点是应用程序启动时间会变长，相比延迟初始化而言，因为所有东西都要先加载。为了检查这种代价，让我们在 Singleton 类里加一个虚拟方法。注意在 main 方法里，我只调用这个虚拟方法。现在看看输出。

Java

```
package jdp2e.singleton.questions_answers;
class Captain
{
    //Early initialization
    private static final Captain captain = new Captain();
    //We make the constructor private to prevent the use of "new"
    private Captain()
    {
        System.out.println("A captain is elected for your team.");
    }
     /* Global point of access.The method getCaptain() is a public static 
method*/
    public static Captain getCaptain()
    {
        System.out.println("You have a captain for your team.");
        return captain;
    }
    public static void dummyMethod()    {        System.out.println("It is a dummy method");    }}
public class EagerInitializationExample {
    public static void main(String[] args) {
         System.out.println("***Singleton Pattern Demo With Eager Initialization***\n");
        Captain.dummyMethod();        /*System.out.println("Trying to make a captain for your team:");
        Captain captain1 = Captain.getCaptain();
         System.out.println("Trying to make another captain for your team:");
        Captain captain2 = Captain.getCaptain();
            if (captain1 == captain2)
            {
                 System.out.println("captain1 and captain2 are same instance.");
            }*/
    }
}
```

**输出**

Plaintext

```
***Singleton Pattern Demo With Eager Initialization***
A captain is elected for your team.
It is a dummy method
```

分析

注意，你的团队选出了一名队长这句话还是出现在输出里了，虽然你可能根本没打算处理这个。所以在前面的情况里，Singleton 类的对象总是被实例化。另外，在 Java 5 之前，处理 Singleton 类有很多问题。

Bill Pugh 的解决方案

Bill Pugh 提出了一个不同的方法，使用静态嵌套辅助类。

Java

```
package jdp2e.singleton.questions_answers;
class Captain1
{
    private Captain1() {
        System.out.println("A captain is elected for your team.");
    }
    //Bill Pugh solution
    private static class SingletonHelper{
        /*Nested class is referenced after getCaptain() is called*/
        private static final Captain1 captain = new Captain1();
    }
    public static Captain1 getCaptain()
    {
        return SingletonHelper.captain;
    }
    /*public static void dummyMethod()
    {
        System.out.println("It is a dummy method");
    }  */
}
```

这个方法不使用同步技术和急切初始化。注意，只有当有人调用 getCaptain 方法时，SingletonHelper 类才会被考虑进来。而且这种方法不会产生任何你不想要的输出，如果你只是在 main 里调用 dummyMethod 的话，就像刚才那个案例一样。这也是 Java 中实现单例的常用标准方法之一。

双重检查锁定

还有一种流行的方法叫双重检查锁定。如果你注意我们同步实现的单例模式，你会发现同步操作通常开销很大，而且这种方法只对可能破坏单例实现的初始线程有用。但在后期，同步操作会造成额外的开销。为了避免这个问题，你可以在 if 条件里使用同步块，像下面的代码这样，确保不会创建不需要的实例。

Java

```
package jdp2e.singleton.questions_answers;
final class Captain2
{
    private static Captain2 captain;
    //We make the constructor private to prevent the use of "new"
    static int numberOfInstance=0;
    private Captain2() {
        numberOfInstance++;
         System.out.println("Number of instances at this moment="+ numberOfInstance);
    }
    public static  Captain2 getCaptain(){
        if (captain == null) {
            synchronized (Captain2.class) {
                // Lazy initialization
                if (captain == null){
                    captain = new Captain2();
                     System.out.println("New captain is elected for your team.");
                }
                else
                {
                     System.out.print("You already have a captain for your team.");
                    System.out.println("Send him for the toss.");
                }
            }
        }
        return captain;
    }
}
```

如果你对单例模式还有兴趣，可以阅读 [www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples](https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples) 这篇文章。

## **问题 6：简而言之，如果我需要创建同步代码，我可以使用 Java 里的 synchronized 关键字。对吗？**

回答：是的，JVM 保证了这一点。在内部，它在类或对象上使用锁来确保**只有一个线程在访问数据**。在 Java 里，你可以把这个关键字应用到方法或语句块上。

## **问题 7：为什么创建多个对象是个大问题？**

回答：在现实场景中，创建对象被视为昂贵的操作。有时候你需要实现一个中心化系统以便于维护，因为它能帮你提供一个全局访问机制。

## **问题 8：我应该什么时候考虑单例模式？**

回答：使用模式取决于具体的使用案例。但一般来说，你可以考虑用单例模式来实现中心化管理系统，维护一个公共日志文件，在多线程环境里维护线程池，或者实现缓存机制或设备驱动程序等等。

## **问题 9：我对急切初始化的例子有些疑问。根据定义，这看起来不完全是急切初始化。这个类只有在应用程序执行期间被代码引用时才会被 JVM 加载。这意味着这也是延迟初始化。对吗？**

回答：是的，你的观察在某种程度上是正确的。关于这一点有争议。简而言之，相比前面的方法，它是急切的。你看到了当你只调用 dummyMethod 时，你还是实例化了单例，虽然你并不需要它。所以，在这种语境下它是急切的，但从单例实例化直到类初始化才会发生这个意义上说，它又是延迟的。所以，延迟的程度是这里的关键所在。 

# QAL1

1. 为什么要把程序搞得这么复杂？你完全可以像下面这样简单地写单例类。

结论：简单的写法在多线程环境下不安全。

原因：如果单纯使用简单的条件判断，当多个线程同时访问时，可能会创建多个实例，从而违反单例模式关于类只能有一个实例的核心定义。



1. 为什么在代码中使用了懒加载这个术语？

结论：为强调对象创建被延迟。

原因：该术语着重说明实例并非在程序启动或类加载时立即创建，而是在实际被使用时才进行初始化。



1. 什么是延迟初始化？

结论：这是一种将对象创建推迟至真正需要时刻的技术。

原因：此方法有助于在集中式系统中限制不必要的对象创建，从而节省系统资源。



为何将类声明为final？私有构造函数本可阻止继承，此说法正确吗？

结论：此说法正确，但添加final关键字能更明确设计意图。

原因：虽然私有构造函数确实阻止了外部子类化，但显式声明类为final可清晰告知代码阅读者该类绝对不允许被继承，同时在某些情况下有助于编译器优化。



是否存在其他实现单例设计模式的方法？

结论：存在替代方案。

原因：除文档提及的懒加载模式外，还可采用饿加载模式、静态内部类或枚举等方式实现单例模式，以满足不同系统需求。



简而言之，若需创建同步代码，使用Java的synchronized关键字即可？

结论：这种说法是正确的，但需要考虑性能影响。

原因：虽然使用synchronized关键字可以解决并发问题，但如果将其加在整个方法上，会导致每次获取实例时都进行同步操作，从而降低程序运行效率。



为何多实例创建会引发重大问题？

结论：将导致资源浪费与数据管理混乱。

原因：在集中式系统中，单例模式通过限制非必要对象创建来提升可维护性；若存在多个实例，可能引发资源（如文件系统）管理冲突。



何时应考虑使用单例模式？

结论：当类只能存在一个实例且需要全局访问点时。

原因：如同现实世界中球队只能有一个队长，或计算机系统中仅使用一个文件系统进行集中管理时，该模式非常适用。





我对急切初始化示例存有疑虑。根据定义，这似乎并非严格意义上的急切初始化。该类仅在应用程序运行期间被代码引用时才会被JVM加载，这意味着它也属于延迟初始化。这种理解正确吗？

结论：从JVM类加载机制的角度来看是正确的，但在设计模式语境下仍被视为饿汉式。

原因：虽然JVM确实是按需加载类，但在单例模式分类中，饿汉式指依靠类加载机制本身来完成实例化（通常是静态变量初始化），而懒汉式特指在getInstance方法内部进行显式的空值判断和初始化。