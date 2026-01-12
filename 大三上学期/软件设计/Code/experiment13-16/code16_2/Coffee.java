package experiment16_2;

class Coffee extends DivdrinkTemplate{
    @Override
    protected void brew() {
        System.out.println("用开水冲咖啡");
    }

    @Override
    protected void pourIncup() {
        System.out.println("把咖啡倒进杯子里");
    }

    @Override
    protected void addConditions() {
        System.out.println("加糖");
    }
}
