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
        System.out.println("Flight: " + flightDetails.getName());
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
        // Creating flights based on user input
        FlightOrder domesticFlight = createFlight();
        FlightOrder internationalFlight = createFlight();
        FlightOrder connectingFlight = createFlight();

        // Creating flight items using generics
        Flight<FlightOrder> domesticFlightItem = new Flight<>(domesticFlight);
        Flight<FlightOrder> internationalFlightItem = new Flight<>(internationalFlight);
        Flight<FlightOrder> connectingFlightItem = new Flight<>(connectingFlight);

        // Displaying flight details
        System.out.println("Flight Details:");
        domesticFlightItem.displayFlightDetails();
        internationalFlightItem.displayFlightDetails();
        connectingFlightItem.displayFlightDetails();
    }

    // Helper method to create a FlightOrder based on user input
    private static FlightOrder createFlight() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter flight name: ");
        String name = scanner.nextLine();

        System.out.print("Enter flight price: Rs");
        double price = scanner.nextDouble();

        System.out.print("Is the flight delayed? (true/false): ");
        boolean isDelayed = scanner.nextBoolean();

        return new FlightOrder(name, price, isDelayed);
    }
}
