package onlineShopping.user;

import onlineShopping.cart.ShoppingCart;
import onlineShopping.cart.ShoppingCartModel;
import onlineShopping.order.OrderHistory;

public class UserModel implements User{
    private String name;
    private ShoppingCart shoppingCart;
    private OrderHistory orderHistory;

    public UserModel(String name) {
        this.name = name;
        this.shoppingCart = new ShoppingCartModel();
        this.orderHistory = new OrderHistory();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

}
