package tech.kirk.hotelreservation.model;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType){
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return (this.price != null) && this.price.equals(0.0);
    }

    @Override
    public String toString(){
        return String.format("Room Number: %s \nPrice: %s \nRoom Type: %s", getRoomNumber(), getRoomPrice(),
                getRoomType());
    }
}
