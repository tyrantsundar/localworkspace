package libraryManagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckoutEntity {
    private static  final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
    private String bookName;
    private String patronName;
    private int count;
    private Date checkedOutDate;
    private Date returnDate;
    private boolean isReturnBack;

    public CheckoutEntity(String bookName, String patronName, int count) {
        Calendar instance = Calendar.getInstance();
        this.bookName = bookName;
        this.patronName = patronName;
        this.count = count;
        this.isReturnBack = false;
        this.checkedOutDate = instance.getTime();
        long monthTimeInMillisec = 30 * 24 * 60 * 60 * 1000l;
        this.returnDate = new Date(instance.getTime().getTime() + monthTimeInMillisec);
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getCheckedOutDate() {
        return checkedOutDate;
    }

    public void setCheckedOutDate(Date checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    public String getReturnDate() {
        return simpleDateFormat.format(returnDate);
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturnBack() {
        return isReturnBack;
    }

    public void setReturnBack(boolean returnBack) {
        isReturnBack = returnBack;
    }

    @Override
    public String toString() {
        return "CheckoutEntity{" +
                "bookName='" + bookName + '\'' +
                ", patronName='" + patronName + '\'' +
                ", count=" + count +
                ", checkedOutDate=" + simpleDateFormat.format(checkedOutDate) +
                ", returnDate=" + simpleDateFormat.format(returnDate) +
                ", isReturnBack=" + isReturnBack +
                '}';
    }
}
