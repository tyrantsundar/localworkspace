package libraryManagement;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        Patron patron = new Patron("sundar",1);
        Book book = new Book("Kalki","Ponniyin Selvan",101,true,10);
        library.addBook(book);
        library.checkOutBook(patron,book);
        book = new Book("JayaKandhan","Yanai_Doctor",111,true,10);
        library.addBook(book);
        library.checkOutBook(patron,book);
        patron = new Patron("prabu",2);
        library.addPatron(patron);
        library.checkOutBook(patron,book);

        System.out.println("Display Books");
        library.displayBooks();
        System.out.println("\nCheckout Records");
        library.getCheckoutRecords();
        System.out.println("\nReturning the Book");
        library.returnBook(patron,book);
        System.out.println("\nCheckout Records");
        library.getCheckoutRecords();
    }
}
