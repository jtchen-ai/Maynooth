package experiment9_2;

public class Test {
    public static void main(String[] args) {
        PointsGift pointsGift = new PointsGift("Lucy", 200);
        GiftExchangeService giftExchangeService = new GiftExchangeService();
        int userPoints = 300;
        giftExchangeService.exchangeGift(pointsGift, userPoints);
    }
}
