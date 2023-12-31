package ParkingLotLLD.vehicle;

public class Bike implements Vehicle {
    private VehicleType type;
    private String registrationNo;
    public Bike(String registrationNo) {
        this.type = VehicleType.BIKE;
        this.registrationNo = registrationNo;
    }
    @Override
    public VehicleType getType() {
        return type;
    }
    public String getRegistrationNo() {
        return registrationNo;
    }
}
