//Package declaration for BookingValidator class
package main.java.com.ctu.reservationportal.reservation.abstraction;
// Import CreateObjects model class
import main.java.com.ctu.reservationportal.reservation.model.CreateObjects;
import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;
// Import Connection class from java.sql package
import java.sql.Connection;
// Import DriverManager class from java.sql package
import java.sql.DriverManager;
// Import PreparedStatement class from java.sql package
import java.sql.PreparedStatement;
// Import ResultSet class from java.sql package
import java.sql.ResultSet;
// Import SQLException class from java.sql package
import java.sql.SQLException;


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
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/roomportaldb",
                "root",
                "mypassword");
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM BOOKINGDETAILS WHERE roomType=? AND roomNumber=? AND checkInDate=? AND checkOutDate=? AND checkInTime<=? AND checkOutTime>=? ")
        ) {
            // Setting parameters for the prepared statement
            preparedStatement.setString(1, createObjects.getRoomType());
            preparedStatement.setString(2, createObjects.getRoomNumber());
            preparedStatement.setDate(3, createObjects.getCheckInDate());
            preparedStatement.setDate(4, createObjects.getCheckOutDate());
            preparedStatement.setTime(5, createObjects.getCheckOutTime());
            preparedStatement.setTime(6, createObjects.getCheckInTime());

            // Executing query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Moving cursor to the next row
                resultSet.next();
                // Getting the value of the first column
                int count = resultSet.getInt(1);

                // Returning true if count is 0 (indicating the room is available)
                return count == 0;
            }
            // Handling SQL exceptions
        } catch (SQLException e) {
            // Printing stack trace
            e.printStackTrace();
            // Returning false in case of exception
            return false;
        }
    }

    /**
     * Validates whether the provided username and email exist in the database.
     *
     * @param username The username to validate.
     * @param email    The email to validate.
     * @return true if the username and email exist, false otherwise.
     */
    public boolean isValidUsernameAndEmail(String username, String email) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/roomportaldb",
                "root",
                "mypassword");
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM USERINFO WHERE userName=? AND email=?")
        ) {
            // Setting parameters for the prepared statement
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);

            // Executing query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Moving cursor to the next row
                resultSet.next();
                // Getting the value of the first column
                int count = resultSet.getInt(1);

                // Returning true if count is greater than 0 (indicating username and email exist)
                return count > 0;
            }
            // Handling SQL exceptions
        } catch (SQLException e) {
            // Printing stack trace
            e.printStackTrace();
            // Returning false in case of exception
            return false;
        }
    }

}