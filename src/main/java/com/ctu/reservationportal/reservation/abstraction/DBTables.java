package main.java.com.ctu.reservationportal.reservation.abstraction;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTables {

        public static void main(String[] args) {
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/booking_schema",
                        "root",
                        "admin123$"
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM RESERVATION_BOOKING_RECORDS");

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("userName"));
                    System.out.println(resultSet.getString("email"));
                    System.out.println(resultSet.getString("date"));
                    System.out.println(resultSet.getString("time"));
                    System.out.println(resultSet.getString("room"));
                    System.out.println(resultSet.getString("bookingId"));
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

