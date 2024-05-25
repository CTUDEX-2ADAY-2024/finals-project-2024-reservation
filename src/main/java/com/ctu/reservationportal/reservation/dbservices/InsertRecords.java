package main.java.com.ctu.reservationportal.reservation.dbservices;

import main.java.com.ctu.reservationportal.reservation.model.CreateObjects;

import java.sql.*;
import java.util.Random;

/**
 * Insert PrepareStatement JDBC Example
 *
 * @author Ramesh Fadatare
 *
 */
public class InsertRecords {
    private static final String INSERT_BOOKING_SQL = "INSERT INTO BookingDetails (userName, email, checkInDate, checkOutDate, checkInTime, checkOutTime, roomType, roomNumber, bookingId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public InsertRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JDBC MySQL Driver", e);
        }
    }

    public int insertBookingDetails(CreateObjects bookingRequest) {
        // Generate a random booking ID
        int bookingId = generateBookingId();

        // Step 1: Establishing a Connection
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "mypassword");
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING_SQL)) {

            preparedStatement.setString(1, bookingRequest.getUserName());
            preparedStatement.setString(2, bookingRequest.getEmail());
            preparedStatement.setDate(3, bookingRequest.getCheckInDate());
            preparedStatement.setDate(4, bookingRequest.getCheckOutDate());
            preparedStatement.setTime(5, bookingRequest.getCheckInTime());
            preparedStatement.setTime(6, bookingRequest.getCheckOutTime());
            preparedStatement.setString(7, bookingRequest.getRoomType());
            preparedStatement.setString(8, bookingRequest.getRoomNumber());
            preparedStatement.setInt(9, bookingId);

            // Execute the statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\nA new room reservation record was inserted successfully!");
                return bookingId;
            }

        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return -1;
    }

    private static int generateBookingId() {
        return 100000 + new Random().nextInt(900000);
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
