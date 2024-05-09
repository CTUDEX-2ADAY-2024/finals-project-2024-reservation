package main.java.com.ctu.reservationportal.reservation.infrastructure;

import main.java.com.ctu.reservationportal.reservation.model.RetrieveObjects;

import java.util.Scanner;

/**
 * The RetrieveBooking class handles the retrieval of booking information from a database.
 */
public class Retrieve {

    private final RetrieveObjects retrieveObjects;

    public Retrieve() {
        retrieveObjects = new RetrieveObjects();
    }

    /**
     * Displays the search and retrieve booking menu and handles user input.
     */
    public void SearchNRetrieveBookingSystem() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("-----------------Welcome to the Reservation System!-----------------");
            System.out.println("Search Booking Request");
            System.out.println("1. Search and Retrieve Booking\n2. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    RetrieveObjects.BookingInfo bookingInfo = retrieveObjects.getBooking(scanner);
                    if (bookingInfo != null) {
                        System.out.println("Booking found:");
                        System.out.println("________________________________________________________");
                        System.out.println("Booking ID: " + bookingInfo.getBookingID());
                        System.out.println("Date: " + bookingInfo.getDate());
                        System.out.println("Time: " + bookingInfo.getTime());
                        System.out.println("Room Information: " + bookingInfo.getRoomInformation());
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }
}
