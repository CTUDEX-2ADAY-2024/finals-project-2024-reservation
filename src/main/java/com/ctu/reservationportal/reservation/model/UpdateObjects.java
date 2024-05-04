package main.java.com.ctu.reservationportal.reservation.model;
import main.java.com.ctu.reservationportal.reservation.infrastructure.UpdateBooking;
public class UpdateObjects {

    /**
     * The type Update objects.
     */

        private UpdateBooking updateBookingInfo;
        private String roomInformation;
        private String date;
        private String time;

        /**
         * Instantiates a new Update objects.
         *
         * @param updateBookingInfo the update booking
         */
        public UpdateObjects(UpdateBooking updateBookingInfo) {
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
        public void updateBookingDetails() {
            UpdateBooking.Booking booking = new UpdateBooking.Booking(roomInformation, date, time);
        }
    }

