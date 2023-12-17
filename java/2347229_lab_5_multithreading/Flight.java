import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Flight extends Thread {
    private static int sharedCounter = 0; // Shared resource
    public static boolean readyForTakeoff = false; // Flag for inter-thread communication

    private String airportName;
    private String flightName;
    private int delayMinutes;

    public Flight(String airportName, String flightName) {
        this.airportName = airportName;
        this.flightName = flightName;
        this.delayMinutes = generateRandomDelay();
    }

    public void run() {
        synchronized (Flight.class) {
            sharedCounter++;
            int flightNumber = sharedCounter;

            // Notify that the flight is ready for takeoff
            readyForTakeoff = true;
            Flight.class.notify();

            // Other operations after notifying...
        }
    }

    private int generateRandomDelay() {
        Random random = new Random();
        return random.nextInt(60); // Simulating a delay from 0 to 59 minutes
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    public String getDepartureTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public String getAdditionalInfo() {
        Random random = new Random();
        String[] aircraftTypes = {"Boeing 747", "Airbus A320", "Boeing 777", "Embraer E190"};
        String[] destinations = {"New York", "London", "Tokyo", "Paris", "Sydney"};
        String[] specialServices = {"In-flight Wi-Fi", "Premium Lounge Access", "Extra Legroom Seats"};

        String randomAircraftType = aircraftTypes[random.nextInt(aircraftTypes.length)];
        String randomDestination = destinations[random.nextInt(destinations.length)];
        String randomSpecialService = specialServices[random.nextInt(specialServices.length)];

        return "[Type of aircraft: " + randomAircraftType + ", Destination: " + randomDestination + ", Special Service: " + randomSpecialService + "]";
    }

    public int getFlightNumber() {
        return sharedCounter;
    }
}
