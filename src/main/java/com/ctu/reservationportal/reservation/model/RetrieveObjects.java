package main.java.com.ctu.reservationportal.reservation.model;
import main.java.com.ctu.reservationportal.reservation.infrastructure.Retrieve;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Retrieve objects.
 */
public class RetrieveObjects {
    private Retrieve retrieveInfo;
    private int bookingID;
    private int date;
    private int time;
    private String roomInformation;
    private Map<Integer, Retrieve.BookingInfo> bookings;

    /**
     * Instantiates a new Retrieve objects.
     *
     * @param bookingID       the booking id
     * @param date            the date
     * @param time            the time
     * @param roomInformation the room information
     */
    public RetrieveObjects(int bookingID, String date, String time, String roomInformation) {
        this.bookingID = bookingID;
        this.date = Integer.parseInt(date);
        this.time = Integer.parseInt(time);
        this.roomInformation = roomInformation;
        this.bookings = new HashMap<Integer, Retrieve.BookingInfo>();
    }

    /**
     * Instantiates a new Retrieve objects.
     *
     * @param retrieveInfo the retrieve info
     */
    public RetrieveObjects(Retrieve retrieveInfo) {
        this.retrieveInfo = retrieveInfo;
        this.bookings = new HashMap<Integer, Retrieve.BookingInfo>();
    }

    /**
     * Gets retrieve info.
     *
     * @return the retrieve info
     */
    public Retrieve getRetrieveInfo() {
        return retrieveInfo;
    }

    /**
     * Sets retrieve info.
     *
     * @param retrieveInfo the retrieve info
     */
    public void setRetrieveInfo(Retrieve retrieveInfo) {
        this.retrieveInfo = retrieveInfo;
    }

    /**
     * Gets booking id.
     *
     * @return the booking id
     */
    public int getBookingID() {
        return bookingID;
    }
    public void setBookingID(){
        this.bookingID = bookingID;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public int getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(int time) {
        this.time = time;
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
     * Gets room information.
     *
     * @return the room information
     */
    public String getRoomInformation() {
        return this.roomInformation;
    }

    /**
     * Gets bookings.
     *
     * @return the bookings
     */
    public Map<Integer, Retrieve.BookingInfo> getBookings() {
        return bookings;
    }

    /**
     * Add booking.
     *
     * @param bookingID the booking id
     * @param booking   the booking
     */
    public void addBooking(int bookingID, Retrieve.BookingInfo booking) {
        bookings.put(bookingID, booking);
    }
}