package tech.kirk.hotelreservation.model;

public enum RoomType {
    SINGLE("Single", 1),
    DOUBLE("Double", 2);

    private final String displayName;
    private final int capacity;

    RoomType(String displayName, int capacity){
        this.displayName = displayName;
        this.capacity = capacity;
    }

    public String getDisplayName(){
        return displayName;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
