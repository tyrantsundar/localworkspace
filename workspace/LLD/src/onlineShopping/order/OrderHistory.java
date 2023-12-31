package onlineShopping.order;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    List<Order> orderList;

    public OrderHistory() {
        this.orderList = new ArrayList<>();
    }

    public void addOrder(Order order){
        this.orderList.add(order);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void printAllOrderHistory(){
        for(Order order : orderList){
            System.out.println(order.toString());
        }
    }
}
