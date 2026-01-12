# L17QA



```
package jdp2e.command.modified.demo;

/**
 * In general, an undo operation involves complex logic.
 * But for simplicity, in this example, I assume that executeDo() can either 
 * add 2 with a given integer or it can switch on a machine.
 * Similarly, executeUnDo() can either subtract 2 from a given number() or,
 * it will switch off a machine. But you cannot go beyond the initialized 
 * value (i.e. 10 in this case)
 */
interface Command {
    void executeDo();
    void executeUnDo();
}

class AdditionCommand implements Command {
    private Receiver receiver;

    public AdditionCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void executeDo() {
        receiver.performDo();
    }

    @Override
    public void executeUnDo() {
        receiver.performUnDo();
    }
}

class PowerCommand implements Command {
    private Receiver receiver;

    public PowerCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void executeDo() {
        receiver.performDo();
    }

    @Override
    public void executeUnDo() {
        receiver.performUnDo();
    }
}

// To deal with multiple receivers, we are using interfaces here
interface Receiver {
    // It will add 2 with a number or switch on the m/c
    void performDo();

    // It will subtract 2 from a number or switch off the m/c
    void performUnDo();
}

// Receiver Class
class Receiver1 implements Receiver {
    private int myNumber;

    public int getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(int myNumber) {
        this.myNumber = myNumber;
    }

    public Receiver1() {
        myNumber = 10;
        System.out.println("Receiver1 initialized with " + myNumber);
        System.out.println("The objects of receiver1 cannot set beyond " + myNumber);
    }

    @Override
    public void performDo() {
        System.out.println("Received an addition request.");
        int presentNumber = getMyNumber();
        setMyNumber(presentNumber + 2);
        System.out.println(presentNumber + " + 2 =" + this.myNumber);
    }

    @Override
    public void performUnDo() {
        System.out.println("Received an undo addition request.");
        int presentNumber = this.myNumber;
        // We started with number 10. We'll not decrease further.
        if (presentNumber > 10) {
            setMyNumber(this.myNumber - 2);
            System.out.println(presentNumber + " - 2 =" + this.myNumber);
            System.out.println("\t Undo request processed.");
        } else {
            System.out.println("Nothing more to undo...");
        }
    }
}

// Receiver2 Class
class Receiver2 implements Receiver {
    boolean status;

    public Receiver2() {
        System.out.println("Receiver2 initialized ");
        status = false;
    }

    @Override
    public void performDo() {
        System.out.println("Received a system power on request.");
        if (status == false) {
            System.out.println("System is starting up.");
            status = true;
        } else {
            System.out.println("System is already running. So, power on request is ignored.");
        }
    }

    @Override
    public void performUnDo() {
        System.out.println("Received a undo request.");
        if (status == true) {
            System.out.println("System is currently powered on.");
            status = false;
            System.out.println("\t Undo request processed. System is switched off now.");
        } else {
            System.out.println("System is switched off at present.");
            status = true;
            System.out.println("\t Undo request processed. System is powered on now.");
        }
    }
}

// Invoker class
class Invoker {
    Command commandToBePerformed;

    public void setCommand(Command command) {
        this.commandToBePerformed = command;
    }

    public void executeCommand() {
        commandToBePerformed.executeDo();
    }

    public void undoCommand() {
        commandToBePerformed.executeUnDo();
    }
}

// Client
public class ModifiedCommandPatternExample {
    public static void main(String[] args) {
        System.out.println("***Command Pattern Q&As***");
        System.out.println("***A simple demo with undo supported operations***\n");
        
        // Client holds both the Invoker and Command Objects
        // Testing receiver - Receiver1
        System.out.println("-----Testing operations in Receiver1-----");
        Receiver intendedreceiver = new Receiver1();
        Command currentCmd = new AdditionCommand(intendedreceiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(currentCmd);
        
        System.out.println("*Testing single do/undo operation*");
        invoker.executeCommand();
        invoker.undoCommand();
        
        System.out.println("_______");
        System.out.println("**Testing a series of do/undo operations**");
        
        // Executed the command 2 times
        invoker.executeCommand();
        // invoker.undoCommand();
        invoker.executeCommand();
        
        // Trying to undo 3 times
        invoker.undoCommand();
        invoker.undoCommand();
        invoker.undoCommand();
        
        System.out.println("\n-----Testing operations in Receiver2-----");
        intendedreceiver = new Receiver2();
        currentCmd = new PowerCommand(intendedreceiver);
        invoker.setCommand(currentCmd);
        
        System.out.println("*Testing single do/undo operation*");
        invoker.executeCommand();
        invoker.undoCommand();
        
        System.out.println("_______");
        System.out.println("**Testing a series of do/undo operations**");
        
        // Executing the command 2 times
        invoker.executeCommand();
        invoker.executeCommand();
        
        // Trying to undo 3 times
        invoker.undoCommand();
        invoker.undoCommand();
        invoker.undoCommand();
    }
}
```

**问题 1：在这个例子中，你只处理了一个接收者。如何处理多个接收者？另外 GoF 的定义说这个模式支持可撤销操作，你能展示一个使用此模式并包含真正撤销操作的例子吗？**

回答：

为了处理多个接收者，我在代码中引入了一个公共接口 Receiver，让 Receiver1 和 Receiver2 这两个不同的接收者都去实现这个接口的方法。关于撤销操作，通常逻辑会比较复杂，但为了演示，我在代码里做了一些设定。Receiver1 初始化值为 10，它的操作是给数字加 2，撤销就是减 2，但规定不能减到 10 以下。Receiver2 负责机器的开关，它的操作是开机，如果机器已经开着就忽略；它的撤销操作则是反转当前状态，比如把开着的机关掉，或者把关着的机打开。

**问题 2：在这个修改后的程序中，两个接收者做的事情完全不同。这是故意的吗？**

回答：

是的。这正好展示了命令设计模式的能力和灵活性。你可以看到这两个接收者里的 performDo 方法实际上执行的是完全不同的动作。Receiver1 是在一个整数基础上加 2，而 Receiver2 是打开机器。你可能会觉得用 addNumber 和 powerOn 这样的名字更合适，但在这种情况下，我需要同时处理这两个接收者，所以需要使用一个公共的接口和公共的方法名。这样一来，如果需要处理两个具有不同方法名的接收者，你可以用一个通用的名字来代替，使用公共接口，然后通过多态性，就可以很轻松地调用这些方法了。

**问题 3：为什么需要 Invoker（调用者）？**

回答：

当处理一系列复杂的命令时，这种方法很有意义。你创建命令对象发送给接收者并调用某些方法，但你是通过 Invoker 来执行这些命令的，Invoker 会去调用命令对象的方法，比如 executeCommand。对于简单的情况，Invoker 类并不是非要不可的，比如命令对象只有一个方法要执行，你完全可以省掉 Invoker 直接调用。但是，当你想要在日志文件或者队列中记录多个命令时，Invoker 就起到了很重要的作用。

比如遭遇高并发，多个命令，此时可以让 invoker 维护一个队列，这个队列就可以有条不紊地执行多个命令



 

```
import java.util.ArrayList;
import java.util.List;

class AdvancedInvoker {
    // 这里就是所谓的“队列”或“日志文件”的内存形式
    // 它记录了所有执行过的命令
    private List<Command> commandHistory = new ArrayList<>();

    public void executeAndRecord(Command cmd) {
        // 1. 执行命令
        cmd.executeDo();
        // 2. 把命令加入历史队列
        commandHistory.add(cmd);
    }

    // 批量撤销功能
    public void undoAll() {
        System.out.println("开始执行批量撤销...");
        // 倒序遍历队列，逐个撤销
        for (int i = commandHistory.size() - 1; i >= 0; i--) {
            Command cmd = commandHistory.get(i);
            cmd.executeUnDo();
        }
        commandHistory.clear();
    }
}
```





**问题 4：为什么需要记录这些日志？**

回答：

如果你想要执行撤销或者重做操作，这些日志就会非常有用。

**问题 5：命令模式的主要优势有哪些？**

回答：

主要有这几点：请求的创建和最终的执行是解耦的，客户端不需要知道 Invoker 具体是如何执行操作的；你可以创建宏，也就是一系列的命令组合；添加新的命令不需要修改现有的系统；最重要的一点是，你可以支持撤销和重做操作。

**问题 6：命令模式面临的挑战有哪些？**

回答：

为了支持更多的命令，你需要创建更多的类，随着时间推移，维护起来会比较困难。另外，当发生错误情况时，如何处理错误或者决定如何处理返回值会变得比较棘手。客户端可能想知道这些情况，但因为你把命令和客户端代码解耦了，这些情况就很难处理。特别是在多线程环境下，如果 Invoker 运行在不同的线程中，这个挑战会变得更加明显。