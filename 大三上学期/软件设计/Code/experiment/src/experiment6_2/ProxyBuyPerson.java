package experiment6_2;


public class ProxyBuyPerson implements Person{
    private RealBuyPerson realBuyPerson;
    private String[] productList;
    private String[] priceList;

    public ProxyBuyPerson(String[] productList, String[] priceList) {
        this.productList = productList;
        this.priceList = priceList;
    }

    public void PreBuyProduct(){
        System.out.println("Before buying, record the goods your friends need:");
        for(int i = 1;i <= productList.length;i++){
            System.out.println("Friend "+i+" needs to buy: "+productList[i-1]);
        }
    }

    @Override
    public void BuyProduct() {
        PreBuyProduct();
        System.out.println("Proxy Man is buying products...");
        for (String product : productList) {
            realBuyPerson = new RealBuyPerson(product);
            realBuyPerson.BuyProduct();
        }
        PostBuyProduct();
    }

    public void PostBuyProduct(){
        System.out.println("After purchase, record the amount paid by friends:");
        for(int i = 1;i <= priceList.length;i++){
            System.out.println("Friend "+i+" needs to pay: "+priceList[i-1]);
        }
    }
}
