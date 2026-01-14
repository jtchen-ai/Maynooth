# QA L19 





## **问题 1：我可以恢复到上一个快照或者恢复点。但是在现实场景中，我可能有多个恢复点。使用这个设计模式，你应该怎么实现这种情况？**

回答：

这种情况下你可以使用 ArrayList。看看下面这个程序。Originator 类和 Memento 类跟之前一样，所以我只展示修改后的 Caretaker 类。接下来的实现里我会用到这一行代码 List savedStateIds = new ArrayList(); 所以你需要在开头加上这两行代码。

Java

```
import java.util.ArrayList;
import java.util.List;
```

（代码部分保持原样）

## **问题 2：在很多应用程序中，我注意到 Memento 类是作为 Originator 的内部类出现的。你为什么不采用这种方法呢？**

回答：

备忘录设计模式有很多种不同的实现方式，比如使用包私有可见性或者对象序列化技术。但在每种情况下，如果你分析核心目标，你会发现一旦 Originator 创建了备忘录实例，除了它的创建者之外，其他人包括 Caretaker 或客户端都不允许访问内部状态。

Caretaker 的工作就是存储备忘录实例（在我们的例子中就是恢复点），并在你需要的时候把它们提供回来。所以，如果你的 Memento 类是公开的也没有害处。你只需要屏蔽掉 Memento 的公共 setter 方法。我相信这就足够了。

**问题 3：但是你仍然使用了 getter 方法 getStateId()。这难道没有违反封装性吗？**

回答：

这取决于你想要施加的严格程度。例如，如果你毫无理由地为所有字段提供 getter/setter，那肯定是一个糟糕的设计。当你使用对象内的所有公共字段时也是如此。但有时访问器方法是必需且有用的。在这本书里，我的目的是鼓励你通过简单的例子学习设计模式。如果我需要考虑像这样每一个微小的细节，你可能会失去兴趣。所以，在这些例子中，我展示了一种简单的方法来利用备忘录模式促进封装。但是，如果你想更严格一些，你可能更喜欢将 Memento 类实现为 Originator 的内部类，并修改最初的实现，就像下面这样。

（代码部分保持原样）

## **问题 4：使用备忘录设计模式的主要优势是什么？**

回答：

最大的优势是

你总是可以丢弃不需要的更改，并将它恢复到预期的或稳定的状态。

你**不需要牺牲**参与此模型的关键对象的封装性。它保持了高内聚。它提供了一种简单的恢复技术。

## **问题 5：与备忘录设计模式相关的主要挑战是什么？**

回答：

**大量的备忘录需要更多的存储空间。同时，它们给 Caretaker 增加了额外的负担，同时也增加了维护成本。你不能忽略保存这些状态的时间。保存状态的额外时间会降低系统的整体性能**。注意：在像 C# 或 Java 这样的语言中，开发人员可能更喜欢序列化/反序列化技术，而不是直接实现备忘录设计模式。这两种技术各有优缺点。但你也可以在你的应用程序中结合使用这两种技术。

## **问题 6：在这些实现中，如果你把 Originator 的状态设为公开，那么我们的客户端也可以直接访问这些状态。这正确吗？**

回答：

是的。但是你**不应该试图破坏封装**。注意 GoF 的定义是以“在不违反封装性的前提下……”开头的。

## **问题 7：在这些实现中，Memento 类没有公共 setter 方法。这背后的原因是什么？**

回答：

**只有创建备忘录的 Originator 才允许访问它。**如果不为你的 Memento 类提供公共 setter 方法，Caretaker 或客户端就无法修改由 Originator 创建的备忘录实例。

## **问题 8：在这些实现中，你可以通过将 stateId 设置为包私有可见性来忽略 Memento 的 getter 方法。例如，你可以像下面这样编写 Memento 类。**

```
class Memento
{
    //private int stateId;
    int stateId;//←-Change is here
    public Memento(int stateId)
    {
        this.stateId = stateId;
    }
    public int getStateId() {
        return stateId;
    }
    /*This class does not have the
    setter method.We need to use this class
    to get the state of the object only.*/
    /*public void setState(String state) {
        this.state = state;
    }*/
}
```

**然后你可以使用下面这行代码。**

```
//System.out.println(" Restoring to state id..."+ 
previousMemento.getStateId());
         System.out.println(" Restoring to state id..."+ 
previousMemento.stateId);//←The change is shown in bold
```

**这正确吗？**

回答：

是的。在许多应用程序中，其他类（除了 Originator）甚至无法读取备忘录的状态。当你使用**包私有可见性**时，你不需要任何访问器方法。换句话说，在这种情况下你只是使用了默认修饰符。所以，这种可见性比私有可见性稍微开放一些，同一个包中的其他类可以访问类成员。所以，在这种情况下，预期的类需要放在同一个包里。同时，你需要接受同一个包内的所有其他类都可以直接访问这个状态。所以，当你把类放在你的特殊包里时，你需要足够小心。

## **问题 9：我很困惑。为了支持撤销操作，我应该首选哪种模式——备忘录还是命令？**

回答：

GoF 告诉我们要区分这两种模式。这主要取决于你想如何处理这种情况。例如，假设你正在给一个整数加 10。加完之后，你想通过做反向操作来撤销这个操作（也就是 50 + 10 = 60，所以要回去，你就做 60 - 10 = 50）。在这种类型的操作中，我们不需要存储以前的状态。

但考虑一种情况，你需要存储操作之前对象的状态。在这种情况下，备忘录就是你的救星。



所以，在一个绘图应用程序中，你可以避免撤销绘图操作的成本。你可以在执行命令之前存储对象列表。在这种情况下，这个存储列表可以被视为备忘录。你可以保留这个列表以及相关的命令。所以，一个应用程序可以使用**这两种模式来支持撤销操作**。最后，你必须记住，在备忘录模式中存储备忘录对象是强制性的，这样你才能回滚到以前的状态；



但在命令模式中，不一定要存储命令。一旦你执行了一个命令，它的工作就完成了。如果你不支持“撤销”操作，你可能根本没兴趣存储这些命令。

## **问题 10：你在第一个实现之后谈到了深拷贝。为什么我需要它？**

回答：

在第 2 章（原型模式）中，我讨论了浅拷贝和深拷贝。你可以参考那个讨论。为了回答你的问题，让我们通过一个简单的例子来分析深拷贝有什么特别之处。考虑下面的例子。

**Java 中的浅拷贝与深拷贝**

在 Java 中，你通过 `clone()` 方法来克隆对象，但同时你需要实现 `Cloneable` 接口（这是一个标记接口），因为只有实现了这个接口的 Java 对象才有资格被克隆。默认版本的 `clone()` 创建的是浅拷贝。要创建深拷贝，你需要重写 `clone()` 方法。

**下面这个程序的关键特征**

在这个例子中，有两个类：Employee 和 EmpAddress。

Employee 类有三个字段：id、name 和 EmpAddress。你可以注意到，要构建一个 Employee 对象，你需要传入一个 EmpAddress 对象。所以在例子中你会看到这样的代码：`Employee emp = new Employee(1, "John", initialAddress);`

EmpAddress 只有一个叫 address 的字段，是 String 类型的。

在客户端代码中，你创建了一个 Employee 对象 emp，然后通过克隆创建了另一个对象 empClone。代码是这样的：`Employee empClone = (Employee)emp.clone();`

然后你改变 emp 对象的字段值。但是作为这种改变的副作用，empClone 对象的地址也变了，但这并不是我们想要的。

**实现代码**

这是具体的代码实现：

Java

```
package jdp2e.memento.questions_answers;

class EmpAddress implements Cloneable
{
    String address;
    public EmpAddress(String address)
    {
        this.address=address;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    @Override
    public String toString()
    {
        return  this.address;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        //Shallow Copy
        return super.clone();
    }
}

class Employee implements Cloneable
{
    int id;
    String name;
    EmpAddress empAddress;
    public Employee(int id,String name,EmpAddress empAddress)
    {
        this.id=id;
        this.name=name;
        this.empAddress=empAddress;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public EmpAddress getAddress()
    {
        return this.empAddress;
    }
    public void setAddress(EmpAddress newAddress) 
    {
        this.empAddress=newAddress;
    }
    @Override
    public String toString()
    {
         return "EmpId=" +this.id+ " EmpName="+ this.name+ " EmpAddressName="+ this.empAddress;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        //Shallow Copy
        return super.clone();
    }
}

public class ShallowVsDeepCopy {
     public static void main(String[] args) throws CloneNotSupportedException  {
        System.out.println("***Shallow vs Deep Copy Demo***\n");
        EmpAddress initialAddress=new EmpAddress("21, abc Road, USA");
        Employee emp=new Employee(1,"John",initialAddress);
        System.out.println("emp1 object is as follows:");
        System.out.println(emp);
        Employee empClone=(Employee)emp.clone();
        System.out.println("empClone object is as follows:");
        System.out.println(empClone);
        
        System.out.println("\n Now changing the name, id and address of the emp object ");
        emp.setId(10);
        emp.setName("Sam");
        emp.empAddress.setAddress("221, xyz Road, Canada");
        
        System.out.println("Now emp1 object is as follows:");
        System.out.println(emp);
        System.out.println("And emp1Clone object is as follows:");
        System.out.println(empClone);
    }
}
```

**输出**

这是输出结果：

Plaintext

```
***Shallow vs Deep Copy Demo***
emp1 object is as follows:
EmpId=1 EmpName=John EmpAddressName=21, abc Road, USA
empClone object is as follows:
EmpId=1 EmpName=John EmpAddressName=21, abc Road, USA

 Now changing the name, id and address of the emp object 
Now emp1 object is as follows:
EmpId=10 EmpName=Sam EmpAddressName=221, xyz Road, Canada
And emp1Clone object is as follows:
EmpId=1 EmpName=John EmpAddressName=221, xyz Road, Canada
```

**浅拷贝分析**

注意输出的最后一行。你看到了一个不想看到的副作用。由于对 emp 对象的修改，克隆对象的地址也被修改了。这是因为原始对象和克隆对象都指向同一个地址对象，它们并没有 100% 分离。这就是浅拷贝的情况。

**深拷贝的实现**

现在让我们尝试深拷贝的实现。我们将修改 Employee 类的 clone 方法，如下所示：

Java

```
@Override
public Object clone() throws CloneNotSupportedException
{
    //Shallow Copy
    //return super.clone();
    
    //For deep copy
    Employee employee = (Employee)  super.clone();
    employee.empAddress = (EmpAddress) empAddress.clone();
    return employee;
}
```

**修改后的输出**

这是修改代码后的输出：

Plaintext

```
***Shallow vs Deep Copy Demo***
emp1 object is as follows:
EmpId=1 EmpName=John EmpAddressName=21, abc Road, USA
empClone object is as follows:
EmpId=1 EmpName=John EmpAddressName=21, abc Road, USA

 Now changing the name, id and address of the emp object 
Now emp1 object is as follows:
EmpId=10 EmpName=Sam EmpAddressName=221, xyz Road, Canada
And emp1Clone object is as follows:
EmpId=1 EmpName=John EmpAddressName=21, abc Road, USA
```

**深拷贝分析**

注意输出的最后一行。现在你看不到那个不想看到的副作用了。原对象的修改没有影响到克隆对象。这是因为原对象和克隆对象是完全不同且互相独立的。这就是为什么在备忘录模式中，为了真实保存状态，通常需要深拷贝的原因。