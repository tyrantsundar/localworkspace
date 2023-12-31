package onlineShopping.payment;

public class PaymentFactory {
    public static PaymentProcessor getPaymentProcessor(PaymentType paymentType, String paymentCard_Id){
        switch (paymentType){
            case UPI -> {
                return new UPIPayment(paymentCard_Id,paymentType);
            }
            case DEBIT_CARD -> {
                return new DebitCardPayment(paymentCard_Id,paymentType);
            }
            case CREDIT_CARD -> {
                return new CreditCardPayment(paymentCard_Id,paymentType);
            }
        }
        return null;
    }
}
