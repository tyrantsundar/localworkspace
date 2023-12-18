package libraryManagement;

import java.util.HashSet;
import java.util.Set;

public class Patron {
    private String name;
    private int id;
    private Set<Book> checkOutBooks;

    public Patron(String name, int id) {
        this.name = name;
        this.id = id;
        this.checkOutBooks = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Book> getCheckOutBooks() {
        return checkOutBooks;
    }

    public void addBooksIntoCheckoutBooks(Book book){
        this.checkOutBooks.add(book);
    }

    public void removeBookFromCheckoutBooks(Book book){
        this.checkOutBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", checkOutBooks=" + checkOutBooks +
                '}';
    }
}
