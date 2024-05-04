package main.java.com.ctu.reservationportal.reservation.infrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * The type Update booking.
 */
public class UpdateBooking{
        private Map<String, Booking> bookings;
        private String bookingID;

    /**
     * Instantiates a new Update booking.
     */
    public UpdateBooking() {
            this.bookings = new HashMap<>();
        }

    /**
     * Update booking request system.
     */
    public void UpdateBookingRequestSystem() {
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to the Reservation System!");
            System.out.println("Update Booking Request");
            System.out.println("1. Update Booking,\n 2. Exit");
            int choice = input.nextInt();

            if (choice == 1) {
                updateBooking();
            } else if (choice == 2) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice");
            }
        }

        private void updateBooking() {
            System.out.println("Enter booking ID to update:");
            Scanner scanner = new Scanner(System.in);
            String bookingID = scanner.next();

            if (bookingID.isEmpty()) {
                System.out.println("Booking ID cannot be empty");
                return;
            }


            if (!bookings.containsKey(bookingID)) {
                System.out.println("Invalid Booking ID");
                return;


            }

            Booking booking = bookings.get(bookingID);
            System.out.println("Booking Details:");
            System.out.println("Room Information: " + booking.getRoomInformation());
            System.out.println("Date: " + booking.getDate());
            System.out.println("Time: " + booking.getTime());

            System.out.println("Updating Details:");
            System.out.println("1. Update");
            System.out.println("2. Exit");

            int updateChoice = scanner.nextInt();

            if (updateChoice == 1) {
                updateBookingDetails(booking);
            } else if (updateChoice == 2) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice");
                return;
            }
        }

        private void updateBookingDetails(Booking booking) {
            // Implement updating booking details logic here
            System.out.println("Updated Booking Details:");
            System.out.println("Room Information: " + booking.getRoomInformation());
            System.out.println("Date: " + booking.getDate());
            System.out.println("Time: " + booking.getTime());
        }

    /**
     * The type Booking.
     */
    public static class Booking {
            private String roomInformation;
            private String date;
            private String time;

        /**
         * Instantiates a new Booking.
         *
         * @param roomInformation the room information
         * @param date            the date
         * @param time            the time
         */
        public Booking(String roomInformation, String date, String time) {
                this.roomInformation = roomInformation;
                this.date = date;
                this.time = time;
            }

        /**
         * Gets room information.
         *
         * @return the room information
         */
        public String getRoomInformation() {
                return roomInformation;
            }

        /**
         * Gets date.
         *
         * @return the date
         */
        public String getDate() {
                return date;
            }

        /**
         * Gets time.
         *
         * @return the time
         */
        public String getTime() {
                return time;
            }
        }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
            UpdateBooking system = new UpdateBooking();
            system.UpdateBookingRequestSystem();
        }
    }







