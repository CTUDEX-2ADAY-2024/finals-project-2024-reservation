package main.java.com.ctu.reservationportal.reservation.model;

import java.sql.Time;
import java.sql.Date;

/**
 * The type Update objects.
 */
public class UpdateObjects {

    private String username;
    private String email;
    private String roomType;
    private Time checkInTime;
    private Time checkOutTime;
    private Date checkInDate;
    private Date checkOutDate;
    private int bookingID;

    public String roomNumber;

    public UpdateObjects() {
        this.bookingID = bookingID;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
        this.username = username;
        this.email = email;
        this.roomType =roomType;
    }

    /**
     * Instantiates a new Update objects.
     *
     * @param updateRecords the update records service
     */

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets room type.
     *
     * @return the room type
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Sets room type.
     *
     * @param roomType the room type
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
    }

    /**
     * Gets check-in time.
     *
     * @return the check-in time
     */
    public Time getCheckInTime() {
        return checkInTime;
    }

    /**
     * Sets check-in time.
     *
     * @param checkInTime the check-in time
     */
    public void setCheckInTime(Time checkInTime) {
        this.checkInTime = checkInTime;
    }

    /**
     * Gets check-out time.
     *
     * @return the check-out time
     */
    public Time getCheckOutTime() {
        return checkOutTime;
    }

    /**
     * Sets check-out time.
     *
     * @param checkOutTime the check-out time
     */
    public void setCheckOutTime(Time checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    /**
     * Gets check-in date.
     *
     * @return the check-in date
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * Sets check-in date.
     *
     * @param checkInDate the check-in date
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Gets check-out date.
     *
     * @return the check-out date
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Sets check-out date.
     *
     * @param checkOutDate the check-out date
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingId(int bookingId) {
        this.bookingID = bookingId;
    }
}
