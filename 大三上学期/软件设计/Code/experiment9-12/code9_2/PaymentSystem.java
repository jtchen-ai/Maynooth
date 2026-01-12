package experiment9_2;

class PaymentSystem {
    public boolean deductPoints(PointsGift gift, int userPoints) {
        System.out.println("Deducting " + gift.getPoints() + " points for gift: " + gift.getName());
        System.out.println(gift.getName() + " still has " + (userPoints - gift.getPoints()) + " points left.");
        if (userPoints >= gift.getPoints()) {
            return true;
        } else {
            System.out.println("Insufficient points! Required: " + gift.getPoints() + ", Available: " + userPoints);
            return false;
        }
    }
}
