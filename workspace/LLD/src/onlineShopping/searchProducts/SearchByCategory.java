package onlineShopping.searchProducts;

import onlineShopping.product.ProductCollection;
import onlineShopping.product.ProductComponent;

import java.util.ArrayList;
import java.util.List;

public class SearchByCategory implements SearchStrategy{
    @Override
    public List<ProductComponent> search(ProductComponent productComponent, String query) {
        List<ProductComponent> searchProucts = new ArrayList<>();
        if(productComponent.getCategory() != null && query.equalsIgnoreCase(productComponent.getCategory())){
            addAllProducts(searchProucts,productComponent);
        }else{
            for(ProductComponent product : productComponent.getProductComponentList()){
                if(product instanceof ProductCollection){
                    ProductComponent searchResult = product.findByCategory(query);
                    if(searchResult != null){
                        addAllProducts(searchProucts,searchResult);
                    }
                }
            }
        }
        return searchProucts;
    }
}
