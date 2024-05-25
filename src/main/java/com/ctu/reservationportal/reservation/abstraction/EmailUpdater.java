package main.java.com.ctu.reservationportal.reservation.abstraction;

import main.java.com.ctu.reservationportal.reservation.abstraction.RoomNumberValidator;
import main.java.com.ctu.reservationportal.reservation.model.UpdateObjects;

import java.util.Scanner;

public class EmailUpdater {
    private Scanner scanner;
    private UpdateObjects updateObjects;

    public EmailUpdater(Scanner scanner, UpdateObjects updateObjects) {
        this.scanner = scanner;
        this.updateObjects = updateObjects;
    }

    public void update() {
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        updateObjects.setEmail(newEmail);
    }
}
