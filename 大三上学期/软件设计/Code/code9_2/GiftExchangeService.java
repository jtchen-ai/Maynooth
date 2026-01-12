package experiment9_2;

class GiftExchangeService {
    PaymentSystem paymentSystem;
    IntegralCalibrationSystem integralCalibrationSystem;
    LogisticsSystem logisticsSystem;

    public GiftExchangeService(){
        paymentSystem = new PaymentSystem();
        integralCalibrationSystem = new IntegralCalibrationSystem();
        logisticsSystem = new LogisticsSystem();
    }

    public void exchangeGift(PointsGift gift, int userPoints) {
        System.out.println("Starting gift exchange process for: " + gift.getName());
        if(integralCalibrationSystem.verifyPoints(gift)){
            if(paymentSystem.deductPoints(gift, userPoints)){
                logisticsSystem.dispatchGift(gift);
            }else{
                System.out.println("Gift exchange failed due to insufficient points.");
            }
        }else{
            System.out.println("Gift exchange failed due to invalid integral calibration.");
        }
    }
}
