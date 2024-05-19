package main.java.com.ctu.reservationportal.user.infrastructure;

import java.util.Random;
import java.util.Scanner;

public class RegistrationCode {

    private static final int CODE_LENGTH = 6;
    private static int attempts = 3; // Number of attempts allowed

    /**
     * Generates a random 6-digit registration code
     * @return a random 6-digit registration code
     */
    public static String generateCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        // Generates each digit of the registration code by appending a random digit (0-9) to the codeBuilder StringBuilder
        // The loop runs for CODE_LENGTH times.
        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            codeBuilder.append(digit);
        }

        // Returns the generated registration code as a string
        return codeBuilder.toString();
    }

    /**
     * Displays a registration code generated randomly.
     * Prompts the user to enter a registration code and checks if it matches the generated code.
     * Continuously loops until a valid registration code is entered.
     */
    public static void displayRegistrationCode() {
        Scanner scanner = new Scanner(System.in); // Initialize Scanner object for user input

        // Generate a registration code
        String code = generateCode();
        System.out.println("Registration Code :" + code); // Print the generated registration code

        boolean codeMatched = false; // Variable to track if the entered code matches the generated code

        // Loop to prompt for the registration code and check if it matches the generated code
        do {
            // Prompt the user to enter the registration code
            System.out.print("Enter Registration Code: ");
            String inputCode = scanner.nextLine();

            // Check if the entered code matches the generated code
            if (inputCode.equals(code)) {
                codeMatched = true; // Set codeMatched to true if the codes match
                System.out.println("Registration code is valid.");
                System.out.println("User Registration Successful!");
                System.out.println("You can now Create An Account");
                CreateAccount.createAccount(scanner);
            } else {
                // Inform the user that the entered code does not match and prompt for re-entry
                System.out.println("Registration code does not match. Please try again.");
            }
        } while (!codeMatched); // Continue looping until a valid registration code is entered

    }
    //main for the display of registration code
    public static void main(String[] args) {
        displayRegistrationCode();
    }
}
