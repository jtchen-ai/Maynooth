package experiment8_1;

class ChargeAdapter implements ThreeHoleIf{
    private TwoHoleIf twoHoleIf;

    public ChargeAdapter(TwoHoleIf twoHoleIf){
        this.twoHoleIf = twoHoleIf;
    }

    @Override
    public void headWithThreeHoles() {
        twoHoleIf.headWithTwoHoles();
        System.out.println("Adapting two-hole charger to three-hole charger");
        System.out.println("Laptop is now charging.");
    }
}
