package experiment15_1;

public class Context {
    protected Strategy strategy;
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    public int showOperation(int num1, int num2, int num3){
        return strategy.doOperation(num1, num2, num3);
    }
}
