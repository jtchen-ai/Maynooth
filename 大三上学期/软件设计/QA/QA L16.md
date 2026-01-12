# QA L16



**1. 在这个模式中，我看到子类可以简单地根据需要重新定义方法。这种理解正确吗？**

是的。

**2. 在抽象类 BasicEngineering 中，只有一个方法是抽象的，其他两个方法是具体方法。这背后的原因是什么？**

这是一个只有3个方法的简单例子，我希望子类只覆盖 completeSpecialPaper 方法。其他方法对于两个流派都是通用的，它们不需要被子类覆盖。

**3. 考虑这样一种情况：假设你想在 BasicEngineering 类中添加更多的方法，但你希望只有在子类需要它们时才去执行这些方法，否则就忽略它们。这种情况在一些博士课程中非常常见，有些课程并非对所有候选人都是强制性的。例如，如果一个学生拥有特定的资格，他或她可能不需要参加这些科目的讲座。你能用模板方法模式设计这种情况吗？**

是的，我们可以。基本上，你需要放置一个钩子，它其实就是一个能帮助我们在算法中控制流程的方法。

为了展示这种设计的例子，我在 BasicEngineering 中增加了一个名为 isAdditionalPapersNeeded 的方法。让我们假设计算机科学的学生需要完成这门课，但电子学的学生可以选择跳过这篇论文。让我们来看看程序和输出。

修改后的实现

以下是修改后的实现。关键的变化用粗体显示（注：在下方的代码块中已保留）。

Java

```
package jdp2e.templatemethod.questions_answers;
abstract class BasicEngineering
{
    //Making the method final to prevent overriding.
    public final void completeCourse()
    {
        //The course needs to be completed in the following sequence
        //1.Math-2.SoftSkills-3.Special Paper-4.Additional Papers(if any)
        //Common Papers:
        completeMath();
        completeSoftSkills();
        //Specialization Paper:
        completeSpecialPaper();
        if (isAdditionalPapersNeeded())
        {
            completeAdditionalPapers();
        }
    }
    private void completeMath()
    {
        System.out.println("1.Mathematics");
    }
    private void completeSoftSkills()
    {
        System.out.println("2.SoftSkills");
    }
    public abstract void completeSpecialPaper();
    
    
    private void completeAdditionalPapers()    {        System.out.println("4.Additional Papers are needed for this course.");    }    //By default, AdditionalPapers are needed for a course.    
    
    
    boolean isAdditionalPapersNeeded()    {        return true;    }}




class ComputerScience extends BasicEngineering
{
    @Override
    public void completeSpecialPaper()
    {
        System.out.println("3.Object-Oriented Programming");
    }
    //Additional papers are needed for ComputerScience
    //So, there is no change for the hook method.
}


class Electronics extends BasicEngineering
{
    @Override
    public void completeSpecialPaper()
    {
        System.out.println("3.Digital Logic and Circuit Theory");
    }
    //Overriding the hook method:    //Indicating that AdditionalPapers are not needed for Electronics.    @Override
   
   
    public  boolean isAdditionalPapersNeeded()    {        return false;    }}
public class TemplateMethodPatternModifiedExample {
    
    
    
    public static void main(String[] args) {
         System.out.println("***Template Method Pattern Modified Demo***\n");
        BasicEngineering preferrredCourse = new ComputerScience();
         System.out.println("Computer Sc. course will be completed in following order:");
        preferrredCourse.completeCourse();
        System.out.println();
        preferrredCourse = new Electronics();
         System.out.println("Electronics course will be completed in following order:");
        preferrredCourse.completeCourse();
    }
}
```

修改后的输出

以下是修改后的输出：

Template Method Pattern Modified Demo

Computer Sc. course will be completed in following order:

1.Mathematics

2.SoftSkills

3.Object-Oriented Programming

4.Additional Papers are needed for this course.

Electronics course will be completed in following order:

1.Mathematics

2.SoftSkills

3.Digital Logic and Circuit Theory

注意：你可能更喜欢另一种方法。例如，你可以在 BasicEngineering 中制作一个默认方法 isAdditionalpapersneeded。然后在 Electronics 类中覆盖该方法，并将方法体设为空。但是如果你将其与前一种方法进行比较，这种方法看起来并不好。

**4. 看起来这个模式与建造者模式很相似。这种理解正确吗？**

不正确。你不应该忘记核心意图；模板方法是一种行为设计模式，而建造者是一种创建型设计模式。在建造者模式中，客户或顾客是老板，他们可以控制算法的顺序。另一方面，在模板方法模式中，你是老板，你把代码放在一个中心位置，并且只提供相应的行为。例如，注意 BasicEngineering 中的 completeCourse 方法，看看课程完成的顺序是如何在那里定义的。所以，你对执行流程拥有绝对的控制权。你还可以根据需要更改模板，然后其他参与者需要跟随你。

**5. 使用模板设计模式的主要优势是什么？**

你可以控制算法的流程。客户端无法更改它们。注意抽象类 BasicEngineering 中的 completeCourse 被标记了 final 关键字。通用的操作被放置在一个中心位置，例如在抽象类中。子类只能重新定义变化的部分，这样你就可以避免重复的代码。

**6. 与模板设计模式相关的主要挑战是什么？**

客户端代码无法指导步骤的顺序。如果你需要这种方式，你可以遵循建造者模式。子类可能会覆盖父类中定义的方法，即隐藏父类中的原始定义，这可能会违反里氏替换原则，该原则基本上是说：如果 S 是 T 的子类型，那么 T 类型的对象可以用 S 类型的对象替换。你可以从维基百科的链接了解详情。更多的子类意味着代码更加分散，维护也更困难。

**7. 看起来子类也可以覆盖 BasicEngineering 中的其他父类方法。这种理解正确吗？**

你可以这样做，但理想情况下这不应该是你的意图。在这个模式中，你可能不希望完全覆盖所有的父类方法来在子类中引入根本性的变化。在这方面，它不同于简单的多态性。(你想这么干，你用这个模式干嘛)