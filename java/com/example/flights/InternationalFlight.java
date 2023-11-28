// File: InternationalFlight.java
package com.example.flights;

import java.util.Scanner;

public class InternationalFlight implements Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int scheduledDepartureTime;
    private String aircraftType;

    public InternationalFlight(String flightNumber, String origin, String destination, int scheduledDepartureTime, String aircraftType) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.aircraftType = aircraftType;
    }

    @Override
    public void schedule() {
        System.out.println("International flight " + flightNumber + " scheduled from " + origin + " to " + destination +
                " at " + scheduledDepartureTime + " hours with " + aircraftType + ".");
    }

    @Override
    public void delay(int hours) {
        System.out.println("International flight " + flightNumber + " delayed by " + hours + " hours.");
        scheduledDepartureTime += hours;
    }

    public static InternationalFlight createInternationalFlightFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flight number: ");
        String flightNumber = scanner.nextLine();

        System.out.println("Enter origin: ");
        String origin = scanner.nextLine();

        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.println("Enter scheduled departure time: ");
        int scheduledDepartureTime = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter aircraft type: ");
        String aircraftType = scanner.nextLine();

        return new InternationalFlight(flightNumber, origin, destination, scheduledDepartureTime, aircraftType);
    }
}
