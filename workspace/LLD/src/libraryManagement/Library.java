package libraryManagement;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Patron> patrons;
    private CheckOutManagement checkOutManagement;
    public Library(){
        books = new ArrayList<>();
        patrons = new ArrayList<>();
        checkOutManagement = CheckOutManagement.getInstance();
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void removeBook(Book book){
        books.remove(book);
    }
    public Book searchBookByName(String bookName){
        for(Book book : books){
            if(book.getBookName().equalsIgnoreCase(bookName)){
                return book;
            }
        }
        return null;
    }

    public Book searchBookByISBN(int ISBN){
        for(Book book : books){
            if(book.getISBN() == ISBN){
                return book;
            }
        }
        return null;
    }

    public List<Book> searchBookByAuthor(String author){
        List<Book> booksOfAuthor = new ArrayList<>();
        for(Book book : books){
            if(book.getAuthor().equalsIgnoreCase(author)){
                booksOfAuthor.add(book);
            }
        }
        return booksOfAuthor;
    }

    public void displayBooks(){
        int count = 0;
        for(Book book : books){
            count++;
            System.out.println(count+"\t"+book.toString());
        }
    }
    public void addPatron(Patron patron){
        patrons.add(patron);
    }
    public void removePatron(Patron patron){
        patrons.remove(patron);
    }

    public void checkOutBook(Patron patron,Book book){
        if(!book.isAvailable()){
            System.out.println("Book is not available !!!");
            return;
        }
        patron.addBooksIntoCheckoutBooks(book);
        checkOutManagement.addCheckoutEntity(patron,book);
    }

    public void  returnBook(Patron patron,Book book){
        patron.removeBookFromCheckoutBooks(book);
        checkOutManagement.returnBook(patron,book);
    }

    public void getCheckoutRecords(){
        checkOutManagement.checkoutRecords();
    }
}
