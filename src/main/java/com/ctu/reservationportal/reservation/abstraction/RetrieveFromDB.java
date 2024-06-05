package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.RetrieveObjects;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for retrieving booking information from the database.
 */
public class RetrieveFromDB {

    /**
     * Retrieves booking information from the database based on the provided parameters.
     *
     * @param bookingID The ID of the booking to retrieve.
     * @param roomType  The room type name.
     * @param username  The username.
     * @return A list of RetrieveObjects representing the retrieved bookings.
     */
    public static List<RetrieveObjects> retrieveBookingFromDB(int bookingID, String roomType, String username) {
        // Create a new ArrayList to store retrieved bookings
        List<RetrieveObjects> bookings = new ArrayList<>();

        // Attempt to establish a connection to the database
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/roomportaldb", // Database URL
                "root", // Database username
                "mypassword")) // Database password
        {
            // SQL query to retrieve bookings
            String query = "SELECT * FROM BOOKINGDETAILS WHERE bookingID=? OR roomType=? OR username=?";

            // Create a PreparedStatement to execute the query
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set the parameter in the prepared statement
                preparedStatement.setInt(1, bookingID);
                preparedStatement.setString(2, roomType);
                preparedStatement.setString(3, username);

                // Execute the query and obtain the result set
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Iterate through the result set and retrieve booking information
                    while (resultSet.next()) {
                        // Retrieve the booking ID from the result set and store it in the variable retrievedBookingID
                        int retrievedBookingID = resultSet.getInt("bookingID");

                        // Retrieve the check-in time from the result set and store it in the variable checkInTime
                        Time checkInTime = resultSet.getTime("checkInTime");

                        // Retrieve the check-out time from the result set and store it in the variable checkOutTime
                        Time checkOutTime = resultSet.getTime("checkOutTime");

                        // Retrieve the check-in date from the result set and store it in the variable checkInDate
                        Date checkInDate = resultSet.getDate("checkInDate");

                        // Retrieve the check-out date from the result set and store it in the variable checkOutDate
                        Date checkOutDate = resultSet.getDate("checkOutDate");

                        // Retrieve the room number from the result set and store it in the variable roomNumber
                        String roomNumber = resultSet.getString("roomNumber");

                        // Retrieve the room type from the result set and store it in the variable roomType
                        roomType = resultSet.getString("roomType");

                        // Retrieve the username from the result set and store it in the variable retrievedUsername
                        String retrievedUsername = resultSet.getString("username");

                        // Retrieve the email from the result set and store it in the variable email
                        String email = resultSet.getString("email");


                        // Create a new RetrieveObjects instance with retrieved booking information
                        RetrieveObjects booking = new RetrieveObjects(retrievedBookingID, checkInTime, checkOutTime, checkInDate, checkOutDate, roomNumber, roomType, retrievedUsername, email);
                        // Add the booking to the list of bookings
                        bookings.add(booking);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("An SQL error occurred: " + e.getMessage());
        }

        // Return the list of retrieved bookings
        return bookings;
    }
}
