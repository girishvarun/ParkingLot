package com.model;

/**
 * Created by girishv on 15/12/15.
 */
public class ParkingAttendant {


    public ParkingTicket parkCar(Parkinglot parkinglot, Car car, Integer parkingSlot) throws CarAlreadyParkedException, NoSpaceAvailableException, ParkingSlotNotAvailableException {
       return parkinglot.parkCar(car,parkingSlot);
    }
}
