package com.model;

/**
 * Created by girishv on 15/12/15.
 */
public class AirportSecurityStaff implements ISubscriber {
    private Parkinglot parkinglot;

    public AirportSecurityStaff(Parkinglot parkinglot) {
        this.parkinglot = parkinglot;
    }

    @Override

    public void subscribeToFullLotNotification() {
        this.parkinglot.addToSubscribers(this);
    }

    @Override
    public void getEmptyLotNotification() {

    }

    @Override
    public void getFullLotNotification() {

    }
}
