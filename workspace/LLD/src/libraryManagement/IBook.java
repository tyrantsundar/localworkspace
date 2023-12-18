package libraryManagement;

public interface IBook {
    String getAuthor();
    boolean isAvailable();
    int getAvailableCount();
    String getBookName();
}
