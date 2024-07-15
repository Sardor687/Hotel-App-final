package Hotel.Service;


import Hotel.Database.Database;
import Hotel.entities.Booking;
import Hotel.entities.Kard;
import Hotel.entities.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


public class UserService {
    static String from;
    static String to;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void UserMenyu() {
        while (true) {
            System.out.println("""
                    0  exit
                    1  my booking
                    2  show rooms
                    3  add booking
                    4  balance
                    5  cancel booking
                    6  history
                    """);
            switch (AuthService.scanner.nextInt()) {
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                case 1 -> Mybooking();
                case 2 -> FreeRooms();
                case 3 -> addBooking();
                case 4 -> {
                    System.out.println("""
                            0 exit
                            1 Add card
                            2 Show my balance
                            3 Fill the card
                            """);
                    switch (AuthService.scanner.nextInt()) {
                        case 0 -> {
                            System.out.println("Bye!");
                            return;
                        }
                        case 1 -> AddKard();
                        case 2 -> ShowBalance();
                        case 3 -> AddBalance();
                        default -> System.out.println("Invalid choose");
                    }
                }
                case 5 -> Cancel();

                case 6 -> history();

                default -> System.out.println("Invalid choose");
            }
        }
    }

    private static void history() {
        int n = 1;
        boolean hasBookings = false;
        for (Booking booking : Database.historyBooking) {
            if (booking.getUser().equals(AuthService.Currentuser)) {
                hasBookings = true;
                System.out.println(n + "- Booking");
                System.out.println("Booking Id: " + booking.getId());
                System.out.println("Floor number: " + (booking.getRoom().getFloor_number() + 1));
                System.out.println("Room number: " + (booking.getRoom().getRoom_number() + 1));
                System.out.println("From date: " + booking.getFrom_Date());
                System.out.println("To date: " + booking.getTo_Date());
                long between = ChronoUnit.DAYS.between(booking.getFrom_Date(), booking.getTo_Date());
                System.out.println("Booked for: " + between + " days");
                n++;
            }
        }
        if (!hasBookings) {
            System.out.println("You don't have any past bookings.");
        }
    }


    private static void addBooking() {
        LocalDate today = LocalDate.of(2024, 7, 15);
        System.out.println("Thanks for use our service: ");
        System.out.println("cost per days 100000! ");
        System.out.println("from day: (day/month/year)");
        from = AuthService.strScanner.nextLine();
        LocalDate Lfrom = LocalDate.parse(from, formatter);
        System.out.println("to day: (day/month/year)");
        to = AuthService.strScanner.nextLine();
        LocalDate Lto = LocalDate.parse(to, formatter);
        if (Lfrom.isAfter(Lto) || Lfrom.isBefore(today) || Lto.isBefore(today)) {
            System.out.println("Invalid date!");
            return;
        }
        System.out.println("Floor number: [1-10]");
        int q = AuthService.scanner.nextInt();
        q--;
        if (q > 10 || q < 0) {
            System.out.println("10 floors ");
            return;
        }
        System.out.println("Room number: [1-20]");
        int x = AuthService.scanner.nextInt();
        x--;
        if (x > 20 || x < 0) {
            System.out.println("20 rooms for each floor");
            return;
        }


        if (Database.historyBooking.isEmpty()) {
            if (Database.rooms[q][x].getFree().equals(true) && Database.rooms[q][x].getActive().equals(true)) {
                if (Objects.equals(AuthService.Currentuser.getKard(), null)) {
                    System.out.println("enter card!");
                    return;
                }
                long between = ChronoUnit.DAYS.between(Lfrom, Lto);
                double summa = (between * Database.rooms[q][x].getPrice());
                if (((AuthService.Currentuser.getKard().getBalance() - 1) < summa)) {
                    System.out.println("There are insufficient funds in the balance sheet!");
                } else {
                    double balance = (AuthService.Currentuser.getKard().getBalance() - summa);
                    AuthService.Currentuser.getKard().setBalance(balance);
                    Booking b = new Booking();
                    b.setUser(AuthService.Currentuser);
                    Room rom = new Room(q, x, true, false);
                    b.setRoom(rom);
                    b.setFrom_Date(Lfrom);
                    b.setTo_Date(Lto);
                    b.setSharh(AuthService.Currentuser + " by user " + rom + " by user " + Lfrom + " from date " + Lto + " to date booked!");
                    Database.historyBooking.add(b);
                    System.out.println((q + 1) + "-floor " + (x + 1) + "-room successfully booked!");
                    System.out.println("booked for you!");

                }

            }


        } else {
            boolean v = true;
            LocalDate gfrom = null;
            LocalDate gto = null;
            for (Booking booking : Database.historyBooking) {

                if (booking.getRoom().getFloor_number() == q && booking.getRoom().getRoom_number() == x) {
                    LocalDate gfrom_2 = booking.getFrom_Date().minusDays(1);
                    LocalDate gto_2 = booking.getTo_Date().plusDays(1);
                    gfrom = booking.getFrom_Date();
                    gto = booking.getTo_Date();
                    if (!(Lfrom.isAfter(gto_2) || Lto.isBefore(gfrom_2))) {
                        v = false;
                    }

                }
            }
            if (v) {
                if (Database.rooms[q][x].getFree().equals(true) && Database.rooms[q][x].getActive().equals(true)) {
                    System.out.println((q + 1) + "-floor " + (x + 1) + "-room ready to use!");
                    if (Objects.equals(AuthService.Currentuser.getKard(), null)) {
                        System.out.println("enter card!");
                        return;
                    }
                    long between = ChronoUnit.DAYS.between(Lfrom, Lto);
                    double summa = (between * Database.rooms[q][x].getPrice());
                    if (((AuthService.Currentuser.getKard().getBalance() - 1.0) < summa)) {
                        System.out.println("not enough money!");
                    } else {
                        double balance = (AuthService.Currentuser.getKard().getBalance() - summa);
                        AuthService.Currentuser.getKard().setBalance(balance);
                        Booking b = new Booking();
                        b.setUser(AuthService.Currentuser);
                        Room rom = new Room(q, x, true, false);
                        b.setRoom(rom);
                        b.setFrom_Date(Lfrom);
                        b.setTo_Date(Lto);
                        b.setSharh(AuthService.Currentuser + " by user " + rom + " room " + Lfrom + " from date " + Lto + " to date successfully booked!");
                        Database.historyBooking.add(b);
                        System.out.println("booked for you!");
                    }
                }


            } else {
                System.out.println(gfrom.minusDays(1) + " from date " + gto.plusDays(1) + " to date, no empty room!");
            }


        }


    }


    private static void FreeRooms() {
        System.out.println("""
                0 exit
                1 show free room by rooms
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
                        System.out.println((booking.getFrom_Date().minusDays(1)) + " from date " + booking.getTo_Date().plusDays(1) + " to date, already booked by another user!");
                        System.out.println("--------------");

                    }

                }
                if (b) System.out.println("room free!");
            }
            case 2 -> {
                if (Database.historyBooking.isEmpty()) {
                    System.out.println("free 1:1 ");
                    System.out.println("busy -- ");

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
                                    System.out.println(Database.rooms[i][j].getFloor_number() + ":" + Database.rooms[i][j].getRoom_number() + " room " + booking.getFrom_Date().minusDays(1) + " from date " + booking.getTo_Date().plusDays(1) + " to date busy room!");
                                }
                            }
                        }
                    }
                    System.out.println("the rest of the rooms are empty!");

                }

            }
            default -> System.out.println("Invalid choose!");
        }


    }


    private static void AddKard() {
        Kard kard = new Kard();
        System.out.println("enter card number: ");
        kard.setKard_number(AuthService.strScanner.nextLine());
        System.out.println("enter card name: ");
        kard.setKard_name(AuthService.strScanner.nextLine());
        System.out.println("enter expression date: ");
        kard.setYaroqlilik_sana(AuthService.strScanner.nextLine());
        System.out.println("fill balance: ");
        kard.setBalance((double) AuthService.scanner.nextInt());
        AuthService.Currentuser.setKard(kard);
        System.out.println("success!");
    }


    private static void AddBalance() {
        System.out.println("Enter summa: ");
        Double d = (double) AuthService.scanner.nextInt();
        Double d1 = (AuthService.Currentuser.getKard().getBalance() + d);
        AuthService.Currentuser.getKard().setBalance(d1);
        System.out.println("success!");
        System.out.println(AuthService.Currentuser.getKard().getBalance());


    }

    private static void ShowBalance() {
        System.out.println(AuthService.Currentuser.getKard().getBalance() + " sum");
    }

    private static void Cancel() {
        if (Database.historyBooking.isEmpty()) {
            System.out.println("not booking yet!");
        } else {
            for (int i = 0; i < Database.historyBooking.size(); i++) {
                if (Database.historyBooking.get(i).getUser().equals(AuthService.Currentuser)) {
                    System.out.println();
                    System.out.println((i + 1) + "- booking ");
                    System.out.println("booking Id " + Database.historyBooking.get(i).getId());
                    System.out.println("floor " + (Database.historyBooking.get(i).getRoom().getFloor_number() + 1));
                    System.out.println("room " + (Database.historyBooking.get(i).getRoom().getRoom_number() + 1));
                    System.out.println(Database.historyBooking.get(i).getFrom_Date() + "- from date ");
                    System.out.println(Database.historyBooking.get(i).getTo_Date() + "- to date ");
                    long between = ChronoUnit.DAYS.between(Database.historyBooking.get(i).getFrom_Date(), Database.historyBooking.get(i).getTo_Date());
                    System.out.println(between + "-busy for the day!");
                }
            }
            System.out.println("enter booking number: ");
            int r = AuthService.scanner.nextInt();
            if ((r - 1) > Database.historyBooking.size()) {
                System.out.println("invalid number");
                return;

            }
            Booking booking1 = Database.historyBooking.get(r - 1);
            if (booking1.getUser().equals(AuthService.Currentuser)) {
                long between = ChronoUnit.DAYS.between(booking1.getFrom_Date(), booking1.getTo_Date());
                double summa = (between * booking1.getRoom().getPrice());
                double res = (AuthService.Currentuser.getKard().getBalance() + summa);
                AuthService.Currentuser.getKard().setBalance(res);

                System.out.println("successfully cancelled✅✅");
            }
        }

    }

    private static void Mybooking() {
        int n = 1;
        boolean b = true;
        for (Booking booking : Database.historyBooking) {
            if (booking.getUser().equals(AuthService.Currentuser)/* && booking.getRoom().getFree().equals(false)*/) {
                b = false;
                System.out.println(n + "-Booking");
                System.out.println("Booking Id " + booking.getId());
                System.out.println("floor number: " + (booking.getRoom().getFloor_number() + 1));
                System.out.println("room number: " + (booking.getRoom().getRoom_number() + 1));
                System.out.println(booking.getFrom_Date() + "- from date ");
                System.out.println(booking.getTo_Date() + "- to date ");
                long between = ChronoUnit.DAYS.between(booking.getFrom_Date(), booking.getTo_Date());
                System.out.println(between + "-busy for the day!");
                n++;
            }
        }
        if (b) {
            System.out.println("not bookings yet!");
        }
    }

}

