package main.java.com.ctu.reservationportal.user.dbservices;

import main.java.com.ctu.reservationportal.user.model.UserInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static main.java.com.ctu.reservationportal.user.dbservices.CreateTables.printSQLException;

public class SelectRecords {
    private static final String SELECT_USERS_SQL = "SELECT * FROM userinfo WHERE userName = ?";

    public SelectRecords() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JDBC MySQL Driver", e);
        }
    }

    public UserInfo selectUserRecord(String username) throws SQLException {
        UserInfo userInfo = null;
        System.out.println("Selecting user data from DB");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/roomportaldb",
                        "root",
                        "mypassword");
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)) {

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userInfo = new UserInfo();
                userInfo.setUserName(resultSet.getString("username"));
                userInfo.setFirstName(resultSet.getString("firstName"));
                userInfo.setMiddleName(resultSet.getString("middleName"));
                userInfo.setLastName(resultSet.getString("lastName"));
                userInfo.setBirthDate(String.valueOf(resultSet.getDate("birthDate").toLocalDate()));
                userInfo.setStreet(resultSet.getString("street"));
                userInfo.setBarangay(resultSet.getString("barangay"));
                userInfo.setMunicipality(resultSet.getString("municipality"));
                userInfo.setCity(resultSet.getString("city"));
                userInfo.setZIPcode(resultSet.getInt("zipCode"));
                userInfo.setEmail(resultSet.getString("Email"));
                userInfo.setPhoneNumber(String.valueOf(resultSet.getLong("PhoneNumber")));
                userInfo.setNationality(resultSet.getString("nationality"));
                userInfo.setGender(resultSet.getString("gender"));
                userInfo.setroleAtschool(resultSet.getString("roleAtSchool"));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return userInfo;
    }

    public static void main(String[] args) {
        SelectRecords selectRecords = new SelectRecords();
        try {
            // Example usage: Select user record with username "johndoe123"
            UserInfo userInfo = selectRecords.selectUserRecord("johndoe123");
            if (userInfo != null) {
                System.out.println("User found: " + userInfo.getFirstName() + " " + userInfo.getLastName());
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
