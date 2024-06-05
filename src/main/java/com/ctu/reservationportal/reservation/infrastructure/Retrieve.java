    package main.java.com.ctu.reservationportal.reservation.infrastructure;
    
    import main.java.com.ctu.reservationportal.reservation.model.RetrieveObjects;
    import main.java.com.ctu.reservationportal.reservation.abstraction.BookingValidator;
    import static main.java.com.ctu.reservationportal.reservation.abstraction.UsernameValidator.usernameExistsInDatabase;
    import static main.java.com.ctu.reservationportal.reservation.abstraction.RetrieveFromDB.retrieveBookingFromDB;
    import java.util.List;
    import java.util.Scanner;
    import java.text.SimpleDateFormat;
    import java.sql.Time;
    
    /**
     * The Retrieve class handles searching and retrieving booking information from the reservation system.
     */
    public class Retrieve {
        private BookingValidator bookingValidator = new BookingValidator();
    
        /**
         * Prompts the user to search and retrieve booking information based on different criteria.
         */
        public void searchAndRetrieveBooking() {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    // Print a line of dashes for visual separation
                    for (int i = 0; i < 150; i++) {
                        System.out.print("-");
                    }
                    System.out.println();
                    System.out.println("Welcome to the Reservation System!");
                    System.out.println("Search Booking Request");
                    System.out.println("1. Search and Retrieve Booking");
                    System.out.println("2. Exit");
    
                    // Prompt the user for their choice
                    int choice = 0;
                    while (true) {
                        System.out.print("Enter your choice: ");
                        if (scanner.hasNextInt()) {
                            choice = scanner.nextInt();
                            if (choice == 1 || choice == 2) {
                                break;
                            } else {
                                System.out.println("Invalid choice. Please enter 1 or 2.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.next(); // Clear the scanner buffer
                        }
                    }
    
                    // Switch statement to handle different user choices
                    switch (choice) {
                        case 1:
                            System.out.println("Select filter option:");
                            System.out.println("1. Booking ID");
                            System.out.println("2. Room");
                            System.out.println("3. Username");
    
                            // Prompt the user for the filter choice
                            int filterChoice = 0;
                            while (true) {
                                System.out.print("Select filter option: ");
                                if (scanner.hasNextInt()) {
                                    filterChoice = scanner.nextInt();
                                    if (filterChoice >= 1 && filterChoice <= 3) {
                                        break;
                                    } else {
                                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                                    }
                                } else {
                                    System.out.println("Invalid input. Please enter a number.");
                                    scanner.next(); // Clear the scanner buffer
                                }
                            }
                            // Switch statement to handle different filter options chosen by the user
                            switch (filterChoice) {
                                // Case 1: Filter by booking ID
                                case 1:
                                    int bookingID = 0;
                                    boolean validInput = false;
                                    while (!validInput) {
                                        System.out.print("Enter booking ID: ");
                                        if (scanner.hasNextInt()) {
                                            bookingID = scanner.nextInt();
                                            // Check if the booking ID exists in the database
                                            if (bookingValidator.isValidBookingID(bookingID)) {
                                                validInput = true;
                                            } else {
                                                System.out.println("Invalid booking ID. Please enter a valid booking ID.");
                                            }
                                        } else {
                                            System.out.println("Invalid input. Please enter a valid number.");
                                            scanner.next(); // Clear the scanner buffer
                                        }
                                    }
                                    // Retrieve booking by ID and display booking info
                                    RetrieveObjects retrieveByID = retrieveBookingFromDB(bookingID, null, null).getFirst();
                                    printHeader();
                                    displayBookingInfo(retrieveByID);
                                    break;
    
                                // Case 2: Filter by room type
                                // Case 2: Filter by room type
                                case 2:
                                    boolean validRoomType = false;
                                    String roomType = null;
                                    while (!validRoomType) {
                                        System.out.println("\nList of Room Types:\n 1. Classroom\n 2. CompLaboratory\n 3. Library\n 4. SmartRoom\n");
                                        System.out.print("Enter room type number:");
                                        int roomNumber = 0;
                                        if (scanner.hasNextInt()) {
                                            roomNumber = scanner.nextInt();
                                            switch (roomNumber) {
                                                case 1:
                                                    roomType = "Classroom";
                                                    validRoomType = true;
                                                    break;
                                                case 2:
                                                    roomType = "CompLaboratory";
                                                    validRoomType = true;
                                                    break;
                                                case 3:
                                                    roomType = "Library";
                                                    validRoomType = true;
                                                    break;
                                                case 4:
                                                    roomType = "SmartRoom";
                                                    validRoomType = true;
                                                    break;
                                                default:
                                                    System.out.println("Invalid room number. Please enter a number between 1 and 4.");
                                                    break;
                                            }
                                        } else {
                                            System.out.println("Invalid input. Please enter a valid number.");
                                            scanner.next(); // Clear the scanner buffer
                                        }
                                    }
                                    if (roomType != null) {
                                        printHeader();
                                        retrieveByRoom(roomType);
                                    }
                                    break;
    
                                // Case 3: Filter by username
                                case 3:
                                    String username = "";
                                    while (true) {
                                        System.out.print("Enter username: ");
                                        username = scanner.next();
                                        if (!usernameExistsInDatabase(username)) {
                                            System.out.println("Username does not exist. Please enter a valid username.");
                                        } else {
                                            break;
                                        }
                                    }
                                    // Retrieve bookings by username and display them
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
                    break;
                }
            }
        }
    
        /**
         * Retrieves booking information based on room type and displays it.
         *
         * @param roomType The type of room to search for bookings.
         */
        private void retrieveByRoom(String roomType) {
            List<RetrieveObjects> bookingInfoList = retrieveBookingFromDB(0, roomType, null);
    
            // Check if bookings were found for the room type
            if (!bookingInfoList.isEmpty()) {
                for (RetrieveObjects bookingInfo : bookingInfoList) {
                    // Display each booking's information
                    displayBookingInfo(bookingInfo);
                }
            } else {
                System.out.println("No bookings found for room '" + roomType + "'.");
            }
        }
    
        /**
         * Retrieves booking information based on username and displays it.
         *
         * @param username The username to search for bookings.
         */
        private void retrieveByUsername(String username) {
            List<RetrieveObjects> bookingInfoList = retrieveBookingFromDB(0, null, username);
    
            // Check if bookings were found for the username
            if (!bookingInfoList.isEmpty()) {
                for (RetrieveObjects bookingInfo : bookingInfoList) {
                    // Display each booking's information
                    displayBookingInfo(bookingInfo);
                }
            } else {
                System.out.println("No bookings found for username '" + username + "'.");
            }
        }
    
        /**
         * Displays the booking information.
         *
         * @param bookingInfo The booking information to display.
         */
        private void displayBookingInfo(RetrieveObjects bookingInfo) {
            if (bookingInfo != null) {
                String checkInTime12Hour = convertTo12HourFormat(bookingInfo.getCheckInTime());
                String checkOutTime12Hour = convertTo12HourFormat(bookingInfo.getCheckOutTime());
    
                // Print booking information in a formatted table
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
    
        /**
         * Prints the header for displaying booking information.
         * The header includes column names and a line separator.
         */
        private void printHeader() {
            // Print message indicating that bookings are found
            System.out.println("\nBooking found:\n");
    
            // Print a line separator
            for (int i = 0; i < 150; i++) {
                System.out.print("-");
            }
            System.out.println();
    
            // Print the column headers with specific formatting
            System.out.printf("%-12s%-15s%-25s%-15s%-15s%-15s%-15s%-20s%-10s\n",
                    "Booking ID", "Username", "Email",
                    "Check-in Date", "Check-out Date",
                    "Check-in Time", "Check-out Time", "Room Type", "Room No");
    
            // Print a line separator
            for (int i = 0; i < 150; i++) {
                System.out.print("-");
            }
            System.out.println();
        }
    
        /**
         * Converts a given Time object to a 12-hour format time string.
         *
         * @param time The Time object to be converted.
         * @return A string representing the time in 12-hour format (e.g., "10:30 AM").
         */
        private String convertTo12HourFormat(Time time) {
            // Check if the time object is not null
            if (time != null) {
                // Create a SimpleDateFormat object for formatting time to 12-hour format
                SimpleDateFormat sdf12Hour = new SimpleDateFormat("hh:mm a");
                // Format the given Time object using the 12-hour format pattern and return the result
                return sdf12Hour.format(time);
            } else {
                // If the time object is null, return an empty string or any appropriate default value
                return "N/A";
            }
        }
    
    }
    
