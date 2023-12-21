package ParkingLotLLD.vehicle;

public class HandicapedVehicle implements Vehicle {
    private VehicleType type;
    private String registrationNo;
    public HandicapedVehicle(String registrationNo) {
        this.type = VehicleType.HANDICAPED_VEHICLE;
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
