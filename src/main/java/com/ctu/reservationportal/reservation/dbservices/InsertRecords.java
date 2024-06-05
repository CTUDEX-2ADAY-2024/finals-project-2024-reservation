package main.java.com.ctu.reservationportal.reservation.dbservices;

import main.java.com.ctu.reservationportal.reservation.model.CreateObjects;

import java.sql.*;
import java.util.Random;

/**
 * Insert PrepareStatement JDBC Example
 *
 * Author: Ramesh Fadatare
 */
public class InsertRecords {
    // SQL statement to insert booking details with placeholders for parameters
    private static final String INSERT_BOOKING_SQL = "INSERT INTO BookingDetails (userName, email, checkInDate, checkOutDate, checkInTime, checkOutTime, roomType, roomNumber, bookingId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // Constructor to load MySQL JDBC driver
    public InsertRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Throw a runtime exception if JDBC MySQL Driver loading fails
            throw new RuntimeException("Error loading JDBC MySQL Driver", e);
        }
    }

    // Method to insert booking details into the database
    public int insertBookingDetails(CreateObjects bookingRequest) {
        // Generate a random booking ID
        int bookingId = generateBookingId();

        // Establishing a database connection and preparing the SQL statement
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "mypassword");
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING_SQL)) {

            // Set values for the prepared statement parameters
            preparedStatement.setString(1, bookingRequest.getUserName());
            preparedStatement.setString(2, bookingRequest.getEmail());
            preparedStatement.setDate(3, bookingRequest.getCheckInDate());
            preparedStatement.setDate(4, bookingRequest.getCheckOutDate());
            preparedStatement.setTime(5, bookingRequest.getCheckInTime());
            preparedStatement.setTime(6, bookingRequest.getCheckOutTime());
            preparedStatement.setString(7, bookingRequest.getRoomType());
            preparedStatement.setString(8, bookingRequest.getRoomNumber());
            preparedStatement.setInt(9, bookingId);

            // Execute the SQL statement
            int rowsInserted = preparedStatement.executeUpdate();

            // Check if rows were successfully inserted
            if (rowsInserted > 0) {
                // Print success message and return the generated booking ID
                System.out.println("\nA new room reservation record was inserted successfully!");
                return bookingId;
            }

        } catch (SQLException e) {
            // Print SQL exception information
            printSQLException(e);
        }
        // Return -1 if insertion fails
        return -1;
    }

    // Method to generate a random booking ID
    private static int generateBookingId() {
        return 100000 + new Random().nextInt(900000);
    }

    // Method to print detailed information about SQL exceptions
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                // Print stack trace, SQL state, error code, and message
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());

                // Print cause of the exception, if any
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
