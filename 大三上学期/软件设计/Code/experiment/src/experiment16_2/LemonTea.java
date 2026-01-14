package experiment16_2;

class LemonTea extends DivdrinkTemplate{
    @Override
    protected void brew() {
        System.out.println("用开水冲茶叶");
    }

    @Override
    protected void pourIncup() {
        System.out.println("把茶倒进杯子里");
    }

    @Override
    protected void addConditions() {
        System.out.println("加柠檬");
    }
}
