package main.java.com.ctu.reservationportal.user.infrastructure;

import java.util.Scanner;

public class TeachingDetails {
    private int numberOfSubjects;
    private String[] subjectNames;

    // Constructor
    public TeachingDetails(int numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
        this.subjectNames = new String[numberOfSubjects];
    }

    public TeachingDetails() {
        // Default constructor
    }

    // Method to input subject names and display teaching details
    public void CollectTeachingDetails(Scanner scanner) {
        // Prompt for number of subjects
        System.out.print("Enter number of subjects: ");
        numberOfSubjects = Integer.parseInt(scanner.nextLine());

        // Initialize subjectNames array
        subjectNames = new String[numberOfSubjects];

        // Input subject names
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter subject name " + (i + 1) + ": ");
            subjectNames[i] = scanner.nextLine();
        }

        // Display teaching details
        System.out.println("\nTeaching details:");
        System.out.println("Number of subjects: " + numberOfSubjects);
        System.out.println("Subject names:");
        for (String subject : subjectNames) {
            System.out.println("- " + subject);
        }
        RegistrationCode.displayRegistrationCode();
    }
}
