package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;
import java.sql.*;

/**
 * Class responsible for updating booking records in the database.
 */
public class UpdateRecords {

    /**
     * Updates the booking details in the database.
     *
     * @param bookingId     The ID of the booking to update.
     * @param updateObjects The updated booking details.
     * @return True if the update was successful, false otherwise.
     */
    public boolean updateBookingDetails(int bookingId, UpdateObjects updateObjects) {
        // Attempt to establish a connection to the database
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/roomportaldb", // Database URL
                "root", // Database username
                "mypassword")) // Database password
        {
            // Define SQL query to update booking details
            String query = "UPDATE BOOKINGDETAILS SET userName = ?, email = ?, roomType = ?, checkInTime = ?, checkOutTime = ?, checkInDate = ?, checkOutDate = ?, roomNumber = ? WHERE bookingId = ?";
            // Create a PreparedStatement to execute the query
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Set parameters in the query using data from updateObjects
                statement.setString(1, updateObjects.getUsername());
                statement.setString(2, updateObjects.getEmail());
                statement.setString(3, updateObjects.getRoomType());
                statement.setTime(4, updateObjects.getCheckInTime());
                statement.setTime(5, updateObjects.getCheckOutTime());
                statement.setDate(6, updateObjects.getCheckInDate());
                statement.setDate(7, updateObjects.getCheckOutDate());
                statement.setInt(8, bookingId);
                statement.setString(9, updateObjects.getRoomNumber());

                // Execute the update operation and get the number of rows affected
                int rowsAffected = statement.executeUpdate();
                // Return true if at least one row was affected, indicating a successful update
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            // Print stack trace if an SQL exception occurs during the update operation
            e.printStackTrace();
            // Return false to indicate update failure
            return false;
        }
    }

    /**
     * Checks if the booking details are valid (room availability) in the database.
     *
     * @param updateObjects The updated booking details.
     * @return True if the booking is valid (room available), false otherwise.
     */
    public boolean isValidBooking(UpdateObjects updateObjects) {
        // Attempt to establish a connection to the database
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/roomportaldb", // Database URL
                "root", // Database username
                "mypassword")) // Database password
        {
            // Define SQL query to check booking validity
            String query = "SELECT COUNT(*) FROM BOOKINGDETAILS WHERE roomType=? AND checkInDate=? AND checkOutDate=? AND checkInTime<=? AND checkOutTime>=? AND userName=? AND bookingId=?";
            // Create a PreparedStatement to execute the query
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Set parameters in the query using data from updateObjects
                statement.setString(1, updateObjects.getRoomType());
                statement.setDate(2, updateObjects.getCheckInDate());
                statement.setDate(3, updateObjects.getCheckOutDate());
                statement.setTime(4, updateObjects.getCheckInTime());
                statement.setTime(5, updateObjects.getCheckOutTime());
                statement.setString(6, updateObjects.getUsername());
                statement.setInt(7, updateObjects.getBookingID());

                // Execute the query and obtain the result set
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Check if the result set contains any rows
                    if (resultSet.next()) {
                        // Retrieve the count value from the first column
                        int count = resultSet.getInt(1);
                        // Return true if the count is 0, indicating the booking is valid
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            // Print stack trace if an SQL exception occurs during the query execution
            e.printStackTrace();
        }
        // Return false to indicate booking invalidity or error
        return false;
    }
}
