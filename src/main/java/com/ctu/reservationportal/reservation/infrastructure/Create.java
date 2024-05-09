package main.java.com.ctu.reservationportal.reservation.infrastructure;
import main.java.com.ctu.reservationportal.reservation.abstraction.BookingValidator;
import main.java.com.ctu.reservationportal.reservation.model.CreateObjects;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;


/**
 * The type Create booking.
 */
public class Create {

    /**
     * Input receiver.
     */
    public void inputReceiver() {
        // Reads user input
        Scanner scanner = new Scanner(System.in);

        // Display welcome messages
        System.out.println("Welcome to the Reservation System!");
        System.out.println("Create Booking Request");

        // Prompt user for name
        System.out.print("Enter your Username: ");
        String username = scanner.next();

        // Prompt user for email
        System.out.print("Enter your email: ");
        String email = scanner.next();

        // Display available rooms and prompt user for room choice
        System.out.println("Available rooms: Room A, Room B, Room C");
        System.out.print("Enter room: ");
        String room = scanner.next();

        // Prompt user for date
        System.out.print("Enter booking date (YYYY-MM-DD): ");
        String date = scanner.next();

        // Prompt user for time
        System.out.print("Enter booking time (HH:MM): ");
        String timeInput = scanner.next();


        CreateObjects createObjects = new CreateObjects(username, email, date, timeInput, room);


        createObjects.setTimeInput(timeInput);
        String storedTimeInput = createObjects.getTimeInput();
        Time time = createObjects.convertTimeInput();

        // Pass the input data to the validator
        BookingValidator validator = new BookingValidator();
        if (validator.isValidBooking(createObjects)) {
            // Insert the booking into the database
            if (storeBooking(createObjects)) {
                System.out.println("\nConfirm Booking");
                System.out.println("\nBooking Details:");
                System.out.println("Name: " + createObjects.getUserName());
                System.out.println("Email: " + createObjects.getEmail());
                System.out.println("Date: " + createObjects.getDate());
                System.out.println("Time: " + createObjects.getTimeInput());
                System.out.println("Room: " + createObjects.getRoom());
                System.out.println("Booking ID:" + createObjects.getBookingId());
                System.out.println("\nBooking Confirmed! Thank You.");
            } else {
                System.out.println("\nFailed to store booking. Please try again later.");
            }
        } else {
            System.out.println("\nThe selected room is not yet available at the chosen date and time.");
        }

        scanner.close();
    }

    /**
     * Store booking boolean.
     *
     * @param createObjects the createObjects
     * @return the boolean
     */
    public static boolean storeBooking(CreateObjects createObjects) {
        try {
            // Generate a random booking ID
            int bookingId = generateBookingId();

            // Connect to the database
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/booking_schema",
                    "root",
                    "admin123$"
            );

            // Create a prepared statement for inserting data
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO RESERVATION_BOOKING_RECORDS (bookingId, userName, email, date, time, room) VALUES (?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            // Set the values for the prepared statement
            preparedStatement.setInt(1, bookingId);
            preparedStatement.setString(2, createObjects.getUserName());
            preparedStatement.setString(3, createObjects.getEmail());
            preparedStatement.setString(4, createObjects.getDate());
            preparedStatement.setString(5, createObjects.getTimeInput());
            preparedStatement.setString(6, createObjects.getRoom());

            // Execute the insert statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Set the generated bookingId in the CreateObjects instance
            createObjects.setBookingId(bookingId);

            // Close the result set, prepared statement, and connection
            preparedStatement.close();
            connection.close();

            // Return true if the insert was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int generateBookingId() {
        // Generate a random booking ID between 100000 and 999999
        return 100000 + new Random().nextInt(900000);
    }

}