package ParkingLotLLD.payment;

public class HandicappedVehiclePayment implements Payment{
    private float timeDuration;
    private static final float perHourCost = 10;
    public HandicappedVehiclePayment(float timeDuration) {
        this.timeDuration = timeDuration;
    }
    @Override
    public double getPaymentAmount() {
        return timeDuration*perHourCost;
    }
}
