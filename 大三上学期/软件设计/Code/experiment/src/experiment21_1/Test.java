package experiment21_1;

public class Test {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        Colleague colleague_I = new Colleague_I(mediator);
        Colleague colleague_II = new Colleague_II(mediator);
        mediator.setColleagueI(colleague_I);
        mediator.setColleagueII(colleague_II);
        colleague_I.send("Hello, ColleagueII!");
        colleague_II.send("Hello, ColleagueI!");

    }
}
