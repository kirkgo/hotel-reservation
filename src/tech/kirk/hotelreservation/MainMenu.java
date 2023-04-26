package tech.kirk.hotelreservation;

import tech.kirk.hotelreservation.api.AdminResource;
import tech.kirk.hotelreservation.api.HotelResource;
import tech.kirk.hotelreservation.model.IRoom;
import tech.kirk.hotelreservation.model.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class MainMenu {
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMainMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> findAndReserveRoom();
                case 2 -> seeMyReservations();
                case 3 -> createAccount();
                case 4 -> AdminMenu.adminMenu();
                case 5 -> {
                    running = false;
                    System.out.println("Exiting the application...");
                }
                default -> System.out.println("Invalid option. Please select the number between 1-5.");
            }
        }
        scanner.close();
    }

    private static void displayMainMenu(){
        System.out.println("\nMain Menu:");
        System.out.println("1. Find and Reserve a Room");
        System.out.println("2. See My Reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("Please select an option between 1-5: ");
    }

    private static void findAndReserveRoom(){
        System.out.println("Enter check-in date (dd/mm/yyyy): ");
        String checkInDateString = scanner.nextLine();
        LocalDate checkInDate = LocalDate.parse(checkInDateString, dateTimeFormatter);

        System.out.println("Enter check-out date (dd/mm/yyyy): ");
        String checkOutDateString = scanner.nextLine();
        LocalDate checkOutDate = LocalDate.parse(checkOutDateString, dateTimeFormatter);

        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);

        if(availableRooms.isEmpty()) {
            System.out.println("No rooms available for the given dates.");
        } else {
            System.out.println("Available Rooms: ");
            for(IRoom room : availableRooms) {
                System.out.println(room);
            }
            System.out.println("Enter your email: ");
            String email = scanner.nextLine();

            System.out.println("Enter the room number you'd like to reserve: ");
            String roomNumber = scanner.nextLine();
            IRoom room = hotelResource.getRoom(roomNumber);

            if(room == null) {
                System.out.println("Invalid room number.");
            } else {
                try {
                    Reservation reservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
                    if (reservation != null) {
                        System.out.println("Reservation successful!");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Error: unable to create reservation.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }



    private static void seeMyReservations(){
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        Collection<Reservation> reservations = hotelResource.getCustomerReservations(email);
        if(reservations == null || reservations.isEmpty()){
            System.out.println("No reservations found for the given email.");
        } else {
            System.out.println("Your reservations: ");
            for(Reservation reservation : reservations){
                System.out.println(reservation);
                System.out.println("---------------------");
            }
        }
    }

    private static void createAccount() {
        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        try {
            hotelResource.createACustomer(firstName, lastName, email);
            System.out.println("Account created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unable to create an account. " + e.getMessage());
        }
    }
}
