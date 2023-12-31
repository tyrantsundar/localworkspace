package onlineShopping.product;

public interface IProductObserver {
    void update(ProductObserverSubject productObserverSubject);
    void addProductObserver(ProductObserverSubject productObserver);
    void removeProductObserver(ProductObserverSubject productObserver);

}
