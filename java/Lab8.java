import java.util.*;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int scheduledDepartureTime;
    private int actualDepartureTime;
    private int delayMinutes;

    // Constructor
    public Flight(String flightNumber, String origin, String destination,
                  int scheduledDepartureTime, int actualDepartureTime, int delayMinutes) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.actualDepartureTime = actualDepartureTime;
        this.delayMinutes = delayMinutes;
    }

    // Getter methods
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public int getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public int getActualDepartureTime() {
        return actualDepartureTime;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    // ... (you can add more getter methods if needed)

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", scheduledDepartureTime=" + scheduledDepartureTime +
                ", actualDepartureTime=" + actualDepartureTime +
                ", delayMinutes=" + delayMinutes +
                '}';
    }
}

public class Lab8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a HashMap to store flights
        Map<String, Flight> flightsMap = new HashMap<>();

        // Creating an ArrayList to store delayed flights
        List<Flight> delayedFlightsList = new ArrayList<>();

        // Creating a TreeMap to store flights sorted by scheduled departure time
        Map<Integer, Flight> scheduledDepartureTimeMap = new TreeMap<>();

        // Creating a HashSet to store unique origins
        Set<String> uniqueOriginsSet = new HashSet<>();

        // Taking user input for adding flights
        System.out.println("Enter flight details (flightNumber origin destination scheduledDepartureTime actualDepartureTime delayMinutes):");
        for (int i = 0; i < 3; i++) {  // Let's add 3 flights for demonstration
            System.out.print("Flight " + (i + 1) + ": ");
            String input = scanner.nextLine();
            String[] flightDetails = input.split(" ");

            // Assuming the input is in correct format
            Flight flight = new Flight(
                    flightDetails[0],
                    flightDetails[1],
                    flightDetails[2],
                    Integer.parseInt(flightDetails[3]),
                    Integer.parseInt(flightDetails[4]),
                    Integer.parseInt(flightDetails[5])
            );

            // Adding to HashMap
            flightsMap.put(flight.getFlightNumber(), flight);

            // Checking for delayed flights and adding to ArrayList
            if (flight.getDelayMinutes() > 0) {
                delayedFlightsList.add(flight);
            }

            // Adding to TreeMap based on scheduled departure time
            scheduledDepartureTimeMap.put(flight.getScheduledDepartureTime(), flight);

            // Adding origin to HashSet
            uniqueOriginsSet.add(flight.getOrigin());
        }

        // Displaying all flights from HashMap
        System.out.println("\nAll Flights (HashMap):");
        for (Flight flight : flightsMap.values()) {
            System.out.println(flight);
        }

        // Displaying delayed flights from ArrayList
        System.out.println("\nDelayed Flights (ArrayList):");
        for (Flight flight : delayedFlightsList) {
            System.out.println(flight);
        }

        // Displaying flights sorted by scheduled departure time from TreeMap
        System.out.println("\nFlights Sorted by Scheduled Departure Time (TreeMap):");
        for (Flight flight : scheduledDepartureTimeMap.values()) {
            System.out.println(flight);
        }

        // Displaying unique origins from HashSet
        System.out.println("\nUnique Origins (HashSet):");
        for (String origin : uniqueOriginsSet) {
            System.out.println(origin);
        }

        // Taking user input for retrieving a specific flight from HashMap
        System.out.print("\nEnter the flight number to retrieve (HashMap): ");
        String flightNumberToRetrieve = scanner.nextLine();
        Flight retrievedFlight = flightsMap.get(flightNumberToRetrieve);

        if (retrievedFlight != null) {
            System.out.println("\nRetrieved Flight " + flightNumberToRetrieve + " from HashMap:");
            System.out.println(retrievedFlight);
        } else {
            System.out.println("\nFlight " + flightNumberToRetrieve + " not found in HashMap.");
        }

        scanner.close();
    }
}
