package main.java.com.ctu.reservationportal.reservation.infrastructure;

import main.java.com.ctu.reservationportal.reservation.dbservices.CreateTables;

import java.util.Scanner;

/**
 * The type Booking menu.
 */
public class BookingMenu {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[]args){
        CreateTables createTables = new CreateTables();

        Scanner input = new Scanner(System.in);


        System.out.print("Welcome to Reservation Management System");

        System.out.println(" \nBooking System Menu");
        System.out.println("1. Create Booking Request\n2. Search and Retrieve Booking Request\n3. Update Booking Request:");
        int choice = input.nextInt();
        switch (choice){

            case 1:
                //Call the Create Booking Request Class
                CreateBooking object1 = new CreateBooking();
                object1.InputReceiver();
                break;
            case 2:
                //Call the Search and Retrieve
                Retrieve object2 = new Retrieve();
                object2.SearchNRetrieveSystem();
                break;
            case 3:
                UpdateBooking object3 = new UpdateBooking();
                object3.UpdateBookingRequestSystem();
                break;
            default:
                System.out.println("Invalid Input, Please Try Again");
                return;
        }

    }








