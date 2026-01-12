# QA L20

**问题 1：你能再举个现实生活中的例子，详细说说这个模式有什么用吗？**

回答：

心理学家多次记录过这样一个事实，当人们处于放松模式、没有压力时，表现是最好的；反过来说，当他们满脑子都是压力时，就拿不出好的结果。所以心理学家总是建议要在放松的心情下工作。你可以把这个简单的道理和电视机的例子联系起来。如果电视是开着的，它能娱乐你；如果是关着的，就不行。对吧？所以，**如果你想设计那种对象的行为会随着内部状态改变而改变的功能，这个模式就很有用**。除了这个例子，你还可以考虑顾客买在线票然后在稍后阶段退票的场景。退款金额可能会根据不同的条件而变化，比如你**在距离出发多少天之前取消车票。**

**问题 2：在这个例子里，你只考虑了电视的三个状态：开机、关机和静音。其实还有很多其他状态，比如处理连接问题的状态或者显示设置的状态。你为什么忽略了那些呢？**

回答：

直接的回答就是为了展示起来简单。如果系统里的状态数量显著增加，系统维护起来就会变得很困难，这也是这个设计模式面临的主要挑战之一。但是如果你理解了这个实现方式，你就可以很容易地添加任何你想要的状态。

**问题 3：我注意到 GoF 在他们那本著名的书里，把状态模式和策略模式画成了类似的结构。看到这个我很困惑。**

回答：

是的，结构确实很像，但你需要注意它们的意图是不同的。除了这个主要区别，你还可以简单地这样想：策略模式提供了一个比生成子类更好的替代方案。另一方面，在状态设计模式中，不同类型的行为被封装在一个状态对象里，上下文会把工作委托给这些状态中的任何一个。当上下文的内部状态发生变化时，它的行为也会随之改变。

状态模式还能帮我们在某些情境下避免写大量的 if 条件语句。再看看我们的例子。如果电视处于关机状态，它就不能直接变成静音状态。从这个状态，它只能变成开机状态。所以，如果你不喜欢用状态设计模式，你可能就得像下面这样编写按下开机按钮的代码：

Java

```
class TV
{
//Some code before
public void pressOnButton()
{
if(currentState==Off )
{
System.out.println (" You pressed Onbutton. Going from Off to OnState");
//Do some operations
}
if(currentState==On )
 {
   System.out.println (" You pressed On button. TV is already  in On state");
 }
//TV presently is in mute mode
else
 {
   System.out.println (" You pressed On button . Going from Mute mode to On State");
 }
//Do some operations
}
```

注意，对于不同种类的按钮按下操作，你需要重复这些检查逻辑。比如，对于 pressOffButton() 和 pressMuteButton() 方法，你也需要重复这些检查并执行相应的操作。

如果你不从状态的角度去思考，一旦你的代码库变大，维护起来就会变得很困难。

**问题 4：你们的实现是如何支持开闭原则的？**

回答：

这些电视状态中的每一个都是对修改关闭的，但是你可以向 TV 类中添加全新的状态。

**问题 5：策略模式和状态模式之间有什么共同特征？**

回答：

两者都能促进组合和委托的使用。

**问题 6：在我看来这些状态对象表现得就像单例一样。这么说对吗？**

回答：

是的。大多数时候它们都是这样运作的。

**问题 7：你能避免在方法参数中使用上下文吗。比如，你能避免在下面这样的语句中使用它们吗？（简单来说，他在问：**“我每次调用按钮方法时，非得把电视机（TV）作为参数传进去吗？能不能不传？”**）**

Java

```
void pressOnButton(TV context);
....
```

回答：



如果你不想每次都在参数里传，那你就在**创建状态对象的时候**，把电视机传进去，让状态对象把它**存起来**。”

```
package jdp2e.state.modified.demo;

interface PossibleStates
{
    void pressOnButton();
    void pressOffButton();
    void pressMuteButton();
}

class Off implements PossibleStates
{
    TV tvContext;
    //Initially we'll start from Off state
    public Off(TV context)
    {
        //System.out.println(" TV is Off now.");
        this.tvContext = context;
    }

    //Users can press any of these buttons at this state-On, Off or Mute
    //TV is Off now, user is pressing On button
    @Override
    public void pressOnButton()
    {
         System.out.println(" You pressed On button. Going from Off to On state");
        tvContext.setCurrentState(tvContext.getOnState());
        System.out.println(tvContext.getCurrentState().toString());
    }

    //TV is Off already, user is pressing Off button again
    @Override
    public void pressOffButton()
    {
         System.out.println(" You pressed Off button. TV is already in Off state");
    }

    //TV is Off now, user is pressing Mute button
    @Override
    public void pressMuteButton()
    {
         System.out.println(" You pressed Mute button.TV is already in Off state, so Mute operation will not work.");
    }

    public String toString()
    {
        return "\t**TV is switched off now.**";
    }
}

class On implements PossibleStates
{
    TV tvContext;
    public On(TV context)
    {
        //System.out.println(" TV is On now.");
        this.tvContext = context;
    }

    //Users can press any of these buttons at this state-On, Off or Mute
    //TV is On already, user is pressing On button again
    @Override
    public void pressOnButton()
    {
         System.out.println("You pressed On button. TV is already in On state.");
    }

    //TV is On now, user is pressing Off button
    @Override
    public void pressOffButton()
    {
         System.out.println(" You pressed Off button.Going from On to Off state.");
        tvContext.setCurrentState(tvContext.getOffState());
        System.out.println(tvContext.getCurrentState().toString());
    }

    //TV is On now, user is pressing Mute button
    @Override
    public void pressMuteButton()
    {
         System.out.println("You pressed Mute button.Going from On to Mute mode.");
        tvContext.setCurrentState(tvContext.getMuteState());
        System.out.println(tvContext.getCurrentState().toString());
    }

    public String toString()
    {
        return "\t**TV is switched on now.**";
    }
}

class Mute implements PossibleStates
{
    TV tvContext;
    public Mute(TV context)
    {
        this.tvContext = context;
    }

    //Users can press any of these buttons at this state-On, Off or Mute
    //TV is in mute, user is pressing On button
    @Override
    public void pressOnButton()
    {
         System.out.println("You pressed On button.Going from Mute mode to On state.");
        tvContext.setCurrentState(tvContext.getOnState());
        System.out.println(tvContext.getCurrentState().toString());
    }

    //TV is in mute, user is pressing Off button
    @Override
    public void pressOffButton()
    {
         System.out.println("You pressed Off button. Going from Mute mode to Off state.");
        tvContext.setCurrentState(tvContext.getOffState());
        System.out.println(tvContext.getCurrentState().toString());
    }

    //TV is in mute already, user is pressing mute button again
    @Override
    public void pressMuteButton()
    {
         System.out.println(" You pressed Mute button.TV is already in Mute mode.");
    }

    public String toString()
    {
        return "\t**TV is silent(mute) now**";
    }
}

class TV
{
    private PossibleStates currentState;
    private PossibleStates onState;
    private PossibleStates offState;
    private PossibleStates muteState;

    public TV()
    {
        onState=new On(this);
        offState=new Off(this);
        muteState=new Mute(this);
        setCurrentState(offState);
    }

    public PossibleStates getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(PossibleStates currentState)
    {
        this.currentState = currentState;
    }

    public void pressOffButton()
    {
        currentState.pressOffButton();
    }

    public void pressOnButton()
    {
        currentState.pressOnButton();
    }

    public void pressMuteButton()
    {
        currentState.pressMuteButton();
    }

    public PossibleStates getOnState()
    {
        return onState;
    }

    public PossibleStates getOffState()
    {
        return offState;
    }

    public PossibleStates getMuteState()
    {
        return muteState;
    }
}

//Client
public class StatePatternAlternativeImplementation {
    public static void main(String[] args) {
         System.out.println("***State Pattern Alternative Implementation Demo***\n");
        //Initially TV is Off.
        TV tv = new TV();
         System.out.println("User is pressing buttons in the following sequence:");
        System.out.println("Off->Mute->On->On->Mute->Mute->Off\n");

        //TV is already in Off state.Again Off button is pressed.
        tv.pressOffButton();
        //TV is already in Off state.Again Mute button is pressed.
        tv.pressMuteButton();
        //Making the TV on
        tv.pressOnButton();
        //TV is already in On state.Again On button is pressed.
        tv.pressOnButton();
        //Putting the TV in Mute mode
        tv.pressMuteButton();
        //TV is already in Mute mode.Again Mute button is pressed.
        tv.pressMuteButton();
        //Making the TV off
        tv.pressOffButton();
    }
}
```

**输出**

```
***State Pattern Alternative Implementation Demo***
User is pressing buttons in the following sequence:
Off->Mute->On->On->Mute->Mute->Off

 You pressed Off button. TV is already in Off state
 You pressed Mute button.TV is already in Off state, so Mute operation will not work.
 You pressed On button. Going from Off to On state
    **TV is switched on now.**
You pressed On button. TV is already in On state.
You pressed Mute button.Going from On to Mute mode.
    **TV is silent(mute) now**
 You pressed Mute button.TV is already in Mute mode.
You pressed Off button. Going from Mute mode to Off state.
    **TV is switched off now.**
```





**问题 8：在这些实现中，TV 是一个具体类。为什么这种情况下你没有针对接口编程？**

**回答：** 我假设 TV 类是不会变的，所以为了减少程序的代码量，我忽略了那部分。但是是的，你总是可以从一个定义了契约的接口开始。

**问题 9：状态设计模式的优点和缺点是什么？**

**回答：** **优点：**遵循开闭原则，你可以很容易地添加新的状态和新的行为。而且，扩展状态行为也很容易。例如，在这个实现中，你可以为 TV 类添加一个新的状态和一个新的行为，而不需要修改 TV 类本身。 它减少了 if-else 语句的使用，也就是说降低了条件逻辑的复杂度（参考问题 3 的答案）。

**缺点：** 状态模式也被称为状态对象。所以，你可以认为更多的状态需要更多的代码，显而易见的副作用就是你的维护难度会增加。

**问题 10：在 TV 类的构造函数中，你把 TV 初始化为 Off 状态。那么，状态类和上下文类都可以触发状态转换吗？**

**回答：** 是的。