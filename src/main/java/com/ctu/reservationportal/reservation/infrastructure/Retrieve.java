//Package declaration for Retrieve class
package main.java.com.ctu.reservationportal.reservation.infrastructure;
// import necessary packages
import main.java.com.ctu.reservationportal.reservation.model.RetrieveObjects;

import static main.java.com.ctu.reservationportal.reservation.abstraction.UsernameValidator.usernameExistsInDatabase;
import static main.java.com.ctu.reservationportal.reservation.abstraction.RetrieveFromDB.retrieveBookingFromDB;

// Import SQL classes for Date and Time
import java.util.List;
// Import Scanner class for user input
import java.util.Scanner;


/**
 * This class is responsible for retrieving booking information based on user input.
 */
public class Retrieve {

    /**
     * Provides a menu for searching and retrieving booking information.
     */
    public void searchAndRetrieveBooking() {
        try (Scanner scanner = new Scanner(System.in)) {
            // Print horizontal line
            for (int i = 0; i < 130; i++) {
                System.out.print("-");
            }
            System.out.println();
            // Display the main menu for the reservation system
            System.out.println("Welcome to the Reservation System!");
            System.out.println("Search Booking Request");
            System.out.println("1. Search and Retrieve Booking");
            System.out.println("2. Exit");

            // Get the user's choice from the menu
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Display filter options for searching bookings
                    System.out.println("Select filter option:");
                    System.out.println("1. Booking ID");
                    System.out.println("2. Room");
                    System.out.println("3. Username");

                    // Get the user's filter choice
                    int filterChoice = scanner.nextInt();
                    switch (filterChoice) {
                        case 1:
                            int bookingID = 0;
                            boolean validInput = false;
                            while (!validInput) {
                                System.out.print("Enter booking ID: ");
                                if (scanner.hasNextInt()) {
                                    bookingID = scanner.nextInt();
                                    validInput = true;
                                } else {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    scanner.next(); // Clear the invalid input
                                }
                            }
                            RetrieveObjects retrieveByID = retrieveBookingFromDB(bookingID, null, null).getFirst();
                            printHeader();
                            displayBookingInfo(retrieveByID);
                            break;


                        case 2:
                            // Search by Room Type
                            System.out.println("\nList of Rooms:\n 1. Classroom\n 2. CompLaboratory\n 3. Library\n 4. SmartRoom\n");
                            // Prompt the user to enter the room number
                            System.out.print("Enter room number: \n");
                            // Read the room number from the user input
                            int roomNumber = scanner.nextInt();
                            // Switch statement to determine the room type based on the room number
                            String roomType = switch (roomNumber) {
                                // If room number is 1, set room type to Classroom
                                case 1 -> "Classroom";
                                // If room number is 2, set room type to CompLaboratory
                                case 2 -> "CompLaboratory";
                                // If room number is 3, set room type to Library
                                case 3 -> "Library";
                                // If room number is 4, set room type to SmartRoom
                                case 4 -> "SmartRoom";
                                // If room number is invalid, set room type to null
                                default -> null;
                            };
                            // Check if roomType is not null
                            if (roomType != null) {
                                // Print the header for the retrieved data
                                printHeader();
                                // Retrieve data based on the selected room type
                                retrieveByRoom(roomType);
                            } else {
                                // Print error message for invalid room number
                                System.out.println("Invalid room number.");
                            }

                            break;
                        case 3:
                            // Search by Username
                            System.out.print("Enter username: ");
                            String username = scanner.next();
                            if (!usernameExistsInDatabase(username)) {
                                System.out.println("Username does not exist. Please enter a valid username.");
                                break;}
                            RetrieveObjects retrieveByUsername = retrieveBookingFromDB(0, null, username).getFirst();
                            printHeader();
                            displayBookingInfo(retrieveByUsername);
                            break;
                        default:
                            // Handle invalid filter choice
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;
                case 2:
                    // Exit the system
                    System.out.println("Exiting...");
                    break;
                default:
                    // Handle invalid menu choice
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    /**
     * Retrieves and displays booking information for a specific room.
     *
     * @param roomType The room for which booking information needs to be retrieved.
     */
    private void retrieveByRoom(String roomType) {
        // Retrieve list of bookings for the specified room type
        List<RetrieveObjects> bookingInfoList = retrieveBookingFromDB(0, roomType, null);

        // Check if there are any bookings
        if (!bookingInfoList.isEmpty()) {
            // Iterate through the list of bookings to display each one
            for (RetrieveObjects bookingInfo : bookingInfoList) {
                displayBookingInfo(bookingInfo);
            }
        } else {
            // Print message if no bookings are found
            System.out.println("No bookings found for room '" + roomType + "'.");
        }
    }

    /**
     * Displays the booking information.
     *
     * @param bookingInfo The booking object containing the information to be displayed.
     */
    private void displayBookingInfo(RetrieveObjects bookingInfo) {
        // Check if booking information is not null
        if (bookingInfo != null) {
            // Print the actual booking details
            System.out.printf("%-12d%-15s%-25s%-15s%-15s%-15s%-15s%-25s\n",
                    bookingInfo.getBookingID(),
                    bookingInfo.getUsername(),
                    bookingInfo.getEmail(),
                    bookingInfo.getCheckInDate(),
                    bookingInfo.getCheckOutDate(),
                    bookingInfo.getCheckInTime(),
                    bookingInfo.getCheckOutTime(),
                    bookingInfo.getRoomInformation());
        } else {
            // Print message if no booking is found
            System.out.println("Booking not found.");
        }
    }

    /**
     * Prints the header for the booking details table.
     */
    private void printHeader() {
        System.out.println("\nBooking found:\n");
        // Print horizontal line
        for (int i = 0; i < 130; i++) {
            System.out.print("-");
        }
        System.out.println();
        // Print the header for booking details
        System.out.printf("%-12s%-15s%-25s%-15s%-15s%-15s%-15s%-20s\n",
                "Booking ID", "Username", "Email",
                "Check-in Date", "Check-out Date",
                "Check-in Time", "Check-out Time", "Room Information");
        // Print horizontal line
        for (int i = 0; i < 130; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
