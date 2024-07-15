import Hotel.Database.Database;
import Hotel.entities.Role.Role;
import Hotel.Service.AuthService;
import Hotel.entities.Kard;
import Hotel.entities.Room;
import Hotel.entities.User;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; ++i) {
            for(int j = 0; j < 20; ++j) {
                Room room = new Room(i + 1, j + 1, true, true);
                Database.rooms[i][j] = room;
            }
        }

        Kard kard = new Kard(null, null, null, 0.0);
        User user1 = new User("user", "user", "user", kard, Role.USER);
        User admin1 = new User("admin", "admin", "admin", kard, Role.ADMIN);
        Database.users.add(user1);
        Database.users.add(admin1);
        AuthService.Menyu();
    }
}
