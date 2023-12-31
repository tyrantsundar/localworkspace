package onlineShopping.order;

import onlineShopping.delivery.OrderHandler;
import onlineShopping.delivery.PackingOrderHandler;
import onlineShopping.payment.PaymentDetail;
import onlineShopping.payment.PaymentProcessor;
import onlineShopping.payment.PaymentType;
import onlineShopping.product.ProductComponent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private List<ProductOrder> orderedProducts;
    private OrderHandler orderHandler;
    private double totalCost;
    private String date;
    private PaymentDetail paymentDetail;

    static SimpleDateFormat smp = new SimpleDateFormat("dd/MMM/yyyy");

    public Order() {
        this.orderedProducts = new ArrayList<>();
        this.date = smp.format(new Date());
    }

    public  void addProducts(ProductComponent productComponent,int quantity){
        double productPrice = productComponent.getPrice();
        this.totalCost += (productPrice*quantity);
        this.orderedProducts.add(new ProductOrder(productComponent,quantity));
    }

    public  void addProducts(List<ProductComponent> productComponents){
        for(ProductComponent productComponent : productComponents){
            addProducts(productComponent, productComponent.getQuantity());
        }
    }

    public List<ProductOrder> getOrderedProducts() {
        return orderedProducts;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void doPayment(PaymentProcessor paymentProcessor){
        this.paymentDetail = paymentProcessor.doPayment(totalCost);
        this.orderHandler = new PackingOrderHandler();
    }

    public OrderHandler getOrderHandler() {
        return orderHandler;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderedProducts=" + orderedProducts+
                ", paymentDetail=" + paymentDetail.toString() +
                '}';
    }
}
