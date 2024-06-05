package main.java.com.ctu.reservationportal.reservation.infrastructure;

import main.java.com.ctu.reservationportal.reservation.abstraction.EmailUpdater;
import main.java.com.ctu.reservationportal.reservation.abstraction.RoomTypeUpdater;
import main.java.com.ctu.reservationportal.reservation.abstraction.UsernameUpdater;
import main.java.com.ctu.reservationportal.reservation.abstraction.DateTimeUpdater;
import main.java.com.ctu.reservationportal.reservation.model.RetrieveObjects;
import main.java.com.ctu.reservationportal.reservation.abstraction.RetrieveFromDB;
import main.java.com.ctu.reservationportal.reservation.abstraction.UpdateRecords;
import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;
import main.java.com.ctu.reservationportal.reservation.abstraction.BookingValidator;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * This class handles updating booking requests in the reservation system.
 */
public class Update {
    BookingValidator bookingValidator = new BookingValidator();

    /**
     * Method to update a booking request in the reservation system.
     */
    public void updateBookingRequestSystem() {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("\nWelcome to the Reservation System!");
        System.out.println("Update Booking Request\n");

        // Prompt for booking ID
        System.out.print("Enter booking ID to update: ");
        int bookingID = 0;
        boolean validInput = false;
        while (!validInput) {
            if (scanner.hasNextInt()) {
                bookingID = scanner.nextInt();
                // Check if the booking ID exists in the database
                if (bookingValidator.isValidBookingID(bookingID)) {
                    validInput = true;
                } else {
                    System.out.println("Booking not found for ID: " + bookingID);
                    System.out.print("Enter a valid booking ID: ");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid booking ID.");
                scanner.next(); // Consume the newline character
                System.out.print("Enter a valid booking ID: ");
            }
        }
        scanner.nextLine(); // Consume the newline character

        // Retrieve booking details
        RetrieveObjects booking = RetrieveFromDB.retrieveBookingFromDB(bookingID, "", null).getFirst();
        if (booking == null) {
            System.out.println("Booking not found for ID: " + bookingID);
            return;
        }

        displayBookingInfo(booking);

        // Prompt user for the field to modify
        System.out.println("\nWhat field would you like to modify?");
        System.out.println("1. Username");
        System.out.println("2. Email");
        System.out.println("3. Room Type");
        System.out.println("4. Date and Time");
        System.out.print("\nEnter the number of the field you want to modify: ");
        int fieldChoice = 0;
        validInput = false;
        while (!validInput) {
            if (scanner.hasNextInt()) {
                fieldChoice = scanner.nextInt();
                if (fieldChoice >= 1 && fieldChoice <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the newline character
            }
        }
        scanner.nextLine(); // Consume the newline character

        // Create an UpdateObjects instance to store updated values
        UpdateObjects updateObjects = new UpdateObjects();
        // Ensure the booking ID is set for the update objects
        updateObjects.setBookingId(bookingID);

        // Switch statement to handle different field choices
        switch (fieldChoice) {
            case 1:
                // Update username, Create UsernameUpdater instance
                UsernameUpdater usernameUpdater = new UsernameUpdater(scanner, updateObjects);
                // Call update method to update username
                usernameUpdater.update();
                break;
            case 2:
                // Update email, Create EmailUpdater instance
                EmailUpdater emailUpdater = new EmailUpdater(scanner, updateObjects);
                // Call update method to update email
                emailUpdater.update();
                break;
            case 3:
                // Update room type, Create RoomTypeUpdater instance
                RoomTypeUpdater roomTypeUpdater = new RoomTypeUpdater(scanner, updateObjects);
                // Call update method to update room type
                roomTypeUpdater.update();
                break;
            case 4:
                // Update date and time, Create DateTimeUpdater instance
                DateTimeUpdater dateTimeUpdater = new DateTimeUpdater(scanner, updateObjects);
                // Call updateDateTime method to update date and time
                dateTimeUpdater.update();
                break;
            default:
                // Print message indicating an invalid choice
                System.out.println("Invalid choice.");
                break;
        }

        // Validate and update booking records if the input is valid
        if (validInput) {
            // Create UpdateRecords instance
            UpdateRecords updateRecords = new UpdateRecords();
            // Check if the updated booking details are valid
            if (updateRecords.isValidBooking(updateObjects)) {
                // Update the booking details in the database
                boolean success = updateRecords.updateBookingDetails(bookingID, updateObjects);
                // If the update operation is successful
                if (success) {
                    // Print message indicating successful update
                    System.out.println("\nUpdated Booking Details:");
                    // Print header for the updated booking details
                    printHeader();
                    // Display the updated booking information
                    displayBookingInfo(updateObjects);
                }
            }
        }

        scanner.close(); // Close the scanner to release system resources
    }

    /**
     * Display booking information in a tabular format.
     * @param bookingInfo The booking information to display.
     */
    private void displayBookingInfo(UpdateObjects bookingInfo) {
        // Display updated booking information in tabular column form
        System.out.println("\nBooking Information:");

        // Print horizontal line
        for (int i = 0; i < 130; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Print header row
        System.out.printf("%-12s%-15s%-25s%-10s%-15s%-15s%-15s%-15s%-20s\n",
                "Booking ID", "Username", "Email", "Room No", "Check-in Date", "Check-out Date",
                "Check-in Time", "Check-out Time", "Room Type");
        // Print horizontal line
        for (int i = 0; i < 130; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Print booking info row
        System.out.printf("%-12s%-15s%-25s%-10s%-15s%-15s%-15s%-15s%-20s\n",
                bookingInfo.getBookingID(), bookingInfo.getUsername(), bookingInfo.getEmail(),
                bookingInfo.getRoomNumber() != null ? bookingInfo.getRoomNumber() : "not specified",
                bookingInfo.getCheckInDate() != null ? bookingInfo.getCheckInDate() : "not specified",
                bookingInfo.getCheckOutDate() != null ? bookingInfo.getCheckOutDate() : "not specified",
                bookingInfo.getCheckInTime() != null ? formatTimeTo12Hour(bookingInfo.getCheckInTime()) : "not specified",
                bookingInfo.getCheckOutTime() != null ? formatTimeTo12Hour(bookingInfo.getCheckOutTime()) : "not specified",
                bookingInfo.getRoomType());
    }

    /**
     * Format time to 12-hour format.
     * @param time The time to format.
     * @return The time in 12-hour format.
     */
    private String formatTimeTo12Hour(Time time) {
        if (time == null) {
            return "not specified";
        }
        SimpleDateFormat sdf12Hour = new SimpleDateFormat("hh:mm a");
        return sdf12Hour.format(time);
    }

    /**
     * Print header for booking information.
     */
    private void printHeader() {
        System.out.println("\nBooking found:\n");
        for (int i = 0; i < 130; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
