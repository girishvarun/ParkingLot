package com.model;

import java.util.List;

/**
 * Created by girishv on 15/12/15.
 */
public class ParkinglotOwner implements  ISubscriber {

    private Parkinglot parkinglot;

    private ParkingAttendant attendant;

    public ParkinglotOwner(Parkinglot parkinglot,ParkingAttendant attendant) {
        this.parkinglot = parkinglot;
        this.attendant = attendant;
    }

    @Override
    public void subscribeToFullLotNotification() {
        parkinglot.addToSubscribers(this);
    }

    @Override
    public void getEmptyLotNotification() {
    }

    @Override
    public void getFullLotNotification() {
    }

    public ParkingTicket parkCar(Car car) throws NoSpaceAvailableException, CarAlreadyParkedException, ParkingSlotNotAvailableException {
        List<Integer> parkingSlotList = parkinglot.fetchParkingSlots();
        return attendant.parkCar(parkinglot, car, decideParkingSlot(parkingSlotList));
    }

    private Integer decideParkingSlot(List<Integer> parkingSlotList) {
        return parkingSlotList.get(0);
    }
}
