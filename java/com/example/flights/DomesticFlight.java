// File: DomesticFlight.java
package com.example.flights;

import java.util.Scanner;

public class DomesticFlight implements Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int scheduledDepartureTime;

    public DomesticFlight(String flightNumber, String origin, String destination, int scheduledDepartureTime) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    @Override
    public void schedule() {
        System.out.println("Flight " + flightNumber + " scheduled from " + origin + " to " + destination +
                " at " + scheduledDepartureTime + " hours.");
    }

    @Override
    public void delay(int hours) {
        System.out.println("Flight " + flightNumber + " delayed by " + hours + " hours.");
        scheduledDepartureTime += hours;
    }

    public static DomesticFlight createDomesticFlightFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter flight number: ");
        String flightNumber = scanner.nextLine();

        System.out.println("Enter origin: ");
        String origin = scanner.nextLine();

        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.println("Enter scheduled departure time: ");
        int scheduledDepartureTime = scanner.nextInt();

        return new DomesticFlight(flightNumber, origin, destination, scheduledDepartureTime);
    }
}
