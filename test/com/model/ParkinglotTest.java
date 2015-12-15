package com.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class ParkinglotTest {

    @Test
    public void shouldReturnTickethWhenCarIsParked() throws Exception {

        Parkinglot parkinglot = new Parkinglot(10);
        Car car = new Car();
        ParkingTicket ticket = parkinglot.parkCar(car,1);
        Assert.assertNotNull(ticket);
        Assert.assertEquals("1", ticket.toString());
    }


    @Test(expected = NoSpaceAvailableException.class)
    public void shouldThrowExceptionWhenNoSpaceAvailable() throws Exception {

        Parkinglot parkinglot = new Parkinglot(0);
        Car car = new Car();
        parkinglot.parkCar(car,1);
    }

    @Test(expected = CarAlreadyParkedException.class)
    public void shouldThrowExceptionWhenTryingToParkAlreadyParkedCar() throws Exception {
        Parkinglot parkinglot = new Parkinglot(10);
        Car car = new Car();
        parkinglot.parkCar(car,1);
        parkinglot.parkCar(car,1);
    }

    @Test
    public void shouldUnparkCar() throws Exception {
        Parkinglot parkinglot = new Parkinglot(10);
        Car car = new Car();
        ParkingTicket ticket = parkinglot.parkCar(car,1);

        Assert.assertEquals(car, parkinglot.unparkCar(ticket));

    }

    @Test(expected = CarNotParkedException.class)
    public void shouldThrowAnExceptionOnInvalidTicket() throws Exception {
        Parkinglot parkinglot = new Parkinglot(10);
        parkinglot.unparkCar(new ParkingTicket(-20));
    }

    @Test
    public void shouldNotifyParkingOwner() throws Exception {
        ParkinglotOwner owner = Mockito.mock(ParkinglotOwner.class);
        Parkinglot parkinglot = new Parkinglot(1);
        parkinglot.addToSubscribers(owner);
        parkinglot.parkCar(new Car(),1);

        Mockito.verify(owner).getFullLotNotification();

    }

    @Test
    public void shouldNotifyAirportSecurityStaff() throws Exception {
        AirportSecurityStaff airportStaff = Mockito.mock(AirportSecurityStaff.class);
        Parkinglot parkinglot = new Parkinglot(1);
        parkinglot.addToSubscribers(airportStaff);
        parkinglot.parkCar(new Car(),1);

        Mockito.verify(airportStaff).getFullLotNotification();

    }

    @Test
    public void shouldNotifyEmptyspaceToOwner() throws Exception {
        ParkinglotOwner owner = Mockito.mock(ParkinglotOwner.class);
        Parkinglot parkinglot = new Parkinglot(1);
        parkinglot.addToSubscribers(owner);

        ParkingTicket ticket = parkinglot.parkCar(new Car(),1);
        parkinglot.unparkCar(ticket);
        Mockito.verify(owner).getEmptyLotNotification();

    }

    @Test
    public void shouldParkCarByAttendant() throws Exception {
        Parkinglot parkinglot =new Parkinglot(10);
        ParkinglotOwner owner = new ParkinglotOwner(parkinglot, new ParkingAttendant());
        ParkingTicket ticket = owner.parkCar(new Car());

        Assert.assertNotNull(ticket);
        Assert.assertEquals("1", ticket.toString());

    }

    @Test(expected = ParkingSlotNotAvailableException.class)
    public void shouldThrowExceptionWhenParkingAtSameSlot() throws Exception {
        Parkinglot parkinglot = new Parkinglot(10);
        Car car = new Car();
        parkinglot.parkCar(car,1);
        parkinglot.parkCar(new Car(),1);
    }
}
