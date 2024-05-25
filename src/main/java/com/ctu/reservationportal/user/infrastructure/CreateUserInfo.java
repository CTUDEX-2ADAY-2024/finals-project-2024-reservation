package main.java.com.ctu.reservationportal.user.infrastructure;

import main.java.com.ctu.reservationportal.user.dbservices.InsertRecords;
import main.java.com.ctu.reservationportal.user.model.UserInfo;

import java.sql.SQLException;
import java.util.Scanner;

import static main.java.com.ctu.reservationportal.user.infrastructure.MainCreate.promptForInput;

public class CreateUserInfo {
    /**
     * Prompts the user for input and validates it using a provided validator.
     *
     * @param scanner       Scanner object for input
     * @param promptMessage Message to display when prompting for input
     * @param validator     Validator for input validation
     * @return Validated user input
     */
    public static String promptForInput(Scanner scanner, String promptMessage, CreateUserInfo.Validator validator) {
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

    private Scanner scanner;
    private UserInfo userRegistration;

    public CreateUserInfo() {
        this.scanner = new Scanner(System.in);
        userRegistration = new UserInfo();
    }

    public UserInfo registerUser() {

        //Main class for user registration
        /**
         * Prompts the user for input and validates it using a provided validator.
         *
         * @param scanner       Scanner object for input
         * @param promptMessage Message to display when prompting for input
         * @param validator     Validator for input validation
         * @return Validated user input
         */


        // Print welcome message and instructions for user registration
        System.out.println("\n              WELCOME TO USER REGISTRATION!");
        System.out.println("Each field has specific guidelines.To ensure a smooth process.\n" +
                "Please review the information and enter details that match those guidelines\n");


        // Prompt for and validate user details
        // Each detail is prompted for using the promptForInput method with appropriate validators
        //In the promptForInput method, Validators::isValidName is passed as an argument to indicate that the input provided by the user should be validated using the isValidName method defined in the Validators class.
        String firstName = promptForInput(scanner, "Enter First name: ", Validators::isValidName);
        userRegistration.setFirstName(firstName);

        String middleName = promptForInput(scanner, "Enter Middle name: ", Validators::isValidName);
        userRegistration.setMiddleName(middleName);

        String lastName = promptForInput(scanner, "Enter Last name: ", Validators::isValidName);
        userRegistration.setLastName(lastName);

        String birthdate = promptForInput(scanner, "Enter Birthdate (YYYY-MM-DD): ", Validators::isValidDate);
        userRegistration.setBirthDate(birthdate);

        String email = promptForInput(scanner, "Enter Email: ", Validators::isValidEmail);
        userRegistration.setEmail(email);

        String phoneNumber = promptForInput(scanner, "Enter Phone Number: ", Validators::isValidPhoneNumber);
        userRegistration.setPhoneNumber(phoneNumber);

        String street = promptForInput(scanner, "Enter Street: ", Validators::isValidStreet);
        userRegistration.setStreet(street);

        String barangay = promptForInput(scanner, "Enter Barangay: ", Validators::isValidLocation);
        userRegistration.setBarangay(barangay);

        String municipality = promptForInput(scanner, "Enter Municipality: ", Validators::isValidLocation);
        userRegistration.setMunicipality(municipality);

        String city = promptForInput(scanner, "Enter City: ", Validators::isValidLocation);
        userRegistration.setCity(city);

        int zipcode = Integer.parseInt(promptForInput(scanner, "Enter ZIP Code: ", Validators::isValidZIPCode));
        userRegistration.setZIPcode(zipcode);

        String nationality = promptForInput(scanner, "Enter Nationality: ", Validators::isValidName);
        userRegistration.setNationality(nationality);

        String gender = promptForInput(scanner, "Enter Gender (M-Male|F-Female|N-Not to say): ", Validators::isValidGender);
        userRegistration.setGender(gender);

        // Create a new UserInfo object to store the collected information
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(firstName);
        userInfo.setMiddleName(middleName);
        userInfo.setLastName(lastName);
        userInfo.setBirthDate(birthdate);
        userInfo.setEmail(email);
        userInfo.setPhoneNumber(phoneNumber);
        userInfo.setStreet(street);
        userInfo.setBarangay(barangay);
        userInfo.setMunicipality(municipality);
        userInfo.setCity(city);
        userInfo.setZIPcode(zipcode);
        userInfo.setNationality(nationality);
        userInfo.setGender(gender);
        userInfo.setroleAtschool(userInfo.getRoleAtschool());

        // Instantiate the InsertRecords class
        InsertRecords insertRecords = new InsertRecords();

        // Call the insertUserRecord method of InsertRecords and pass the UserInfo object
        insertRecords.insertUserRecord(userInfo);

        // Display the details provided by the user during the registration process.
        System.out.println("\nDETAILS PROVIDED");
        System.out.println("\nUSER DETAILS");
        System.out.println("First name: " + userRegistration.getFirstName());
        System.out.println("Middle name: " + userRegistration.getMiddleName());
        System.out.println("Last name: " + userRegistration.getLastName());
        System.out.println("Birthdate: " + userRegistration.getBirthdate());
        System.out.println("Email: " + userRegistration.getEmail());
        System.out.println("Phone Number: " + userRegistration.getPhoneNumber());
        System.out.println("\nHOME ADDRESS DETAILS");
        System.out.println("Street: " + userRegistration.getStreet());
        System.out.println("Barangay: " + userRegistration.getBarangay());
        System.out.println("Municipality: " + userRegistration.getMunicipality());
        System.out.println("City: " + userRegistration.getCity());
        System.out.println("ZIP Code: " + userRegistration.getZIPcode());

        System.out.println("\nUSER OTHER DETAILS");
        System.out.println("Nationality: " + userRegistration.getNationality());
        System.out.println("Gender: " + userRegistration.getGender());





        // Validation of role at school
        // Check the role at school and validate it
        String roleAtSchool;
        do {
            System.out.print("\nEnter role at school (Teacher/Student/Staff): ");
            roleAtSchool = scanner.nextLine();
            userRegistration.setroleAtschool(roleAtSchool);

            if (!roleAtSchool.equalsIgnoreCase("Teacher") &&
                    !roleAtSchool.equalsIgnoreCase("Student") &&
                    !roleAtSchool.equalsIgnoreCase("Staff")) {
                System.out.println("Invalid role. Please enter Teacher, Student, or Staff.");
            }
        } while (!roleAtSchool.equalsIgnoreCase("Teacher") &&
                !roleAtSchool.equalsIgnoreCase("Student") &&
                !roleAtSchool.equalsIgnoreCase("Staff"));
        // Check if the user is a teacher
        if (roleAtSchool.equalsIgnoreCase("Teacher")) {
            System.out.println("\nWelcome to Teaching Details.");
            TeachingDetails teachingDetails = new TeachingDetails();
            teachingDetails.CollectTeachingDetails(scanner);
        } else {
            // Call Check user detail
            CheckDetails.checkDetails(userRegistration);
            // Call Edit user info
            UserInfoEditor editUserInfo = new UserInfoEditor(userRegistration);
            editUserInfo.editUserInfo();
            //Call create account
            CreateAccount.createAccount(scanner);
            //Call preferRole
            PreferRole.selectPreferRole(userRegistration);
            // End the program after all actions are completed
            System.out.println("You have succesfully registered.");
            System.exit(0);


        }
        return this.userRegistration;
    }
}



