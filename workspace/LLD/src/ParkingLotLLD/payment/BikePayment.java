package ParkingLotLLD.payment;

public class BikePayment implements Payment{
    private float timeDuration;
    private static final float perHourCost = 15;
    public BikePayment(float timeDuration) {
        this.timeDuration = timeDuration;
    }
    @Override
    public double getPaymentAmount() {
        return timeDuration*perHourCost;
    }
}
