package main.java.com.ctu.reservationportal.reservation.model;
import java.sql.Time;

/**
 * Class representing input data for booking creation.
 */
public class CreateObjects {

    private String userName;
    private String email;
    private String date;
    private String timeInput;
    private String room;
    private int bookingId;


    /**
     * Constructs a CreateObjects instance with the provided information.
     *
     * @param userName the username
     * @param email    the email
     * @param date     the date
     * @param timeInput     the time
     * @param room     the room
     */
    public CreateObjects(String userName, String email, String date, String timeInput, String room) {
        this.userName = userName;
        this.email = email;
        this.date = date;
        this.timeInput = timeInput;
        this.room = room;
    }

    /**
     * Sets the username.
     *
     * @param userName the username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the time.
     *
     * @param timeInput the time
     */
    public void setTimeInput(String timeInput) {
        this.timeInput = timeInput;
    }

    /**
     * Sets the room.
     *
     * @param room the room
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the time.
     *
     * @return the time
     */
    public String getTimeInput() {
        return timeInput;
    }

    /**
     * Gets the room.
     *
     * @return the room
     */
    public String getRoom() {
        return room;
    }

    /**
     * Gets the booking ID.
     *
     * @return the booking ID
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID.
     *
     * @param bookingId the booking ID
     */
    public void setBookingId(int bookingId) {

        this.bookingId = bookingId;
    }

    public Time convertTimeInput() {
        String concatenated = timeInput.concat(":00");
        return Time.valueOf(concatenated);
    }
}
