package Hotel.entities;

public class Room {
    private Integer floor_number;
    private Integer room_number;
    private Boolean active;
    private Boolean free;
    private final Double price=100000.0;

    public Room(Integer floor_number, Integer room_number, Boolean active, Boolean free) {
        this.floor_number = floor_number;
        this.room_number = room_number;
        this.active = active;
        this.free = free;
    }

    public Integer getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(Integer floor_number) {
        this.floor_number = floor_number;
    }

    public Integer getRoom_number() {
        return room_number;
    }

    public void setRoom_number(Integer room_number) {
        this.room_number = room_number;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "floor_number=" + floor_number +
                ", room_number=" + room_number +
                ", active=" + active +
                ", free=" + free +
                ", price=" + price +
                '}';
    }
}
