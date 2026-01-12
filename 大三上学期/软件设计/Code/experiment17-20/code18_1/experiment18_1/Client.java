package experiment18_1;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<Object> products = new ArrayList<Object>();
        products.add("Product 1");
        products.add("Product 2");
        products.add("Product 3");
        products.add("Product 4");

        ProductList productList = new ProductList(products);
        AbstractIterator iterator = productList.createIterator();

        System.out.println("Forward iteration:");
        while (!iterator.isLast()) {
            System.out.println(iterator.getNextItem());
            iterator.next();
        }



    }
}
