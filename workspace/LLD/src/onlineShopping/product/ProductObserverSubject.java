package onlineShopping.product;

public interface ProductObserverSubject {
    void addObserver(IProductObserver observer);
    void removeObserver(IProductObserver observer);
    void notifyObservers();
}
