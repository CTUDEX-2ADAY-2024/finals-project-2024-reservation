package main.java.com.ctu.reservationportal.reservation.abstraction;
// Import RetrieveObjects class from the specified package
import main.java.com.ctu.reservationportal.reservation.model.RetrieveObjects;
// Import SQL classes for database operations
import java.sql.*;
// Import ArrayList class from java.util package
import java.util.ArrayList;
// Import List interface from java.util package
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

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/roomportaldb",
                "root",
                "mypassword"))
        {
            // SQL query to retrieve bookings
            String query = "SELECT * FROM BOOKINGDETAILS WHERE bookingID=? OR roomType=? OR username=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                //Set the parameter in the prepared statement
                preparedStatement.setInt(1, bookingID);
                preparedStatement.setString(2, roomType);
                preparedStatement.setString(3, username);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Iterate through the result set and retrieve booking information
                    while (resultSet.next()) {
                        int retrievedBookingID = resultSet.getInt("bookingID");
                        Time checkInTime = resultSet.getTime("checkInTime");
                        Time checkOutTime = resultSet.getTime("checkOutTime");
                        Date checkInDate = resultSet.getDate("checkInDate");
                        Date checkOutDate = resultSet.getDate("checkOutDate");
                        String roomInformation = resultSet.getString("roomType");
                        String retrievedUsername = resultSet.getString("username");
                        String retrievedEmail = resultSet.getString("email");

                        // Create a new RetrieveObjects instance with retrieved booking information
                        RetrieveObjects booking = new RetrieveObjects(retrievedBookingID, checkInTime, checkOutTime, checkInDate, checkOutDate, roomInformation, retrievedUsername, retrievedEmail);
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
