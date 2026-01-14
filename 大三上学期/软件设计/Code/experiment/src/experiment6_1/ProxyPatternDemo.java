package experiment6_1;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        String fileName = "test.png";
        ProxyImage proxyImage = new ProxyImage(fileName);
        proxyImage.display();
    }
}
