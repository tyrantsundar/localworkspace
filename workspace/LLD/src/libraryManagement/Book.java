package libraryManagement;

public class Book implements IBook{
    private String author;
    private String bookName;
    private int ISBN;
    private boolean isAvailable;
    private int availableCount;

    public Book(String author, String bookName, int ISBN, boolean isAvailable, int availableCount) {
        this.author = author;
        this.bookName = bookName;
        this.ISBN = ISBN;
        this.isAvailable = isAvailable;
        this.availableCount = availableCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", ISBN=" + ISBN +
                ", isAvailable=" + isAvailable +
                ", availableCount=" + availableCount +
                '}';
    }
}
