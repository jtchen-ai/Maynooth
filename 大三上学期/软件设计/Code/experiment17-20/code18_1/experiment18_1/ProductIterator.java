package experiment18_1;

import java.util.List;

class ProductIterator implements AbstractIterator{
    private ProductList productList;
    private List<Object> products;
    private int cursor1;//正向遍历
    private int cursor2;//反向遍历

    public ProductIterator(ProductList list){
        this.productList = list;
        this.products = list.getObjects();
        this.cursor2 = products.size() - 1;
    }

    @Override
    public void next() {
        if(cursor1 < products.size()){
            cursor1++;
        }
    }

    @Override
    public boolean isLast() {
        return cursor1 == products.size();
    }

    @Override
    public void previous() {
        if (cursor2 > -1) {
            cursor2--;
        }
    }

    @Override
    public boolean isFirst() {
        return cursor2 == 0;
    }

    @Override
    public Object getNextItem() {
        return products.get(cursor1);
    }
}
