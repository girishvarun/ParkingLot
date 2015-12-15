package com.model;

/**
 * Created by girishv on 12/12/15.
 */
public class ParkingTicket {

    private Integer parkingslot;

    public ParkingTicket(Integer parkingslot) {
        this.parkingslot = parkingslot;
    }

    public Integer getParkingSlot(){
        return parkingslot;
    }

    @Override
    public String toString() {
        return parkingslot.toString();
    }
}
