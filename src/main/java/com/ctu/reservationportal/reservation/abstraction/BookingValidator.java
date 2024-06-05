
package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.CreateObjects;
import java.sql.*;

/**
 * Class responsible for validating booking details and user information.
 */
public class BookingValidator {

    /**
     * Validates whether a booking can be made based on the provided booking details.
     *
     * @param createObjects The booking details to validate.
     * @return true if the booking is valid and can be made, false otherwise.
     */
    public boolean isValidBooking(CreateObjects createObjects) {
        String query = "SELECT COUNT(*) FROM BOOKINGDETAILS WHERE roomType=? AND roomNumber=? AND checkInDate=? AND checkOutDate=? AND checkInTime<=? AND checkOutTime>=?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/roomportaldb", "root", "mypassword");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Setting parameters for the prepared statement
            preparedStatement.setString(1, createObjects.getRoomType());
            preparedStatement.setString(2, createObjects.getRoomNumber());
            preparedStatement.setDate(3, createObjects.getCheckInDate());
            preparedStatement.setDate(4, createObjects.getCheckOutDate());
            preparedStatement.setTime(5, createObjects.getCheckOutTime());
            preparedStatement.setTime(6, createObjects.getCheckInTime());

            // Executing query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count == 0; // Return true if count is 0 (indicating the room is available)
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Validates whether the provided username exists in the database.
     *
     * @param username The username to validate.
     * @return true if the username exists, false otherwise.
     */
    // Define a public method isValidUsername that returns a boolean value
    public boolean isValidUsername(String username) {
        // SQL query to count the number of records in the USERINFO table with the specified username
        String query = "SELECT COUNT(*) FROM USERINFO WHERE userName=?";

        // Try-with-resources block to manage database resources
        try (
                // Establish a connection to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/roomportaldb", "root", "mypassword");
                // Prepare the SQL statement to prevent SQL injection
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the username parameter in the prepared statement
            preparedStatement.setString(1, username);

            // Execute the query and get the result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Move to the first row of the result set
                resultSet.next();
                // Get the count of records with the specified username
                int count = resultSet.getInt(1);
                // Return true if the count is greater than 0, indicating the username exists
                return count > 0;
            }
        } catch (SQLException e) {
            // Print the stack trace if a SQL exception occurs
            e.printStackTrace();
            // Return false if an exception occurs, indicating an error or that the username does not exist
            return false;
        }
    }


    /**
     * Validates whether the provided email exists in the database.
     *
     * @param email The email to validate.
     * @return true if the email exists, false otherwise.
     */
    public boolean isValidEmail(String email) {
        // SQL query to count the number of records in the USERINFO table with the specified email
        String query = "SELECT COUNT(*) FROM USERINFO WHERE email=?";

        // Try-with-resources block to manage database resources
        try (
                // Establish a connection to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/roomportaldb", "root", "mypassword");
                // Prepare the SQL statement to prevent SQL injection
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Set the email parameter in the prepared statement
            preparedStatement.setString(1, email);

            // Execute the query and get the result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Move to the first row of the result set
                resultSet.next();
                // Get the count of records with the specified email
                int count = resultSet.getInt(1);
                // Return true if the count is greater than 0, indicating the email exists
                return count > 0;
            }
        } catch (SQLException e) {
            // Print the stack trace if a SQL exception occurs
            e.printStackTrace();
            // Return false if an exception occurs, indicating an error or that the email does not exist
            return false;
        }
    }



    /**
     * Validates whether the provided booking ID exists in the database.
     *
     * @param bookingID The booking ID to validate.
     * @return true if the booking ID exists, false otherwise.
     */
    public boolean isValidBookingID(int bookingID) {
        String query = "SELECT COUNT(*) FROM BOOKINGDETAILS WHERE bookingID=?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/roomportaldb", "root", "mypassword");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Setting parameters for the prepared statement
            preparedStatement.setInt(1, bookingID);

            // Executing query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0; // Return true if count is greater than 0 (indicating booking ID exists)
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}