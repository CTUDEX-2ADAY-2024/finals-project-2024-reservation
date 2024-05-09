package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.CreateObjects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingValidator {

    public boolean isValidBooking(CreateObjects createObjects) {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/booking_schema",
                    "root",
                    "admin123$"
            );

            // Create a prepared statement for checking room availability
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM RESERVATION_BOOKING_RECORDS WHERE room=? AND date=? AND time=?"
            );
            preparedStatement.setString(1, createObjects.getRoom());
            preparedStatement.setString(2, createObjects.getDate());
            preparedStatement.setString(3, createObjects.getTimeInput());

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            // Close the result set, prepared statement, and connection
            resultSet.close();
            preparedStatement.close();
            connection.close();

            // Return true if the room is available (count is 0)
            return count == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


