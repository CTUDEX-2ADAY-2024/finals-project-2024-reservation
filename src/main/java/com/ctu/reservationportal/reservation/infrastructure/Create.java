package main.java.com.ctu.reservationportal.reservation.infrastructure;

import main.java.com.ctu.reservationportal.reservation.abstraction.BookingValidator;
import main.java.com.ctu.reservationportal.reservation.abstraction.roomInfo.RoomType;
import main.java.com.ctu.reservationportal.reservation.dbservices.InsertRecords;
import main.java.com.ctu.reservationportal.reservation.model.CreateObjects;
import main.java.com.ctu.reservationportal.reservation.abstraction.CheckInOutTimeValidation;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Class responsible for creating booking requests.
 */
public class Create {

    /**
     * Receives input from the user to create a booking request.
     * This method interacts with the user via the console to gather all necessary information
     * for creating a booking, validates the input, and attempts to create a booking record.
     */
    public void inputReceiver() {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Print a welcome message and prompt to create a booking request
        System.out.println("Welcome to the Reservation System!");
        System.out.println("Create Booking Request\n");

        // Prompt the user to enter their username
        System.out.print("Enter your username: ");
        // Read the username input from the console
        String username = scanner.nextLine();

        String email;
        // Keep prompting the user for their email until a valid one is entered
        while (true) {
            System.out.print("Enter your email: ");
            // Read the email input from the console
            email = scanner.nextLine();
            // Validate the email format (must end with @gmail.com)
            if (email.toLowerCase().endsWith("@gmail.com")) {
                // Exit the loop if the email is valid
                break;
            } else {
                // Print an error message if the email is invalid
                System.out.println("Invalid email address. Please enter a valid email.");
            }
        }

        // Create a BookingValidator object to validate the username and email
        BookingValidator validator = new BookingValidator();
        // Validate the username and email against the database
        if (!validator.isValidUsernameAndEmail(username, email)) {
            // Print an error message and return if the validation fails
            System.out.println("Invalid username or email.");
            return;
        }

        // Display the available room options to the user
        System.out.println("\nAvailable rooms:\n 1. Classroom\n 2. CompLaboratory\n 3. Library\n 4. SmartRoom\n");
        // Prompt the user to select a room type
        System.out.print("Enter room type of your choice: \n");
        // Read the room type choice from the console
        int choice = scanner.nextInt();
        // Consume the newline character left by nextInt()
        scanner.nextLine();

        // Variable to store the selected room type
        String roomType;

        // Switch case to handle the user's room type selection
        switch (choice) {
            case 1:
                roomType = "Classroom";
                System.out.println("\nCLASSROOM INFORMATION:");
                // Create a Classroom object and display its information
                RoomType.Classroom classroom = new RoomType.Classroom("101", 40, true, "COT Building", "none", true, 40);
                classroom.displayRoomInfo();
                break;
            case 2:
                roomType = "CompLaboratory";
                System.out.println("\nCOMPUTER LABORATORY INFORMATION:");
                // Create a CompLaboratory object and display its information
                RoomType.CompLaboratory compLaboratory = new RoomType.CompLaboratory("102", 25, true, "COT Building", "none", true, 25);
                compLaboratory.displayRoomInfo();
                break;
            case 3:
                roomType = "Library";
                System.out.println("\nLIBRARY INFORMATION:");
                // Create a Library object and display its information
                RoomType.Library library = new RoomType.Library("103", 50, true, "COT Building", "none", true, 50);
                library.displayRoomInfo();
                break;
            case 4:
                roomType = "SmartRoom";
                System.out.println("\nSMART ROOM INFORMATION:");
                // Create a SmartRoom object and display its information
                RoomType.Smartroom smartRoom = new RoomType.Smartroom("104", 50, true, "COT Building", "none", true, true, true);
                smartRoom.displayRoomInfo();
                break;
            default:
                // Print an error message if the user selects an invalid room type
                System.out.println("Invalid choice. Please select a valid room type.");
                return;
        }

        // Prompt the user to enter the check-in date
        System.out.print("\nEnter check-in date (YYYY-MM-DD): ");
        // Read the check-in date from the console and convert it to a Date object
        String checkInDateStr = scanner.nextLine();
        Date checkInDate;
        try {
            checkInDate = Date.valueOf(checkInDateStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid check-in date format. Please enter the date in YYYY-MM-DD format.");
            return;
        }

        // Prompt the user to enter the check-out date
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        // Read the check-out date from the console and convert it to a Date object
        String checkOutDateStr = scanner.nextLine();
        Date checkOutDate;
        try {
            checkOutDate = Date.valueOf(checkOutDateStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid check-out date format. Please enter the date in YYYY-MM-DD format.");
            return;
        }

        // Prompt the user to enter the check-in time
        System.out.print("Enter check-in time (hh:mmAM/PM): ");
        // Read the check-in time from the console and convert it to a 24-hour format
        String checkInTimeStr = scanner.nextLine();
        Time checkInTime;
        String checkInTime12HourFormat;
        try {
            checkInTime = convertTo24HourFormat(checkInTimeStr);
            checkInTime12HourFormat = checkInTimeStr;
        } catch (ParseException e) {
            System.out.println("Invalid check-in time format. Please enter the time in hh:mmAM/PM format.");
            return;
        }

        // Prompt the user to enter the check-out time
        System.out.print("Enter check-out time (hh:mmAM/PM): \n");
        // Read the check-out time from the console and convert it to a 24-hour format
        String checkOutTimeStr = scanner.nextLine();
        Time checkOutTime;
        String checkOutTime12HourFormat;
        try {
            checkOutTime = convertTo24HourFormat(checkOutTimeStr);
            checkOutTime12HourFormat = checkOutTimeStr;
        } catch (ParseException e) {
            System.out.println("Invalid check-out time format. Please enter the time in hh:mmAM/PM format.");
            return;
        }

        // Create a CheckInOutTimeValidation object to validate the check-in and check-out times
        CheckInOutTimeValidation timeValidator = new CheckInOutTimeValidation();
        // Validate the check-in and check-out times and ensure check-in is before check-out
        if (!timeValidator.isValidTime(checkInTime) || !timeValidator.isValidTime(checkOutTime) ||
                !timeValidator.isCheckInBeforeCheckOut(checkInTime, checkOutTime, checkInDateStr, checkOutDateStr)) {
            // Print an error message and return if the time validation fails
            System.out.println("\nInvalid check-in or check-out time.");
            return;
        }

        // Create a CreateObjects object to store the booking details
        CreateObjects createObjects = new CreateObjects(username, email, roomType, checkInTime, checkOutTime, checkInDate, checkOutDate);

        // Create an InsertRecords object to handle database operations
        InsertRecords insertRecords = new InsertRecords();
        // Validate the booking details and insert the booking record if valid
        if (validator.isValidBooking(createObjects)) {
            // Insert the booking details into the database and get the booking ID
            int bookingId = insertRecords.insertBookingDetails(createObjects);
            if (bookingId > 0) {
                // Print the booking confirmation details if the insertion was successful
                System.out.println("\nConfirm Booking");
                // Print horizontal line
                for (int i = 0; i < 130; i++) {
                    System.out.print("-");
                }
                System.out.println();
                // Print table header
                System.out.printf("%-12s%-15s%-25s%-15s%-15s%-15s%-15s%-20s\n",
                        "Booking ID", "Username", "Email", "Check-in Date", "Check-out Date",
                        "Check-in Time", "Check-out Time", "Room Information");
                // Print horizontal line
                for (int i = 0; i < 130; i++) {
                    System.out.print("-");
                }
                System.out.println();
                // Print booking details
                System.out.printf("%-12d%-15s%-25s%-15s%-15s%-15s%-15s%-20s\n",
                        bookingId, createObjects.getUserName(), createObjects.getEmail(),
                        createObjects.getCheckInDate().toString(), createObjects.getCheckOutDate().toString(),
                        checkInTime12HourFormat, checkOutTime12HourFormat,
                        createObjects.getRoomType());

                System.out.println("\nBooking Confirmed! Thank You.");
            } else {
                // Print an error message if the insertion failed
                System.out.println("\nFailed to store booking. Please try again later.");
            }
        } else {
            // Print an error message if the booking details are invalid or the room is unavailable
            System.out.println("\nThe selected room is not available at the chosen date and time.");
        }

        // Close the Scanner object to release resources
        scanner.close();
    }

    /**
     * Converts time in 12-hour format (e.g., 6:00PM) to a 24-hour format Time object.
     *
     * @param time12Hour The time in 12-hour format as a string.
     * @return The time in 24-hour format as a Time object.
     * @throws ParseException If the input time format is invalid.
     */
    private Time convertTo24HourFormat(String time12Hour) throws ParseException {
        SimpleDateFormat sdf12Hour = new SimpleDateFormat("hh:mma");
        SimpleDateFormat sdf24Hour = new SimpleDateFormat("HH:mm:ss");
        return Time.valueOf(sdf24Hour.format(sdf12Hour.parse(time12Hour)));
    }
}
