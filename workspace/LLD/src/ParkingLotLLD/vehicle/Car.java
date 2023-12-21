package ParkingLotLLD.vehicle;

public class Car implements Vehicle {
    private VehicleType type;
    private String registrationNo;
    public Car(String registrationNo) {
        this.type = VehicleType.CAR;
        this.registrationNo = registrationNo;
    }
    @Override
    public VehicleType getType() {
        return this.type;
    }
    @Override
    public String getRegistrationNo() {
        return this.registrationNo;
    }
}
