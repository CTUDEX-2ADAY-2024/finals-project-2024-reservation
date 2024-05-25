package main.java.com.ctu.reservationportal.reservation.infrastructure;

import main.java.com.ctu.reservationportal.reservation.model.RetrieveObjects;
import static main.java.com.ctu.reservationportal.reservation.abstraction.UsernameValidator.usernameExistsInDatabase;
import static main.java.com.ctu.reservationportal.reservation.abstraction.RetrieveFromDB.retrieveBookingFromDB;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.sql.Time;

public class Retrieve {

    public void searchAndRetrieveBooking() {
        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < 150; i++) {
                System.out.print("-");
            }
            System.out.println();
            System.out.println("Welcome to the Reservation System!");
            System.out.println("Search Booking Request");
            System.out.println("1. Search and Retrieve Booking");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Select filter option:");
                    System.out.println("1. Booking ID");
                    System.out.println("2. Room");
                    System.out.println("3. Username");

                    int filterChoice = scanner.nextInt();
                    switch (filterChoice) {
                        case 1:
                            int bookingID = 0;
                            boolean validInput = false;
                            while (!validInput) {
                                System.out.print("Enter booking ID: ");
                                if (scanner.hasNextInt()) {
                                    bookingID = scanner.nextInt();
                                    validInput = true;
                                } else {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    scanner.next();
                                }
                            }
                            RetrieveObjects retrieveByID = retrieveBookingFromDB(bookingID, null, null).getFirst();
                            printHeader();
                            displayBookingInfo(retrieveByID);
                            break;

                        case 2:
                            System.out.println("\nList of Room Types:\n 1. Classroom\n 2. CompLaboratory\n 3. Library\n 4. SmartRoom\n");
                            System.out.print("Enter room type number:");
                            int roomNumber = scanner.nextInt();
                            String roomType = switch (roomNumber) {
                                case 1 -> "Classroom";
                                case 2 -> "CompLaboratory";
                                case 3 -> "Library";
                                case 4 -> "SmartRoom";
                                default -> null;
                            };
                            if (roomType != null) {
                                printHeader();
                                retrieveByRoom(roomType);
                            } else {
                                System.out.println("Invalid room number.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter username: ");
                            String username = scanner.next();

                            if (!usernameExistsInDatabase(username)) {
                                System.out.println("Username does not exist. Please enter a valid username.");
                                return;
                            }

                            printHeader();
                            retrieveByUsername(username);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private void retrieveByRoom(String roomType) {
        List<RetrieveObjects> bookingInfoList = retrieveBookingFromDB(0, roomType, null);

        if (!bookingInfoList.isEmpty()) {
            for (RetrieveObjects bookingInfo : bookingInfoList) {
                displayBookingInfo(bookingInfo);
            }
        } else {
            System.out.println("No bookings found for room '" + roomType + "'.");
        }
    }

    private void retrieveByUsername(String username) {
        List<RetrieveObjects> bookingInfoList = retrieveBookingFromDB(0, null, username);

        if (!bookingInfoList.isEmpty()) {
            for (RetrieveObjects bookingInfo : bookingInfoList) {
                displayBookingInfo(bookingInfo);
            }
        } else {
            System.out.println("No bookings found for username '" + username + "'.");
        }
    }

    private void displayBookingInfo(RetrieveObjects bookingInfo) {
        if (bookingInfo != null) {
            String checkInTime12Hour = convertTo12HourFormat(bookingInfo.getCheckInTime());
            String checkOutTime12Hour = convertTo12HourFormat(bookingInfo.getCheckOutTime());

            System.out.printf("%-12d%-15s%-25s%-15s%-15s%-15s%-15s%-20s%-10s\n",
                    bookingInfo.getBookingID(),
                    bookingInfo.getUsername(),
                    bookingInfo.getEmail(),
                    bookingInfo.getCheckInDate(),
                    bookingInfo.getCheckOutDate(),
                    checkInTime12Hour,
                    checkOutTime12Hour,
                    bookingInfo.getRoomType(),
                    bookingInfo.getRoomNumber());
        } else {
            System.out.println("Booking not found.");
        }
    }

    private void printHeader() {
        System.out.println("\nBooking found:\n");
        for (int i = 0; i < 150; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("%-12s%-15s%-25s%-15s%-15s%-15s%-15s%-20s%-10s\n",
                "Booking ID", "Username", "Email",
                "Check-in Date", "Check-out Date",
                "Check-in Time", "Check-out Time", "Room Type", "Room No");
        for (int i = 0; i < 150; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private String convertTo12HourFormat(Time time) {
        SimpleDateFormat sdf12Hour = new SimpleDateFormat("hh:mm a");
        return sdf12Hour.format(time);
    }
}
