package tech.kirk.hotelreservation.model;

import java.time.LocalDate;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public String toString(){
        return String.format("Customer: %s \nRoom: %s \nCheckInDate: %s \nCheckOutDate: %s", getCustomer(), getRoom(),
                getCheckInDate(), getCheckOutDate());
    }
}
