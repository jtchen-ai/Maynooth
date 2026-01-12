package experiment8_1;

public class Test {
    public static void main(String[] args) {
        TwoHoleIf powerSocket = new PowerSocket();
        ThreeHoleIf laptopAdapter = new ChargeAdapter(powerSocket);
        laptopAdapter.headWithThreeHoles();
    }
}
