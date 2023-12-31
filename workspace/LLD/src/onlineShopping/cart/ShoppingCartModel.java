package onlineShopping.cart;

import onlineShopping.product.IProductObserver;
import onlineShopping.product.ProductComponent;
import onlineShopping.product.ProductObserverSubject;
import onlineShopping.user.User;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartModel implements ShoppingCart, IProductObserver {
    List<ProductObserverSubject> productObserverSubjects;
    List<ProductComponent> productComponentList;
    public ShoppingCartModel(){
        this.productComponentList = new ArrayList<>();
        this.productObserverSubjects = new ArrayList<>();
    }
    @Override
    public void addProduct(ProductComponent productComponent) {
        this.productComponentList.add(productComponent);
        if(productComponent instanceof  ProductObserverSubject){
            ProductObserverSubject subject = ( ProductObserverSubject)productComponent;
            subject.addObserver(this);
        }
    }
    @Override
    public void addProduct(List<ProductComponent> productComponents) {
        for(ProductComponent productComponent : productComponents){
            addProduct(productComponent);
        }
    }
    @Override
    public void removeProduct(ProductComponent productComponent) {
        this.productComponentList.remove(productComponent);
    }


    @Override
    public void update(ProductObserverSubject productObserverSubject) {
        for(ProductComponent productComponent : productComponentList){
            if(productComponent == productObserverSubject){
                System.out.println("Quantity Updated >> "+productComponent.getName());
            }
        }
    }

    @Override
    public void addProductObserver(ProductObserverSubject productObserverSubject) {
        this.productObserverSubjects.add(productObserverSubject);
    }

    @Override
    public void removeProductObserver(ProductObserverSubject productObserverSubject) {
        this.productObserverSubjects.remove(productObserverSubject);
    }
}
