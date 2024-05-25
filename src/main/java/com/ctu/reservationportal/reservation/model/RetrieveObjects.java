package main.java.com.ctu.reservationportal.reservation.model;

import java.sql.Date;
import java.sql.Time;

/**
 * The RetrieveObjects class handles the retrieval of booking information from a database.
 */
public class RetrieveObjects extends UpdateObjects {
    private int bookingID;
    private Time checkInTime;
    private Time checkOutTime;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomNumber;
    private String roomType;
    private String username;
    private String email;

    /**
     * Constructs a new RetrieveObjects instance.
     */
    public RetrieveObjects(int bookingID, Time checkInTime, Time checkOutTime, Date checkInDate, Date checkOutDate, String roomNumber, String roomType, String username, String email) {
        this.bookingID = bookingID;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
