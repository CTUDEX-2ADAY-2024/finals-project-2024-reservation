package main.java.com.ctu.reservationportal.reservation.infrastructure;

import main.java.com.ctu.reservationportal.reservation.abstraction.BookingValidator;
import main.java.com.ctu.reservationportal.reservation.abstraction.roomInfo.RoomType;
import main.java.com.ctu.reservationportal.reservation.dbservices.InsertRecords;
import main.java.com.ctu.reservationportal.reservation.model.CreateObjects;
import main.java.com.ctu.reservationportal.reservation.abstraction.CheckInOutTimeValidation;
import main.java.com.ctu.reservationportal.reservation.abstraction.AvailableTimeSlotsDisplay;
import main.java.com.ctu.reservationportal.reservation.abstraction.RoomNumberValidator;

import java.sql.*;
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
        System.out.println("\nWelcome to the Reservation System!");
        System.out.println("Create Booking Request\n");

        BookingValidator validator = new BookingValidator();

        // Prompt the user to enter their username and validate it against the database
        // Declare a variable to store the username
        String username;

        // Start an infinite loop to repeatedly prompt the user for their username until a valid one is provided
        while (true) {
            // Prompt the user to enter their username
            System.out.print("Enter your username: ");

            // Read the user input from the console and store it in the 'username' variable
            username = scanner.nextLine();

            // Check if the entered username is valid using the validator's isValidUsername method
            if (validator.isValidUsername(username)) {
                // If the username is valid, exit the loop
                break;
            } else {
                // If the username is invalid, print an error message and prompt the user again
                System.out.println("Invalid username. Please enter a valid username.");
            }
        }


        // Prompt the user to enter their email and validate it
        // Declare a variable to store the email
        String email;

        // Start an infinite loop to repeatedly prompt the user for their email until a valid one is provided
        while (true) {
            // Prompt the user to enter their email
            System.out.print("Enter your email: ");

            // Read the user input from the console and store it in the 'email' variable
            email = scanner.nextLine();

            // Check if the entered email ends with '@gmail.com' (case-insensitive) and is valid using the validator's isValidEmail method
            if (email.toLowerCase().endsWith("@gmail.com") && validator.isValidEmail(email)) {
                // If the email is valid, exit the loop
                break;
            } else {
                // If the email is invalid, print an error message and prompt the user again
                System.out.println("Invalid email address. Please enter a valid email.");
            }
        }


        // Display the available room options to the user
        System.out.println("\nAvailable rooms:\n 1. Classroom\n 2. CompLaboratory\n 3. Library\n 4. SmartRoom\n");
        int choice;
        // Prompt the user to select a room type
        System.out.print("Enter room type of your choice: \n");
        // Loop until a valid choice is entered
        while (true) {
            // Read the room type choice from the console
            try {
                choice = Integer.parseInt(scanner.nextLine());
                // Check if the choice is within the valid range
                if (choice >= 1 && choice <= 4) {
                    // If the choice is valid, break out of the loop
                    break;
                } else {
                    // If the choice is not valid, display an error message
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                // If the input cannot be parsed as an integer, display an error message
                System.out.println("Invalid input. Please enter a valid integer choice.");
            }
        }
        // Variable to store the selected room type
        String roomType;
        String roomNumber = null;
        RoomNumberValidator roomNumberValidator = new RoomNumberValidator();
        // Switch case to handle the user's room type selection
        switch (choice) {
            case 1:
                roomType = "Classroom";
                System.out.println("\nAvailable rooms:\n 101\n 102\n 103\n");
                // Initialize a flag to control the loop
                boolean validRoomClassroom = false;
                // Loop until a valid room number is entered
                while (!validRoomClassroom) {
                    // Prompt the user to select a room number
                    System.out.print("Enter room number: ");
                    // Read the room number choice from the console
                    roomNumber = scanner.nextLine();
                    // Validate the selected room number for Classroom
                    if (!roomNumberValidator.isValidClassroom(roomNumber)) {
                        System.out.println("Invalid room number for Classroom. Please enter a valid room number.");
                    } else {
                        // Set the flag to true to exit the loop
                        validRoomClassroom = true;
                    }
                }
                System.out.println("\nCLASSROOM INFORMATION:");
                // Create a Classroom object and display its information
                RoomType.Classroom classroom = new RoomType.Classroom(roomNumber, 40, true, "COT Building", "none", true, 40);
                classroom.displayRoomInfo();
                break;
            case 2:
                roomType = "CompLaboratory";
                System.out.println("\nAvailable rooms:\n 201\n 202\n 203\n");
                // Initialize a flag to control the loop
                boolean validRoomCompLab = false;
                // Loop until a valid room number is entered
                while (!validRoomCompLab) {
                    // Prompt the user to select a room number
                    System.out.print("Enter room number: ");
                    // Read the room number choice from the console
                    roomNumber = scanner.nextLine();
                    // Validate the selected room number for CompLaboratory
                    if (!roomNumberValidator.isValidCompLaboratory(roomNumber)) {
                        System.out.println("Invalid room number for CompLaboratory. Please enter a valid room number.");
                    } else {
                        // Set the flag to true to exit the loop
                        validRoomCompLab = true;
                    }
                }
                System.out.println("\nCOMPUTER LABORATORY INFORMATION:");
                // Create a CompLaboratory object and display its information
                RoomType.CompLaboratory compLaboratory = new RoomType.CompLaboratory(roomNumber, 25,true, "COT Building", "none", true, 25);
                compLaboratory.displayRoomInfo();
                break;
            case 3:
                roomType = "Library";
                System.out.println("\nAvailable rooms:\n 301\n 302\n 303\n");
                // Initialize a flag to control the loop
                boolean validRoomLibrary = false;
                // Loop until a valid room number is entered
                while (!validRoomLibrary) {
                    // Prompt the user to select a room number
                    System.out.print("Enter room number: ");
                    // Read the room number choice from the console
                    roomNumber = scanner.nextLine();
                    // Validate the selected room number for Library
                    if (!roomNumberValidator.isValidLibrary(roomNumber)) {
                        System.out.println("Invalid room number for Library. Please enter a valid room number.");
                    } else {
                        // Set the flag to true to exit the loop
                        validRoomLibrary = true;
                    }
                }
                System.out.println("\nLIBRARY INFORMATION:");
                // Create a Library object and display its information
                RoomType.Library library = new RoomType.Library(roomNumber, 50, true, "COT Building", "none", true, 50);
                library.displayRoomInfo();
                break;
            case 4:
                roomType = "SmartRoom";
                System.out.println("\nAvailable rooms:\n 401\n 402\n 403\n");
                // Initialize a flag to control the loop
                boolean validRoomSmart = false;
                // Loop until a valid room number is entered
                while (!validRoomSmart) {
                    // Prompt the user to select a room number
                    System.out.print("Enter room number: ");
                    // Read the room number choice from the console
                    roomNumber = scanner.nextLine();
                    // Validate the selected room number for SmartRoom
                    if (!roomNumberValidator.isValidSmartRoom(roomNumber)) {
                        System.out.println("Invalid room number for SmartRoom. Please enter a valid room number.");
                    } else {
                        // Set the flag to true to exit the loop
                        validRoomSmart = true;
                    }
                }
                System.out.println("\nSMART ROOM INFORMATION:");
                // Create a SmartRoom object and display its information
                RoomType.Smartroom smartRoom = new RoomType.Smartroom(roomNumber, 50, true, "COT Building", "none", true, true, true);
                smartRoom.displayRoomInfo();
                break;
            default:
                // Print an error message if the user selects an invalid room type
                System.out.println("Invalid choice. Please select a valid room type.");
                return;
        }

        // Prompt the user to enter the check-in date
        Date checkInDate;
        while (true) {
            System.out.print("\nEnter check-in date (YYYY-MM-DD): ");
            String checkInDateStr = scanner.nextLine();
            try {
                checkInDate = Date.valueOf(checkInDateStr);
                break; // Break the loop if the date format is valid
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid check-in date format. Please enter the date in YYYY-MM-DD format.");
            }
        }

        // Prompt the user to enter the check-out date
        Date checkOutDate;
        while (true) {
            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            String checkOutDateStr = scanner.nextLine();
            try {
                checkOutDate = Date.valueOf(checkOutDateStr);
                if (checkOutDate.equals(checkInDate) || checkOutDate.after(checkInDate)) {
                    break; // Break the loop if the date format is valid and check-out date is after or equal to check in date
                } else {
                    System.out.println("Check-out date must be after or equal to check-in date.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid check-out date format. Please enter the date in YYYY-MM-DD format.");
            }
        }


        // Display available time slots before prompting the user for booking time
        AvailableTimeSlotsDisplay availableTimeSlotsDisplay = new AvailableTimeSlotsDisplay();
        availableTimeSlotsDisplay.displayAvailableTimeSlots(roomType, roomNumber);

        // Prompt the user to enter the check-in time
        Time checkInTime;
        while (true) {
            System.out.print("\nEnter check-in time (hh:mmAM/PM): ");
            String checkInTimeStr = scanner.nextLine();
            try {
                checkInTime = convertTo24HourFormat(checkInTimeStr);
                break; // Break the loop if the time format is valid
            } catch (ParseException e) {
                System.out.println("Invalid check-in time format. Please enter the time in hh:mmAM/PM format.");
            }
        }

        // Prompt the user to enter the check-out time
        Time checkOutTime;
        while (true) {
            System.out.print("Enter check-out time (hh:mmAM/PM): ");
            String checkOutTimeStr = scanner.nextLine();
            try {
                checkOutTime = convertTo24HourFormat(checkOutTimeStr);
                if (checkOutTime.after(checkInTime)) {
                    break; // Break the loop if the time format is valid and check-out time is after check-in time
                } else {
                    System.out.println("Check-out time must be after check-in time.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid check-out time format. Please enter the time in hh:mmAM/PM format.");
            }
        }

        // Create a CheckInOutTimeValidation object to validate the check-in and check-out times
        CheckInOutTimeValidation timeValidator = new CheckInOutTimeValidation();
        // Validate the check-in and check-out times and ensure check-in is before check-out
        if (!timeValidator.isValidTime(checkInTime) || !timeValidator.isValidTime(checkOutTime) ||
                !timeValidator.isCheckInBeforeCheckOut(checkInTime, checkOutTime, checkInDate.toString(), checkOutDate.toString())) {
            // Print an error message and return if the time validation fails
            System.out.println("\nInvalid check-in or check-out time.");
            return;
        }

        // Create a CreateObjects object to store the booking details
        CreateObjects createObjects = new CreateObjects(username, email, roomType, roomNumber, checkInTime, checkOutTime, checkInDate, checkOutDate);

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
                System.out.printf("%-12s%-15s%-25s%-10s%-15s%-15s%-15s%-15s%-20s\n",
                        "Booking ID", "Username", "Email", "Room No", "Check-in Date", "Check-out Date","Check-inTime", "Check-out Time", "Room Type");
                        // Print horizontal line
                for (int i = 0; i < 130; i++) {
                    System.out.print("-");
                }
                System.out.println();
                // Print booking details
                System.out.printf("%-12d%-15s%-25s%-10s%-15s%-15s%-15s%-15s%-20s\n",
                        bookingId, createObjects.getUserName(), createObjects.getEmail(), createObjects.getRoomNumber(),
                        createObjects.getCheckInDate().toString(), createObjects.getCheckOutDate().toString(),
                        convertTo12HourFormat(checkInTime), convertTo12HourFormat(checkOutTime), createObjects.getRoomType());

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
        return new Time(sdf12Hour.parse(time12Hour).getTime());
    }
    /**
     * Converts time in 24-hour format to a 12-hour format string (e.g., 6:00PM).
     *
     * @param time24Hour The time in 24-hour format as a Time object.
     * @return The time in 12-hour format as a string.
     */
    private String convertTo12HourFormat(Time time24Hour) {
        SimpleDateFormat sdf12Hour = new SimpleDateFormat("hh:mm a");
        return sdf12Hour.format(time24Hour);
    }

}



