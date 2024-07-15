package Hotel.Service;

import Hotel.Database.Database;
import Hotel.entities.Role.Role;
import Hotel.entities.Booking;
import Hotel.entities.Kard;
import Hotel.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Hotel.Database.Database.users;

public class AdminService {
    public static void adminMenyu() {
        while (true) {
            System.out.println("""
                    0 exit
                    1 show rooms
                    2 show bookings        \s
                    3 cancel booking
                    4 show users                   \s
                    5 remove user
                    6 add user  \s
                    7 update booking                              \s
                    8 history
                    """);
            switch (AuthService.scanner.nextInt()) {
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                case 1 -> {
                    System.out.println("""
                            0 exit
                            1 show active rooms
                            2 show free rooms
                            """);
                    switch (AuthService.scanner.nextInt()) {
                        case 0 -> {
                            System.out.println("Bye!");
                            return;
                        }
                        case 1 -> ActiveRoomss();
                        case 2 -> FreeRooms();
                        default -> System.out.println("Error");
                    }
                }
                case 2 -> ActiveBookings();
                case 3 -> RemoveBooking();
                case 4 -> ShowUsers();
                case 5 -> RemoveUser();
                case 6 -> AddConsumer();
                case 7 -> UpdateBooking();
                case 8 -> history();
                default -> System.out.println("invalid choise!");
            }
        }
    }


    private static void UpdateBooking() {
        if (Database.historyBooking.isEmpty()) {
            System.out.println("not bookings yet!");
        } else {
            int n = 1;
            for (Booking booking : Database.historyBooking) {
                if (booking.getRoom().getFree().equals(false)) {
                    System.out.println(n + "- busy booking: " + booking);

                }
            }
            System.out.println("choose booking: ");
            int v = AuthService.scanner.nextInt();
            v--;
            Database.historyBooking.get(v).getRoom().setFree(true);
            System.out.println("success!");

        }
    }


    private static void AddConsumer() {
        User user = new User();
        System.out.println("Enter name: ");
        user.setName(AuthService.strScanner.nextLine());
        System.out.println("Enter password: ");
        user.setPassword(AuthService.strScanner.nextLine());
        System.out.println("Enter phone number: ");
        user.setTel_number(AuthService.strScanner.nextLine());
        Kard kard = new Kard(null, null, null, 0.0);
        user.setKard(kard);
        user.setRole(Role.USER);
        users.add(user);
        System.out.println("Successfully added consumer!");

    }

    private static void RemoveUser() {
        if (users.isEmpty()) {
            System.out.println("no users yet!");
        } else {
            int n = 1;
            for (User user : users) {
                if (user.getRole().equals(Role.USER)) {
                    System.out.println(n + " - " + user);
                    n++;
                }
            }


            System.out.println("choose user: ");
            int m = AuthService.scanner.nextInt();
            m--;
            users.remove(m);
            System.out.println("successfully cancelled!");
        }
    }

    private static void ShowUsers() {

        if (users.isEmpty()) {
            System.out.println("no users yet!");
        } else {
            int n = 1;
            for (User user : users) {
                if (user.getRole().equals(Role.USER)) {
                    System.out.println(n + " - " + user);
                    n++;
                }
            }
        }
    }

    private static void RemoveBooking() {
        if (Database.historyBooking.isEmpty()) {
            System.out.println("no bookings yet!");
        } else {
            int n = 1;
            for (Booking booking : Database.historyBooking) {
                if (booking.getRoom().getFree().equals(false)) {
                    System.out.println();
                    System.out.println(n + "-booking");
                    System.out.println("booking Id " + booking.getId());
                    System.out.println("comment " + booking.getSharh());
                    n++;

                }
            }
            System.out.println("choose booking: ");
            int m = AuthService.scanner.nextInt();
            m--;
            System.out.println(Database.historyBooking.get(m).getId());
            Database.historyBooking.remove(m);
            System.out.println("successfully!");
        }
    }

    private static void ActiveBookings() {
        for (Booking booking : Database.historyBooking) {
            if (booking.getRoom().getFree().equals(false)) {
                System.out.println(booking.getSharh());

            }
        }
    }

    private static void FreeRooms() {
        System.out.println("""
                0 exit
                1 show free room
                2 show free all rooms
                """);
        switch (AuthService.scanner.nextInt()) {
            case 0 -> System.out.println("Bye!");
            case 1 -> {
                System.out.println("enter floor number: ");
                int q = AuthService.scanner.nextInt();
                q--;
                System.out.println("enter room number: ");
                int x = AuthService.scanner.nextInt();
                x--;
                boolean b = true;
                for (Booking booking : Database.historyBooking) {

                    if (((Database.rooms[q][x].getFloor_number() - 1) == booking.getRoom().getFloor_number()) && ((Database.rooms[q][x].getRoom_number() - 1) == booking.getRoom().getRoom_number())) {
                        b = false;
                        System.out.println("--------------");
                        System.out.println((booking.getFrom_Date().minusDays(1)) + " form date " + booking.getTo_Date().plusDays(1) + " to date, busy!");
                        System.out.println("--------------");

                    }

                }
                if (b) System.out.println("room free!");


            }
            case 2 -> {
                if (Database.historyBooking.isEmpty()) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 20; j++) {
                            if (Database.rooms[i][j].getFree().equals(true)) {
                                System.out.print(Database.rooms[i][j].getFloor_number() + ":" + Database.rooms[i][j].getRoom_number() + " ");
                            }
                            if (Database.rooms[i][j].getFree().equals(false)) System.out.print("-- ");
                        }
                        System.out.println();
                    }


                } else {

                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 20; j++) {
                            for (Booking booking : Database.historyBooking) {
                                if (((Database.rooms[i][j].getFloor_number() - 1) == booking.getRoom().getFloor_number()) && ((Database.rooms[i][j].getRoom_number() - 1) == booking.getRoom().getRoom_number())) {
                                    System.out.println(booking.getSharh());
                                }
                            }
                        }

                    }
                }

            }
            default -> System.out.println("invalid choose!");
        }


    }

    private static void ActiveRoomss() {
        System.out.println("""
                0 exit
                1 show active room
                2 show active all rooms
                """);
        switch (AuthService.scanner.nextInt()) {
            case 0 -> System.out.println("Bye!");
            case 1 -> {
                System.out.println("enter floor number: ");
                int q = AuthService.scanner.nextInt();
                q--;
                System.out.println("enter room number: ");
                int x = AuthService.scanner.nextInt();
                x--;
                if (Database.rooms[q][x].getActive().equals(true)) {
                    System.out.println("active");
                } else System.out.println("busy");

            }
            case 2 -> {
                System.out.println("active 1:1 ");
                System.out.println("busy -- ");

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (Database.rooms[i][j].getActive().equals(true)) {
                            System.out.print(Database.rooms[i][j].getFloor_number() + ":" + Database.rooms[i][j].getRoom_number() + " ");
                        }
                        if (Database.rooms[i][j].getActive().equals(false)) System.out.print("-- ");
                    }
                    System.out.println();
                }

            }
            default -> System.out.println("invalid choose!");
        }

    }

    public static void history() {
        System.out.println("""
                1. Show All Bookings
                2. Show Bookings by User
                3. Show Bookings Between Dates
                """);
        switch (AuthService.scanner.nextInt()) {
            case 1 -> showAllBookings();
            case 2 -> showBookingsByUser();
            case 3 -> showBookingsBetweenDates();
            default -> System.out.println("Invalid Choice!");
        }
    }

    private static void showAllBookings() {
        if (Database.historyBooking.isEmpty()) {
            System.out.println("There are no bookings in the history yet.");
        } else {
            System.out.println("All Bookings:");
            for (Booking booking : Database.historyBooking) {
                System.out.println(booking); // Assuming Booking class has a meaningful toString() method
            }
        }
    }

    private static void showBookingsByUser() {
        // Implement logic to get user input and filter bookings by user
        System.out.println("Enter User Name:");
        String userName = AuthService.strScanner.nextLine();

        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : Database.historyBooking) {
            if (booking.getUser().getName().equals(userName)) {
                userBookings.add(booking);
            }
        }

        if (userBookings.isEmpty()) {
            System.out.println("No bookings found for user: " + userName);
        } else {
            System.out.println("Bookings by User: " + userName);
            for (Booking booking : userBookings) {
                System.out.println(booking);
            }
        }
    }

    private static void showBookingsBetweenDates() {

        System.out.println("Enter Start Date (YYYY-MM-DD):");
        String startDateStr = AuthService.strScanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateStr);

        System.out.println("Enter End Date (YYYY-MM-DD):");
        String endDateStr = AuthService.strScanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateStr);

        List<Booking> bookedDates = new ArrayList<>();
        for (Booking booking : Database.historyBooking) {
            if ((booking.getFrom_Date().isAfter(startDate) || booking.getFrom_Date().isEqual(startDate)) &&
                    (booking.getTo_Date().isBefore(endDate) || booking.getTo_Date().isEqual(endDate))) {
                bookedDates.add(booking);
            }
        }

        if (bookedDates.isEmpty()) {
            System.out.println("No bookings found between " + startDate + " and " + endDate);
        } else {
            System.out.println("Bookings Between " + startDate + " and " + endDate);
            for (Booking booking : bookedDates) {
                System.out.println(booking);
            }
        }
    }


}
