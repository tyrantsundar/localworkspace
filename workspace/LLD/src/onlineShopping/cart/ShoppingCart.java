package onlineShopping.cart;

import onlineShopping.product.ProductComponent;

import java.awt.color.ProfileDataException;
import java.util.ArrayList;
import java.util.List;

public interface ShoppingCart {
    public void addProduct(ProductComponent productComponent);
    public void addProduct(List<ProductComponent> productComponents);
    public void removeProduct(ProductComponent productComponent);
}
