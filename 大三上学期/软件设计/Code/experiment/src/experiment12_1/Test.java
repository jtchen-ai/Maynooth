package experiment12_1;

public class Test {
    public static void main(String[] args) {
        HandsetBrand ab;
        HandsetSoft game = new HandsetGame();
        HandsetSoft list = new HandsetAddressList();

        ab = new HandsetBrandA();
        ab.setHandsetSoft(game);
        ab.Run();
        ab.setHandsetSoft(list);
        ab.Run();

        ab = new HandsetBrandB();
        ab.setHandsetSoft(game);
        ab.Run();
        ab.setHandsetSoft(list);
        ab.Run();
    }
}
