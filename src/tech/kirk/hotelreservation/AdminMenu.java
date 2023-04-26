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
                case 3 -> addRoom();
                case 4 -> {
                    running = false;
                    System.out.println("Returning to main menu...");
                }
                default -> System.out.println("Invalid option. Please select the number between 1-4.");
            }
        }
    }

    private static void displayAdminMenu(){
        System.out.println("\nAdmin Menu: ");
        System.out.println("1. See All Customers");
        System.out.println("2. See All Rooms");
        System.out.println("3. Add a Room");
        System.out.println("4. Back to Main Menu");
        System.out.println("Please select an option between 1-4: ");
    }

    private static void seeAllCustomers(){
        System.out.println("All Customers: ");
        for(Customer customer : adminResource.getAllCustomers()){
            System.out.println(customer);
            System.out.println("---------------------");
        }
    }

    private static void seeAllRooms(){
        System.out.println("All Rooms: ");
        for(IRoom room : adminResource.getAllRooms()){
            System.out.println(room);
            System.out.println("---------------------");
        }
    }

    private static void addRoom(){
        System.out.println("Enter room number: ");
        String roomNumber = scanner.nextLine();

        System.out.println("Enter room price: ");
        double roomPrice = scanner.nextDouble();

        System.out.println("Enter room type (1 for Single bed, 2 for Double bed): ");
        int roomType = scanner.nextInt();
        scanner.nextLine();

        if(roomType == 1 || roomType == 2){
            RoomType type = roomType == 1 ? RoomType.SINGLE : RoomType.DOUBLE;
            IRoom room = new Room(roomNumber, roomPrice,type);
            adminResource.addRoom(Collections.singletonList(room));
            System.out.println("Room added successfully!");
        } else {
            System.out.println("Invalid room type.");
        }
    }
}
