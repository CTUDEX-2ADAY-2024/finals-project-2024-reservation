package main.java.com.ctu.reservationportal.reservation.infrastructure;
import main.java.com.ctu.reservationportal.reservation.model.RetrieveObjects;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * This class handles the retrieval of booking information.
 */
public class Retrieve {

    private Map<Integer, BookingInfo> bookings;

    /**
     * Initializes the RetrieveBooking object with an empty map of bookings.
     */
    public Retrieve() {
        this.bookings = new HashMap<>();
    }

    /**
     * Adds a booking to the map of bookings.
     *
     * @param bookingID the booking ID
     * @param booking   the booking information
     */
    public void addBooking(int bookingID, BookingInfo booking) {
        bookings.put(bookingID, booking);
    }

    /**
     * Retrieves a booking from the map of bookings.
     *
     * @param bookingID the booking ID
     * @return the booking information if found, null otherwise
     */
    public BookingInfo getBooking(int bookingID) {
        return bookings.get(bookingID);
    }

    /**
     * The main method to search and retrieve a booking.
     */
    public static void SearchNRetrieveSystem() {
        try (Scanner scanner = new Scanner(System.in)) {
            // Taking booking ID from user input
            System.out.print("Enter booking ID: ");
            int bookingID = scanner.nextInt();

            Retrieve bookingRequest = new Retrieve();
            BookingInfo booking = bookingRequest.getBooking(bookingID);
            if (booking != null) {
                // Display the booking information if found
                System.out.println("Booking found:");
                System.out.println("Booking ID: " + bookingID);
                System.out.println("Date: " + booking.getDate());
                System.out.println("Time: " + booking.getTime());
                System.out.println("Room Information: " + booking.getRoomInformation());
            } else {
                // Display a message if the booking is not found
                System.out.println("Booking not found for ID: " + bookingID);
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

            /**
             * Retrieves booking information from the database.
             *
             * @param bookingID the booking ID
             * @return the booking information if found, null otherwise
             */

        }

        /**
         * Inner class representing booking information.
         */
//    public static class BookingInfo {
//        private int bookingID;
//        private String date;
//        private String time;
//        private String roomInformation;
//
//        public BookingInfo(int bookingID, String date, String time, String roomInformation) {
//            this.bookingID = bookingID;
//            this.date = date;
//            this.time = time;
//            this.roomInformation = roomInformation;
//        }
//
//        public int getBookingID() {
//            return bookingID;
//        }
//
//        public String getDate() {
//            return date;
//        }
//
//        public String getTime() {
//            return time;
//        }
//
//        public String getRoomInformation() {
//            return roomInformation;
//        }
//    }
    }
}