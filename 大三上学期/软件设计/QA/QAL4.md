# QAL4 （原版）



## 问题 1：为什么要把 CreateAnimal 方法从客户端代码中分离出来？

回答：这正是我的真实意图。我希望由子类来创建特定的对象。如果你仔细观察，就会发现只有这个创建对象的部分在不同产品之间是变化的。

## 问题 2：像这样使用工厂模式有什么优势？

回答：这样做的好处是你把会变化的代码和不会变化的代码分离开了，也就是说简单工厂模式的优势在这里依然存在。这种技术

- 能帮你轻松地维护代码。你的代码没有紧密耦合，
- 所以你可以在系统中随时添加像 Lion、Bear 等新类，而不需要修改现有的架构。
- 遵循了对修改关闭但对扩展开放的原则。

## 问题 3：像这样使用工厂模式有什么挑战？

回答：如果你需要处理大量的类，那么你可能会遇到维护开销的问题。

## 问题 4：我看出来这个工厂模式支持两个平行的层级结构。这个理解对吗？

回答：观察得很敏锐。是的，从图 4-3 的类图中可以明显看出，这个模式支持平行的类层级结构。

在这个例子中，

- AnimalFactory、DogFactory 和 TigerFactory 位于一个层级结构中，
- 而 Animal、Dog 和 Tiger 位于另一个层级结构中。
- 所以，创建者和它们的创建物或者说产品是两个平行运行的层级结构。

## 问题 5：我应该总是用 abstract 关键字来标记工厂方法，以便子类去完成它们。这对吗？

回答：不对。如果创建者没有子类，你可能会想要一个默认的工厂方法。在那种情况下，你就不能用 abstract 关键字来标记工厂方法。

```
// 父类：创建者
abstract class Dialog {
    // 这里用了 abstract，意味着 Dialog 自己根本不知道怎么造按钮
    // 它强制要求必须有一个子类来实现这个方法
    public abstract Button createButton(); 

    public void render() {
        // ...
    }
}
```

**此时的问题：** 如果你现在的项目很简单，**根本没有** `WindowsDialog` 或 `WebDialog` 这种子类，你只想直接用 `Dialog`。

- 你会发现：**你用不了！**
- 因为 `Dialog` 是抽象的，你不能 `new Dialog()`。
- **死局**：你没有子类，而父类又是抽象的，导致你的代码跑不起来

为了展示工厂方法模式的真正威力，你可能需要遵循类似这里实现的设计。

## 问题 6：在我看来，工厂方法模式和简单工厂模式区别不大。这个理解对吗？

回答：不对，这个理解不对。

工厂方法模式的主要目的是提供一个框架，让**不同的子类可以通过这个框架制造不同的产品**。而在简单工厂模式中，你**没法像工厂方法模式那样灵活地改变产品**。最重要的是，**简单工厂模式的创建部分没有对修改关闭**，每当你**想要添加新产品时，都必须在工厂类里修改代码**，添加 if-else 代码块或者 switch 语句。

要记住 GoF 的定义，工厂方法是让一个类把实例化推迟到子类进行。所以在简单工厂模式中，只用了一个具体的工厂类，不需要重写创建方法，也没有子类参与最终的决策或产品制造过程。简单工厂模式只是单纯地把实例化逻辑和客户端代码分离开，在这种情况下，工厂类必须知道所有它能创建对象的类。而当使用工厂方法模式时，是把对象创建委托给子类，而且工厂方法事先并不确定具体会有哪些产品子类。

## 问题 7：在工厂方法模式中，我可以简单地使用子类化机制，也就是使用继承，然后实现定义在父类中的工厂方法。这对吗？

回答：如果你严格遵循 GoF 的定义，这个问题的答案是肯定的。但在许多应用程序或实现中，并没有使用抽象类或接口，这一点很重要。例如在 Java 中，XML 读取器对象是像这样使用的：

Java

```
//Some code before…
XMLReader xmlReader1 = XMLReaderFactory.createXMLReader();
//Some code after
```

XMLReaderFactory 在 Java 中是一个 final 类。所以你不能继承它。

但是当你像下面这样使用 SAXParserFactory 时，你使用的就是一个抽象类 SAXParserFactory。

Java

```
//some code before….
SAXParserFactory factory = SAXParserFactory.newInstance();
              SAXParser parser = factory.newSAXParser();
              XMLReader xmlReader2 = parser.getXMLReader();
//Some code after
```



# QAL4



**问答环节**

1. 为什么将 CreateAnimal() 方法与客户端代码分离？

结论：为了降低代码耦合度并提高可维护性。

原因：如果在客户端代码中包含具体的对象创建逻辑，当需要支持新类型时，会导致代码中出现大量的条件判断语句，如 if-else 或 switch 结构，这不仅造成代码重复，也难以维护 。将创建方法分离并推迟到子类中实现，可以避免在客户端硬编码具体的类，从而使代码结构更清晰 



2. 使用这种工厂模式有什么优势？

结论：易于扩展新类而无需修改现有代码。

原因：这种模式允许系统在不重新设置整个架构的情况下引入新特性或新模型 。通过定义一个创建对象的接口并由子类决定具体实例化哪一个类，当需要支持新的连接类型或产品模型时，开发者无需修改现有的核心代码，只需进行扩展即可 。







3. 使用这种工厂模式有什么挑战？

结论：为每个新产品创建对应的工厂子类，导致类爆炸和维护成本上升。

原因：从类图结构可以看出，为了实现这种模式，需要构建和维护对应的类层级 5。每当引入一个新的具体产品（例如 Tiger），通常就需要引入一个新的具体工厂类（例如 TigerFactory）来负责其创建，这会导致项目中类的总数增加 





4. 我看到工厂模式支持两个平行的层级结构。这是正确的吗？

结论：是正确的。

原因：如图所示，该模式包含**产品类的层级结构**（Animal 接口及其具体实现类如 Dog 和 Tiger）以及**创建者类的层级结构（AnimalFactory 及其具体子类如 DogFactory 和 TigerFactory）** 。这两个层级结构相互平行，具体的**工厂子类负责实例化对应的产品子类** 。





5. 我应该始终用 abstract 关键字标记工厂方法，以便子类可以完成它们。这是正确的吗？

结论：基本是正确的，核心在于**定义接口供子类实现**。

原因：工厂方法模式的概念是首先定义一个抽象的**创建者类（creator）**来**确立应用程序的基本结构**，而具体的**实例化过程**必须由**继承自该抽象类的子类**来执行 。因此，父类通常定义接口或抽象方法，将具体的实现决策权完全交给子类 。







6. 在我看来，工厂方法模式与简单工厂模式没什么不同。这是正确的吗？

结论：是不正确的，两者的实现机制不同。

原因：工厂方法模式的关键在于它让一个类将实例化操作推迟到了子类中进行 。工厂方法模式旨在解决这类代码难以维护的问题，通过继承和多态来处理对象创建，避免了修改原有代码的需要 。

```
// 1. 抽象产品 (和上面一样)
interface Payment {
    void pay();
}

// 2. 具体产品 (和上面一样)
class WeChatPay implements Payment {
    public void pay() { System.out.println("使用微信支付"); }
}
class AliPay implements Payment {
    public void pay() { System.out.println("使用支付宝支付"); }
}

// 3. 抽象工厂 (核心变化：这里变成了接口，不写具体逻辑)
interface PaymentFactory {
    Payment createPayment();
}

// 4. 具体工厂 (核心变化：每个产品都有自己的工厂)
class WeChatPayFactory implements PaymentFactory {
    public Payment createPayment() {
        return new WeChatPay(); // 具体的实例化在这里
    }
}

class AliPayFactory implements PaymentFactory {
    public Payment createPayment() {
        return new AliPay(); // 具体的实例化在这里
    }
}

// 客户端调用
PaymentFactory factory = new WeChatPayFactory(); // 用户选择具体的工厂
Payment payment = factory.createPayment();
payment.pay();
```



相比之下，简单工厂往往依赖于集中的条件判断逻辑（如 if-else 或 switch），

```
// 1. 抽象产品
interface Payment {
    void pay();
}

// 2. 具体产品
class WeChatPay implements Payment {
    public void pay() { System.out.println("使用微信支付"); }
}

class AliPay implements Payment {
    public void pay() { System.out.println("使用支付宝支付"); }
}

// 3. 简单工厂类 (核心就在这里)
class SimplePaymentFactory {
    public static Payment createPayment(String type) {
        if ("wechat".equals(type)) {
            return new WeChatPay();
        } else if ("ali".equals(type)) {
            return new AliPay();
        } else {
            throw new IllegalArgumentException("不支持的支付方式");
        }
    }
}

// 客户端调用
Payment payment = SimplePaymentFactory.createPayment("wechat");
payment.pay();
```









7. 在工厂方法模式中，我可以简单地使用子类化机制（即使用继承），然后实现工厂方法（在父类中定义）。这是正确的吗？

结论：是正确的。

原因：这描述了该模式的标准实现方式。开发过程从定义基本结构的抽象创建者类开始，随后通过派生出的子类来执行实际的实例化过程 。子类利用继承机制，具体实现父类中定义的工厂方法接口，从而完成特定对象的创建 。