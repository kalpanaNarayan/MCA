import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Generic interface representing an orderable item
interface Orderable {
    String getName();
    double getPrice();
}

// Generic class representing a Flight
class Flight<T extends Orderable & FlightDelayPredictor> {
    private T flightDetails;

    public Flight(T flightDetails) {
        this.flightDetails = flightDetails;
    }

    public T getFlightDetails() {
        return flightDetails;
    }

    public void displayFlightDetails() {
        System.out.println("\nFlight: " + flightDetails.getName());
        System.out.println("Price: Rs" + flightDetails.getPrice());
        System.out.println("Delay Prediction: " + flightDetails.predictDelay());
        System.out.println();
    }
}

// Generic interface for predicting flight delays
interface FlightDelayPredictor {
    boolean predictDelay();
}

// Concrete class representing flight order details
class FlightOrder implements Orderable, FlightDelayPredictor {
    private String flightName;
    private double price;
    private boolean isDelayed;

    public FlightOrder(String flightName, double price, boolean isDelayed) {
        this.flightName = flightName;
        this.price = price;
        this.isDelayed = isDelayed;
    }

    @Override
    public String getName() {
        return flightName;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean predictDelay() {
        return isDelayed;
    }
}

public class FlightGenericsExample {
    public static void main(String[] args) {
        List<Flight<FlightOrder>> flights = new ArrayList<>();

        // Adding flights based on user input
        while (true) {
            FlightOrder newFlight = createFlight();
            Flight<FlightOrder> newFlightItem = new Flight<>(newFlight);
            flights.add(newFlightItem);

            System.out.print("Do you want to add another flight? (yes/no): ");
            Scanner scanner = new Scanner(System.in);
            if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }

        // Displaying flight details
        System.out.println("\nFlight Details:");
        for (Flight<FlightOrder> flightItem : flights) {
            flightItem.displayFlightDetails();
        }
    }

    // Helper method to create a FlightOrder based on user input
    private static FlightOrder createFlight() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter flight name: ");
        String name = scanner.nextLine();

        System.out.print("Enter flight price: Rs");
        double price = scanner.nextDouble();

        System.out.print("Is the flight delayed? (true/false):");
        boolean isDelayed = scanner.nextBoolean();

        return new FlightOrder(name, price, isDelayed);
    }
}
