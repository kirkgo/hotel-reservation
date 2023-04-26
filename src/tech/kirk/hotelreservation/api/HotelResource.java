package tech.kirk.hotelreservation.api;

import tech.kirk.hotelreservation.model.Customer;
import tech.kirk.hotelreservation.model.IRoom;
import tech.kirk.hotelreservation.model.Reservation;
import tech.kirk.hotelreservation.service.CustomerService;
import tech.kirk.hotelreservation.service.ReservationService;

import java.time.LocalDate;
import java.util.Collection;
import java.time.LocalDate;

public class HotelResource {
    private static HotelResource hotelResourceInstance;
    private final CustomerService customerService;
    private final ReservationService reservationService;

    private HotelResource() {
        this.customerService = CustomerService.getInstance();
        this.reservationService = ReservationService.getInstance();
    }

    public static HotelResource getInstance(){
        if(hotelResourceInstance == null) {
            hotelResourceInstance = new HotelResource();
        }
        return hotelResourceInstance;
    }

    public Customer getCustomer(String email){
       return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getRoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        if(customer == null) {
            throw new IllegalArgumentException("Customer not found for email: " + customerEmail);
        }
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        if(customer == null){
            throw new IllegalArgumentException("Customer not found for email: " + customerEmail);
        }
        return reservationService.getCustomerReservation(customer);
    }

    public Collection<IRoom> findARoom(LocalDate checkInDate, LocalDate checkOutDate) {
        return reservationService.findRooms(checkInDate, checkOutDate);
    }
}
