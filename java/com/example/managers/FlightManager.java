// File: FlightManager.java
package com.example.managers;

import com.example.flights.DomesticFlight;
import com.example.flights.Flight;
import com.example.flights.InternationalFlight;

import java.util.Scanner;

public class FlightManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example usage of the Flight interface and implementations
        Flight domesticFlight = DomesticFlight.createDomesticFlightFromUserInput();
        domesticFlight.schedule();
        System.out.println("Enter delay for domestic flight: ");
        int domesticDelay = scanner.nextInt();
        domesticFlight.delay(domesticDelay);
        domesticFlight.schedule();

        // Using InternationalFlight
        Flight internationalFlight = InternationalFlight.createInternationalFlightFromUserInput();
        internationalFlight.schedule();
        System.out.println("Enter delay for international flight: ");
        int internationalDelay = scanner.nextInt();
        internationalFlight.delay(internationalDelay);
        internationalFlight.schedule();
    }
}
