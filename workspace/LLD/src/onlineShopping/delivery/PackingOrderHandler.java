package onlineShopping.delivery;

public class PackingOrderHandler implements OrderHandler {
    private OrderHandler nextHandler;

    public PackingOrderHandler() {
        nextHandler = new ShippingOrderHandler();
    }
    @Override
    public void setNextHandler(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void processOrder() {
        System.out.println("Order Received Successfully !");
        if(nextHandler==null){
            System.out.println("Waiting for shipment !");
        }else{
            System.out.println("Proceed further for shipment");
            nextHandler.processOrder();
        }
    }
}
