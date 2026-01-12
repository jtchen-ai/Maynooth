# QA L21



**问题 1：为什么你要把事情搞得这么复杂？在第一个例子里，每个参与者本来可以直接相互交流，你完全可以跳过中介者不用。是这样吗？**

回答：

得考虑一种相对复杂的情况。比如，一个参与者只有在目标参与者在线时才能给对方发送消息，这是聊天服务器的常见场景。所以，按照你提议的架构，如果他们试图互相交流，每个人在发送消息之前都需要维护所有其他参与者的状态。而且如果参与者数量不断增加，你能想象系统的复杂性会有多大吗？所以，中介者肯定能帮你处理像这样的场景。





图 21-6 和图 21-7 描绘了这个场景。情况 1 是不使用中介者的交流，

```
class UserWithoutMediator:
    def __init__(self, name):
        self.name = name
        self.is_online = False
        # [痛点1] 每个用户都要维护一个“必须联系的人”的列表
        self.contacts = [] 

    def connect_to(self, other_user):
        self.contacts.append(other_user)

    def send_message(self, message):
        print(f"[{self.name}] 正在尝试群发消息: {message}")
        for contact in self.contacts:
            # [痛点2] 业务逻辑（检查是否在线）分散在每个用户类里
            # 如果以后逻辑变了（比如增加了黑名单功能），你得改这儿
            if contact.is_online:
                print(f"    -> 发送给 {contact.name}: 成功")
            else:
                print(f"    -> 发送给 {contact.name}: 失败 (对方离线)")

# --- 客户端代码 ---
# 创建用户
alice = UserWithoutMediator("Alice")
bob = UserWithoutMediator("Bob")
charlie = UserWithoutMediator("Charlie")

# 设置状态
alice.is_online = True
bob.is_online = True
charlie.is_online = False # Charlie 离线

# [痛点3] 令人头大的手动连线（N * N-1 复杂度）
# 假如你有100个用户，这里要写近10000行代码来互相添加好友
alice.connect_to(bob)
alice.connect_to(charlie)

bob.connect_to(alice)
bob.connect_to(charlie)

charlie.connect_to(alice)
charlie.connect_to(bob)

# 发送消息
alice.send_message("大家好！")
```

情况 2 是使用中介者的交流。





```
# --- 中介者接口 ---
class ChatMediator:
    def send_message(self, message, sender):
        pass
    def register_user(self, user):
        pass

# --- 具体的中介者 (聊天室) ---
class ChatRoom(ChatMediator):
    def __init__(self):
        self.users = []

    def register_user(self, user):
        self.users.append(user)
        user.mediator = self # 自动建立反向连接

    # [优势]: 所有的复杂逻辑都集中在这里！
    def send_message(self, message, sender):
        for user in self.users:
            # 不发给自己
            if user != sender:
                # 集中管理的逻辑：只发给在线用户
                if user.is_online:
                    user.receive(message)
                else:
                    print(f"    [系统日志] {user.name} 离线，消息未送达")

# --- 同事类 (用户) ---
class User(object):
    def __init__(self, name):
        self.name = name
        self.is_online = False
        self.mediator = None # 只依赖中介者，不依赖其他具体用户

    def send(self, message):
        print(f"[{self.name}] 发送: {message}")
        # 用户变得非常“傻”，只管发，不管怎么发、发给谁
        self.mediator.send_message(message, self)

    def receive(self, message):
        print(f"    -> [{self.name}] 收到: {message}")

# --- 客户端代码 ---
# 1. 创建中介者
chat_room = ChatRoom()

# 2. 创建用户
alice = User("Alice")
bob = User("Bob")
charlie = User("Charlie")

# 3. 注册 (中介者统一管理)
chat_room.register_user(alice)
chat_room.register_user(bob)
chat_room.register_user(charlie)

# 设置状态
alice.is_online = True
bob.is_online = True
charlie.is_online = False

# 4. 发送
alice.send("大家好！")
```



另外，你可以看看这个上下文中的修改版实现。在修改后的实现中，你可以看到中介者正在维护逻辑，比如谁应该被允许在服务器上发布消息，以及应该如何对待他。

**问题 2：使用中介者模式有什么优点？**

回答：

你可以降低系统中对象通信的复杂性。这个模式促进了松耦合。它减少了系统中的子类数量。你可以把多对多的关系替换成一对多的关系，所以读起来和理解起来更容易。看看我们在这一节的第一个插图就知道了。一个显而易见的效果就是维护变得简单了。你可以通过中介者在这个模式下提供集中控制。简而言之，我们的目标总是从代码中移除对象间的紧耦合，而这个模式在这方面得分很高。

**问题 3：使用中介者模式有什么缺点？**

回答：

在某些情况下，实现恰当的封装比较棘手。如果你把太多的逻辑放进去，中介者对象的架构可能会变得很复杂。不恰当地使用中介者模式可能会导致出现上帝类反模式。你会在第 28 章学到反模式。有时候维护中介者本身会成为一个大问题。

**问题 4：如果你需要添加新的规则或逻辑，你可以直接把它添加到中介者里。是这样吗？**

回答：

是的。

**问题 5：我发现外观模式和中介者模式有一些相似之处。是这样吗？**

回答：

是的。Steve Holzner 在他的书《Design Pattern for Dummies》里提到了这种相似性，他把中介者模式描述为多路复用的外观模式。在中介者模式中，你不是在处理单个对象的接口，而是在多个对象之间建立一个多路复用的接口，以提供平滑的过渡。

**问题 6：在这个模式中，你减少了各种对象之间的互连数量。由于这种减少，你获得了什么主要好处？**

回答：

对象之间过多的互连会形成一个单体系统，导致系统的行为很难改变，因为系统的行为分布在许多对象中。作为一个副作用，你可能需要创建许多子类才能在这个系统中引入那些改变。

**问题 7：在修改后的实现中，你使用了 Thread.Sleep(1000)。这是什么原因？**

回答：

你可以忽略那个。我用它来模拟现实生活的场景。我假设参与者是在正确阅读完消息后才发布消息的，而这个活动至少需要 1 秒钟。

**问题 8：在有些应用程序中，我看到只使用了具体的中介者。这种方法可以吗？**

回答：

中介者模式并不限制你只能使用具体的中介者。但我喜欢遵循专家的建议，也就是针对超类，比如抽象类或接口进行编程是更好的方法，而且从长远来看，它能提供更多的灵活性。

**问题 9：我能不能简单地说，如果一个类仅仅是调用了多个对象的方法，它就是中介者？**

回答：

绝对不是。中介者的主要目的是简化系统中对象之间复杂的通信。我建议你时刻记住 GoF 的定义以及相应的概念。

**问题 10：在第一个实现中，两个发送方法（中介者和员工的）都叫 sendMessage()，但在修改后的实现中，它们是不一样的，一个是 send() 而另一个是 sendMessage()。我需要遵循什么特定的命名约定吗？**

回答：

不需要。两种都可以。这是你的选择。



