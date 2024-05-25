package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;

import java.sql.*;

public class UpdateDBconnector {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/roomportaldb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mypassword";

    public boolean updateBookingDetails(int bookingId, UpdateObjects updateObjects) {
        // First retrieve the current booking details to ensure we have all fields
        UpdateObjects currentBooking = getBookingDetails(bookingId);
        if (currentBooking == null) {
            return false;
        }

        // Update the specific fields with new values, keeping unchanged fields as is
        if (updateObjects.getUsername() != null) {
            currentBooking.setUsername(updateObjects.getUsername());
        }
        if (updateObjects.getEmail() != null) {
            currentBooking.setEmail(updateObjects.getEmail());
        }
        if (updateObjects.getRoomType() != null) {
            currentBooking.setRoomType(updateObjects.getRoomType());
        }
        if (updateObjects.getCheckInTime() != null) {
            currentBooking.setCheckInTime(updateObjects.getCheckInTime());
        }
        if (updateObjects.getCheckOutTime() != null) {
            currentBooking.setCheckOutTime(updateObjects.getCheckOutTime());
        }
        if (updateObjects.getCheckInDate() != null) {
            currentBooking.setCheckInDate(updateObjects.getCheckInDate());
        }
        if (updateObjects.getCheckOutDate() != null) {
            currentBooking.setCheckOutDate(updateObjects.getCheckOutDate());
        }
        if (updateObjects.getRoomNumber() != null) {
            currentBooking.setRoomNumber(updateObjects.getRoomNumber());
        }

        String query = "UPDATE BOOKINGDETAILS SET userName = ?, email = ?, roomType = ?, checkInTime = ?, checkOutTime = ?, checkInDate = ?, checkOutDate = ?, roomNumber = ? WHERE bookingId = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, currentBooking.getUsername());
            statement.setString(2, currentBooking.getEmail());
            statement.setString(3, currentBooking.getRoomType());
            statement.setTime(4, currentBooking.getCheckInTime());
            statement.setTime(5, currentBooking.getCheckOutTime());
            statement.setDate(6, currentBooking.getCheckInDate());
            statement.setDate(7, currentBooking.getCheckOutDate());
            statement.setString(8, currentBooking.getRoomNumber());
            statement.setInt(9, bookingId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating booking details: " + e.getMessage());
            return false;
        }
    }

    public UpdateObjects getBookingDetails(int bookingId) {
        String query = "SELECT userName, email, roomType, checkInTime, checkOutTime, checkInDate, checkOutDate, roomNumber FROM BOOKINGDETAILS WHERE bookingId = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bookingId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UpdateObjects bookingDetails = new UpdateObjects();
                    bookingDetails.setBookingId(bookingId);
                    bookingDetails.setUsername(resultSet.getString("userName"));
                    bookingDetails.setEmail(resultSet.getString("email"));
                    bookingDetails.setRoomType(resultSet.getString("roomType"));
                    bookingDetails.setCheckInTime(resultSet.getTime("checkInTime"));
                    bookingDetails.setCheckOutTime(resultSet.getTime("checkOutTime"));
                    bookingDetails.setCheckInDate(resultSet.getDate("checkInDate"));
                    bookingDetails.setCheckOutDate(resultSet.getDate("checkOutDate"));
                    bookingDetails.setRoomNumber(resultSet.getString("roomNumber"));
                    return bookingDetails;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving booking details: " + e.getMessage());
        }
        return null;
    }

    public boolean isValidBooking(UpdateObjects updateObjects) {
        String query = "SELECT COUNT(*) FROM BOOKINGDETAILS WHERE roomType = ? AND checkInDate = ? AND checkOutDate = ? AND checkInTime <= ? AND checkOutTime >= ? AND userName = ? AND bookingId = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

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
        } catch (SQLException e) {
            System.err.println("Error validating booking: " + e.getMessage());
        }
        return false;
    }
}
