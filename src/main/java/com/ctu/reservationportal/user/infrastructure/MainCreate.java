package main.java.com.ctu.reservationportal.user.infrastructure;

import main.java.com.ctu.reservationportal.user.dbservices.InsertRecords;
import main.java.com.ctu.reservationportal.user.model.UserInfo;

import java.sql.SQLException;
import java.util.Scanner;

//Main class for user registration
public class MainCreate {
    /**
     * Prompts the user for input and validates it using a provided validator.
     *
     * @param scanner       Scanner object for input
     * @param promptMessage Message to display when prompting for input
     * @param validator     Validator for input validation
     * @return Validated user input
     */
    public static String promptForInput(Scanner scanner, String promptMessage, Validator validator) {
        String input;
        boolean isValid;
        do {
            System.out.print(promptMessage);
            input = scanner.nextLine();
            isValid = validator.isValid(input);
            if (!isValid) {
                System.out.println("Oops! Looks like there's a typo in there! " +
                        "Please input details correctly.");
            }
        } while (!isValid);
        return input;
    }

    //Interface for input validation.
    interface Validator {
        boolean isValid(String input);
    }

    //Main method for user registration.
    public static void main(String[] args) throws SQLException {
        // Initialization of Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // Create a new UserInfo object to store user information
        UserInfo userInfo = new UserInfo();
        // Print welcome message and instructions for user registration
        System.out.println("\n              WELCOME TO USER REGISTRATION!");
        System.out.println("Each field has specific guidelines.To ensure a smooth process.\n" +
                "Please review the information and enter details that match those guidelines\n");


        // Prompt for and validate user details
        // Each detail is prompted for using the promptForInput method with appropriate validators
        //In the promptForInput method, Validators::isValidName is passed as an argument to indicate that the input provided by the user should be validated using the isValidName method defined in the Validators class.
        String firstName = promptForInput(scanner, "Enter First name: ", Validators::isValidName);
        userInfo.setFirstName(firstName);

        String middleName = promptForInput(scanner, "Enter Middle name: ", Validators::isValidName);
        userInfo.setMiddleName(middleName);

        String lastName = promptForInput(scanner, "Enter Last name: ", Validators::isValidName);
        userInfo.setLastName(lastName);

        String birthdate = promptForInput(scanner, "Enter Birthdate (YYYY-MM-DD): ", Validators::isValidDate);
        userInfo.setBirthDate(birthdate);

        String email = promptForInput(scanner, "Enter Email: ", Validators::isValidEmail);
        userInfo.setEmail(email);

        String phoneNumber = promptForInput(scanner, "Enter Phone Number: ", Validators::isValidPhoneNumber);
        userInfo.setPhoneNumber(phoneNumber);

        String street = promptForInput(scanner, "Enter Street: ", Validators::isValidStreet);
        userInfo.setStreet(street);

        String barangay = promptForInput(scanner, "Enter Barangay: ", Validators::isValidLocation);
        userInfo.setBarangay(barangay);

        String municipality = promptForInput(scanner, "Enter Municipality: ", Validators::isValidLocation);
        userInfo.setMunicipality(municipality);

        String city = promptForInput(scanner, "Enter City: ", Validators::isValidLocation);
        userInfo.setCity(city);

        int zipcode = Integer.parseInt(promptForInput(scanner, "Enter ZIP Code: ", Validators::isValidZIPCode));
        userInfo.setZIPcode(zipcode);

        String nationality = promptForInput(scanner, "Enter Nationality: ", Validators::isValidName);
        userInfo.setNationality(nationality);

        String gender = promptForInput(scanner, "Enter Gender (M-Male|F-Female|N-Not to say): ", Validators::isValidGender);
        userInfo.setGender(gender);
        userInfo.setUserName("dabonrj");


        InsertRecords insertRecord = new InsertRecords();
        // Insert the user record into the database
        insertRecord.insertUserRecord(userInfo);

        // Display the details provided by the user during the registration process.
        System.out.println("\nDETAILS PROVIDED");
        System.out.println("\nUSER DETAILS");
        System.out.println("First name: " + userInfo.getFirstName());
        System.out.println("Middle name: " + userInfo.getMiddleName());
        System.out.println("Last name: " + userInfo.getLastName());
        System.out.println("Birthdate: " + userInfo.getBirthdate());
        System.out.println("Email: " + userInfo.getEmail());
        System.out.println("Phone Number: " + userInfo.getPhoneNumber());
        System.out.println("\nHOME ADDRESS DETAILS");
        System.out.println("Street: " + userInfo.getStreet());
        System.out.println("Barangay: " + userInfo.getBarangay());
        System.out.println("Municipality: " + userInfo.getMunicipality());
        System.out.println("City: " + userInfo.getCity());
        System.out.println("ZIP Code: " + userInfo.getZIPcode());

        System.out.println("\nUSER OTHER DETAILS");
        System.out.println("Nationality: " + userInfo.getNationality());
        System.out.println("Gender: " + userInfo.getGender());


        // Validation of role at school
        // Check the role at school and validate it
        String roleAtschool;
        do {
            System.out.print("\nEnter role at school (Teacher/Student/Staff): ");
            roleAtschool = scanner.nextLine();
            userInfo.setroleAtschool(roleAtschool);
            System.out.println("Role At School: " + roleAtschool);

            if (!roleAtschool.equalsIgnoreCase("Teacher") &&
                    !roleAtschool.equalsIgnoreCase("Student") &&
                    !roleAtschool.equalsIgnoreCase("Staff")) {
                System.out.println("Invalid role. Please enter Teacher, Student, or Staff.");
            }
        } while (!roleAtschool.equalsIgnoreCase("Teacher") &&
                !roleAtschool.equalsIgnoreCase("Student") &&
                !roleAtschool.equalsIgnoreCase("Staff"));
        // Check if the user is a teacher
        if (roleAtschool.equalsIgnoreCase("Teacher")) {
            System.out.println("\nWelcome to Teaching Details.");
            TeachingDetails teachingDetails = new TeachingDetails();
            teachingDetails.CollectTeachingDetails(scanner);
        } else {
            // Call Check user detail
            CheckDetails.checkDetails(userInfo);
            // Call Edit user info
            UserInfoEditor editUserInfo = new UserInfoEditor(userInfo);
            editUserInfo.editUserInfo();
            //Call create account
            CreateAccount.createAccount(scanner);
            //Call preferRole
            PreferRole.selectPreferRole(userInfo);



            // End the program after all actions are completed
            System.out.println("You have succesfully registered.");
            System.exit(0);
        }
    }
}
