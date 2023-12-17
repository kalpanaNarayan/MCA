import java.util.Scanner;

public class Delay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Flight Delay Prediction System!");

        System.out.print("Enter the name of the airport: ");
        String airportName = scanner.nextLine();

        int numFlights = 0;

        while (numFlights <= 0) {
            System.out.print("Enter the number of flights (should be a positive integer): ");

            try {
                numFlights = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        Flight[] flights = new Flight[numFlights];

        for (int i = 0; i < numFlights; i++) {
            System.out.print("Enter the name of flight " + (i + 1) + ": ");
            String flightName = scanner.nextLine();

            flights[i] = new Flight(airportName, flightName);
            flights[i].start();
        }

        // Wait for all flights to complete
        for (Flight flight : flights) {
            try {
                flight.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Output delay predictions summary
        System.out.println("****************************************************");
        for (Flight flight : flights) {
            System.out.println("Flight " + flight.getFlightNumber() + " departing from " + airportName + " is scheduled to depart.");
            System.out.println("Flight " + flight.getFlightNumber() + " is predicted to be on time.");
            System.out.println("Flight " + flight.getFlightNumber() + " is delayed by " + flight.getDelayMinutes() + " minutes and now scheduled to depart at " + flight.getDepartureTime() + ".");
            System.out.println("Additional information for Flight " + flight.getFlightNumber() + ": " + flight.getAdditionalInfo());
            System.out.println("****************************************************");
        }

        System.out.println("Flight delay predictions have been made for all flights.");
        System.out.println("Thank you for using the Flight Delay Prediction System.");

        scanner.close();
    }
}
