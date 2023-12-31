package onlineShopping.delivery;

public class ShippingOrderHandler implements OrderHandler {
    private OrderHandler nextHandler;

    public ShippingOrderHandler() {
        nextHandler = new OutForDeliveryHandler();
    }

    @Override
    public void processOrder() {
        if(nextHandler == null){
            System.out.println("Shipping in progress");
        }else{
            System.out.println("Shipping completed !\nSoon will be out for delivery !!");
            nextHandler.processOrder();
        }
    }

    @Override
    public void setNextHandler(OrderHandler orderHandler) {
        this.nextHandler = orderHandler;
    }
}
