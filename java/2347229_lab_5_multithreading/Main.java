import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n~~~~~~~~~Welcome to the Flight Delay Prediction System!~~~~~~~~~~~~");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the name of the airport: ");
            String airportName = scanner.nextLine();

            System.out.print("Enter the name of the flight: ");
            String flightName = scanner.nextLine();

            // Create thread and start
            Flight flight = new Flight(airportName, flightName);
            flight.start();

            // Wait until the flight is ready for takeoff
            synchronized (Flight.class) {
                while (!Flight.readyForTakeoff) {
                    try {
                        Flight.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Output flight information after the flight is ready for takeoff
            System.out.println("Flight Information for " + flightName + " departing from " + airportName + ":");
            System.out.println("    - The flight is delayed by " + flight.getDelayMinutes() + " minutes and now scheduled to depart at " + flight.getDepartureTime() + ".");
            System.out.println("    - " + flight.getAdditionalInfo());
            System.out.println("    - Flight number: " + flight.getFlightNumber());
            System.out.println("****************************************************");

            // Continue the loop for more flights
            System.out.print("Do you want to add another flight? (yes/no): ");
            String userInput = scanner.nextLine().toLowerCase();
            if (!userInput.equals("yes")) {
                break; // Exit the loop if the user doesn't want to add more flights
            }
        }

        // Wait for all flights to complete
        try {
            Thread.sleep(1000); // Add a small delay to ensure all flights have completed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Summary after processing all flights
        System.out.println("Flight delay predictions have been made for all flights.");
        System.out.println("Thank you for using the Flight Delay Prediction System.");
        System.out.println("All flights are ready for takeoff. Continuing with other operations.");

        scanner.close();
    }
}
