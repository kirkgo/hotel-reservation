package tech.kirk.hotelreservation.service;

import tech.kirk.hotelreservation.model.Customer;
import tech.kirk.hotelreservation.model.IRoom;
import tech.kirk.hotelreservation.model.Reservation;

import java.sql.Array;
import java.time.LocalDate;
import java.util.*;

public class ReservationService {

    private final Map<String, IRoom> roomMap;
    private final Map<Customer, List<Reservation>> customerReservationMap;
    private static ReservationService reservationServiceInstance;

    public ReservationService() {
        this.roomMap = new HashMap<>();
        this.customerReservationMap = new HashMap<>();
    }

    public static ReservationService getInstance() {
        if (reservationServiceInstance == null) {
            reservationServiceInstance = new ReservationService();
        }
        return reservationServiceInstance;
    }

    public void addRoom(IRoom room){
        roomMap.put(room.getRoomNumber(), room);
    }

    public IRoom getRoom(String roomId){
        return roomMap.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        List<Reservation> customerReservations = customerReservationMap.getOrDefault(customer, new ArrayList<>());
        customerReservations.add(reservation);
        customerReservationMap.put(customer, customerReservations);
        return reservation;
    }

    public Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate){
        List<IRoom> availableRooms = new ArrayList<>();

        if(customerReservationMap.isEmpty()) {
            return new ArrayList<>(roomMap.values());
        }

        for(IRoom room : roomMap.values()){
            boolean isAvailable = true;

            for(List<Reservation> reservations : customerReservationMap.values()){
                for(Reservation reservation : reservations) {
                    if(reservation.getRoom().equals(room) &&
                            (reservation.getCheckInDate().compareTo(checkInDate) <= 0 &&
                                    reservation.getCheckOutDate().compareTo(checkInDate) > 0) ||
                            (reservation.getCheckInDate().compareTo(checkOutDate) < 0 &&
                                    reservation.getCheckOutDate().compareTo(checkOutDate) >= 0)) {
                        isAvailable = false;
                        break;
                    }
                }
                if(!isAvailable){
                    break;
                }
            }

            if(isAvailable){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        return customerReservationMap.getOrDefault(customer, Collections.emptyList());
    }

    public void printAllReservation(){
        for(List<Reservation> reservations : customerReservationMap.values()) {
            for(Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}
