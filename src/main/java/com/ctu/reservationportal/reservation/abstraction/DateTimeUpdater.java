package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateTimeUpdater {
    private Scanner scanner;
    private UpdateObjects updateObjects;

    public DateTimeUpdater(Scanner scanner, UpdateObjects updateObjects) {
        this.scanner = scanner;
        this.updateObjects = updateObjects;
    }

    public void updateDateTime() {
        updateDate();
        updateTime();
    }

    private void updateDate() {
        System.out.print("Enter new check-in date (YYYY-MM-DD): ");
        String newCheckInDateStr = scanner.nextLine();
        try {
            Date newCheckInDate = Date.valueOf(newCheckInDateStr);
            updateObjects.setCheckInDate(newCheckInDate);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid check-in date format. Please enter the date in YYYY-MM-DD format.");
        }
        if (updateObjects.getCheckInDate() != null) {
            System.out.print("Enter new check-out date (YYYY-MM-DD): ");
            String newCheckOutDateStr = scanner.nextLine();
            try {
                Date newCheckOutDate = Date.valueOf(newCheckOutDateStr);
                updateObjects.setCheckOutDate(newCheckOutDate);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid check-out date format. Please enter the date in YYYY-MM-DD format.");
            }
        }
    }

    private void updateTime() {
        System.out.print("Enter new check-in time (hh:mmAM/PM): ");
        String newCheckInTimeStr = scanner.nextLine();
        try {
            Time newCheckInTime = convertTo24HourFormat(newCheckInTimeStr);
            updateObjects.setCheckInTime(newCheckInTime);
        } catch (ParseException e) {
            System.out.println("Invalid check-in time format. Please enter the time in hh:mmAM/PM format.");
        }
        if (updateObjects.getCheckInTime() != null) {
            System.out.print("Enter new check-out time (hh:mmAM/PM): ");
            String newCheckOutTimeStr = scanner.nextLine();
            try {
                Time newCheckOutTime = convertTo24HourFormat(newCheckOutTimeStr);
                updateObjects.setCheckOutTime(newCheckOutTime);
            } catch (ParseException e) {
                System.out.println("Invalid check-out time format. Please enter the time in hh:mmAM/PM format.");
            }
        }
    }

    private Time convertTo24HourFormat(String time12Hour) throws ParseException {
        SimpleDateFormat sdf12Hour = new SimpleDateFormat("hh:mma");
        SimpleDateFormat sdf24Hour = new SimpleDateFormat("HH:mm:ss");
        return new Time(sdf24Hour.parse(sdf12Hour.format(sdf12Hour.parse(time12Hour))).getTime());
    }
}
