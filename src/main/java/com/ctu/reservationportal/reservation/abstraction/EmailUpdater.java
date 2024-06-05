package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.sql.Time;

/**
 * Class responsible for updating email information in a booking.
 */
public class EmailUpdater {
    private Scanner scanner;
    private UpdateObjects updateObjects;

    /**
     * Constructs a new EmailUpdater with the given scanner and updateObjects.
     *
     * @param scanner       The scanner to read user input.
     * @param updateObjects The UpdateObjects containing the booking information to be updated.
     */
    public EmailUpdater(Scanner scanner, UpdateObjects updateObjects) {
        this.scanner = scanner;
        this.updateObjects = updateObjects;
    }

    /**
     * Updates the email information associated with the booking.
     */
    public void update() {
        while (true) {
            // Prompt user to enter new email
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();

            // Check if the entered email is valid
            if (isValidEmail(newEmail)) {
                // Set the new email in updateObjects
                updateObjects.setEmail(newEmail);
                UpdateDBconnector dbConnector = new UpdateDBconnector();

                // Retrieve current booking details to ensure all fields are populated
                UpdateObjects currentBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());
                if (currentBooking == null) {
                    System.out.println("Booking ID not found.");
                    return;
                }

                // Update only the email in currentBooking
                currentBooking.setEmail(newEmail);

                // Update the booking details in the database
                boolean isUpdated = dbConnector.updateBookingDetails(currentBooking.getBookingID(), currentBooking);
                if (isUpdated) {
                    System.out.println("\nEmail updated successfully.\n");
                    UpdateObjects updatedBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());
                    if (updatedBooking != null) {
                        // Display updated booking information
                        displayBookingInfo(updatedBooking);
                    }
                } else {
                    System.out.println("Failed to update email.");
                }
                break;
            } else {
                System.out.println("Invalid email address. Please enter a valid email.");
            }
        }
    }

    /**
     * Validates the email format.
     *
     * @param email The email address to validate.
     * @return True if the email address ends with "@gmail.com", false otherwise.
     */
    private boolean isValidEmail(String email) {
        return email.toLowerCase().endsWith("@gmail.com");
    }

    /**
     * Displays the updated booking information.
     *
     * @param bookingInfo The updated booking information.
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
     * Converts time to 12-hour format.
     *
     * @param time The time to convert.
     * @return The time in 12-hour format.
     */
    private String convertTo12HourFormat(Time time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(time);
    }
}
