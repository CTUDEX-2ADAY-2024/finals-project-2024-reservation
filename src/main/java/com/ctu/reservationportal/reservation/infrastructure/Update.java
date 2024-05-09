package main.java.com.ctu.reservationportal.reservation.infrastructure;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * The UpdateBooking class manages the updating of booking details in the reservation system.
 */
public class Update {
    /**
     * Update booking request system.
     */
    public void UpdateBookingRequestSystem() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Reservation System!");
        System.out.println("Update Booking Request");
        System.out.println("1. Update Booking\n2. Exit");
        int choice = input.nextInt();

        if (choice == 1) {
            updateBooking();
        } else if (choice == 2) {
            System.out.println("Exiting...");
        } else {
            System.out.println("Invalid choice");
        }
    }

    /**
     * Update booking.
     */
    private void updateBooking() {
        System.out.println("Enter booking ID to update:");
        Scanner scanner = new Scanner(System.in);
        String bookingID = scanner.next();

        if (bookingID.isEmpty()) {
            System.out.println("Booking ID cannot be empty");
            return;
        }

        UpdateObjects.Booking booking = fetchBookingFromDB(bookingID);
        if (booking == null) {
            System.out.println("Booking not found for ID: " + bookingID);
            return;
        }

        // Display booking details based on users booking ID
        System.out.println("------------------Booking Details------------------");
        System.out.println("Room Information: " + booking.getRoomInformation());
        System.out.println("Date: " + booking.getDate());
        System.out.println("Time: " + booking.getTime());

        // Prompts user to input new details
        System.out.println("------------------Updating Details------------------");
        System.out.println("Enter new room information: (Room A, Room B, Room C)");
        String newRoomInformation = scanner.next();
        System.out.println("Enter new date:");
        String newDate = scanner.next();
        System.out.println("Enter new time:");
        String newTime = scanner.next();

        // Update the booking details
        booking.setRoomInformation(newRoomInformation);
        booking.setDate(newDate);
        booking.setTime(newTime);

        // Update the database with the new details
        updateBookingInDB(bookingID, newRoomInformation, newTime, newDate);

        System.out.println("-----------Updated Booking Details-----------");
        System.out.println("Room Information: " + booking.getRoomInformation());
        System.out.println("Date: " + booking.getDate());
        System.out.println("Time: " + booking.getTime());

        // Utilize UpdateObjects to update the booking details
        UpdateObjects updateObjects = new UpdateObjects(this);
        updateObjects.setRoomInformation(newRoomInformation);
        updateObjects.setDate(newDate);
        updateObjects.setTime(newTime);
        updateObjects.updateBookingDetails(bookingID);
    }

    /**
     * Fetch booking from database based on booking ID.
     *
     * @param bookingID the booking ID
     * @return the booking
     */
    private UpdateObjects.Booking fetchBookingFromDB(String bookingID) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/booking_schema",
                "root",
                "mypassword");
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM RESERVATION_BOOKING_RECORDS WHERE bookingID=?")) {

            preparedStatement.setString(1, bookingID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String roomInformation = resultSet.getString("room");
                    String date = resultSet.getString("date");
                    String time = resultSet.getString("time");
                    return new UpdateObjects.Booking(roomInformation, date, time);
                }
            }

        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e.getMessage());
        }

        return null;
    }

    /**
     * Update booking in database.
     *
     * @param bookingID          the booking ID
     * @param newRoomInformation the new room information
     * @param newDate            the new date
     */
    public void updateBookingInDB(String bookingID, String newRoomInformation, String newTime, String newDate) {
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/booking_schema",
                        "root",
                        "mypassword");
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE RESERVATION_BOOKING_RECORDS SET room=?, date=?, time=? WHERE bookingID=?")) {

            preparedStatement.setString(1, newRoomInformation);
            preparedStatement.setString(2, newDate);
            preparedStatement.setString(3, newTime);
            preparedStatement.setString(4, bookingID);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("______________________________________________________");
                System.out.println("Booking details updated successfully.");
                System.out.println("______________________________________________________");
            } else {
                System.out.println("Failed to update booking details.");
            }

        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e.getMessage());
        }

    }
}
