package main.java.com.ctu.reservationportal.reservation.abstraction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomNumberValidator {
    // Method to validate room numbers for Classroom
    public boolean isValidClassroom(String roomNumber) {
        return roomNumber.equals("101") || roomNumber.equals("102") || roomNumber.equals("103");
    }

    // Method to validate room numbers for CompLaboratory
    public boolean isValidCompLaboratory(String roomNumber) {
        return roomNumber.equals("201") || roomNumber.equals("202") || roomNumber.equals("203");
    }

    // Method to validate room numbers for Library
    public boolean isValidLibrary(String roomNumber) {
        return roomNumber.equals("301") || roomNumber.equals("302") || roomNumber.equals("303");
    }

    // Method to validate room numbers for SmartRoom
    public boolean isValidSmartRoom(String roomNumber) {
        return roomNumber.equals("401") || roomNumber.equals("402") || roomNumber.equals("403");
    }

    // Method to validate room number for the given room type
    public boolean isValidRoomNumber(String roomType, String roomNumber) {
        switch (roomType) {
            case "Classroom":
                return isValidClassroom(roomNumber);
            case "CompLaboratory":
                return isValidCompLaboratory(roomNumber);
            case "Library":
                return isValidLibrary(roomNumber);
            case "SmartRoom":
                return isValidSmartRoom(roomNumber);
            default:
                return false;
        }
    }

    // Method to retrieve available room numbers for the given room type
    public List<String> getAvailableRoomNumbers(String roomType) {
        List<String> availableRoomNumbers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/roomportaldb",
                "root",
                "mypassword")) {
            String query = "SELECT roomNumber FROM Rooms WHERE roomType = ? AND isAvailable = TRUE";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, roomType);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        availableRoomNumbers.add(resultSet.getString("roomNumber"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableRoomNumbers;
    }
}
