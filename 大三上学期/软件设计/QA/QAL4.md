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