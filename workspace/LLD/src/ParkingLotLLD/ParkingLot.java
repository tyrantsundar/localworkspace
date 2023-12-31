package ParkingLotLLD;

import ParkingLotLLD.vehicle.VehicleType;

public class ParkingLot {
    private int floor;
    private int row;
    private boolean isAvailable;
    private int slotNo;
    private VehicleType vehicleType;
    public ParkingLot(int floor, int row, int slotNo, VehicleType vehicleType) {
        this.floor = floor;
        this.row = row;
        this.isAvailable = true;
        this.slotNo = slotNo;
        this.vehicleType = vehicleType;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
