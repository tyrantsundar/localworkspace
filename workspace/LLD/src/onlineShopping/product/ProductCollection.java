package onlineShopping.product;

import java.util.ArrayList;
import java.util.List;

public class ProductCollection extends ProductComponent{
    private String category;
    private List<ProductComponent> productComponentList;

    public ProductCollection(String category) {
        this.category = category;
        this.productComponentList = new ArrayList<>();
    }

    @Override
    public void addChild(ProductComponent productComponent){
        this.productComponentList.add(productComponent);
    }
    @Override
    public void removeChild(ProductComponent productComponent){
        this.productComponentList.remove(productComponent);
    }

    @Override
    public String getCategory(){
        return category;
    }
    @Override
    public ProductComponent getChild(int i){
        return this.productComponentList.get(i);
    }
    @Override

    public List<ProductComponent> getProductComponentList() {
        return productComponentList;
    }
    @Override
    public ProductComponent findByCategory(String category){
        if(this.category.equalsIgnoreCase(category)){
            return this;
        }
        for(ProductComponent component : this.getProductComponentList()){
            ProductComponent searchResult = component.findByCategory(category);
            if(searchResult != null){
                return searchResult;
            }
        }
        return  null;
    }

    @Override
    public void findByName(List<ProductComponent> searchList,String name){
        for(ProductComponent component : this.getProductComponentList()){
            component.findByName(searchList,name);
        }
    }

    @Override
    public void print(){
        System.out.println("Product category >>> "+this.category);
        for(ProductComponent product : this.getProductComponentList()){
            product.print();
        }
    }
    @Override
    public boolean isAvailable(){
        for(ProductComponent productComponent : this.getProductComponentList()){
            boolean isAvailable = productComponent.isAvailable();
            if(isAvailable){
                return true;
            }
        }
        return false;
    }
}
