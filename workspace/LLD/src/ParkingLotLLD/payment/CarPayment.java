package ParkingLotLLD.payment;

public class CarPayment implements Payment{
    private float timeDuration;
    private static final float perHourCost = 25;
    public CarPayment(float timeDuration) {
        this.timeDuration = timeDuration;
    }
    @Override
    public double getPaymentAmount() {
        return timeDuration*perHourCost;
    }
}
