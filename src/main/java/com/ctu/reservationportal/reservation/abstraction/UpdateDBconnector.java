package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;
import java.sql.*;


/**
 * Class responsible for connecting to the database and updating booking records.
 */
public class UpdateDBconnector {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/roomportaldb"; // Database URL
    private static final String DB_USER = "root"; // Database username
    private static final String DB_PASSWORD = "mypassword"; // Database password

    /**
     * Updates the booking details in the database.
     *
     * @param bookingId     The ID of the booking to update.
     * @param updateObjects The updated booking details.
     * @return True if the update was successful, false otherwise.
     */
    public boolean updateBookingDetails(int bookingId, UpdateObjects updateObjects) {
        // First retrieve the current booking details to ensure we have all fields
        UpdateObjects currentBooking = getBookingDetails(bookingId);
        // Check if booking details are found
        if (currentBooking == null) {
            // Return false if booking details are not found
            return false;
        }

        // Update the specific fields with new values, keeping unchanged fields as is
        if (updateObjects.getUsername() != null) {
            // Update username if provided
            currentBooking.setUsername(updateObjects.getUsername());
        }
        if (updateObjects.getEmail() != null) {
            // Update email if provided
            currentBooking.setEmail(updateObjects.getEmail());
        }
        if (updateObjects.getRoomType() != null) {
            // Update room type if provided
            currentBooking.setRoomType(updateObjects.getRoomType());
        }
        if (updateObjects.getCheckInTime() != null) {
            // Update check-in time if provided
            currentBooking.setCheckInTime(updateObjects.getCheckInTime());
        }
        if (updateObjects.getCheckOutTime() != null) {
            // Update check-out time if provided
            currentBooking.setCheckOutTime(updateObjects.getCheckOutTime());
        }
        if (updateObjects.getCheckInDate() != null) {
            // Update check-in date if provided
            currentBooking.setCheckInDate(updateObjects.getCheckInDate());
        }
        if (updateObjects.getCheckOutDate() != null) {
            // Update check-out date if provided
            currentBooking.setCheckOutDate(updateObjects.getCheckOutDate());
        }
        if (updateObjects.getRoomNumber() != null) {
            // Update room number if provided
            currentBooking.setRoomNumber(updateObjects.getRoomNumber());
        }

        // Prepare the SQL query for updating booking details
        String query = "UPDATE BOOKINGDETAILS SET userName = ?, email = ?, roomType = ?, checkInTime = ?, checkOutTime = ?, checkInDate = ?, checkOutDate = ?, roomNumber = ? WHERE bookingId = ?";

        // Establish database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters in the prepared statement
            statement.setString(1, currentBooking.getUsername());
            statement.setString(2, currentBooking.getEmail());
            statement.setString(3, currentBooking.getRoomType());
            statement.setTime(4, currentBooking.getCheckInTime());
            statement.setTime(5, currentBooking.getCheckOutTime());
            statement.setDate(6, currentBooking.getCheckInDate());
            statement.setDate(7, currentBooking.getCheckOutDate());
            statement.setString(8, currentBooking.getRoomNumber());
            statement.setInt(9, bookingId);

            // Execute the update query and get the number of rows affected
            int rowsAffected = statement.executeUpdate();
            // Return true if at least one row was affected, indicating a successful update
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Print error message if update operation fails
            System.err.println("Error updating booking details: " + e.getMessage());
            // Return false to indicate update failure
            return false;
        }
    }

    /**
     * Retrieves the booking details from the database.
     *
     * @param bookingId The ID of the booking to retrieve.
     * @return The booking details if found, null otherwise.
     */
    public UpdateObjects getBookingDetails(int bookingId) {
        // Prepare the SQL query for retrieving booking details
        String query = "SELECT userName, email, roomType, checkInTime, checkOutTime, checkInDate, checkOutDate, roomNumber FROM BOOKINGDETAILS WHERE bookingId = ?";

        // Establish database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the booking ID parameter in the prepared statement
            statement.setInt(1, bookingId);

            // Execute the query and obtain the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                // Check if the result set contains any rows
                if (resultSet.next()) {
                    // Create a new UpdateObjects instance to hold the retrieved booking details
                    UpdateObjects bookingDetails = new UpdateObjects();
                    // Set the booking ID
                    bookingDetails.setBookingId(bookingId);
                    // Set other booking details from the result set
                    bookingDetails.setUsername(resultSet.getString("userName"));
                    bookingDetails.setEmail(resultSet.getString("email"));
                    bookingDetails.setRoomType(resultSet.getString("roomType"));
                    bookingDetails.setCheckInTime(resultSet.getTime("checkInTime"));
                    bookingDetails.setCheckOutTime(resultSet.getTime("checkOutTime"));
                    bookingDetails.setCheckInDate(resultSet.getDate("checkInDate"));
                    bookingDetails.setCheckOutDate(resultSet.getDate("checkOutDate"));
                    bookingDetails.setRoomNumber(resultSet.getString("roomNumber"));
                    // Return the retrieved booking details
                    return bookingDetails;
                }
            }
        } catch (SQLException e) {
            // Print error message if retrieval operation fails
            System.err.println("Error retrieving booking details: " + e.getMessage());
        }
        // Return null if no booking details are found or an error occurs
        return null;
    }
    /**
     * Checks if a room of the specified type is available for the specified date and time range.
     *
     * @param roomType     The type of room to check availability for.
     * @param checkInDate  The check-in date for the reservation.
     * @param checkOutDate The check-out date for the reservation.
     * @param roomNumber   The room number to check availability for.
     * @return True if a room of the specified type and room number is available, false otherwise.
     */
    public boolean checkRoomAvailability(String roomType,String roomNumber, Date checkInDate, Date checkOutDate) {
        // Prepare the SQL query to check for conflicting bookings, considering room numbers
        String query = "SELECT * FROM BOOKINGDETAILS WHERE roomType = ? AND roomNumber = ? AND ((checkInDate <= ? AND checkOutDate >= ?) OR (checkInDate >= ? AND checkOutDate <= ?))";

        // Establish database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set parameters in the prepared statement
            statement.setString(1, roomType);
            statement.setString(2, roomNumber);
            statement.setDate(3, checkOutDate);
            statement.setDate(4, checkInDate);
            statement.setDate(5, checkInDate);
            statement.setDate(6, checkOutDate);

            // Execute the query and obtain the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                // Check if any conflicting bookings are found
                if (resultSet.next()) {
                    // If a conflicting booking exists, return false (unavailable)
                    return false;
                }
            }
        } catch (SQLException e) {
            // Print error message if an error occurs during query execution
            System.err.println("Error checking room availability: " + e.getMessage());
        }

        // If no conflicting bookings are found, return true (available)
        return true;
    }
}
