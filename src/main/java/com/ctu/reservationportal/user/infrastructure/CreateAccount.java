package main.java.com.ctu.reservationportal.user.infrastructure;

import main.java.com.ctu.reservationportal.user.model.UserInfo;

import java.util.Scanner;

public class CreateAccount {

    /**
     * Prompts the user to create an account by entering a username and password.
     * Checks for password complexity and displays user-friendly examples.
     * Performs validation by checking password confirmation.
     *
     * @param scanner The Scanner object to read user input.
     */
    public static void createAccount(Scanner scanner) {
        String username, password, confirmPassword;

        // Outer do-while loop ensures the entire account creation process is repeated if needed
        do {
            System.out.println("Create an Account");

            // Prompt the user for username
            System.out.print("Username: ");
            username = scanner.nextLine();
            System.out.println("Username: " + username);

            // Inner do-while loop ensures the password and its confirmation are validated
            do {
                System.out.println("Password must be 8-20 characters long, contain uppercase, lowercase, digits, and special characters.");
                System.out.print("Password: ");
                password = scanner.nextLine();

                System.out.print("Confirm Password: ");
                confirmPassword = scanner.nextLine();

                // Check if passwords match
                if (!password.equals(confirmPassword)) {
                    System.out.println("Passwords do not match. Please try again.");
                } else if (!Validators.isValidPassword(password)) { // Check if password meets complexity requirements
                    System.out.println("Password does not meet complexity requirements. Please try again.");
                }
            } while (!password.equals(confirmPassword) || !Validators.isValidPassword(password));

            System.out.println("Congrats! You can now freely reserve preferred room.");

            // Create an instance of UserInfo
            UserInfo userInfo = new UserInfo();

            // Call PreferRole.selectPreferRole() with the userInfo object
            PreferRole.selectPreferRole(userInfo);

            // Breaks out of the outer loop after successful account creation
            break;

        } while (true); // Outer loop continues until a valid account is created
    }
}
