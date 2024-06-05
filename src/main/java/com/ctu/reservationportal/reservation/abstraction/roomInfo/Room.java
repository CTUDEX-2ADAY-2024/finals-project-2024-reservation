
/**
 * Package declaration specifying the location of this project structure
 */
package main.java.com.ctu.reservationportal.reservation.abstraction.roomInfo;
/**
 *This code defines an abstract class named 'Room'.
 * An abstract class cannot be directly instantiated (you can't create object of this class directly).
 * It's meant to serve as a blueprint for subclasses that inherit methods.
 */
public abstract class Room {

    /**
     * Member variables (also called fields) to store information about a room.
     * -roomNumber: String to store the room identifier
     * -capacity: int to store the maximum number of occupants
     * -roomStatus: boolean to indicate if the room available (true) or occupied (false)
     * -buildingLocation: String to store the building where the room is located (consider moving this to subclasses if specific for each type)
     * -roomType: String to specify the type of room (e.g., Classroom, library, etc.)
     * -hasProjector: boolean to indicate if the room has a projector
     * These member variables are declared with 'protected' access modifier
     * This means they are accessible within the class itself, subclasses, and the package they are declared in
     */
    protected String roomNumber; // Encapsulation: Keep details hidden and access them through getters and setters
    protected int capacity;
    protected boolean roomStatus;
    protected String buildingLocation;
    protected String maintenanceNotes;
    protected String roomType;
    protected boolean hasProjector;

    // Constructor to initialize a 'Room' object
    // It takes several argument to set the initial values for the member variables
    public Room(String roomNumber, int capacity, boolean roomStatus, String buildingLocation, String maintenanceNotes, String roomType, boolean hasProjector) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.roomStatus= roomStatus;
        this.buildingLocation=buildingLocation;
        this.maintenanceNotes = maintenanceNotes;
        this.hasProjector = hasProjector;
        this.roomType= roomType;
    }

    // Abstract method for displaying room information
    // Subclasses inheriting from 'Room' must implement this method to provide specific about their room type
    // Since this is an abstract method, it has nobody here in the parent class
    public abstract void displayRoomInfo(); // Abstraction

    // Getters and setters for encapsulation
    //These methods follow the convention of 'get' for retrieving values and 'set' for updating values
    //They provide controlled access to the member variables following principles of encapsulation
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(boolean roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getBuildingLocation() {
        return buildingLocation;
    }

    public void setBuildingLocation(String buildingLocation) {
        this.buildingLocation = buildingLocation;
    }

    public String getMaintenanceNotes() {
        return maintenanceNotes;
    }

    public void setMaintenanceNotes(String maintenanceNotes) {
        this.maintenanceNotes = maintenanceNotes;
    }
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

}