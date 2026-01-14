package experiment16_2;

abstract class DivdrinkTemplate {
    public final void div(){
        boilWater();
        brew();
        pourIncup();
        addConditions();
    };
    protected void boilWater(){
        System.out.println("烧水");
    };
    protected abstract void brew();
    protected abstract void pourIncup();
    protected abstract void addConditions();
}
