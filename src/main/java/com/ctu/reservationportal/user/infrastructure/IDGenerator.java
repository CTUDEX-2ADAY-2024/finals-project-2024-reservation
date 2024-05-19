package main.java.com.ctu.reservationportal.user.infrastructure;
import java.util.Random;

public class IDGenerator {

    public static String generateUserID() {
        Random random = new Random();
        StringBuilder userID = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            userID.append(random.nextInt(10)); // Generate a random digit (0-9)
        }
        return userID.toString();
    }

    public static String generateAdminID() {
        Random random = new Random();
        StringBuilder adminID = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            adminID.append(random.nextInt(10)); // Generate a random digit (0-9)
        }
        return adminID.toString();
    }
}
