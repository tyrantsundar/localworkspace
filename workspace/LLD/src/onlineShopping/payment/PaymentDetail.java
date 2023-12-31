package onlineShopping.payment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentDetail {
    double paymentAmount;
    String paymentMode;
    String paymentDate;
    static SimpleDateFormat smp = new SimpleDateFormat("dd/MMM/yyyy");
    public PaymentDetail(double paymentAmount, String paymentMode) {
        this.paymentAmount = paymentAmount;
        this.paymentMode = paymentMode;
        this.paymentDate = smp.format(new Date());
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentDetail{" +
                "paymentAmount=" + paymentAmount +
                ", paymentMode='" + paymentMode + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                '}';
    }
}
