package Hotel.entities;


import java.time.LocalDate;
import java.util.UUID;

public class Booking {
    private final String Id = UUID.randomUUID().toString();
    private LocalDate from_Date;
    private LocalDate to_Date;
    private String sharh;
    User user;
    Room room;
    public Booking(){}

    public Booking(LocalDate from_Date, LocalDate to_Date, String sharh, User user, Room room) {
        this.from_Date = from_Date;
        this.to_Date = to_Date;
        this.sharh = sharh;
        this.user = user;
        this.room = room;
    }

    public String getId() {
        return Id;
    }

    public LocalDate getFrom_Date() {
        return from_Date;
    }

    public void setFrom_Date(LocalDate from_Date) {
        this.from_Date = from_Date;
    }

    public LocalDate getTo_Date() {
        return to_Date;
    }

    public void setTo_Date(LocalDate to_Date) {
        this.to_Date = to_Date;
    }

    public String getSharh() {
        return sharh;
    }

    public void setSharh(String sharh) {
        this.sharh = sharh;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "Id='" + Id + '\'' +
                ", from_Date=" + from_Date +
                ", to_Date=" + to_Date +
                ", sharh='" + sharh + '\'' +
                ", user=" + user +
                ", room=" + room +
                '}';
    }
}