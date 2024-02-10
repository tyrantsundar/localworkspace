package onlineShopping.order;

import onlineShopping.product.ProductComponent;

public class ProductOrder {
    private String name;
    private String details;
    private int quantity;
    private double price;
    private String paymentMode;

    public ProductOrder(ProductComponent productComponent, int quantity) {
        this.name = productComponent.getName();
        this.details = productComponent.getDetails();
        this.quantity = quantity;
        this.price = productComponent.getPrice();
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
