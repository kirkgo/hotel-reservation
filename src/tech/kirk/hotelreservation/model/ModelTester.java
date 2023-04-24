package tech.kirk.hotelreservation.model;

public class ModelTester {
    public static void main(String[] args) {
        System.out.println("##### TESTING MODELS #####");

        Room singleRoom = new Room("18", 250.00, RoomType.SINGLE);
        System.out.println("----Room----");
        System.out.println(singleRoom);

        FreeRoom freeRoom = new FreeRoom("1212", 0.00, RoomType.DOUBLE);
        System.out.println("----Free Room----");
        System.out.println(freeRoom);

        Customer customer = new Customer("Kirk", "Patrick", "hi@kirk.tech");
        System.out.println("----Customer----");
        System.out.println(customer);
    }
}
