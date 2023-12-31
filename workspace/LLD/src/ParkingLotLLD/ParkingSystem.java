package ParkingLotLLD;

import ParkingLotLLD.payment.BikePayment;
import ParkingLotLLD.payment.CarPayment;
import ParkingLotLLD.payment.HandicappedVehiclePayment;
import ParkingLotLLD.payment.Payment;
import ParkingLotLLD.vehicle.Vehicle;
import ParkingLotLLD.vehicle.VehicleType;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ParkingSystem {
    private Set<ParkingLot> parkingLotSet;
    private Map<String,Set<ParkingHistory>> parkingHistoryDateWise;
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ParkingSystem() {
        parkingLotSet = new HashSet<>();
        parkingHistoryDateWise = new HashMap<>();
    }

    public void addParkingLot(ParkingLot parkingLot){
        parkingLotSet.add(parkingLot);
    }

    public boolean isParkingLotAvailable(VehicleType vehicleType){
        return parkingLotSet.stream().anyMatch(parkingLot -> parkingLot.isAvailable() && parkingLot.getVehicleType() == vehicleType);
    }

    public List<ParkingLot> getAvailableParkingSlot(){
        return parkingLotSet.stream().filter(parkingLot -> parkingLot.isAvailable()).collect(Collectors.toList());
    }

    public List<ParkingLot> getAvailableParkingSlot(VehicleType vehicleType){
        return parkingLotSet.stream().filter(
                parkingLot -> parkingLot.isAvailable()
                        && parkingLot.getVehicleType() == vehicleType)
                .collect(Collectors.toList());
    }

    public List<ParkingLot>  getAvailableParkingSlot(VehicleType vehicleType, int floor){
        return parkingLotSet.stream().filter(
                        parkingLot -> parkingLot.isAvailable()
                                && parkingLot.getVehicleType() == vehicleType
                                && parkingLot.getFloor() == floor)
                .collect(Collectors.toList());
    }

    public ParkingLot  getParkingSlot(int parkingSlot, int floor){
        return parkingLotSet.stream().filter(
                parkingLot -> parkingLot.getFloor() == floor
                        && parkingLot.getSlotNo() == parkingSlot).findFirst().get();
    }

    public void checkInTheVehicle(Vehicle vehicle) {
        if(isParkingLotAvailable(vehicle.getType())){
            // Just assume we can add more filters using polymorphism
            // For now, I ll use the first one always
            ParkingLot parkingLot = getAvailableParkingSlot(vehicle.getType()).get(0);
            checkInTheVehicle(vehicle,parkingLot);
        }else{
            System.out.println("Parking slot is not available !!!");
        }
    }

    public void checkInTheVehicle(Vehicle vehicle, ParkingLot parkingLot){
        Calendar calendar = Calendar.getInstance();
        parkingLot.setAvailable(false);
        ParkingHistory parkingHistory = new ParkingHistory(parkingLot,vehicle);
        parkingHistory.setCheckInTime(calendar.getTime().getTime());
        String checkInTime = sdf.format(calendar.getTime());
        Set<ParkingHistory> parkingHistories = new HashSet<>();
        if(this.parkingHistoryDateWise.containsKey(checkInTime)){
            parkingHistories = this.parkingHistoryDateWise.get(checkInTime);
        }
        parkingHistories.add(parkingHistory);
        this.parkingHistoryDateWise.put(checkInTime,parkingHistories);
        System.out.println(parkingHistory.toString());
    }

    public void checkOutTheVehicle(Vehicle vehicle, int slotNumber, int floorNumber, String checkInTime){
        ParkingLot parkingLot = getParkingSlot(slotNumber,floorNumber);
        if(parkingLot == null){
            System.out.println("Invalid parking slot details !");
            return;
        }
        parkingLot.setAvailable(true);
        Calendar calendar = Calendar.getInstance();
        if(this.parkingHistoryDateWise.containsKey(checkInTime)){
            Set<ParkingHistory> parkingHistories = this.parkingHistoryDateWise.get(checkInTime);
            Optional<ParkingHistory> parkingHistoryOptional = parkingHistories.stream().filter(
                    history -> history.getParkingLot().getFloor() == floorNumber
                            && history.getParkingLot().getSlotNo() == slotNumber
                            && history.getVehicle().getRegistrationNo() == vehicle.getRegistrationNo()).findFirst();
            ParkingHistory parkingHistory = parkingHistoryOptional.get();
            parkingHistory.setCheckoutTime(calendar.getTime().getTime());
            parkingHistory.setPaymentAmount(calculatePayment(vehicle.getType(),parkingHistory.checkInTime,parkingHistory.checkoutTime));
            parkingHistories.add(parkingHistory);
            this.parkingHistoryDateWise.put(checkInTime,parkingHistories);
            System.out.println(parkingHistory.toString());
        }
    }

    public void printAllParkingHistory(){
        for(String date : this.parkingHistoryDateWise.keySet()){
            Set<ParkingHistory> parkingHistories = this.parkingHistoryDateWise.get(date);
            for(ParkingHistory parkingHistory : parkingHistories){
                System.out.println(parkingHistory.toString());
            }
        }
    }

    private Payment getPayment(VehicleType vehicleType, float timeDuration){
        switch (vehicleType){
            case CAR : return new CarPayment(timeDuration);
            case BIKE : return new BikePayment(timeDuration);
            case HANDICAPED_VEHICLE: return new HandicappedVehiclePayment(timeDuration);
        }
        return null;
    }
    private double calculatePayment(VehicleType vehicleType, long startTime, long endTime){
        float stTime = new Date(startTime).getHours();
        float etTime = new Date(endTime).getHours();
        Payment payment = getPayment(vehicleType, etTime-stTime);
        return payment.getPaymentAmount();
    }
}
