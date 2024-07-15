package Hotel.Service;

import Hotel.Database.Database;
import Hotel.entities.Kard;
import Hotel.entities.User;
import Hotel.entities.Role.Role;

import java.util.Scanner;

public class AuthService {
    public static Scanner scanner = new Scanner(System.in);
    public static Scanner strScanner = new Scanner(System.in);
    public static User Currentuser;

    public static void Menyu() {
        while (true) {
            System.out.println("""
                    0 exit
                    1 sign in
                    2 sign up
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                case 1 -> SignIn();
                case 2 -> SignUp();
            }
        }
    }

    private static void SignUp() {
        User user = new User();
        System.out.println("Enter name: ");
        String name=AuthService.strScanner.nextLine();
        user.setName(name);
        System.out.println("Enter password: ");
        user.setPassword(AuthService.strScanner.nextLine());
        System.out.println("Enter phone number: ");
        user.setTel_number(AuthService.strScanner.nextLine());
        user.setRole(Role.USER);
        Kard kard = new Kard(null, null, null, 0.0);
        user.setKard(kard);
        Database.users.add(user);
        System.out.println("successfully added user!");
    }

    private static void SignIn() {

        System.out.println("Enter name: ");
        String name = AuthService.strScanner.nextLine();
        System.out.println("Enter password: ");
        String password = AuthService.strScanner.nextLine();
        boolean b = true;

        for (User user : Database.users) {
            if (user.getName().equals(name) && user.getPassword().equals(password) && user.getRole().equals(Role.USER)) {
                Currentuser = user;
                b = false;
                UserService.UserMenyu();

            }
            if (user.getName().equals(name) && user.getPassword().equals(password) && user.getRole().equals(Role.ADMIN)) {
                b = false;
                AdminService.adminMenyu();
            }
        }

        if (b) System.out.println("user not found!");
    }


}
