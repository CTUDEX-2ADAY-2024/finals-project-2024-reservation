package main.java.com.ctu.reservationportal.user.infrastructure;

import main.java.com.ctu.reservationportal.user.model.UserInfo;

import java.util.Scanner;
public class CheckDetails {

    /*
     * @param userInfo The UserInfo object containing the user details.
     */
    public static void checkDetails(UserInfo userInfo) {
        Scanner scanner = new Scanner(System.in);
        boolean detailsConfirmed = false;

        do {
            // Displaying the user details for confirmation
            System.out.println("** Sorry you are not allowed in this portal**");
            System.out.println("** Checking Details **");
            System.out.println("Name: " + userInfo.getFirstName() + " " + userInfo.getMiddleName() + " " + userInfo.getLastName());
            System.out.println("Birthdate: " + userInfo.getBirthdate());
            System.out.println("Email: " + userInfo.getEmail());
            System.out.println("HOME ADDRESS DETAILS");
            System.out.println("Home Address: " + userInfo.getStreet() + ", " + userInfo.getBarangay() + ", "
                    + userInfo.getMunicipality() + ", " + userInfo.getCity());
            System.out.println("ZIP code: " + userInfo.getZIPcode());
            System.out.println("");
            System.out.println("USER OTHER DETAILS");
            System.out.println("Nationality: " + userInfo.getNationality());
            System.out.println("Gender: " + userInfo.getGender());

            // Prompt user for confirmation after checking
            System.out.println("Are the details correct?");
            System.out.println("1: Edit Details");
            System.out.println("2: Proceed to Registration Code");
            System.out.println("Please enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 2) {
                // Proceed to generate registration code
                System.out.println("Details confirmed. Proceeding to Registration Code.");
                RegistrationCode generatedCode = new RegistrationCode();
                generatedCode.displayRegistrationCode();
                detailsConfirmed = true;

                // Proceed to create account
                CreateAccount.createAccount(scanner);

                // Redirect to the PreferRole clasS

                // Call PreferRole.selectPreferRole() with the userInfo object
                PreferRole.selectPreferRole(userInfo);

                // Break out of the loop after displaying the registration code
                break;
            } else if (choice == 1) {
                // Edit user information if choice is 1
                UserInfoEditor editor = new UserInfoEditor(userInfo);
                editor.editUserInfo();
            } else {
                System.out.println("Invalid choice.");
            }
        } while (!detailsConfirmed); // Repeat the process until details are confirmed
    }
}