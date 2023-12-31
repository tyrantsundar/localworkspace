package onlineShopping;

import onlineShopping.order.OrderService;
import onlineShopping.payment.PaymentFactory;
import onlineShopping.payment.PaymentProcessor;
import onlineShopping.payment.PaymentType;
import onlineShopping.product.SingleProduct;
import onlineShopping.product.ProductCollection;
import onlineShopping.product.ProductComponent;
import onlineShopping.searchProducts.SearchByCategory;
import onlineShopping.searchProducts.SearchByName;
import onlineShopping.searchProducts.SearchStrategy;
import onlineShopping.user.User;
import onlineShopping.user.UserModel;

import java.util.List;

public class Main {

    public static void addProducts(ProductComponent productComponent){

        // Composite Design pattern is used to categorise products
        // So that we can treat single product and group of products equally
        ProductComponent book1 = new SingleProduct("Ponniyin Selvan","VeryGoodBook",5,125);
        ProductComponent book2 = new SingleProduct("The Alchemeist","Good Book",3,100);
        ProductComponent book3 = new SingleProduct("Ikigai","GoodBook",7,90);

        ProductComponent selfImprovementBooks = new ProductCollection("SelfImprovment");
        selfImprovementBooks.addChild(book2);
        selfImprovementBooks.addChild(book3);

        ProductComponent historyBooks = new ProductCollection("History");
        historyBooks.addChild(book1);

        ProductComponent books = new ProductCollection("Books");
        books.addChild(historyBooks);
        books.addChild(selfImprovementBooks);

        productComponent.addChild(books);

        ProductComponent dress = new ProductCollection("Dress");
        ProductComponent menDress = new ProductCollection("menDress");
        ProductComponent womenDress = new ProductCollection("womenDress");

        ProductComponent dhothi = new SingleProduct("Dhoti","Dhothi's for men",15,300);
        ProductComponent shirt = new SingleProduct("Shirt","UniSex - Men/Women",12,800);
        ProductComponent chudithar = new SingleProduct("Chudi","Girls Chudithar",10,1200);

        menDress.addChild(dhothi);
        womenDress.addChild(chudithar);

        dress.addChild(menDress);
        dress.addChild(womenDress);
        dress.addChild(shirt);

        productComponent.addChild(dress);
    }

    public static void main(String[] args) {
        ProductComponent allProduct = new ProductCollection("AllProduct");
        addProducts(allProduct);
        //Search By Name
        SearchStrategy nameSearch = new SearchByName();
        List<ProductComponent> shirts = nameSearch.search(allProduct, "Shirt");
        for(ProductComponent shirt : shirts){
            shirt.print();
        }

        //Search By Category
        SearchStrategy categorySearch  = new SearchByCategory();
        List<ProductComponent> books = categorySearch.search(allProduct, "SelfImprovment");
        for(ProductComponent book : books){
            book.print();
        }

        User user1 = new UserModel("sundar");
        user1.getShoppingCart().addProduct(shirts);

        User user2 = new UserModel("tyrant");
        OrderService orderService = new OrderService();
        PaymentProcessor paymentProcessor = PaymentFactory.getPaymentProcessor(PaymentType.UPI,"987643210@ybl");

        orderService.orderProduct(user2,shirts.get(0),10,paymentProcessor);
        user2.getOrderHistory().printAllOrderHistory();
    }
}
