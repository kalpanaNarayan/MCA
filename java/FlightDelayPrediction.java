import java.util.Scanner;

class Flight {
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    protected int delayTime; // Change access specifier to protected

    // Default constructor
    public Flight() {
        this.flightNumber = "Unknown";
        this.departureCity = "Unknown";
        this.arrivalCity = "Unknown";
        this.delayTime = 0;
    }

    // Parameterized constructor
    public Flight(String flightNumber, String departureCity, String arrivalCity) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.delayTime = 0;
    }

    // Method to set delay time
    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    // Method to predict delay based on weather conditions
    public void predictDelayByWeather(String weatherCondition) {
        if (weatherCondition.equals("Good")) {
            System.out.println("No delay expected for flight " + flightNumber);
        } else if (weatherCondition.equals("Bad")) {
            delayTime = 60;
            System.out.println("Flight " + flightNumber + " may experience a 60-minute delay due to bad weather.");
        }
    }

    // Method overloading to predict delay based on wind speed
    public void predictDelay(int windSpeed) {
        if (windSpeed <= 10) {
            System.out.println("No significant delay expected for flight " + flightNumber);
        } else if (windSpeed <= 30) {
            delayTime = 30;
            System.out.println("Flight " + flightNumber + " may experience a 30-minute delay due to strong winds.");
        } else {
            delayTime = 60;
            System.out.println("Flight " + flightNumber + " may experience a 60-minute delay due to severe winds.");
        }
    }

    // Method overloading to predict delay based on air traffic
    public void predictDelay(boolean highTraffic) {
        if (highTraffic) {
            delayTime = 45;
            System.out.println("Flight " + flightNumber + " may experience a 45-minute delay due to high air traffic.");
        } else {
            System.out.println("No significant delay expected for flight " + flightNumber);
        }
    }

    // Method to predict delay based on time of day
    public void predictDelayByTimeOfDay(String timeOfDay) {
        if (timeOfDay.equals("Morning")) {
            delayTime = 15;
            System.out.println("Flight " + flightNumber + " may experience a 15-minute delay in the morning.");
        } else if (timeOfDay.equals("Evening")) {
            delayTime = 30;
            System.out.println("Flight " + flightNumber + " may experience a 30-minute delay in the evening.");
        } else {
            System.out.println("No significant delay expected for flight " + flightNumber);
        }
    }

    // Method to display flight details
    public void displayFlightDetails() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Departure City: " + departureCity);
        System.out.println("Arrival City: " + arrivalCity);
        System.out.println("Delay Time: " + delayTime + " minutes");
    }
}

public class FlightDelayPrediction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter flight details:");
        System.out.print("Flight Number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Departure City: ");
        String departureCity = scanner.nextLine();
        System.out.print("Arrival City: ");
        String arrivalCity = scanner.nextLine();

        Flight flight = new Flight(flightNumber, departureCity, arrivalCity);
        flight.displayFlightDetails();

        System.out.print("Enter weather condition (Good/Bad): ");
        String weatherCondition = scanner.nextLine();
        flight.predictDelayByWeather(weatherCondition);

        System.out.print("Enter wind speed (in mph): ");
        int windSpeed = scanner.nextInt();
        flight.predictDelay(windSpeed);

        System.out.print("Is there high air traffic? (true/false): ");
        boolean highTraffic = scanner.nextBoolean();
        flight.predictDelay(highTraffic);

        System.out.print("Enter time of day (Morning/Evening): ");
        scanner.nextLine(); // Consume the newline character
        String timeOfDay = scanner.nextLine();
        flight.predictDelayByTimeOfDay(timeOfDay);

        flight.displayFlightDetails();

        scanner.close();
    }
}
