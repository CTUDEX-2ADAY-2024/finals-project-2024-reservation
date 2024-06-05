package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.abstraction.roomInfo.RoomType;
import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Class responsible for updating the room type and room number associated with a booking.
 */
public class RoomTypeUpdater {
    private Scanner scanner;
    private UpdateObjects updateObjects;

    /**
     * Constructs a new RoomTypeUpdater with the given scanner and updateObjects.
     *
     * @param scanner       The scanner to read user input.
     * @param updateObjects The UpdateObjects containing the booking information to be updated.
     */
    public RoomTypeUpdater(Scanner scanner, UpdateObjects updateObjects) {
        this.scanner = scanner;
        this.updateObjects = updateObjects;
    }

    /**
     * Updates the room type and room number associated with the booking.
     *
     * This method prompts the user to select a room type and enter a room number, validates the inputs,
     * checks room availability, updates the room type and room number in the booking details, and displays
     * the updated booking information.
     */
    public void update() {
        UpdateDBconnector dbConnector = new UpdateDBconnector();
        RoomNumberValidator roomNumberValidator = new RoomNumberValidator();

        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("\nAvailable Room Types:\n 1. Classroom\n 2. CompLaboratory\n 3. Library\n 4. SmartRoom\n");
            System.out.print("Enter the number corresponding to the Room Type you want to update: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please select a valid room type.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer choice.");
            }
        }

        String roomType = "";
        String roomNumber = "";
        validInput = false;

        while (!validInput) {
            switch (choice) {
                case 1:
                    roomType = "Classroom";
                    System.out.println("\nAvailable rooms:\n 101\n 102\n 103\n");
                    System.out.print("Enter new room number: ");
                    roomNumber = scanner.nextLine();
                    if (roomNumberValidator.isValidClassroom(roomNumber)) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid room number for Classroom. Please enter a valid room number.");
                    }
                    break;
                case 2:
                    roomType = "CompLaboratory";
                    System.out.println("\nAvailable rooms:\n 201\n 202\n 203\n");
                    System.out.print("Enter new room number: ");
                    roomNumber = scanner.nextLine();
                    if (roomNumberValidator.isValidCompLaboratory(roomNumber)) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid room number for CompLaboratory. Please enter a valid room number.");
                    }
                    break;
                case 3:
                    roomType = "Library";
                    System.out.println("\nAvailable rooms:\n 301\n 302\n 303\n");
                    System.out.print("Enter new room number: ");
                    roomNumber = scanner.nextLine();
                    if (roomNumberValidator.isValidLibrary(roomNumber)) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid room number for Library. Please enter a valid room number.");
                    }
                    break;
                case 4:
                    roomType = "SmartRoom";
                    System.out.println("\nAvailable rooms:\n 401\n 402\n 403\n");
                    System.out.print("Enter new room number: ");
                    roomNumber = scanner.nextLine();
                    if (roomNumberValidator.isValidSmartRoom(roomNumber)) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid room number for SmartRoom. Please enter a valid room number.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid room type.");
                    break;
            }
        }

        displayRoomInfo(roomType, roomNumber);

        updateObjects.setRoomType(roomType);
        updateObjects.setRoomNumber(roomNumber);

        UpdateObjects currentBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());
        if (currentBooking == null) {
            System.out.println("Booking ID not found.");
            return;
        }

        currentBooking.setRoomType(roomType);
        currentBooking.setRoomNumber(roomNumber);

        boolean isUpdated = dbConnector.updateBookingDetails(currentBooking.getBookingID(), currentBooking);
        if (isUpdated) {
            System.out.println("\nRoom type and room number updated successfully.");
            UpdateObjects updatedBooking = dbConnector.getBookingDetails(updateObjects.getBookingID());
            if (updatedBooking != null) {
                displayBookingInfo(updatedBooking);
            }
        } else {
            System.out.println("Failed to update room type and room number.");
        }
    }

    /**
     * Displays room information based on the room type and room number.
     *
     * @param roomType   The type of the room.
     * @param roomNumber The number of the room.
     */
    private void displayRoomInfo(String roomType, String roomNumber) {
        switch (roomType) {
            case "Classroom":
                System.out.println("\nCLASSROOM INFORMATION:");
                RoomType.Classroom classroom = new RoomType.Classroom(roomNumber, 40, true, "COT Building", "none", true, 40);
                classroom.displayRoomInfo();
                break;
            case "CompLaboratory":
                System.out.println("\nCOMPUTER LABORATORY INFORMATION:");
                RoomType.CompLaboratory compLaboratory = new RoomType.CompLaboratory(roomNumber, 25, true, "COT Building", "none", true, 25);
                compLaboratory.displayRoomInfo();
                break;
            case "Library":
                System.out.println("\nLIBRARY INFORMATION:");
                RoomType.Library library = new RoomType.Library(roomNumber, 50, true, "COT Building", "none", true, 50);
                library.displayRoomInfo();
                break;
            case "SmartRoom":
                System.out.println("\nSMART ROOM INFORMATION:");
                RoomType.Smartroom smartRoom = new RoomType.Smartroom(roomNumber, 50, true, "COT Building", "none", true, true, true);
                smartRoom.displayRoomInfo();
                break;
            default:
                System.out.println("Invalid room type.");
                break;
        }
    }

    /**
     * Displays updated booking information in a table format.
     *
     * @param bookingInfo The updated booking information.
     */
    private void displayBookingInfo(UpdateObjects bookingInfo) {
        for (int i = 0; i < 130; i++) {
            System.out.print("-");
        }
        System.out.println();

        System.out.printf("%-12s%-15s%-25s%-10s%-15s%-15s%-15s%-15s%-20s\n",
                "Booking ID", "Username", "Email", "Room No", "Check-in Date", "Check-out Date",
                "Check-in Time", "Check-out Time", "RoomType");

        for (int i = 0; i < 130; i++) {
            System.out.print("-");
        }
        System.out.println();

        System.out.printf("%-12s%-15s%-25s%-10s%-15s%-15s%-15s%-15s%-20s\n",
                bookingInfo.getBookingID(),
                bookingInfo.getUsername() != null ? bookingInfo.getUsername() : "not specified",
                bookingInfo.getEmail() != null ? bookingInfo.getEmail() : "not specified",
                bookingInfo.getRoomNumber() != null ? bookingInfo.getRoomNumber() : "not specified",
                bookingInfo.getCheckInDate() != null ? bookingInfo.getCheckInDate() : "not specified",
                bookingInfo.getCheckOutDate() != null ? bookingInfo.getCheckOutDate() : "not specified",
                bookingInfo.getCheckInTime() != null ? convertTo12HourFormat(bookingInfo.getCheckInTime()) : "not specified",
                bookingInfo.getCheckOutTime() != null ? convertTo12HourFormat(bookingInfo.getCheckOutTime()) : "not specified",
                bookingInfo.getRoomType() != null ? bookingInfo.getRoomType() : "not specified");
    }

    /**
     * Converts the given time to 12-hour format.
     *
     * @param time The time to be converted.
     * @return The time in 12-hour format.
     */
    private String convertTo12HourFormat(Time time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(time);
    }
}
