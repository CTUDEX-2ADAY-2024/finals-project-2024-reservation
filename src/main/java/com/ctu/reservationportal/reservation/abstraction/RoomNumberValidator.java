package main.java.com.ctu.reservationportal.reservation.abstraction;

/**
 * Class responsible for validating room numbers and retrieving available room numbers from the database.
 */
public class RoomNumberValidator {

    /**
     * Validates if the given room number is valid for a Classroom.
     *
     * @param roomNumber The room number to validate.
     * @return True if the room number is valid for a Classroom, false otherwise.
     */
    public boolean isValidClassroom(String roomNumber) {
        return roomNumber.equals("101") || roomNumber.equals("102") || roomNumber.equals("103");
    }

    /**
     * Validates if the given room number is valid for a Computer Laboratory.
     *
     * @param roomNumber The room number to validate.
     * @return True if the room number is valid for a Computer Laboratory, false otherwise.
     */
    public boolean isValidCompLaboratory(String roomNumber) {
        return roomNumber.equals("201") || roomNumber.equals("202") || roomNumber.equals("203");
    }

    /**
     * Validates if the given room number is valid for a Library.
     *
     * @param roomNumber The room number to validate.
     * @return True if the room number is valid for a Library, false otherwise.
     */
    public boolean isValidLibrary(String roomNumber) {
        return roomNumber.equals("301") || roomNumber.equals("302") || roomNumber.equals("303");
    }

    /**
     * Validates if the given room number is valid for a Smart Room.
     *
     * @param roomNumber The room number to validate.
     * @return True if the room number is valid for a Smart Room, false otherwise.
     */
    public boolean isValidSmartRoom(String roomNumber) {
        return roomNumber.equals("401") || roomNumber.equals("402") || roomNumber.equals("403");
    }

}
