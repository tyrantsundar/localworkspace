package ParkingLotLLD;

import ParkingLotLLD.vehicle.*;

public class Main {
    public static void main(String[] args) {

        ParkingLot cp = new ParkingLot(1,1,1,VehicleType.CAR);
        ParkingLot bp = new ParkingLot(2,2,2,VehicleType.BIKE);
        ParkingLot hp = new ParkingLot(3,3,3,VehicleType.HANDICAPED_VEHICLE);

        Vehicle car = new Car("car1");
        Vehicle bike = new Bike("bike1");
        Vehicle hadiCaped = new HandicapedVehicle("h1");
        Vehicle car2 = new Car("car2");

        ParkingSystem parkingSystem = new ParkingSystem();
        parkingSystem.addParkingLot(cp);
        parkingSystem.addParkingLot(bp);
        parkingSystem.addParkingLot(hp);

        System.out.println("Vehicles CheckIn");
        parkingSystem.checkInTheVehicle(car);
        parkingSystem.checkInTheVehicle(bike);
        parkingSystem.checkInTheVehicle(hadiCaped);
        parkingSystem.checkInTheVehicle(car2);
        System.out.println("Vehicles Checkout");
        parkingSystem.checkOutTheVehicle(car,1,1,"21/12/2023");
        parkingSystem.checkInTheVehicle(car2);
        parkingSystem.checkOutTheVehicle(bike,2,2,"21/12/2023");
        System.out.println("Print All History");
        parkingSystem.printAllParkingHistory();
    }
}
