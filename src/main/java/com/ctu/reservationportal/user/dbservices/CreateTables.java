package main.java.com.ctu.reservationportal.user.dbservices;

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

    private static final String createUserInfoTbl =  "CREATE TABLE userInfo (\n" +
            "    firstName VARCHAR(255),\n" +
            "    middleName VARCHAR(255),\n" +
            "    lastName VARCHAR(255),\n" +
            "    birthDate DATE,\n" +
            "    email VARCHAR(255),\n " +
            "    phoneNumber VARCHAR(255),\n " +
            "    street VARCHAR(255),\n" +
            "    barangay VARCHAR(255),\n" +
            "    municipality VARCHAR(255),\n" +
            "    city VARCHAR(255),\n" +
            "    zipCode INT,\n" +
            "    nationality VARCHAR(255),\n" +
            "    gender VARCHAR(255),\n" +
            "    roleAtSchool VARCHAR(255),\n" +
            "    userName VARCHAR(255),\n" +
            "    idNumber INT\n" +
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
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            System.out.println("Creating UserInfo Table in DB");
            statement.execute(createUserInfoTbl);


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
