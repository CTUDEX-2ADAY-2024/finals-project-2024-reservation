package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;
import main.java.com.ctu.reservationportal.reservation.abstraction.UpdateDBconnector;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.sql.Time;

public class UsernameUpdater {
    private Scanner scanner;
    private UpdateObjects updateObjects;

    public UsernameUpdater(Scanner scanner, UpdateObjects updateObjects) {
        this.scanner = scanner;
        this.updateObjects = updateObjects;
    }

    public void update() {
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        updateObjects.setUsername(newUsername);

        UpdateDBconnector dbConnector = new UpdateDBconnector();
        // Retrieve current booking details to ensure all fields are populated
        UpdateObjects currentBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());
        if (currentBooking == null) {
            System.out.println("Booking ID not found.");
            return;
        }

        // Update only the username
        currentBooking.setUsername(newUsername);

        boolean isUpdated = dbConnector.updateBookingDetails(currentBooking.getBookingID(), currentBooking);
        if (isUpdated) {
            System.out.println("Username updated successfully.");
            UpdateObjects updatedBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());
            if (updatedBooking != null) {
                displayBookingInfo(updatedBooking);
            }
        } else {
            System.out.println("Failed to update username.");
        }
    }

    private void displayBookingInfo(UpdateObjects bookingInfo) {
        System.out.println("Updated Booking Information:");
        System.out.println("Username: " + bookingInfo.getUsername());
        System.out.println("Email: " + bookingInfo.getEmail());
        System.out.println("Room Type: " + bookingInfo.getRoomType());

        // Display room number or "not specified"
        String roomNumber = bookingInfo.getRoomNumber() != null ? bookingInfo.getRoomNumber() : "not specified";
        System.out.println("Room Number: " + roomNumber);

        System.out.println("Check-in Date: " + bookingInfo.getCheckInDate());
        System.out.println("Check-out Date: " + bookingInfo.getCheckOutDate());
        System.out.println("Check-in Time: " + convertTo12HourFormat(bookingInfo.getCheckInTime()));
        System.out.println("Check-out Time: " + convertTo12HourFormat(bookingInfo.getCheckOutTime()));
    }

    private String convertTo12HourFormat(Time time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(time);
    }
}