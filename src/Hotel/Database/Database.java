package Hotel.Database;

import Hotel.entities.Booking;
import Hotel.entities.Room;
import Hotel.entities.User;

import java.util.ArrayList;

public class Database {
    public static ArrayList<User> users = new ArrayList<>();
    public static Room[][] rooms = new Room[10][20];
    public static ArrayList<Booking> historyBooking = new ArrayList<>();

}
