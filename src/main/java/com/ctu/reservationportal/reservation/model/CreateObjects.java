package main.java.com.ctu.reservationportal.reservation.model;

import java.util.Random;
import java.sql.Date;
import java.sql.Time;
/**
 * The type Create objects.
 */
public class CreateObjects {
    /**
     * The type Input data.
     */
// Class to represent the input information
        private String userName;
        private String email;
        private Date date;
        private Time time;
        private String room;
        private int bookingID;

        /**
         * Instantiates a new Input data.
         *
         * @param userName the user name
         * @param email    the email
         * @param date     the date
         * @param time     the time
         * @param room     the room
         */
// Constructors
        public CreateObjects(String userName, String email, Date date, Time time, String room) {
            this.userName = userName;
            this.email = email;
            this.date = date;
            this.time = time;
            this.room = room;
            this.bookingID = generateBookingId();

        }

        /**
         * Sets user name.
         *
         * @param userName the user name
         */
//Setter Method
        public void setUserName(String userName) {

            this.userName = userName;
        }

        /**
         * Sets email.
         *
         * @param email the email
         */
        public void setEmail(String email) {

            this.email = email;
        }

        /**
         * Sets date.
         *
         * @param date the date
         */
        public void setDate(Date date) {

            this.date = date;
        }

        /**
         * Sets time.
         *
         * @param time the time
         */
        public void setTime(Time time) {

            this.time = time;
        }

        /**
         * Sets room.
         *
         * @param room the room
         */
        public void setRoom(String room) {

            this.room = room;
        }
        public void setBookingID (int bookingID){
            this.bookingID = bookingID;
        }


        //Getter Method

        /**
         * Gets user name.
         *
         * @return the user name
         */
        public String getUserName() {

            return userName;
        }

        /**
         * Gets email.
         *
         * @return the email
         */
        public String getEmail() {

            return email;
        }

        /**
         * Gets date.
         *
         * @return the date
         */
        public Date getDate() {

            return date;
        }

        /**
         * Gets time.
         *
         * @return the time
         */
        public Time getTime() {

            return time;
        }

        /**
         * Gets room.
         *
         * @return the room
         */
        public String getRoom() {

            return room;
        }
        public int getBookingID(){
            return bookingID;
        }

        public int generateBookingId(){
            Random random = new Random();
            return random.nextInt(1000000);

    }




}
