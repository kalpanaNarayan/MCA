import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Flight {
    String flightNumber;
    int delayInMinutes;

    public Flight(String flightNumber, int delayInMinutes) {
        this.flightNumber = flightNumber;
        this.delayInMinutes = delayInMinutes;
    }

    public int getDelayInMinutes() {
        return delayInMinutes;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}

interface FlightFilter {
    boolean test(Flight flight);
}

public class lab7 {

    public static void main(String[] args) {
        List<Flight> flights = getUserInput();

        // Using lambda expression to filter delayed flights
        List<Flight> delayedFlights = filterFlights(flights, flight -> flight.getDelayInMinutes() > 15);

        // Displaying delayed flights
        System.out.println("Delayed Flights:");
        for (Flight flight : delayedFlights) {
            System.out.println(flight.getFlightNumber() + " - Delay: " + flight.getDelayInMinutes() + " minutes");
        }
    }

    private static List<Flight> getUserInput() {
        List<Flight> flights = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of flights: ");
        int numberOfFlights = scanner.nextInt();

        for (int i = 0; i < numberOfFlights; i++) {
            System.out.println("Enter details for Flight " + (i + 1) + ":");
            System.out.print("Flight Number: ");
            String flightNumber = scanner.next();
            System.out.print("Delay in Minutes: ");
            int delayInMinutes = scanner.nextInt();

            Flight flight = new Flight(flightNumber, delayInMinutes);
            flights.add(flight);
        }

        return flights;
    }

    private static List<Flight> filterFlights(List<Flight> flights, FlightFilter flightFilter) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flightFilter.test(flight)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}
