package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;
import main.java.com.ctu.reservationportal.reservation.abstraction.UpdateDBconnector;

import java.util.Scanner;
import java.util.List;

public class RoomTypeUpdater {
    private Scanner scanner;
    private UpdateObjects updateObjects;

    public RoomTypeUpdater(Scanner scanner, UpdateObjects updateObjects) {
        this.scanner = scanner;
        this.updateObjects = updateObjects;
    }

    public void update() {
        RoomNumberValidator roomNumberValidator = new RoomNumberValidator();
        System.out.println("\nList of Room Types:\n 1. Classroom\n 2. CompLaboratory\n 3. Library\n 4. SmartRoom\n");
        System.out.print("Enter new room type: ");
        String newRoomType = scanner.nextLine();

        // Display available room numbers
        List<String> availableRoomNumbers = roomNumberValidator.getAvailableRoomNumbers(newRoomType);
        if (availableRoomNumbers.isEmpty()) {
            System.out.println("No available rooms for the selected room type.");
            return;
        }
        System.out.println("Available Room Numbers: " + availableRoomNumbers);
        System.out.print("Enter new room number: ");
        String roomNumber = scanner.nextLine();

        if (roomNumberValidator.isValidRoomNumber(newRoomType, roomNumber)) {
            updateObjects.setRoomType(newRoomType);
            updateObjects.setRoomNumber(roomNumber);

            UpdateDBconnector dbConnector = new UpdateDBconnector();
            // Retrieve current booking details to ensure all fields are populated
            UpdateObjects currentBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());
            if (currentBooking == null) {
                System.out.println("Booking ID not found.");
                return;
            }

            // Update only the room type and room number
            currentBooking.setRoomType(newRoomType);
            currentBooking.setRoomNumber(roomNumber);

            boolean isUpdated = dbConnector.updateBookingDetails(currentBooking.getBookingID(), currentBooking);
            if (isUpdated) {
                System.out.println("Room type and number updated successfully.");
                UpdateObjects updatedBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());
                if (updatedBooking != null) {
                    displayBookingInfo(updatedBooking);
                }
            } else {
                System.out.println("Failed to update room type and number.");
            }
        } else {
            System.out.println("Invalid room number for the selected room type.");
        }
    }

    private void displayBookingInfo(UpdateObjects bookingInfo) {
        System.out.println("Updated Booking Information:");
        System.out.println("Username: " + bookingInfo.getUsername());
        System.out.println("Email: " + bookingInfo.getEmail());
        System.out.println("Room Type: " + bookingInfo.getRoomType());
        System.out.println("Room Number: " + bookingInfo.getRoomNumber());
        System.out.println("Check-in Date: " + bookingInfo.getCheckInDate());
        System.out.println("Check-out Date: " + bookingInfo.getCheckOutDate());
        System.out.println("Check-in Time: " + bookingInfo.getCheckInTime());
        System.out.println("Check-out Time: " + bookingInfo.getCheckOutTime());
    }
}
