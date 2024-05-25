package main.java.com.ctu.reservationportal.user.model;

/**
 * Class representing user information.
 */
public class UserInfo {

    private String firstName; // User's first name
    private String middleName; // User's middle name
    private String lastName; // User's last name
    private String birthDate; // User's birthdate
    private String street; // User's street address
    private String barangay; // User's barangay (local administrative division)
    private String municipality; // User's municipality
    private String city; // User's city
    private int ZIPcode; // User's ZIP code
    private String Email; // User's email address
    private Long PhoneNumber; // User's phone number
    private String nationality; // User's nationality
    private String gender; // User's gender
    private String roleAtschool; // User's role at school
    private String idNumber; // User's  at id numbers
    private String userName;



    /**
     * Get the user's first name.
     *
     * @return The user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the user's first name.
     *
     * @param firstName The user's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the user's middle name.
     *
     * @return The user's middle name.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Set the user's middle name.
     *
     * @param middleName The user's middle name.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Get the user's last name.
     *
     * @return The user's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the user's last name.
     *
     * @param lastName The user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the user's birthdate.
     *
     * @return The user's birthdate.
     */
    public String getBirthdate() {
        return birthDate;
    }

    /**
     * Set the user's birthdate.
     *
     * @param birthDate The user's birthdate.
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Get the user's email address.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Set the user's email address.
     *
     * @param email The user's email address.
     */
    public void setEmail(String email) {
        this.Email = email;
    }

    /**
     * Get the user's phone number.
     *
     * @return The user's phone number.
     */
    public long getPhoneNumber() {
        return  PhoneNumber;
    }

    /**
     * Set the user's phone number.
     *
     * @param phoneNumber The user's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = Long.valueOf(phoneNumber);
    }

    /**
     * Get the user's street address.
     *
     * @return The user's street address.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set the user's street address.
     *
     * @param street The user's street address.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get the user's barangay.
     *
     * @return The user's barangay.
     */
    public String getBarangay() {
        return barangay;
    }

    /**
     * Set the user's barangay.
     *
     * @param barangay The user's barangay.
     */
    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    /**
     * Get the user's municipality.
     *
     * @return The user's municipality.
     */
    public String getMunicipality() {
        return municipality;
    }

    /**
     * Set the user's municipality.
     *
     * @param municipality The user's municipality.
     */
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    /**
     * Get the user's city.
     *
     * @return The user's city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the user's city.
     *
     * @param city The user's city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the user's ZIP code.
     *
     * @return The user's ZIP code.
     */
    public int getZIPcode() {
        return ZIPcode;
    }

    /**
     * Set the user's ZIP code.
     *
     * @param ziPcode The user's ZIP code.
     */
    public void setZIPcode(int ziPcode) {
        this.ZIPcode = ziPcode;
    }

    /**
     * Get the user's gender.
     *
     * @return The user's gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the user's gender.
     *
     * @param gender The user's gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the user's nationality.
     *
     * @return The user's nationality.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Set the user's nationality.
     *
     * @param nationality The user's nationality.
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Get the user's role at school.
     *
     * @return The user's role at school.
     */
    public String getRoleAtschool() {
        return roleAtschool;
    }

    /**
     * Set the user's role at school.
     *
     * @param roleAtschool The user's role at school.
     */
    public void setroleAtschool(String roleAtschool) {
        this.roleAtschool = roleAtschool;
    }

    /**
     * Dummy method to set the validity of an ID.
     *
     * @param adminId The admin ID.
     */
    public void setisValidId(String adminId) {
    }

    /**
     * Dummy method to set the admin ID.
     *
     * @param adminId The admin ID.
     */
    public void setAdminID(String adminId) {
    }

    /**
     * Dummy method to display user information.
     */
    public void displayUserInfo() {
    }
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
    }

    public String getUserName() {
        return userName;
    }

    /**
     * Set the user's user name.
     *
     * @paramThe user's user name.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    }



