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
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/roomportaldb",
                "root",
                "mypassword"))
        {
            String query = "UPDATE BOOKINGDETAILS SET userName = ?, email = ?, roomType = ?, checkInTime = ?, checkOutTime = ?, checkInDate = ?, checkOutDate = ?, roomNumber = ? WHERE bookingId = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, updateObjects.getUsername());
                statement.setString(2, updateObjects.getEmail());
                statement.setString(3, updateObjects.getRoomType());
                statement.setTime(4, updateObjects.getCheckInTime());
                statement.setTime(5, updateObjects.getCheckOutTime());
                statement.setDate(6, updateObjects.getCheckInDate());
                statement.setDate(7, updateObjects.getCheckOutDate());
                statement.setInt(8, bookingId);
                statement.setString(9, updateObjects.getRoomNumber());

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/roomportaldb",
                "root",
                "mypassword"))
        {
            String query = "SELECT COUNT(*) FROM BOOKINGDETAILS WHERE roomType=? AND checkInDate=? AND checkOutDate=? AND checkInTime<=? AND checkOutTime>=? AND userName=? AND bookingId=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, updateObjects.getRoomType());
                statement.setDate(2, updateObjects.getCheckInDate());
                statement.setDate(3, updateObjects.getCheckOutDate());
                statement.setTime(4, updateObjects.getCheckInTime());
                statement.setTime(5, updateObjects.getCheckOutTime());
                statement.setString(6, updateObjects.getUsername());
                statement.setInt(7, updateObjects.getBookingID());

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
