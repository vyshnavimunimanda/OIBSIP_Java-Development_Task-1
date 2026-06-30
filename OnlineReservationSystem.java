import java.util.*;
class User {
    String username;
    String password;
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
class Reservation {
    String passengerName;
    int age;
    String gender;
    int trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String destination;
    String pnr;
    Reservation(String passengerName, int age, String gender,
                int trainNumber, String trainName,
                String classType, String dateOfJourney,
                String from, String destination, String pnr) {
        this.passengerName = passengerName;
        this.age = age;
        this.gender = gender;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.destination = destination;
        this.pnr = pnr;
    }
    void displayReservation() {
        System.out.println("\n----- Reservation Details -----");
        System.out.println("PNR Number      : " + pnr);
        System.out.println("Passenger Name  : " + passengerName);
        System.out.println("Age             : " + age);
        System.out.println("Gender          : " + gender);
        System.out.println("Train Number    : " + trainNumber);
        System.out.println("Train Name      : " + trainName);
        System.out.println("Class Type      : " + classType);
        System.out.println("Journey Date    : " + dateOfJourney);
        System.out.println("From            : " + from);
        System.out.println("Destination     : " + destination);
    }
}

public class OnlineReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Reservation> reservations = new ArrayList<>();

    static HashMap<Integer, String> trains = new HashMap<>();

    static {
        trains.put(101, "Rajdhani Express");
        trains.put(102, "Shatabdi Express");
        trains.put(103, "Duronto Express");
        trains.put(104, "Garib Rath");
    }

    public static void main(String[] args) {

        User user = new User("admin", "1234");

        System.out.println("===== ONLINE RESERVATION SYSTEM =====");

        if (login(user)) {

            int choice;

            do {
                System.out.println("\n===== MAIN MENU =====");
                System.out.println("1. Reservation Form");
                System.out.println("2. Cancellation Form");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        reservationForm();
                        break;

                    case 2:
                        cancellationForm();
                        break;

                    case 3:
                        System.out.println("Thank You for using Online Reservation System!");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } while (choice != 3);

        } else {
            System.out.println("Invalid Login Credentials!");
        }
    }

    static boolean login(User user) {

        System.out.print("Enter Login ID: ");
        String loginId = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        return loginId.equals(user.username) && password.equals(user.password);
    }

    static void reservationForm() {

        System.out.println("\n===== Reservation Form =====");

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();

        System.out.println("\nAvailable Trains:");
        for (Map.Entry<Integer, String> train : trains.entrySet()) {
            System.out.println(train.getKey() + " - " + train.getValue());
        }

        System.out.print("Enter Train Number: ");
        int trainNumber = sc.nextInt();
        sc.nextLine();

        String trainName = trains.get(trainNumber);

        if (trainName == null) {
            System.out.println("Invalid Train Number!");
            return;
        }

        System.out.println("Train Name: " + trainName);

        System.out.print("Enter Class Type (Sleeper/AC/General): ");
        String classType = sc.nextLine();

        System.out.print("Enter Date of Journey: ");
        String date = sc.nextLine();

        System.out.print("From: ");
        String from = sc.nextLine();

        System.out.print("Destination: ");
        String destination = sc.nextLine();

        String pnr = "PNR" + (1000 + reservations.size() + 1);

        Reservation reservation = new Reservation(
                name, age, gender,
                trainNumber, trainName,
                classType, date,
                from, destination, pnr);

        reservations.add(reservation);

        System.out.println("\nReservation Successful!");
        System.out.println("Your PNR Number is: " + pnr);
    }

    static void cancellationForm() {

        System.out.println("\n===== Cancellation Form =====");

        System.out.print("Enter PNR Number: ");
        String pnr = sc.nextLine();

        Reservation foundReservation = null;

        for (Reservation reservation : reservations) {
            if (reservation.pnr.equalsIgnoreCase(pnr)) {
                foundReservation = reservation;
                break;
            }
        }

        if (foundReservation != null) {

            foundReservation.displayReservation();

            System.out.print("\nConfirm Cancellation? (yes/no): ");
            String confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                reservations.remove(foundReservation);
                System.out.println("Ticket Cancelled Successfully!");
            } else {
                System.out.println("Cancellation Aborted!");
            }

        } else {
            System.out.println("PNR Number Not Found!");
        }
    }
}