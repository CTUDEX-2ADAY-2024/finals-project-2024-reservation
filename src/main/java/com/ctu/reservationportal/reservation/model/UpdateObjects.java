package main.java.com.ctu.reservationportal.reservation.model;

import main.java.com.ctu.reservationportal.reservation.infrastructure.Update;

/**
 * The type Update objects.
 */
public class UpdateObjects {
    private final Update updateBookingInfo;
    private String roomInformation;
    private String date;
    private String time;

    /**
     * Instantiates a new Update objects.
     *
     * @param updateBookingInfo the update booking
     */
    public UpdateObjects(Update updateBookingInfo) {
        this.updateBookingInfo = updateBookingInfo;
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
     * Sets room information.
     *
     * @param roomInformation the room information
     */
    public void setRoomInformation(String roomInformation) {
        this.roomInformation = roomInformation;
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
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Update booking details.
     */
    public void updateBookingDetails(String bookingID) {
        UpdateObjects.Booking booking = new UpdateObjects.Booking(roomInformation, date, time);
        updateBookingInfo.updateBookingInDB(bookingID, booking.getRoomInformation(), booking.getDate(), booking.getTime());
    }

    /**
     * The Booking class represents a booking with room information, date, and time.
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
         * Sets room information.
         *
         * @param roomInformation the room information
         */
        public void setRoomInformation(String roomInformation) {
            this.roomInformation = roomInformation;
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
         * Sets date.
         *
         * @param date the date
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         * Gets time.
         *
         * @return the time
         */
        public String getTime() {
            return time;
        }

        /**
         * Sets time.
         *
         * @param time the time
         */
        public void setTime(String time) {
            this.time = time;
        }
    }
}
