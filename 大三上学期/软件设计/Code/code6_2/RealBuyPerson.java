package experiment6_2;


public class RealBuyPerson implements Person{
    private String productName;

    public RealBuyPerson(String productName) {
        this.productName = productName;
    }

    @Override
    public void BuyProduct() {
        System.out.println(productName+" is added to the cart");
    }
}
