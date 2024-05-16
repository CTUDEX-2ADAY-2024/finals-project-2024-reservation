package main.java.com.ctu.reservationportal.reservation.abstraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
// Import ResultSet class from java.sql package
import java.sql.ResultSet;
// Import SQLException class from java.sql package
import java.sql.SQLException;

public class UsernameValidator {

        public static boolean usernameExistsInDatabase(String username) {
            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/roomportaldb",
                    "root",
                    "mypassword");
            ) {
                // Prepare the SQL statement to query the database
                String sql = "SELECT COUNT(*) FROM BOOKINGDETAILS WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Set the username parameter
                    statement.setString(1, username);

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) {
                        // Check if any rows are returned
                        if (resultSet.next()) {
                            int count = resultSet.getInt(1);
                            return count > 0; // Return true if username exists, false otherwise
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle potential database errors
            }
            return false; // Return false by default (if an exception occurs or no result is found)
        }
    }