package onlineShopping.product;

import java.util.ArrayList;
import java.util.List;

public class SingleProduct extends ProductComponent implements ProductObserverSubject {
    private List<IProductObserver> observers;
    private String name;
    private String details;
    private int quantity;
    private double price;

    public SingleProduct(String name, String details,int quantity,double price) {
        this.name = name;
        this.details = details;
        this.quantity = quantity;
        this.price = price;
        this.observers = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyObservers();
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public boolean isAvailable(){
        return this.quantity>0;
    }
    @Override
    public boolean isAvailable(int neededQuantity){
        return this.quantity >= neededQuantity;
    }

    @Override
    public void findByName(List<ProductComponent> searchList, String name){
        if(this.getName().equalsIgnoreCase(name)){
            searchList.add(this);
        }
    }

    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "SingleProduct{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public void addObserver(IProductObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(IProductObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IProductObserver observer : this.observers){
            observer.update(this);
        }
    }
}
