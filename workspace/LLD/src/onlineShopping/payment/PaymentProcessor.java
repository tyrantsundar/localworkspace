package onlineShopping.payment;

public interface PaymentProcessor {
    PaymentDetail doPayment(double amount);
}
