package main.java.com.ctu.reservationportal.reservation.infrastructure;
import main.java.com.ctu.reservationportal.reservation.model.CreateObjects;
import java.util.Random;
import java.util.Scanner;

/**
 * The type Create booking.
 */
public class CreateBooking {

    /**
     * Input receiver.
     */
    public static void InputReceiver() {
        Scanner scanner = new Scanner(System.in);

        // Display welcome messages
        System.out.println("Welcome to the Reservation System!");
        System.out.println("Create Booking Request");

        // Prompt user for name and email
        System.out.print("Enter your Username: ");
        String username = scanner.next();

        System.out.print("Enter your email: ");
        String email = scanner.next();

        // Display available rooms and prompt user for room choice
        System.out.println("Available rooms: Room A, Room B, Room C");
        System.out.print("Enter room: ");
        String room = scanner.next();

        // Prompt user for date and time
        System.out.print("Enter booking date (YYYY-MM-DD): ");
        String date = scanner.next();

        System.out.print("Enter booking time (HH:MM): ");
        String time = scanner.next();

        CreateObjects createObjects = new CreateObjects(username, email, date, time, room);

        // Create an object of InputData class and store the input information

        // Pass the input data to the validator
        BookingValidator validator = new BookingValidator();
        if (validator.isValidBooking(createObjects)) {
            System.out.println("\nConfirm Booking");
            System.out.println("\nBooking Details:");
            System.out.println("Name: " + createObjects.getUserName());
            System.out.println("Email: " + createObjects.getEmail());
            System.out.println("Date: " + createObjects.getDate());
            System.out.println("Time: " + createObjects.getTime());
            System.out.println("Room: " + createObjects.getRoom());
            System.out.println("Booking ID:" + createObjects.generateBookingId());
            System.out.println("\nBooking Confirmed! Thank You.");
        } else {
            System.out.println("\nThe selected room is not yet available at the chosen date and time.");
        }

        scanner.close();
    }
    /**
     * The type Booking validator.
     */
// Class to validate the booking
    static class BookingValidator {
        /**
         * Is valid booking boolean.
         *
         * @param createObjects the input data
         * @return the boolean
         */
// Method to check if the booking is valid
            public boolean isValidBooking(CreateObjects createObjects) {
                // Perform validation logic here (e.g., check if the room is available at the specified date and time)
                // For simplicity, let's assume all bookings are valid
                return true;

            }

        }
    }
