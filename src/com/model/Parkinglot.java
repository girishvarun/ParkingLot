package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by girishv on 12/12/15.
 */
public class Parkinglot {

    Integer parkingSlots;
    Map<ParkingTicket,Car> carsParked;

    Integer index;
    private List<ISubscriber> subscriberList;

    public Parkinglot(Integer parkingSlots) {
        this.parkingSlots = parkingSlots;
        carsParked = new HashMap<ParkingTicket, Car>();
        this.subscriberList = new ArrayList<ISubscriber>();
    }


    public ParkingTicket parkCar(Car car,Integer parkingSlot) throws NoSpaceAvailableException, CarAlreadyParkedException, ParkingSlotNotAvailableException {
        if(parkingSlots == carsParked.size())
            throw new NoSpaceAvailableException();
        if(carsParked.values().contains(car))
            throw new CarAlreadyParkedException();
        
        if(carsParked.keySet().stream().anyMatch(t->t.getParkingSlot()==parkingSlot)) throw new ParkingSlotNotAvailableException();

        ParkingTicket ticket = new ParkingTicket(parkingSlot);
        carsParked.put(ticket,car);
        if(carsParked.size()==parkingSlots)
            subscriberList.forEach(s->s.getFullLotNotification());
        return ticket;
    }

    public Car unparkCar(ParkingTicket ticket) throws CarNotParkedException {
            if(!carsParked.containsKey(ticket))
                throw new CarNotParkedException();
        subscriberList.forEach(s->s.getEmptyLotNotification());
            return carsParked.remove(ticket);
    }

    public void addToSubscribers(ISubscriber subscriber) {
        subscriberList.add(subscriber);
    }

    public List<Integer> fetchParkingSlots() throws NoSpaceAvailableException {

        if(parkingSlots == carsParked.size())
            throw new NoSpaceAvailableException();

        List<Integer> parkingSlotsList = new ArrayList<>();
        for (int index = 1; index <= parkingSlots; index++) {
            boolean foundParkingSlot = false;
            for (ParkingTicket ticket : carsParked.keySet()) {
                if (ticket.getParkingSlot() == index) {
                    foundParkingSlot = true;
                    break;
                }
            }
            if(!foundParkingSlot)
                parkingSlotsList.add(index);
        }
        return parkingSlotsList;

    }
}
