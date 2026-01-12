# QA L6：

### 1. 代理有哪些不同的类型？

结论：主要包括远程代理、虚拟代理和保护代理。

原因：根据文档后续问答环节的分类讨论，代理模式通常根据使用场景分为：用于代表不同地址空间对象的远程代理，用于根据需要创建开销大的对象的虚拟代理，以及用于控制对原始对象访问权限的保护代理。

**虚拟代理**

用于通过延迟加载（Lazy Loading）来节省系统资源，仅在真正需要时才创建高开销对象。

```
public class VirtualProxy implements Subject {
    private ConcreteSubject realSubject;

    @Override
    public void doSomeWork() {
        // 关键点：判断是否为空。
        // 只有在真正调用方法时，才创建昂贵的 ConcreteSubject
        if (realSubject == null) {
            System.out.println("初始化开销巨大的真实对象...");
            realSubject = new ConcreteSubject();
        }
        realSubject.doSomeWork();
    }
}
```



**保护代理 (Protection Proxy)**

用于控制对原始对象的访问权限，决定谁可以调用，谁不可以调用。代理充当了守门人的角色，可以在将请求转发给真实主题之前检查调用者的权限，拒绝未授权的访问，就是封装了权限控制的逻辑



**代码示例：**

Java

```
public class ProtectionProxy implements Subject {
    private ConcreteSubject realSubject;
    private String userRole;

    public ProtectionProxy(String userRole) {
        this.realSubject = new ConcreteSubject();
        this.userRole = userRole;
    }

    @Override
    public void doSomeWork() {
        // 关键点：在转发请求前进行权限检查
        if ("ADMIN".equals(this.userRole)) {
            realSubject.doSomeWork();
        } else {
            System.out.println("权限不足：只有管理员可以访问。");
            // 不调用 realSubject.doSomeWork()
        }
    }
}
```



**远程代理**



为位于不同地址空间（如远程服务器）的对象提供一个本地的代表，就是封装了远程连接的逻辑

```
public class RemoteProxy implements Subject {
    // 这里没有直接持有 ConcreteSubject，因为他在另一台机器上

    @Override
    public void doSomeWork() {
        // 关键点：逻辑不仅仅是简单的转发，而是网络通信
        try {
            // 1. 建立与远程服务器的连接（Socket连接等）
            // connectToRemoteServer();
            
            // 2. 将请求序列化并通过网络发送给远程的 ConcreteSubject
            // sendRequest("doSomeWork");
            System.out.println("正在向远程服务器发送请求...");

            // 3. 接收并解析远程返回的结果
            // receiveResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```



### 2. 你可以在代理类的构造函数中创建具体主题（ConcreteSubject）的实例吗。

结论：这种做法是可行的，但通常不建议在所有场景下都通过构造函数**立即创建**。

原因：如果在代理类的构造函数中直**接实例化真实主题**，就**无法实现延迟加载**。这意味着无论通过代理**是否真正调用了真实主题**的方法，真实主题都会被创建，这在真实主题创建开销很大时会削弱代理模式的优势。



**构造函数直接调用**

```
public class Proxy implements Subject {
    private ConcreteSubject realSubject;

    public Proxy() {
        // 这里的缺点是：一旦创建 Proxy，就会立即创建 ConcreteSubject。
        // 如果 ConcreteSubject 初始化需要连接数据库或加载大量文件，
        // 即使后续没有调用 doSomeWork，这部分开销也已经产生了。
        this.realSubject = new ConcreteSubject(); 
    }

    @Override
    public void doSomeWork() {
        // 直接委派，因为对象已经存在
        realSubject.doSomeWork();
    }
}
```

**一般的延迟调用**

```
public class Proxy implements Subject {
    private ConcreteSubject realSubject;

    public Proxy() {
        // 构造函数不进行繁重的初始化操作
        this.realSubject = null;
    }

    @Override
    public void doSomeWork() {
        // 延迟加载逻辑：
        // 1. 检查对象是否尚未创建
        if (this.realSubject == null) {
            // 2. 只有在真正需要执行任务时，才创建昂贵的对象
            System.out.println("正在初始化重型对象...");
            this.realSubject = new ConcreteSubject();
        }
        
        // 3. 执行任务
        realSubject.doSomeWork();
    }
}
```





### 3. 但是在这种延迟实例化技术中，你可能会在多线程应用程序中创建不必要的对象。这是正确的吗？

结论：是正确的。

原因：在多线程环境下，如果多个线程同时通过代理对象检查真实主题是否已经存在，并且都判断为空，那么这些线程可能会各自创建一个真实主题的实例，从而破坏对象的唯一性并导致资源浪费。

### 4. 你能给出一个远程代理的例子吗？

结论：自动柜员机（ATM）系统是远程代理的一个典型应用。

原因：ATM 可以持有位于远程服务器上的银行信息的代理对象。这个代理对象在本地代表了远程的银行信息，使得客户端与远程系统的交互就像在操作本地对象一样。



```
// 1. Subject 接口：定义银行服务的功能
// 客户端（ATM）和服务器（银行）都遵循这个接口
interface BankService {
    double getBalance(String accountId);
    boolean withdraw(String accountId, double amount);
}

// 2. RealSubject (真实主题)：位于远程服务器上的银行服务
// 在实际应用中，这个类运行在银行的服务器端，通过网络接收请求
class RemoteBankService implements BankService {
    @Override
    public double getBalance(String accountId) {
        // 模拟数据库查询
        System.out.println("[远程服务器] 正在查询数据库中账户 " + accountId + " 的余额...");
        return 5000.00; // 假设余额
    }

    @Override
    public boolean withdraw(String accountId, double amount) {
        // 模拟扣款逻辑
        System.out.println("[远程服务器] 正在处理账户 " + accountId + " 的取款请求: " + amount);
        return true; // 假设取款成功
    }
}

// 3. Proxy (代理)：位于 ATM 机上的本地代理
// 它负责建立网络连接，将请求序列化并发送给远程服务器
class ATMProxy implements BankService {
    private RemoteBankService remoteService; // 持有远程对象的引用（在实际中可能是 RMI 存根或网络 Socket）

    public ATMProxy() {
        // 模拟建立连接的过程
        this.remoteService = new RemoteBankService(); 
    }

    @Override
    public double getBalance(String accountId) {
        System.out.println("[ATM 本地代理] 连接远程银行服务器...");
        simulateNetworkDelay();
        
        // 将请求转发给远程对象
        double balance = remoteService.getBalance(accountId);
        
        System.out.println("[ATM 本地代理] 接收到远程数据。");
        return balance;
    }

    @Override
    public boolean withdraw(String accountId, double amount) {
        System.out.println("[ATM 本地代理] 正在加密取款请求并发送至服务器...");
        simulateNetworkDelay();
        
        // 将请求转发给远程对象
        boolean success = remoteService.withdraw(accountId, amount);
        
        if (success) {
            System.out.println("[ATM 本地代理] 操作成功，准备出钞。");
        }
        return success;
    }

    // 模拟网络延迟的方法
    private void simulateNetworkDelay() {
        try {
            Thread.sleep(1000); // 模拟 1 秒的网络延迟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 4. Client：ATM 客户端代码
public class ATMClient {
    public static void main(String[] args) {
        // 客户端直接与 ATM 代理交互，以为所有的处理都是在本地完成的
        // 实际上代理在背后处理了复杂的网络通信
        BankService atmMachine = new ATMProxy();

        String myAccount = "123-456-789";

        System.out.println("--- 欢迎使用 ATM ---");
        
        // 1. 查询余额
        System.out.println("\n用户请求查询余额...");
        double balance = atmMachine.getBalance(myAccount);
        System.out.println("您的当前余额为: $" + balance);

        // 2. 取款
        System.out.println("\n用户请求取款 $100...");
        atmMachine.withdraw(myAccount, 100.0);
    }
}
```



### 5. 什么时候可以使用虚拟代理？

结论：当创建复杂对象或高开销对象的成本很高时使用。

原因：文档指出在程序世界中创建重型对象的多个实例是昂贵的。虚拟代理作为替身，允许系统仅在真正需要使用该对象时才进行创建，从而节省系统内存并提高性能。

### 6. 什么时候可以使用保护代理？

结论：当原始对象需要被保护或需要控制访问权限时使用。

原因：保护代理可以在将请求转发给真实对象之前，检查调用者是否具有所需的访问权限。

### 7. 代理行为像装饰器。这是正确的吗？

结论：不完全准确，它们结构相似但意图不同。

原因：虽然代理模式和装饰器模式都通过包装对象来实现，且代理在某些方面看似在增强功能，但装饰器主要用于动态地添加职责或行为，而代理模式的核心目的是控制对对象的访问。

### 8. 与代理相关的缺点是什么？

结论：会增加系统的**复杂度**和可能的**响应延迟**。

原因：引入代理模式会增加系统中类的数量，使得代码结构更加复杂。此外，由于客户端与真实对象之间增加了一层中介，请求的处理路径变长，特别是在远程代理的情况下，可能会导致请求响应速度变慢。