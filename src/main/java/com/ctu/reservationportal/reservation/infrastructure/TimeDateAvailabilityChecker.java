package main.java.com.ctu.reservationportal.reservation.infrastructure;
import main.java.com.ctu.reservationportal.reservation.infrastructure.CreateBooking;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Time date availability checker.
 */
public class TimeDateAvailabilityChecker {

    /**
     * The type Room availability checker.
     */
    public class RoomAvailabilityChecker {
        private static final Map<String, Map<LocalDateTime, Boolean>> roomAvailability = new HashMap<>();

        /**
         * Initialize room availability.
         */
// Initialize room availability
        public static void initializeRoomAvailability() {
            // Initialize room availability for Room A, Room B, and Room C
            roomAvailability.put("Room A", new HashMap<>());
            roomAvailability.put("Room B", new HashMap<>());
            roomAvailability.put("Room C", new HashMap<>());
        }

        /**
         * Is room available boolean.
         *
         * @param room     the room
         * @param dateTime the date time
         * @return the boolean
         */
// Method to check if the room is available at the specified date and time
        public static boolean isRoomAvailable(String room, LocalDateTime dateTime) {
            Map<LocalDateTime, Boolean> roomSchedule = roomAvailability.get(room);
            return roomSchedule.getOrDefault(dateTime, true); // If not found, consider it available
        }

        /**
         * Update room availability.
         *
         * @param room        the room
         * @param dateTime    the date time
         * @param isAvailable the is available
         */
// Method to update room availability
        public static void updateRoomAvailability(String room, LocalDateTime dateTime, boolean isAvailable) {
            Map<LocalDateTime, Boolean> roomSchedule = roomAvailability.get(room);
            roomSchedule.put(dateTime, isAvailable);
        }
    }
}
