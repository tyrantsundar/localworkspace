package onlineShopping.payment;

public class DebitCardPayment implements PaymentProcessor
{
    private String cardNo;
    private PaymentType paymentType;

    public DebitCardPayment(String cardNo, PaymentType paymentType) {
        this.cardNo = cardNo;
        this.paymentType = paymentType;
    }

    @Override
    public PaymentDetail doPayment(double amount) {
        System.out.println("Amount "+amount+" payed via "+paymentType+" by card number "+cardNo);
        return new PaymentDetail(amount,paymentType.toString());
    }
}
