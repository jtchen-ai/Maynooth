## QA 原版



## 问题 1：使用建造者模式有什么优势？

回答：可以一步一步地创建一个复杂的对象，还能改变这些步骤。

通过隐藏复杂的建造细节，提升了封装性。主管在整个建造过程结束后，可以从建造者那里拿到最终产品。

大体上来说，在高层级看，你似乎只用了一个方法就造出了成品，但其他内部方法只涉及局部的创建。所以你对建造过程有更精细的控制。

使用这个模式，同样的建造过程可以生产出不同的产品。既然你能改变建造步骤，你自然也能改变产品的内部表现形式。

## 问题 2：建造者模式有什么缺点或陷阱？

回答：如果你要处理以后需要修改的可变对象，它就不太适合。你可能需要复制粘贴一部分代码。在某些情况下，这些重复代码影响很大，甚至变成一种反模式。一个具体的建造者是专门针对特定类型产品的，所以要创建不同类型的产品，你可能需要设计不同的具体建造者。另外，只有当结构非常复杂时，用这种方法才划算。

## 问题 3：在这个模式的演示中，我能用抽象类代替接口吗？

回答：可以。在这个例子里你可以用抽象类代替接口。

## 问题 4：在应用程序里，我怎么决定是该用抽象类还是接口呢？

回答：我认为如果你想要一些集中的或者默认的行为，抽象类是更好的选择。这种情况下，你可以提供一些默认实现。另一方面，接口实现是从零开始的。它们指出要做什么这类规则或契约，比如你必须实现这个方法，但它们不强制规定怎么做。另外，当你试图实现多重继承的概念时，首选接口。

但同时，如果你需要在接口里加一个新方法，那你得追踪这个接口的所有实现，并在所有这些地方加上这个方法的具体实现。而在抽象类里加一个带默认实现的新方法，现有代码就能顺利运行。

Java 特别注意了最后这一点。Java 8 在接口中引入了 default 关键字。你可以在想要的方法签名前面加上 default 这个词，并提供一个默认实现。接口方法默认是公开的，所以你不需要用 public 关键字标记它。

这些总结建议来自 Oracle Java 文档。你应该在以下场景优先选抽象类：你想在多个关系紧密的类之间共享代码；扩展抽象类的类有很多共同的方法或字段，或者需要非公开的访问修饰符；你想要用非静态或非最终字段，这样能定义方法去访问和修改对象的状态。

而在这些场景你应该优先选接口：你预计有几个不相关的类要实现你的接口，比如 Comparable 接口可以被很多不相关的类实现；你指定了特定数据类型的行为，但不关心实现者怎么实现；你想在应用里使用多重继承的概念。

(决定用哪一个主要看你想达到什么目的。如果你要在几个关系紧密的类之间共享代码，或者需要定义成员变量来修改对象状态，那就选抽象类，因为它能提供默认的通用实现。反过来，如果你预计会有很多不相关的类来实现同一个功能，或者你需要支持多重继承，那就选接口，因为它更像是一种行为契约。虽然后来 Java 8 给接口引入了默认方法，解决了接口难以扩展的问题，但核心判断标准还是看你是侧重于代码复用还是侧重于行为约束)







## 问题 5：我看到汽车的型号名称是加在开头，但摩托车的型号名称是加在末尾。这是故意的吗？

回答：是的。这是为了演示每个具体的建造者都可以决定它们想怎么生产最终产品。它们拥有这种自由。

## 问题 6：为什么你要用一个单独的类来做主管？你可以用客户端代码来扮演主管的角色啊。

回答：没人限制你那么做。在前面的实现里，我想在实现中把这个角色和客户端代码分开。但在即将展示的修改后的实现里，我就把客户端当作主管来用了。

## 问题 7：你说的客户端代码是什么意思？

回答：包含 main 方法的类就是客户端代码。书里大部分地方，客户端代码都是这个意思。

## 问题 8：你提到改变步骤好几次了。你能演示一个实现，展示最终产品是怎么通过不同的变体和步骤创建出来的吗？

回答：问得好。你要求演示建造者模式的真正威力。所以，让我们看看另一个例子。

修改后的演示

以下是修改后实现的关键特征。

在这个修改后的实现里，我只把汽车作为最终产品。

我创建的定制汽车有这些属性：一个启动信息 startUpMessage，一个过程完成信息 endOperationsMessage，车身材料 bodyType，车轮数量 noOfWheels，以及车灯数量 noOfHeadLights。

在这个实现里，客户端代码扮演了主管的角色。

我把建造者接口重命名为 ModifiedBuilder。除了 constructCar 和 getConstructedCar 方法外，接口里的每个方法返回类型都是 ModifiedBuilder，这有助于我们在客户端代码里使用方法链机制。

修改后的 Package Explorer 视图

图 3-4 展示了修改后的 Package Explorer 视图。

修改后的实现

下面是修改后的实现代码。

Java

```
package jdp2e.builder.pattern;
//The common interface
interface ModifiedBuilder
{
       /*All these methods return type is ModifiedBuilder.
        * This will help us to apply method chaining*/
       ModifiedBuilder startUpOperations(String startUpMessage);
       ModifiedBuilder buildBody(String bodyType);
       ModifiedBuilder insertWheels(int noOfWheels);
       ModifiedBuilder addHeadlights(int noOfHeadLights);
       ModifiedBuilder endOperations(String endOperationsMessage);
       //Combine the parts and make the final product.
       ProductClass constructCar();
       //Optional method:To get the already constructed object
       ProductClass getConstructedCar();
}
//Car class
class CarBuilder implements ModifiedBuilder
{
        private String startUpMessage="Start building the product";//Default 
//start-up message
       private String  bodyType="Steel";//Default body
       private int noOfWheels=4;//Default number of wheels
       private int  noOfHeadLights=2;//Default number of head lights
        //Default finish up message 
private String  endOperationsMessage="Product creation completed";
       ProductClass product;
       @Override
       public ModifiedBuilder startUpOperations(String startUpMessage)
       {
             this.startUpMessage=startUpMessage;
             return this;
       }
       @Override
       public ModifiedBuilder buildBody(String bodyType)
       {
             this.bodyType=bodyType;
             return this;
       }
       @Override
       public ModifiedBuilder insertWheels(int noOfWheels)
       {
             this.noOfWheels=noOfWheels;
             return this;
       }
       @Override
       public ModifiedBuilder addHeadlights(int noOfHeadLights)
       {
             this.noOfHeadLights=noOfHeadLights;
             return this;
       }
       @Override
       public ModifiedBuilder endOperations(String endOperationsMessage)
       {       this.endOperationsMessage=endOperationsMessage;
       return this;
       }
       @Override
       public ProductClass constructCar() {
              product= new ProductClass(this.startUpMessage,this.
bodyType,this.noOfWheels,this.noOfHeadLights,this.
endOperationsMessage);
             return product;
       }
       @Override
       public ProductClass   getConstructedCar()
 {
       return product;
 }
}
//Product class
final class ProductClass
{
       private String startUpMessage;
       private String  bodyType;
       private int noOfWheels;
       private int  noOfHeadLights;
       private String  endOperationsMessage;
        public ProductClass(final String startUpMessage, String bodyType, 
int noOfWheels, int noOfHeadLights,
                   String endOperationsMessage) {
             this.startUpMessage = startUpMessage;
             this.bodyType = bodyType;
             this.noOfWheels = noOfWheels;
             this.noOfHeadLights = noOfHeadLights;
             this.endOperationsMessage = endOperationsMessage;
       }
       /*There is no setter methods used here to promote immutability.
        Since the attributes are private and there is no setter methods, the 
keyword "final" is not needed to attach with the attributes.
        */
       @Override
       public String toString() {
              return "Product Completed as:\n startUpMessage=" + 
startUpMessage + "\n bodyType=" + bodyType + "\n noOfWheels="
                           + noOfWheels + "\n noOfHeadLights=" + 
noOfHeadLights + "\n endOperationsMessage=" + 
endOperationsMessage
                          ;
       }
}
//Director class
public class BuilderPatternModifiedExample {
       public static void main(String[] args) {
             System.out.println("***Modified Builder Pattern Demo***");
             /*Making a custom car (through builder)
               Note the steps:
               Step1:Get a builder object with required parameters
                Step2:Setter like methods are used.They will set the 
optional fields also.
               Step3:Invoke the constructCar() method to get the final car.
              */
              final ProductClass customCar1 = new CarBuilder().
addHeadlights(5)
                          .insertWheels(5)
                          .buildBody("Plastic")
                          .constructCar();
             System.out.println(customCar1);
             System.out.println("--------------");
              /* Making another custom car (through builder) with a 
different
              * sequence and steps.
              */
             ModifiedBuilder carBuilder2=new CarBuilder();
             final ProductClass customCar2 = carBuilder2.insertWheels(7)
                          .addHeadlights(6)
                          .startUpOperations("I am making my own car")
                          .constructCar();
             System.out.println(customCar2);
             System.out.println("--------------");
             //Verifying the getConstructedCar() method
             final ProductClass customCar3=carBuilder2.getConstructedCar();
             System.out.println(customCar3);
       }
}
```

修改后的输出

这里是修改后的输出。为了让你注意到输出的差异，部分行加粗了。

## **Modified Builder Pattern Demo** Product Completed as: startUpMessage=Start building the product bodyType=Plastic noOfWheels=5 noOfHeadLights=5 endOperationsMessage=Product creation completed

## Product Completed as: startUpMessage=I am making my own car bodyType=Steel noOfWheels=7 noOfHeadLights=6 endOperationsMessage=Product creation completed

Product Completed as:

startUpMessage=I am making my own car

bodyType=Steel

noOfWheels=7

noOfHeadLights=6

endOperationsMessage=Product creation completed

分析

注意前面实现中客户端代码里创建定制汽车的那几行代码。

你正在使用一个建造者通过在调用构建方法之间改变建造者属性来创建多个对象。例如，在第一种情况中，你通过一个建造者对象逐个调用 addHeadLights、insertWheels、buildBody 方法，然后拿到最终的汽车 customCar1。但在第二种情况中，当你创建另一个汽车对象 customCar2 时，你是按照不同的顺序调用方法的。当你没有调用某个方法时，系统会为你提供默认实现。

## 问题 9：我看到客户端代码里用了 final 关键字。但在 ProductClass 属性里你没用。这是为什么？

回答：在客户端代码里，我用 final 关键字是为了提升不可变性。但在 ProductClass 类里，属性已经被标记为 private 了，而且没有 setter 方法，所以它们已经是不可变的了。

## 问题 10：不可变对象的关键好处是什么？

回答：一旦构建完成，它们就可以被安全地共享，最重要的是，它们是线程安全的，所以在多线程环境下你可以省去很多同步成本。

## 问题 11：我什么时候该考虑用建造者模式？

回答：如果你需要制造一个复杂的对象，构建过程涉及很多步骤，同时产品需要是不可变的，建造者模式就是个不错的选择。





### 1. 使用建造者模式有什么优势？

结论：能够将复杂对象的构建与表示分离，并使相同的构建过程生成不同的表示 。

原因：该模式适用于创建具有多个部件的复杂对象，它确立了独立于部件的创建机制，使得构建过程不需要关心部件的具体组装方式 。

### 2. 建造者模式有哪些缺点或陷阱？

结论：会增加系统的复杂性和类的数量。

原因：根据类图所示，实现该模式需要定义指挥者、抽象建造者接口、具体的建造者类（如Car和MotorCycle）以及产品类，这比直接创建对象引入了更多的层级结构 。

### 3. 我可以在此模式的演示中使用抽象类代替接口吗？

结论：可以使用抽象类代替接口。

原因：在具体实现中，如果不同的建造者之间存在公共代码，使用抽象类可以提供默认实现以复用代码 。

### 4. 如何在应用程序中决定使用抽象类还是接口？

结论：根据具体类之间是否存在共享的实现逻辑来决定。

原因：接口仅定义了构建操作的**方法签名**（如buildBody等），适用于完全不同的实现；而抽象类允许定义**部分公共状态或默认行为** 。

### 5. 我看到汽车的模型名称加在开头，而摩托车的模型名称加在末尾。这是故意的吗？

结论：是有意为之，体现了**不同具体建造者生成不同表示的特性**。

原因：GoF定义指出相同的构建过程必须允许创建对象的不同表示，具体的Car和MotorCycle建造者可以在内部逻辑中自由决定如何处理数据（如品牌名称的位置），从而产生结构差异化的产品 。

### 6. 为什么要使用单独的指挥者类？你可以用客户端代码来扮演指挥者的角色。

结论：为了将构建算法的具体步骤与客户端代码解耦。

原因：指挥者（Director）负责执行具体的构建操作序列（construct方法），这使得构建过程独立于部件的组装细节，客户端无需了解具体的构建步骤。



**无指挥者**

```
public class ClientWithoutDirector {
    public static void main(String[] args) {
        // 创建具体的建造者
        Car carBuilder = new Car("Ford");

        // ❌ 客户端必须亲自负责“组装”的每一个细节和顺序
        // 如果顺序搞错了，或者少了一步，产品可能就是坏的
        carBuilder.startUpOperations();
        carBuilder.buildBody();
        carBuilder.insertWheels();
        carBuilder.addHeadlights();
        carBuilder.endOperations();

        // 获取最终产品
        Product myCar = carBuilder.getVehicle();
        myCar.showProduct();
    }
}
```

**有指挥者**

```
// 指挥者类：封装了构建算法（步骤的顺序）
class Director {
    // 指挥者知道如何利用 Builder 接口来构建产品
    public void construct(Builder builder) {
        builder.startUpOperations();
        builder.buildBody();
        builder.insertWheels();
        builder.addHeadlights();
        builder.endOperations();
    }
}

public class ClientWithDirector {
    public static void main(String[] args) {
        // 1. 创建指挥者
        Director director = new Director();

        // 2. 创建具体的建造者 (比如 Car 或 MotorCycle)
        Car carBuilder = new Car("Honda");
        // MotorCycle motorBuilder = new MotorCycle("Harley");

        // 3. ✅ 客户端只需调用 construct，无需了解具体步骤
        director.construct(carBuilder);

        // 4. 获取产品
        Product myCar = carBuilder.getVehicle();
        myCar.showProduct();
    }
}
```



### 7. 客户端代码是什么意思？

结论：指在应用程序中**发起构建请求**并**使用最终产品**的代码部分。

原因：在提供的示例中，BuilderPatternExample类包含main方法，它负责实例化指挥者和具体建造者并启动程序，这部分代码即为客户端代码 。

### 8. 你多次提到变化的步骤。能否演示一个最终产品通过不同的变化和步骤创建的实现？

结论：可以通过查看Car和MotorCycle这两个具体建造者类的实现来演示。

原因：类图中展示了Car和MotorCycle两个类，它们虽然遵循相同的接口（如startUpOperations, buildBody等），但分别代表了不同的实现逻辑，最终生成了包含不同属性或结构的Product对象。