package onlineShopping.user;

import onlineShopping.cart.ShoppingCart;
import onlineShopping.order.OrderHistory;

public interface User {
    public String getName();
    public ShoppingCart getShoppingCart();
    OrderHistory getOrderHistory();

}
