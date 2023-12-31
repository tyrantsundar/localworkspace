package onlineShopping.searchProducts;

import onlineShopping.product.ProductCollection;
import onlineShopping.product.ProductComponent;

import java.util.ArrayList;
import java.util.List;

public class SearchByName implements SearchStrategy{
    @Override
    public List<ProductComponent> search(ProductComponent productComponent, String query) {
        List<ProductComponent> searchList = new ArrayList<>();
        for(ProductComponent product : productComponent.getProductComponentList()){
           product.findByName(searchList,query);
        }
        return searchList;
    }
}
