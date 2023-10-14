
import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private String PNR;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String source;
    private String destination;

    public Reservation(String PNR, String trainNumber, String trainName, String classType,
                       String dateOfJourney, String source, String destination) {
        this.PNR = PNR;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.source = source;
        this.destination = destination;
    }

    public String getPNR() {
        return PNR;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getClassType() {
        return classType;
    }

    public String getDateOfJourney() {
        return dateOfJourney;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}

class OnlineReservationSystem {
    private static List<User> users = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    loginUser(scanner);
                } else if (choice == 2) {
                    System.out.println("Exiting the reservation system.");
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("1. Make a reservation");
                System.out.println("2. Cancel reservation");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        makeReservation(scanner);
                        break;
                    case 2:
                        cancelReservation(scanner);
                        break;
                    case 3:
                        currentUser = null;
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
                return;
            }
        }

        System.out.println("Invalid username or password.");
    }

    private static void makeReservation(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please log in to make a reservation.");
            return;
        }

        System.out.print("Enter train number: ");
        String trainNumber = scanner.next();
        System.out.print("Enter train name: ");
        String trainName = scanner.next();
        System.out.print("Enter class type: ");
        String classType = scanner.next();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.next();
        System.out.print("Enter source: ");
        String source = scanner.next();
        System.out.print("Enter destination: ");
        String destination = scanner.next();

        // Generate a unique PNR (you can implement your own logic here)
        String PNR = generatePNR();

        // Create a reservation
        Reservation reservation = new Reservation(PNR, trainNumber, trainName, classType, dateOfJourney, source, destination);

        // Add the reservation to the list
        reservations.add(reservation);

        System.out.println("Reservation created with PNR: " + PNR);
    }

    private static void cancelReservation(Scanner scanner) {
        if (currentUser == null) {
            System.out.println("Please log in to cancel a reservation.");
            return;
        }

        System.out.print("Enter PNR number to cancel the reservation: ");
        String PNRToCancel = scanner.next();

        // Find the reservation with the given PNR and remove it
        boolean found = false;
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getPNR().equals(PNRToCancel)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Reservation with PNR " + PNRToCancel + " has been canceled.");
        } else {
            System.out.println("Reservation with PNR " + PNRToCancel + " not found.");
        }
    }

    private static String generatePNR() {
        // Implement your logic to generate a unique PNR here
        // For simplicity, you can use a random or incremental PNR generation approach.
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
