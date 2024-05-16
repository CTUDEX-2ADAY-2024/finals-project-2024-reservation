// Package declaration for the BookingMenu class.
package main.java.com.ctu.reservationportal.reservation.infrastructure;

// Importing the Scanner class from the java.util package for user input.
import java.util.Scanner;

/**
 * The main menu for the Reservation Management Portal.
 */
public class BookingMenu {

    /**
     * Main method for the booking menu.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Creating a new Scanner object for user input.
        Scanner input = new Scanner(System.in);

        // Printing a welcome message to the console.
        System.out.println("------------Welcome to Reservation Management System--------------");

        // Displaying the booking system menu options.
        System.out.println("\nBooking System Menu:");

        // Print horizontal line
        for (int i = 0; i < 130; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println("1. Create Booking Request\n2. Search and Retrieve Booking Request\n3. Update Booking Request");

        // Reading the user's choice from the menu.
        int choice = input.nextInt();

        // Switching based on the user's choice.
        switch (choice) {
            // If the user chooses option 1 (Create Booking Request):
            case 1:
                // Creating a new CreateBooking object.
                Create createbooking = new Create();

                // Calling the InputReceiver method of the createBooking object.
                createbooking.inputReceiver();
                break;

            // If the user chooses option 2 (Search and Retrieve Booking Request):
            case 2:
                // Creating a new Retrieve object.
                Retrieve retrieve = new Retrieve();

                // Calling the SearchNRetrieveBookingSystem method of the retrieve object.
                retrieve.searchAndRetrieveBooking();
                break;

            // If the user chooses option 3 (Update Booking Request):
            case 3:
                // Creating a new UpdateBooking object.
                Update updateBooking = new Update();

                // Calling the UpdateBookingRequestSystem method of the updateBooking object.
                updateBooking.UpdateBookingRequestSystem();
                break;

            // If the user enters an invalid choice:
            default:
                // Printing an error message.
                System.out.println("Invalid Input, Please Try Again");
        }
    }
}