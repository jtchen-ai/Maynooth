package experiment18_1;

import java.util.List;

class ProductList extends AbstractObjectList{
    public ProductList(List<Object> products){
        this.objects = products;
    }

    @Override
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }
}
