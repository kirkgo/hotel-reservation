package tech.kirk.hotelreservation.api;

import tech.kirk.hotelreservation.model.Customer;
import tech.kirk.hotelreservation.model.IRoom;
import tech.kirk.hotelreservation.model.Reservation;
import tech.kirk.hotelreservation.service.CustomerService;
import tech.kirk.hotelreservation.service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResourceInstance;
    private final CustomerService customerService;
    private final ReservationService reservationService;

    public AdminResource() {
        this.customerService = CustomerService.getInstance();
        this.reservationService = ReservationService.getInstance();
    }

    public static AdminResource getInstance(){
        if(adminResourceInstance == null){
            adminResourceInstance = new AdminResource();
        }
        return adminResourceInstance;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for(IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.findRooms(null, null);
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }
}
