package tech.kirk.hotelreservation;

import tech.kirk.hotelreservation.api.AdminResource;
import tech.kirk.hotelreservation.model.Customer;
import tech.kirk.hotelreservation.model.IRoom;
import tech.kirk.hotelreservation.model.Room;
import tech.kirk.hotelreservation.model.RoomType;

import java.util.Collections;
import java.util.Scanner;

public class AdminMenu {
    private static final AdminResource adminResource = AdminResource.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void adminMenu(){
        boolean running = true;

        while(running){
            displayAdminMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1 -> seeAllCustomers();
                case 2 -> seeAllRooms();
                case 3 -> seeAllReservations();
                case 4 -> addRoom();
                case 5 -> {
                    running = false;
                    System.out.println("Returning to main menu...");
                }
                default -> System.out.println("Invalid option. Please select the number between 1-5.");
            }
        }
    }

    private static void displayAdminMenu(){
        System.out.println("\nAdmin Menu: ");
        System.out.println("1. See All Customers");
        System.out.println("2. See All Rooms");
        System.out.println("3. See All Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("Please select an option between 1-5: ");
    }

    private static void seeAllCustomers(){
        System.out.println("All Customers: ");
        for(Customer customer : adminResource.getAllCustomers()){
            System.out.println(customer);
            System.out.println("------------------------------------------");
        }
    }

    private static void seeAllRooms(){
        System.out.println("All Rooms: ");
        for(IRoom room : adminResource.getAllRooms()){
            System.out.println(room);
            System.out.println("------------------------------------------");
        }
    }

    private static void seeAllReservations(){
        System.out.println("\nAll Reservations: ");
        adminResource.displayAllReservations();
    }

    private static void addRoom() {
        boolean addingRoom = true;
        while (addingRoom) {
            System.out.println("Enter room number: ");
            String roomNumber = scanner.nextLine();

            if (!isNumeric(roomNumber)) {
                System.out.println("Invalid room number. Room number should only contain numbers.");
                continue;
            }

            System.out.println("Enter room price: ");
            String roomPriceString = scanner.nextLine();

            if (!isNumeric(roomPriceString)) {
                System.out.println("Invalid room price. Enter a valid price. Example: 128.65.");
                continue;
            }

            double roomPrice = Double.parseDouble(roomPriceString);

            System.out.println("Enter room type (1 for Single bed, 2 for Double bed): ");
            int roomType = scanner.nextInt();
            scanner.nextLine();

            if (roomType == 1 || roomType == 2) {
                RoomType type = roomType == 1 ? RoomType.SINGLE : RoomType.DOUBLE;
                IRoom room = new Room(roomNumber, roomPrice, type);
                adminResource.addRoom(Collections.singletonList(room));
                System.out.println("Room added successfully!");
                addingRoom = false;
            } else {
                System.out.println("Invalid room type.");
            }
        }
    }

    private static boolean isNumeric(String strNum) {
        if(strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException e1) {
            try {
                int i = Integer.parseInt(strNum);
            } catch (NumberFormatException e2) {
                return false;
            }
        }
        return true;
    }
}
