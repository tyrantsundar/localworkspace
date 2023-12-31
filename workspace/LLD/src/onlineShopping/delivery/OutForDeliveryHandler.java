package onlineShopping.delivery;

public class OutForDeliveryHandler implements OrderHandler {
    private OrderHandler nextHandler;

    public OutForDeliveryHandler() {
        nextHandler = new DeliveryHandler();
    }

    @Override
    public void processOrder() {
        if(nextHandler == null){
            System.out.println("Order out for delivery not yet received !");
        }else{
            System.out.println("Order out for delivery");
            nextHandler.processOrder();
        }
    }

    @Override
    public void setNextHandler(OrderHandler orderHandler) {
        this.nextHandler = orderHandler;
    }
}
