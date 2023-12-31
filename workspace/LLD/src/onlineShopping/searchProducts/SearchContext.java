package onlineShopping.searchProducts;

import onlineShopping.product.ProductComponent;

import java.util.List;

public class SearchContext {
    SearchStrategy strategy;
    ProductComponent productComponents;

    public SearchContext(SearchStrategy strategy,ProductComponent productComponents) {
        this.strategy = strategy;
        this.productComponents = productComponents;
    }

    public List<ProductComponent> search(String query){
        return strategy.search(productComponents,query);
    }
}
