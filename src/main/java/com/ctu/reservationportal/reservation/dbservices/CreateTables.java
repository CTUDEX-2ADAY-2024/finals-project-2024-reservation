package main.java.com.ctu.reservationportal.reservation.dbservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create Statement JDBC Example
 * @author Ramesh Fadatare
 *
 */
public class CreateTables {
    private static final String createBookingDetailsTbl = "CREATE TABLE BookingDetails (\n" +
            "    userName VARCHAR(255),\n" +
            "    email VARCHAR(255),\n" +
            "    checkInDate DATE,\n" +
            "    checkOut DATE,\n" +
            "    checkInTime TIME,\n" +
            "    checkOutTime TIME,\n" +
            "    roomType VARCHAR(255),\n" +
            "    bookingId INT\n" +
            ");";

    public CreateTables(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            createTable();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading JBCD MySQL Driver", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable() throws SQLException {

        System.out.println("Establishing connection to DB");

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/roomportaldb?useSSL=false&allowPublicKeyRetrieval=true", "root", "mypassword");

             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement()) {

            System.out.println("Creating BookingDetails Table in DB");
            statement.execute(createBookingDetailsTbl);

        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}