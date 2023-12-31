package onlineShopping.delivery;

public interface OrderHandler {
    void processOrder();
    void setNextHandler(OrderHandler orderHandler);
}
