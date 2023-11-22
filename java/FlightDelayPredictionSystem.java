import java.util.Scanner;

// Base class
class Flight {
    private String flightNumber;
    private String destination;
    private int scheduledDepartureTime;

    public Flight(String flightNumber, String destination, int scheduledDepartureTime) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public void displayFlightDetails() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Destination: " + destination);
        System.out.println("Scheduled Departure Time: " + formatTime(scheduledDepartureTime));
    }

    private String formatTime(int time) {
        int hours = time / 100;
        int minutes = time % 100;
        String period = (hours < 12) ? "AM" : "PM";

        if (hours > 12) {
            hours -= 12;
        } else if (hours == 0) {
            hours = 12;
        }

        return String.format("%02d:%02d %s", hours, minutes, period);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(int scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }
}

// Subclass inheriting from Flight
class DelayedFlight extends Flight {
    private int delayMinutes;

    public DelayedFlight(String flightNumber, String destination, int scheduledDepartureTime, int delayMinutes) {
        super(flightNumber, destination, scheduledDepartureTime);
        this.delayMinutes = delayMinutes;
    }

    @Override
    public void displayFlightDetails() {
        super.displayFlightDetails();
        System.out.println("Delay: " + delayMinutes + " minutes");
    }

    public int getTotalDepartureTime() {
        // Calculate the actual departure time including delay
        return getScheduledDepartureTime() + delayMinutes;
    }
}

// Abstract class
abstract class FlightPredictor {
    public abstract void predictDelay();
}

// Final class
final class SimpleFlightPredictor extends FlightPredictor {
    private DelayedFlight delayedFlight;

    public SimpleFlightPredictor(DelayedFlight delayedFlight) {
        this.delayedFlight = delayedFlight;
    }

    @Override
    public void predictDelay() {
        // Simple prediction logic (you can replace this with a more sophisticated algorithm)
        System.out.println("Predicting delay for Flight " + delayedFlight.getFlightNumber() + "...");
        System.out.println("Predicted Delay: " + (delayedFlight.getTotalDepartureTime() - delayedFlight.getScheduledDepartureTime()) + " minutes");
        System.out.println("Prediction successful!");
    }
}

public class FlightDelayPredictionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for base class (Flight)
        System.out.println("Enter flight number: ");
        String flightNumber = scanner.nextLine();

        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.println("Enter scheduled departure time (HHMM): ");
        int scheduledDepartureTime = scanner.nextInt();

        // Create an instance of base class
        Flight flight = new Flight(flightNumber, destination, scheduledDepartureTime);

        // Display details of base class
        System.out.println("Flight Details:");
        flight.displayFlightDetails();

        // User input for subclass (DelayedFlight)
        System.out.println("Enter delay in minutes: ");
        int delayMinutes = scanner.nextInt();

        // Create an instance of subclass
        DelayedFlight delayedFlight = new DelayedFlight(flightNumber, destination, scheduledDepartureTime, delayMinutes);

        // Display details of subclass
        System.out.println("\nDelayed Flight Details:");
        delayedFlight.displayFlightDetails();

        // Create an instance of final class (SimpleFlightPredictor)
        SimpleFlightPredictor flightPredictor = new SimpleFlightPredictor(delayedFlight);

        // Call method from final class
        flightPredictor.predictDelay();

        scanner.close();
    }
}
