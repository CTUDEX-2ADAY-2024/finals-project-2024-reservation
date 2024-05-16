// Package declaration for CheckInOutTimeValidation class
package main.java.com.ctu.reservationportal.reservation.abstraction;

// Import Time class from java.sql package
import java.sql.Time;
// Import LocalDate class from java.time package
import java.time.LocalDate;
// Import LocalTime class from java.time package
import java.time.LocalTime;
// Import DateTimeParseException class from java.time.format package
import java.time.format.DateTimeParseException;

/**
 * Class responsible for validating check-in and check-out times.
 */
public class CheckInOutTimeValidation {

    /**
     * Validates whether the provided Time object represents a valid time.
     *
     * @param time The Time object to be validated.
     * @return true if the time is valid, false otherwise.
     */
    public boolean isValidTime(Time time) {
        try {
            time.toLocalTime();
            return true; // Parsing successful, indicating valid time
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid time format.");
            return false; // Parsing failed, indicating invalid time
        }
    }

    /**
     * Checks if the check-in time is before the check-out time, taking both dates and times into account.
     *
     * @param checkInTime  The check-in time.
     * @param checkOutTime The check-out time.
     * @param checkInDate  The check-in date in the format "YYYY-MM-DD".
     * @param checkOutDate The check-out date in the format "YYYY-MM-DD".
     * @return true if check-in is before check-out, false otherwise.
     */
    public boolean isCheckInBeforeCheckOut(Time checkInTime, Time checkOutTime, String checkInDate, String checkOutDate) {
        LocalTime checkIn = checkInTime.toLocalTime();
        LocalTime checkOut = checkOutTime.toLocalTime();

        try {
            LocalDate checkInDateParsed = LocalDate.parse(checkInDate);
            LocalDate checkOutDateParsed = LocalDate.parse(checkOutDate);

            // Check-in date is before check-out date
            if (checkInDateParsed.isBefore(checkOutDateParsed)) {
                return true;
            } else if (checkInDateParsed.isEqual(checkOutDateParsed)) {
                // If check-in and check-out are on the same day, check-out must be later than check-in time
                if (checkOut.isAfter(checkIn)) {
                    return true;
                } else {
                    System.out.println("Error: Check-out time must be later than check-in time on the same day.");
                    return false;
                }
            } else {
                System.out.println("Error: Check-in date must be before check-out date.");
                return false; // Check-in date is after check-out date
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use the format YYYY-MM-DD.");
            return false;  // Parsing error occurred, indicating invalid dates
        }
    }
}
