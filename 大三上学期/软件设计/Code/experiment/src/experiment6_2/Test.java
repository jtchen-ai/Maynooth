package experiment6_2;

public class Test {
    public static void main(String[] args) {
        String[] productList = {"iphone", "chocolate", "pen"};
        String[] priceList = {"6800", "200", "30"};
        ProxyBuyPerson proxyBuyPerson = new ProxyBuyPerson(productList, priceList);
        proxyBuyPerson.BuyProduct();
    }
}
