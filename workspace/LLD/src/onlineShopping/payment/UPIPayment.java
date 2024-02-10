package onlineShopping.payment;

public class UPIPayment implements PaymentProcessor{
    private String upiId;
    private PaymentType paymentType;

    public UPIPayment(String upiId, PaymentType paymentType) {
        this.upiId = upiId;
        this.paymentType = paymentType;
    }

    @Override
    public PaymentDetail doPayment(double amount) {
        System.out.println("Amount "+amount+" payed via "+paymentType+" by UPIPayment "+upiId);
        return new PaymentDetail(amount,paymentType.toString());
    }
}
