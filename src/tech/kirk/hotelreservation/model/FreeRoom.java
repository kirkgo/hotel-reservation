package tech.kirk.hotelreservation.model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.00, roomType);
    }

    @Override
    public Double getRoomPrice() {
        return 0.00;
    }
}
