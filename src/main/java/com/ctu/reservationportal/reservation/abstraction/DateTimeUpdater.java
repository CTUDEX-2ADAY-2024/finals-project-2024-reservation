package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Class responsible for updating date and time information for booking objects.
 */
public class DateTimeUpdater {
    private Scanner scanner;
    private UpdateObjects updateObjects;
    private AvailableTimeSlotsDisplay timeSlotsDisplay;
    private Date checkInDate;
    private Date checkOutDate;
    private Time checkInTime;
    private Time checkOutTime;
    private UpdateDBconnector dbConnector;

    /**
     * Constructs a new DateTimeUpdater with the given Scanner and UpdateObjects.
     *
     * @param scanner       The Scanner object for user input.
     * @param updateObjects The UpdateObjects object containing booking information to be updated.
     */
    public DateTimeUpdater(Scanner scanner, UpdateObjects updateObjects) {
        this.scanner = scanner;
        this.updateObjects = updateObjects;
        this.timeSlotsDisplay = new AvailableTimeSlotsDisplay();
        this.dbConnector = new UpdateDBconnector();
    }

    /**
     * Updates both date and time information.
     */
    public void update() {
        updateDate();
        updateObjects.setCheckInDate(checkInDate);
        updateObjects.setCheckOutDate(checkOutDate);
        updateObjects = dbConnector.getBookingDetails(updateObjects.getBookingID());
        isExistingRoomValid(updateObjects);
        timeSlotsDisplay.displayAvailableTimeSlots(updateObjects.getRoomType(), updateObjects.getRoomNumber());
        updateTime();
        displayBookingInfo(updateObjects);
    }

    /**
     * Updates the date information.
     */
    private void updateDate() {
        // Prompt the user to enter the check-in date
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
        while (true) {
            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            String checkOutDateStr = scanner.nextLine();
            try {
                checkOutDate = Date.valueOf(checkOutDateStr);
                if (checkOutDate.equals(checkInDate) || checkOutDate.after(checkInDate)) {
                    // Allow check-out date to be the same as check-in date or after check-in date
                    break; // Break the loop if the date format is valid and check-out date is after or equal to check in date
                } else {
                    System.out.println("Check-out date must be after or equal to check-in date.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid check-out date format. Please enter the date in YYYY-MM-DD format.");
            }
        }

        // Update the UpdateObjects instance with the new dates
        updateObjects.setCheckInDate(Date.valueOf(checkInDate.toString()));
        updateObjects.setCheckOutDate(Date.valueOf(checkOutDate.toString()));
    }

    /**
     * Prompts the user to select a room type and enter a room number (informative only).
     */
    private void isExistingRoomValid(UpdateObjects bookingInfo) {
        System.out.println("\nExisting Room Information (for reference only):");
        System.out.println("Room Type: " + bookingInfo.getRoomType());
        System.out.println("Room Number: " + bookingInfo.getRoomNumber());
    }

    /**
     * Updates the time information.
     */
    private void updateTime() {
        // Prompt the user to enter the check-in time
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

            if (!dbConnector.checkRoomAvailability(updateObjects.getRoomType(), updateObjects.roomNumber,
                    updateObjects.getCheckInDate(), updateObjects.getCheckOutDate())) {
                System.out.println("Warning: The existing room might not be available for the selected date and time.");

            }
        }

        // Create a CheckInOutTimeValidation object to validate the check-in and check-out times
        CheckInOutTimeValidation timeValidator = new CheckInOutTimeValidation();
        // Validate the check-in and check-out times
        if (!timeValidator.isValidTime(checkInTime) || !timeValidator.isValidTime(checkOutTime) ||
                !timeValidator.isCheckInBeforeCheckOut(checkInTime, checkOutTime, checkInDate.toString(), checkOutDate.toString())) {
            // Print an error message and return if the time validation fails
            System.out.println("\nInvalid check-in or check-out time.");
            return;
        }

        // Update the UpdateObjects instance with the new times
        updateObjects.setCheckInTime(checkInTime);
        updateObjects.setCheckOutTime(checkOutTime);

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
     * Displays the updated booking information in tabular column form.
     *
     * @param bookingInfo The booking information to be displayed.
     */
    private void displayBookingInfo(UpdateObjects bookingInfo) {
        // Update the booking information in the database
        boolean isUpdated = dbConnector.updateBookingDetails(updateObjects.getBookingID(), updateObjects);
        if (isUpdated) {
            System.out.println("\nBooking information updated successfully.");
        } else {
            System.out.println("Error: Failed to update booking information.");
            return;
        }

        // Retrieve and display the booking information from the database
        bookingInfo = dbConnector.getBookingDetails(updateObjects.getBookingID());
        if (bookingInfo != null) {
            System.out.println("\nUpdated Booking Information:");
            // Print horizontal line
            for (int i = 0; i < 130; i++) {
                System.out.print("-");
            }
            System.out.println();

            // Print header row
            System.out.printf("%-12s%-15s%-25s%-10s%-15s%-15s%-15s%-15s%-20s\n",
                    "Booking ID", "Username", "Email", "Room No", "Check-in Date", "Check-out Date",
                    "Check-in Time", "Check-out Time", "Room Type");
            // Print horizontal line
            for (int i = 0; i < 130; i++) {
                System.out.print("-");
            }
            System.out.println();

            // Print booking info row
            System.out.printf("%-12s%-15s%-25s%-10s%-15s%-15s%-15s%-15s%-20s\n",
                    bookingInfo.getBookingID(), bookingInfo.getUsername(), bookingInfo.getEmail(),
                    bookingInfo.getRoomNumber(), bookingInfo.getCheckInDate(), bookingInfo.getCheckOutDate(),
                    convertTo12HourFormat(bookingInfo.getCheckInTime()), convertTo12HourFormat(bookingInfo.getCheckOutTime()),
                    bookingInfo.getRoomType());
        } else {
            System.out.println("Error: Failed to retrieve booking information.");
        }
    }
    /**
     * Converts the given time to 12-hour format.
     *
     * @param time The time to be converted.
     * @return The time in 12-hour format.
     */
    private String convertTo12HourFormat(Time time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(time);
    }
}