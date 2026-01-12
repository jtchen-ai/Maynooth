package experiment9_2;

class IntegralCalibrationSystem {
    public boolean verifyPoints(PointsGift pointsGift){
        System.out.println("Verifying points for gift: " + pointsGift.getName());
        return pointsGift.getPoints() >= 0;
    }
}
