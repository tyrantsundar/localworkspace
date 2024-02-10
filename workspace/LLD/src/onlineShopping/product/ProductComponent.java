package onlineShopping.product;

import java.util.List;

public abstract class ProductComponent {
    public void addChild(ProductComponent productComponent){
        throw new UnsupportedOperationException();
    }
    public void removeChild(ProductComponent productComponent){
        throw new UnsupportedOperationException();
    }

    public String getName(){
        return null;
    }
    public void setQuantity(int quantity) {
        throw new UnsupportedOperationException();
    }
    public String getDetails() {
        return null;
    }

    public String getCategory(){
        return null;
    }

    public ProductComponent getChild(int i){
        return null;
    }

    public List<ProductComponent> getProductComponentList() {
        return null;
    }

    public ProductComponent findByCategory(String category){
        return null;
    }

    public void findByName(List<ProductComponent> searchList,String name){
        throw new UnsupportedOperationException();
    }


    public void print(){
        throw new UnsupportedOperationException();
    }

    public boolean isAvailable(){
        return false;
    }
    public boolean isAvailable(int neededQuantity){
        return false;
    }

    public Integer getQuantity() {
        return null;
    }

    public Double getPrice() {
        return null;
    }

}
