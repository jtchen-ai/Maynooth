# QA(原版)



## 问题 1：我看到狗和老虎的接口都包含名字相同的方法，比如都包含 speak 和 preferredAction 方法。这是强制要求的吗？

回答：不。你可以给方法起不同的名字。而且，这些接口里的方法数量也可以不同。但在本书里，我涵盖了简单工厂模式和工厂方法模式。你可能会对它们之间的相似点或不同点感兴趣。所以我从一个例子开始，然后不断修改它。这就是为什么我在这个例子里保留了 speak 和 preferredAction 这两个方法。请注意，这些方法在第 24 章的简单工厂模式和第 4 章的工厂方法模式里都用到了。

## 问题 2：像这样使用抽象工厂有什么挑战？

回答：**抽象工厂的任何改变都会迫使我们去修改具体的工厂**。如果你遵循针对接口编程而不是针对实现编程的设计哲学，你需要对此做好准备。这是开发者时刻谨记的关键原则之一。在大多数场景下，开发者是不希望更改抽象工厂的。

另外，整体架构看起来可能会很复杂。

而且，在某些场景下调试会变得很棘手。

## 问题 3：如何区分简单工厂模式、工厂方法模式和抽象工厂模式？

回答：我在第 4 章的问答部分讨论过简单工厂模式和工厂方法模式的区别。让我们结合下面的图表复习一下这三种工厂。

简单工厂模式代码片段

这里是代码片段。

Java

```
Animal preferredType=null;
SimpleFactory simpleFactory = new SimpleFactory();
// The code that will vary based on users preference.
preferredType = simpleFactory.createAnimal();
```

图 5-4 展示了在简单工厂模式中如何获取动物对象。

工厂方法模式代码片段

这里是代码片段。

Java

```
// Creating a Tiger Factory
AnimalFactory tigerFactory =new TigerFactory();
// Creating a tiger using the Factory Method
Animal aTiger = tigerFactory.createAnimal();
//...Some code in between...
// Creating a DogFactory
AnimalFactory dogFactory = new DogFactory();
// Creating a dog using the Factory Method
Animal aDog = dogFactory.createAnimal();
```

图 5-5 展示了在工厂方法模式中如何获取动物对象。

抽象工厂模式代码片段

这里是代码片段。

Java

```
AnimalFactory myAnimalFactory;
Dog myDog;
Tiger myTiger;
System.out.println("***Abstract Factory Pattern Demo***\n");
//Making a wild dog through WildAnimalFactory
myAnimalFactory = new WildAnimalFactory();
myDog = myAnimalFactory.createDog();
//Making a wild tiger through WildAnimalFactory
myTiger = myAnimalFactory.createTiger();
//Making a pet dog through PetAnimalFactory
myAnimalFactory = new PetAnimalFactory();
myDog = myAnimalFactory.createDog();
//Making a pet tiger through PetAnimalFactory
myTiger = myAnimalFactory.createTiger();
myTiger.speak();
myTiger.preferredAction();
```

图 5-6 展示了在抽象工厂模式中如何获取动物对象。

结论

- 使用简单工厂，你可以把变动的代码和其余代码分离开，基本上就是把客户端代码解耦。这种方法可以帮你轻松地管理代码。这个方法的另一个关键优势是客户端不知道对象是如何创建的。所以，它促进了安全性和抽象性。但它可能会**违反开闭原则**。

- 你可以使用工厂方法模式来克服这个缺点，它允许子类决定实例化过程是如何完成的。换句话说，你把创建对象的任务委托给了实现工厂方法的子类。

- 抽象工厂本质上是工厂的工厂。它创建相关**对象的家族**，但不依赖于具体的类。

我尽量保持例子简单且彼此接近。**工厂方法促进继承**，它们的子类需要实现工厂方法来创建对象。**抽象工厂模式促进对象组合**，你可以使用抽象工厂的具体实例来组合类。

所有这些工厂都通过减少对具体类的依赖来促进松耦合。

## 问题 4：在所有这些工厂示例中，你都避免使用带参数的构造函数。这是故意的吗？

回答：在许多应用程序中，你会看到带参数的构造函数的使用，许多专家更喜欢这种方法。但我的关注点纯粹在设计上，所以我忽略了带参数构造函数的使用。但如果你喜欢带参数的构造函数，让我们稍微修改一下实现，这样你就可以在剩余的部分做同样的事了。

修改后的演示

假设你想让工厂初始化指定颜色的老虎，并且客户端可以选择这些颜色。让我们修改下面的代码片段，变化部分在原书中是粗体显示的。

修改后的实现

这里是修改后的实现代码。

Java

```
package jdp2e.abstractfactory.questions_answers;
interface Dog
{
      void speak();
      void preferredAction();
}
interface Tiger
{
      void speak();
      void preferredAction();
}
//Types of Dogs-wild dogs and pet dogs
class WildDog implements Dog
{
      @Override
      public void speak()
      {
            System.out.println("Wild Dog says loudly: Bow-Wow.");
      }
      @Override
      public void preferredAction()
      {
             System.out.println("Wild Dogs prefer to roam freely in jungles.\n");
      }
}
class PetDog implements Dog
{
      @Override
      public void speak()
      {
            System.out.println("Pet Dog says softly: Bow-Wow.");
      }
      @Override
      public void preferredAction()
      {
            System.out.println("Pet Dogs prefer to stay at home.\n");
      }
}
//Types of Tigers-wild tigers and pet tigers
class WildTiger implements Tiger
{
      public WildTiger(String color)      {             System.out.println("A wild tiger with "+ color+ " is created.");      }      @Override
      public void speak()
      {
            System.out.println("Wild Tiger says loudly: Halum.");
      }
      @Override
      public void preferredAction()
      {
            System.out.println("Wild Tigers prefer hunting in jungles.\n");
      }
}
class PetTiger implements Tiger
{
      public PetTiger(String color)      {            System.out.println("A pet tiger with "+ color+ " is created.");      }      @Override
      public void speak()
      {
            System.out.println("Pet Tiger says softly: Halum.");
      }
      @Override
      public void preferredAction()
      {
            System.out.println("Pet Tigers play in the animal circus.\n");
      }
}
//Abstract Factory
interface AnimalFactory
{
      Dog createDog();
      Tiger createTiger(String color);}
//Concrete Factory-Wild Animal Factory
class WildAnimalFactory implements AnimalFactory
{
      @Override
      public Dog createDog()
      {
            return new WildDog();
      }
      @Override
      public Tiger createTiger(String color)      {            return new WildTiger(color);      }}
//Concrete Factory-Pet Animal Factory
class PetAnimalFactory implements AnimalFactory
{
      @Override
      public Dog createDog()
      {
            return new PetDog();
      }
      @Override
      public Tiger createTiger(String color)      {            return new PetTiger(color);      }}
//Client
class AbstractFactoryPatternModifiedExample {
          public static void main(String[] args) {
            AnimalFactory myAnimalFactory;
            Dog myDog;
            Tiger myTiger;
            System.out.println("***Abstract Factory Pattern Demo***\n");
            //Making a wild dog through WildAnimalFactory
            myAnimalFactory = new WildAnimalFactory();
            myDog = myAnimalFactory.createDog();
            myDog.speak();
            myDog.preferredAction();
            //Making a wild tiger through WildAnimalFactory
            //myTiger = myAnimalFactory.createTiger();
            myTiger = myAnimalFactory.createTiger("white and black stripes");            myTiger.speak();
            myTiger.preferredAction();
            System.out.println("******************");
            //Making a pet dog through PetAnimalFactory
            myAnimalFactory = new PetAnimalFactory();
            myDog = myAnimalFactory.createDog();
            myDog.speak();
            myDog.preferredAction();
            //Making a pet tiger through PetAnimalFactory
             myTiger = myAnimalFactory.createTiger("golden and cinnamon stripes");            myTiger.speak();
            myTiger.preferredAction();
      }
}
```

修改后的输出

这里是修改后的输出。

Abstract Factory Pattern Demo

Wild Dog says loudly: Bow-Wow.

Wild Dogs prefer to roam freely in jungles.

A wild tiger with white and black stripes is created.

Wild Tiger says loudly: Halum.

Wild Tigers prefer hunting in jungles.

------

Pet Dog says softly: Bow-Wow.

Pet Dogs prefer to stay at home.

A pet tiger with golden and cinnamon stripes is created.

Pet Tiger says softly: Halum.

Pet Tigers play in the animal circus.





# QA(AI 版)

### 1. 我看到狗和老虎的接口都包含相同名称的方法（两个接口都包含 speak() 和 preferredAction() 方法）。这是强制性的吗？

结论：不是强制性的。

原因：在抽象工厂模式中，不同的产品（如狗和老虎）属于不同的产品等级结构，它们通常具有完全不同的接口和功能。示例中它们具有相同的方法名只是为了演示的便利性，在实际应用中，不同类型的产品接口应当根据其各自的职责独立定义，互不干扰。

### 2. 像这样使用抽象工厂会有什么挑战？

结论：难以支持新种类产品的扩展。

原因：抽象工厂接口确定了可以被创建的产品集合。如果需要添加一种新的产品（例如在现有的狗和老虎之外增加猫），则必须修改抽象工厂的接口，这会导致所有实现了该接口的具体工厂类都需要进行修改，从而违背了软件设计的开闭原则。

### 3. 你如何区分简单工厂模式、工厂方法模式和抽象工厂模式？



#### 简单工厂



简单工厂模式是属于创建型模式，是工厂模式的一种。**简单工厂模式**是**由一个工厂对象决定创建出哪一种产品类的实例**。（工厂类拥有一个工厂方法（create），接受了一个参数，通过不同的参数实例化不同的产品类。）

定义了一个创建对象的类，由这个类来封装实例化对象的行为。

例子：一个汽车生产工厂，生产不同品牌的汽车，每台汽车具有说广告标语的行为。将汽车生产工厂封装成一个简单工厂类。说汽车广告标语的行为封装为一个接口。

- 优点：很明显，简单工厂的特点就是“简单粗暴”，通过一个含参的工厂方法，可以实例化任何产品类。
- 缺点：
  - 任何”东西“的子类都可以被生产，负担太重。**当所要生产产品种类非常多时，工厂方法的代码量可能会很庞大**。
  - **没有遵循开闭原则（对拓展开放，对修改关闭）**，简单工厂对于增加新的产品，无能为力。因为增加新产品只能通过修改工厂方法来实现。

#### 工厂方法

结合上面的简单工厂，工厂方法进一步解耦合，把工厂类进行抽象，不再负责所有实例的创建，而是把具体的创建工作交给其子类去完成，实例化延迟到子类加载，由子类来决定要实例化的类。

一句话概括就是，工厂方法定义了一个对象的接口，但由子类去决定实例化的类是哪一个。

一个电脑配件工厂，创建对应的品牌配件。



- 优点
  - 工厂方法模式就很好的减轻了工厂类的负担，把某一类/某一种东西交由一个工厂生产；（对应简单工厂的缺点)。
  - 同时增加某一类 ”东西“ 并不需要修改工厂类，只需要添加生产这类 ”东西“ 的工厂即可，使得工厂类**符合开闭原则**。
- 缺点
  - 对于某些可以形成产品族（一组产品）的情况处理比较复杂，因为要写一堆类。





#### 抽象工厂

抽象工厂：提供一个接口，用于创建相关或依赖的家族，而不需要明确指定具体类。

例子：假设你做了一个大型游戏，里面人物角色的种族属性及世界观都是不同的。比如，普通人类，魔法师，外星物种…



#### 总结

- 简单工厂 ： 使用一个工厂对象用来生产同一等级结构中的任意产品。（不支持拓展增加产品）
- 工厂方法 ： 使用多个工厂对象用来生产同一等级结构中对应的固定产品。（支持拓展增加产品）
- 抽象工厂 ： 使用多个工厂对象用来生产不同产品族的全部产品。（不支持拓展增加产品；支持增加产品族）

### 4. 在所有这些工厂示例中，你避免使用了参数化构造函数。这是故意的吗？

结论：是有意为之。

原因：工厂模式的核心目的是不在客户端暴露对象创建过程。不使用参数化构造函数可以让客户端在不需要了解对象具体初始化细节（如特定属性值）的情况下请求对象，具体的配置和初始化逻辑被完全封装在具体的工厂类内部，从而降低了系统的耦合度。