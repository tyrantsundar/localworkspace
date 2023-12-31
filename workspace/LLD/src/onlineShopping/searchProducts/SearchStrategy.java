package onlineShopping.searchProducts;

import onlineShopping.product.SingleProduct;
import onlineShopping.product.ProductComponent;

import java.util.List;

public interface SearchStrategy {
    public List<ProductComponent> search(ProductComponent productComponent, String query);
    default void addAllProducts(List<ProductComponent> list, ProductComponent productComponent){
        if(productComponent instanceof SingleProduct){
            list.add(productComponent);
        }else{
            for(ProductComponent product : productComponent.getProductComponentList()){
                addAllProducts(list,product);
            }
        }
    }
}
