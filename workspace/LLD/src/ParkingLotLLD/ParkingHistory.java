package ParkingLotLLD;

import ParkingLotLLD.vehicle.Vehicle;

import java.util.Date;

public class ParkingHistory {
    ParkingLot parkingLot;
    Vehicle vehicle;
    long checkInTime;
    long checkoutTime;
    double paymentAmount;

    public ParkingHistory(ParkingLot parkingLot, Vehicle vehicle) {
        this.parkingLot = parkingLot;
        this.vehicle = vehicle;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public long getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(long checkInTime) {
        this.checkInTime = checkInTime;
    }

    public long getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(long checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
       String str = "ParkingHistory{" +
                "parkingLot = " + parkingLot.getSlotNo() +
                " on Floor "+parkingLot.getFloor() +
                ", vehicle = " + vehicle.getRegistrationNo()+
                ", checkInTime = " + ParkingSystem.sdf.format(new Date(checkInTime));
        if(checkoutTime != 0) {
            str = str + ", checkoutTime = " + ParkingSystem.sdf.format(new Date(checkoutTime))
            +", payment = "+paymentAmount;
        }
        return str+"}";
    }
}
