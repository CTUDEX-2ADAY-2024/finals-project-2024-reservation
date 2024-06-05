package main.java.com.ctu.reservationportal.reservation.abstraction;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class responsible for displaying available time slots for booking in a selected room within a specified date range.
 */
public class AvailableTimeSlotsDisplay {

    private static final Time OPERATING_START_TIME = Time.valueOf("00:00:00");
    private static final Time OPERATING_END_TIME = Time.valueOf("23:59:59");

    /**
     * Displays the available time slots for the selected room within the specified date range.
     *
     * @param roomType The type of room selected by the user.
     */
    public void displayAvailableTimeSlots(String roomType, String roomNumber) {
        // Retrieve booked time slots for the selected room and date range
        Map<Time, Time> bookedTimeSlots = getBookedTimeSlots(roomType, roomNumber);

        // Calculate available time slo ts based on booked slots
        List<String> availableTimeSlots = calculateAvailableTimeSlots(bookedTimeSlots);

        // Display available time slots to the user
        System.out.println("\nAvailable Time Slots for " + roomType + " from room no. " + roomNumber + ":");
        if (availableTimeSlots.isEmpty()) {
            System.out.println("No available time slots for the selected room and date range.");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Available Time Slots");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            for (String timeSlot : availableTimeSlots) {
                System.out.println(timeSlot);
            }
        }
    }

    /**
     * Formats the given time in 12-hour format.
     *
     * @param time The time to format.
     * @return The formatted time in 12-hour format.
     */
    private String formatTimeIn12HourFormat(Time time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(time);
    }

    /**
     * Retrieves booked time slots for the selected room and date range from the database.
     *
     * @param roomType   The type of room selected by the user.
     * @param roomNumber
     * @return A map of booked time slots with start and end times.
     */
    private Map<Time, Time> getBookedTimeSlots(String roomType, String roomNumber) {
        Map<Time, Time> bookedTimeSlots = new HashMap<>();

        // JDBC connection parameters
        String url = "jdbc:mysql://127.0.0.1:3306/roomportaldb";
        String username = "root";
        String password = "mypassword";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // SQL query to retrieve booked time slots based on roomType and date range
            String sql = "SELECT checkInTime, checkOutTime FROM BOOKINGDETAILS WHERE roomType = ? AND roomNumber = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, roomType);
                statement.setString(2, roomNumber);
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Iterate through the result set and populate bookedTimeSlots map
                    while (resultSet.next()) {
                        Time startTime = resultSet.getTime("checkInTime");
                        Time endTime = resultSet.getTime("checkOutTime");
                        bookedTimeSlots.put(startTime, endTime);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions here
        }

        return bookedTimeSlots;
    }

    /**
     * Calculates the available time slots for booking based on the provided map of booked time slots.
     *
     * @param bookedTimeSlots A map containing the start and end times of already booked time slots.
     *                        The keys represent the start times, and the values represent the end times.
     * @return A list of strings representing the available time slots.
     */
    private List<String> calculateAvailableTimeSlots(Map<Time, Time> bookedTimeSlots) {
        // Initialize a list to store the available time slots
        List<String> availableTimeSlots = new ArrayList<>();

        // Initialize the current start time with the operating start time
        Time currentStartTime = OPERATING_START_TIME;

        // Sort booked time slots by start time
        // Create a new list to store the booked time slots and sort them by start time
        List<Map.Entry<Time, Time>> sortedBookedSlots = new ArrayList<>(bookedTimeSlots.entrySet());
        sortedBookedSlots.sort(Map.Entry.comparingByKey());

        // Iterate through the sorted booked time slots
        for (Map.Entry<Time, Time> entry : sortedBookedSlots) {
            // Retrieve the start time and end time of each booked slot
            Time bookedStartTime = entry.getKey();
            Time bookedEndTime = entry.getValue();


            // If there is a gap between current start time and booked start time, it's an available slot
            if (currentStartTime.before(bookedStartTime)) {
                availableTimeSlots.add(formatTimeIn12HourFormat(currentStartTime) + " - " + formatTimeIn12HourFormat(bookedStartTime));
            }

            // Move the current start time to the end of the booked slot
            currentStartTime = bookedEndTime.after(currentStartTime) ? bookedEndTime : currentStartTime;
        }

        // If there is a gap between the end of the last booked slot and the end of operating hours, it's an available slot
        if (currentStartTime.before(OPERATING_END_TIME)) {
            availableTimeSlots.add(formatTimeIn12HourFormat(currentStartTime) + " - " + formatTimeIn12HourFormat(OPERATING_END_TIME));
        }

        return availableTimeSlots;
    }

    /**
     * Displays the available time slots for the selected room within the specified date range.
     *
     * @param roomType The type of room selected by the user.
     */
    public void updateTimeSlot(String roomType, String roomNumber) {
        // Retrieve booked time slots for the selected room and date range
        Map<Time, Time> bookedTimeSlots = getBookedTimeSlots(roomType, roomNumber);

        // Calculate available time slo ts based on booked slots
        List<String> availableTimeSlots = calculateAvailableTimeSlots(bookedTimeSlots);

        // Display available time slots to the user
        System.out.println("\nAvailable Time Slots for " + roomType + " from room no. " + roomNumber + ":");
        if (availableTimeSlots.isEmpty()) {
            System.out.println("No available time slots for the selected room and date range.");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Available Time Slots");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            for (String timeSlot : availableTimeSlots) {
                System.out.println(timeSlot);
            }
        }
    }
}
