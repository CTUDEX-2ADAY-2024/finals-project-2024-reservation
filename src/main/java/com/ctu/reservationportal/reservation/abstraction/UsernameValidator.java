package main.java.com.ctu.reservationportal.reservation.abstraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type UsernameValidator provides functionality to check if a username exists in the database.
 */
public class UsernameValidator {

    /**
     * Checks if the given username exists in the database.
     *
     * This method establishes a connection to the database using JDBC, prepares a SQL statement to
     * query the BOOKINGDETAILS table for the presence of the specified username, executes the query,
     * and returns true if the username exists, or false otherwise.
     *
     * @param username the username to check for existence in the database
     * @return true if the username exists in the database, false otherwise
     */
    public static boolean usernameExistsInDatabase(String username) {
        // Define the database connection URL, username, and password
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/roomportaldb";
        String dbUsername = "root";
        String dbPassword = "mypassword";

        // SQL query to count occurrences of the given username in the BOOKINGDETAILS table
        String sql = "SELECT COUNT(*) FROM BOOKINGDETAILS WHERE username = ?";

        // Establish a connection and execute the query
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set the username parameter in the SQL query
            statement.setString(1, username);

            // Execute the query and process the result
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    // Return true if the username exists (count > 0), false otherwise
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            // Print stack trace in case of SQL exceptions
            e.printStackTrace();
        }

        // Return false by default if an exception occurs or no result is found
        return false;
    }
}
