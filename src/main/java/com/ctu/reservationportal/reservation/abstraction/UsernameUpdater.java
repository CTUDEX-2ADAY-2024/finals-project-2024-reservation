package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;

import java.text.SimpleDateFormat;
import java.sql.Time;
import java.util.Scanner;

/**
 * The UsernameUpdater class is responsible for updating the username associated with a booking.
 */
public class UsernameUpdater {
    private Scanner scanner;
    private UpdateObjects updateObjects;

    /**
     * Constructs a new UsernameUpdater with the given scanner and updateObjects.
     *
     * @param scanner       The scanner to read user input.
     * @param updateObjects The UpdateObjects containing the booking information to be updated.
     */
    public UsernameUpdater(Scanner scanner, UpdateObjects updateObjects) {
        this.scanner = scanner;
        this.updateObjects = updateObjects;
    }

    /**
     * Updates the username associated with the booking.
     *
     * This method prompts the user for a new username, validates it, updates the username in the
     * updateObjects, retrieves the current booking details, updates the booking details in the
     * database, and displays the updated booking information.
     */
    public void update() {
        // Prompt user for new username
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();

        // Validate username
        if (!isValidUsername(newUsername)) {
            System.out.println("Invalid username.");
            return;
        }

        // Update username in updateObjects
        updateObjects.setUsername(newUsername);

        // Create a new instance of UpdateDBconnector to interact with the database
        UpdateDBconnector dbConnector = new UpdateDBconnector();

        // Retrieve the current booking details from the database based on the booking ID stored in updateObjects
        UpdateObjects currentBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());

        // Check if the booking ID was not found in the database
        if (currentBooking == null) {
            // Print a message indicating that the booking ID was not found
            System.out.println("Booking ID not found.");
            // Exit the method early because there is no further action to take
            return;
        }

        // Update the username of the current booking with the new username provided by the user
        currentBooking.setUsername(newUsername);

        // Update the booking details in the database with the modified currentBooking object
        boolean isUpdated = dbConnector.updateBookingDetails(currentBooking.getBookingID(), currentBooking);

        // Check if the update operation was successful
        if (isUpdated) {
            // Print a message indicating that the username was updated successfully
            System.out.println("\nUsername updated successfully.\n");

            // Retrieve the updated booking details from the database to ensure the displayed information is up-to-date
            UpdateObjects updatedBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());

            // Check if the updatedBooking object is not null
            if (updatedBooking != null) {
                // Display the updated booking information to the user
                displayBookingInfo(updatedBooking);
            }
        } else {
            // Print a message indicating that the update operation failed
            System.out.println("Failed to update username.");
        }
    }

    /**
     * Displays the updated booking information in tabular column form.
     *
     * @param bookingInfo The booking information to be displayed.
     */
    private void displayBookingInfo(UpdateObjects bookingInfo) {
        // Display updated booking information in tabular column form
        System.out.println("Updated Booking Information:");

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
                bookingInfo.getRoomNumber(), bookingInfo.getCheckInDate(), bookingInfo.getCheckOutDate(),
                convertTo12HourFormat(bookingInfo.getCheckInTime()), convertTo12HourFormat(bookingInfo.getCheckOutTime()),
                bookingInfo.getRoomType());
    }

    /**
     * Converts the given time to 12-hour format.
     *
     * @param time The time to be converted.
     * @return The time in 12-hour format.
     */
    private String convertTo12HourFormat(Time time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(time);
    }

    /**
     * Constructs the room information string based on the room type.
     *
     * @param bookingInfo The booking information containing the room type and room number.
     * @return The room information string.
     */


    /**
     * Validates the username.
     *
     * @param username The username to be validated.
     * @return True if the username is valid, otherwise false.
     */
    private boolean isValidUsername(String username) {
        // Username must be at least 3 characters long and can contain both letters and numbers,
        // or if only letters, it must be at least 3 characters long
        return username.matches("(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{3,}") ||
                (username.matches("[a-zA-Z]{3,}") && username.length() >= 3);
    }
}
