package libraryManagement;

import java.util.*;

public class CheckOutManagement {
    private static Map<Patron, List<CheckoutEntity>> checkedOutBooks;
    private static CheckOutManagement checkOutManagement = null;
    private CheckOutManagement() {
        this.checkedOutBooks = new HashMap<>();
    }

    public static CheckOutManagement getInstance(){
        if(checkOutManagement == null){
            checkOutManagement = new CheckOutManagement();
        }
        return checkOutManagement;
    }

    public void addCheckoutEntity(Patron patron, Book book){
        CheckoutEntity checkoutEntity = new CheckoutEntity(book.getBookName(),patron.getName(),1);
        List<CheckoutEntity> list = new ArrayList<>();
        if(checkedOutBooks.containsKey(patron)){
            list = checkedOutBooks.get(patron);
        }
        list.add(checkoutEntity);
        this.checkedOutBooks.put(patron,list);
    }
    public void getDueDate(Patron patron, Book book){
        if(checkedOutBooks.containsKey(patron)){
            List<CheckoutEntity> list = checkedOutBooks.get(patron);
            for(CheckoutEntity checkoutEntity : list){
                if(checkoutEntity.getBookName().equalsIgnoreCase(book.getBookName())){
                    System.out.println("Due Date for "+book.getBookName()+" : "+checkoutEntity.getReturnDate());
                }
            }
            checkedOutBooks.put(patron,list);
        }
    }
    public void returnBook(Patron patron,Book book){
        if(checkedOutBooks.containsKey(patron)){
            List<CheckoutEntity> list = checkedOutBooks.get(patron);
            for(CheckoutEntity checkoutEntity : list){
                if(checkoutEntity.getBookName().equalsIgnoreCase(book.getBookName())){
                    checkoutEntity.setReturnBack(true);
                }
            }
            checkedOutBooks.put(patron,list);
        }
    }

    public void checkoutRecords(){
        for(Patron patron : checkedOutBooks.keySet()){
            for(CheckoutEntity checkoutEntity : checkedOutBooks.get(patron)){
                System.out.println(checkoutEntity.toString());
            }
        }
    }
}
