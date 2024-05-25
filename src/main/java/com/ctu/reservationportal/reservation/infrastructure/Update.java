package main.java.com.ctu.reservationportal.reservation.infrastructure;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;
import main.java.com.ctu.reservationportal.reservation.abstraction.EmailUpdater;
import main.java.com.ctu.reservationportal.reservation.abstraction.RoomTypeUpdater;
import main.java.com.ctu.reservationportal.reservation.abstraction.UsernameUpdater;
import main.java.com.ctu.reservationportal.reservation.abstraction.DateTimeUpdater;
import main.java.com.ctu.reservationportal.reservation.model.RetrieveObjects;
import main.java.com.ctu.reservationportal.reservation.abstraction.RetrieveFromDB;
import main.java.com.ctu.reservationportal.reservation.abstraction.UpdateRecords;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Update {
    public void updateBookingRequestSystem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Reservation System!");
        System.out.println("Update Booking Request\n");

        System.out.print("Enter booking ID to update: ");
        int bookingID = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        RetrieveObjects booking = RetrieveFromDB.retrieveBookingFromDB(bookingID, null, null).getFirst();
        if (booking == null) {
            System.out.println("Booking not found for ID: " + bookingID);
            return;
        }

        printHeader();
        displayBookingInfo(booking);

        System.out.println("\nWhat field would you like to modify?");
        System.out.println("1. Username");
        System.out.println("2. Email");
        System.out.println("3. Room Type");
        System.out.println("4. Date and Time");
        System.out.print("\nEnter the number of the field you want to modify: ");
        int fieldChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        UpdateObjects updateObjects = new UpdateObjects();
        updateObjects.setBookingId(bookingID);  // Ensure the booking ID is set for the update objects

        boolean validInput = true;
        switch (fieldChoice) {
            case 1:
                UsernameUpdater usernameUpdater = new UsernameUpdater(scanner, updateObjects);
                usernameUpdater.update();
                break;
            case 2:
                EmailUpdater emailUpdater = new EmailUpdater(scanner, updateObjects);
                emailUpdater.update();
                break;
            case 3:
                RoomTypeUpdater roomTypeUpdater = new RoomTypeUpdater(scanner, updateObjects);
                roomTypeUpdater.update();
                break;
            case 4:
                DateTimeUpdater dateTimeUpdater = new DateTimeUpdater(scanner, updateObjects);
                dateTimeUpdater.updateDateTime();
                break;
            default:
                System.out.println("Invalid choice.");
                validInput = false;
                break;
        }

        if (validInput) {
            UpdateRecords updateRecords = new UpdateRecords();
            if (updateRecords.isValidBooking(updateObjects)) {
                boolean success = updateRecords.updateBookingDetails(bookingID, updateObjects);
                if (success) {
                    System.out.println("\nUpdated Booking Details:");
                    printHeader();
                    displayBookingInfo(updateObjects);
                }
            }
        }

        scanner.close();
    }

    private void displayBookingInfo(UpdateObjects bookingInfo) {
        System.out.println("Booking Information:");
        System.out.println("Username: " + bookingInfo.getUsername());
        System.out.println("Email: " + bookingInfo.getEmail());
        System.out.println("Room Type: " + bookingInfo.getRoomType());
        System.out.println("Room Number: " + bookingInfo.getRoomNumber());
        System.out.println("Check-in Date: " + bookingInfo.getCheckInDate());
        System.out.println("Check-out Date: " + bookingInfo.getCheckOutDate());
        System.out.println("Check-in Time: " + formatTimeTo12Hour(bookingInfo.getCheckInTime()));
        System.out.println("Check-out Time: " + formatTimeTo12Hour(bookingInfo.getCheckOutTime()));
    }

    private String formatTimeTo12Hour(Time time) {
        SimpleDateFormat sdf12Hour = new SimpleDateFormat("hh:mm a");
        return sdf12Hour.format(time);
    }

    private void printHeader() {
        System.out.println("\nBooking found:\n");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
