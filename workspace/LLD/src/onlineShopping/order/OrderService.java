package onlineShopping.order;

import onlineShopping.delivery.OrderHandler;
import onlineShopping.delivery.PackingOrderHandler;
import onlineShopping.exception.InsufficientQuantityException;
import onlineShopping.payment.PaymentProcessor;
import onlineShopping.product.ProductComponent;
import onlineShopping.user.User;

import java.util.List;

public class OrderService {
    public void orderProduct(User user, ProductComponent product, int quantity, PaymentProcessor paymentProcessor){
        if(product.isAvailable(quantity)){
            Order order = new Order();
            order.addProducts(product,quantity);
            // DoPayment
            order.doPayment(paymentProcessor);
            // Add into payment history
            user.getOrderHistory().addOrder(order);
            // Update inventory
            updateInventory(product,quantity);
            //Chain of Responsibility
            order.getOrderHandler().processOrder();
        }else{
            throw new InsufficientQuantityException("Required quantity is not available !!!");
        }
    }
    private void updateInventory(ProductComponent product, int quantity){
        int availableQuantity = product.getQuantity();
        int reducedQuantity = availableQuantity-quantity;
        product.setQuantity(reducedQuantity);
    }
}
