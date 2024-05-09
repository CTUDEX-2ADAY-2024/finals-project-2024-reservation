package main.java.com.ctu.reservationportal.reservation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * The RetrieveObjects class handles the retrieval of booking information from a database.
 */
public class RetrieveObjects {

    /**
     * Retrieves booking information based on user input.
     *
     * @param scanner The Scanner instance to use for user input.
     * @return The BookingInfo object representing the retrieved booking, or null if not found.
     */
    public BookingInfo getBooking(Scanner scanner) {
        System.out.print("Enter booking ID: ");
        int bookingID = scanner.nextInt();
        return retrieveBookingFromDB(bookingID);
    }

    /**
     * Retrieves booking information from the database.
     *
     * @param bookingID The ID of the booking to retrieve.
     * @return The BookingInfo object representing the retrieved booking, or null if not found.
     */
    public BookingInfo retrieveBookingFromDB(int bookingID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/booking_schema", "root", "admin123$");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM RESERVATION_BOOKING_RECORDS WHERE bookingID=?")) {

            preparedStatement.setInt(1, bookingID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int retrievedBookingID = resultSet.getInt("bookingID");
                    String date = resultSet.getString("date");
                    String time = resultSet.getString("time");
                    String room = resultSet.getString("room");

                    return new BookingInfo(retrievedBookingID, date, time, room);
                } else {
                    System.out.println("Booking not found for ID: " + bookingID);
                }
            }

        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e.getMessage());
        }

        return null;
    }

    /**
     * Represents booking information retrieved from the database.
     */
    public static class BookingInfo {
        private int bookingID;
        private String date;
        private String time;
        private String roomInformation;

        /**
         * Constructs a new BookingInfo instance.
         *
         * @param bookingID       The ID of the booking.
         * @param date            The date of the booking.
         * @param time            The time of the booking.
         * @param roomInformation The room information of the booking.
         */
        public BookingInfo(int bookingID, String date, String time, String roomInformation) {
            this.bookingID = bookingID;
            this.date = date;
            this.time = time;
            this.roomInformation = roomInformation;
        }

        public int getBookingID() {
            return bookingID;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getRoomInformation() {
            return roomInformation;
        }

    }
}
