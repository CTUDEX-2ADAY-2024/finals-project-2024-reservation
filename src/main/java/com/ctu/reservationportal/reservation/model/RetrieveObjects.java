package main.java.com.ctu.reservationportal.reservation.model;

import java.sql.*;

/**
 * The RetrieveObjects class handles the retrieval of booking information from a database.
 */
public class RetrieveObjects {
    private Time checkInTime;
    private Time checkOutTime;
    private Date checkInDate;
    private Date checkOutDate;
    private int bookingID;
    private String roomInformation;
    private String username;
    private String email;

    /**
     * Constructs a new BookingInfo instance.
     */
    public RetrieveObjects(int retrievedBookingID, Time checkInTime, Time checkOutTime, Date checkInDate, Date checkOutDate, String roomInformation, String username, String email) {
        this.bookingID = retrievedBookingID;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomInformation = roomInformation;
        this.username = username;
        this.email = email;
    }

    public int getBookingID() {
        return bookingID;
    }

    public Time getCheckInTime() {
        return checkInTime;
    }

    public Time getCheckOutTime() {
        return checkOutTime;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String getRoomInformation() {
        return roomInformation;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
