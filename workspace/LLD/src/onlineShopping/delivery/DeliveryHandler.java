package onlineShopping.delivery;

public class DeliveryHandler implements OrderHandler {

    public DeliveryHandler(){}
    @Override
    public void processOrder() {
        System.out.println("Order Delivered !");
    }

    @Override
    public void setNextHandler(OrderHandler orderHandler) {

    }
}
