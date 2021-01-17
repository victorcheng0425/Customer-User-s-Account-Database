package model;

import org.json.JSONObject;

//Customer Class contain user's personal info in the field
//This Class have several getter function to obtain user's personal info
//Several setting function to modify the personal info
//This Class contain Password object to process the password(Encryption and Decryption)
//Each account can only save exactly one visa/mastercard number
//JSON code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDem
public class Customer {

    private String name;
    private String address;
    private String cardNumber;
    private int age;
    private boolean masterCard;
    private boolean visaCard;
    private String userName;
    private Password password;
    private String phoneNumber;

    public Customer(String name, String username, String password, String address, int age, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.masterCard = false;
        this.visaCard = false;
        this.cardNumber = "None";
        this.userName = username;
        this.password = new Password(password);
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, String username, String password, String address, int age, String phoneNumber,
                    boolean masterCard, boolean visaCard, String cardNumber) {

        this(name, username, password, address, age, phoneNumber);
        this.masterCard = masterCard;
        this.visaCard = visaCard;
        this.cardNumber = cardNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    //REQUIRES: Valid phone number
    //MODIFIES: this
    //EFFECTS: Change the phone number
    public void changePhoneNumber(String newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }

    public String getUserName() {
        return this.userName;
    }

    //REQUIRES: Valid String that contain the password
    //MODIFIES: this
    //EFFECTS: Reset user's password
    public void customerResetPassword(String newpassword) {
        password.resetPassword(newpassword);
    }

    //REQUIRES: Valid String that contain the password
    //EFFECTS: Get user password from database, compare it to the parameter.
    // Return true if they are the same, false otherwise
    public boolean verifyPassword(String password) {
        String temp = this.password.getPassword();
        return temp.equals(password);
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    //REQUIRES: Valid String that contain the address
    //MODIFIES: this
    //EFFECTS: Reset/Change user's address
    public void changeAddress(String newAddress) {
        this.address = newAddress;
    }

    public int getAge() {
        return this.age;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    //EFFECTS: return 1 if user contain mastercard, 0 otherwise
    public boolean containMasterCard() {
        return masterCard;
    }

    //EFFECTS: return 1 if user contain visacard, 0 otherwise
    public boolean containVisaCard() {
        return visaCard;
    }

    //REQUIRES: number must be 16 digits
    //MODIFIES: this
    //EFFECTS: If mastercard/visa card exist in user account, return false
    // Otherwise Turn boolean variable mastercard to 1, and update the card number
    public boolean addMasterCard(String number) {
        if (masterCard || visaCard) {
            return false;
        }
        this.masterCard = true;
        this.cardNumber = number;
        return true;
    }

    //REQUIRES: number must be 16 digits
    //MODIFIES: this
    //EFFECTS: If mastercard/visa card exist in user account, return false
    // Otherwise Turn boolean variable visacard to 1, and update the card number
    public boolean addVisaCard(String number) {
        if (masterCard || visaCard) {
            return false;
        }
        this.visaCard = true;
        this.cardNumber = number;
        return true;
    }

    //MODIFIES: this
    //EFFECTS: Turn boolean variable master/visacard to false, set card number to zero
    public void removeCard() {
        visaCard = false;
        masterCard = false;
        cardNumber = "None";
    }

    //EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("address", address);
        json.put("cardNumber", cardNumber);
        json.put("age", age);
        json.put("masterCardBoolean", masterCard);
        json.put("visaCardBoolean", visaCard);
        json.put("username", userName);
        json.put("password", password.getPasswordEncrypted());
        json.put("phoneNumber", phoneNumber);
        return json;
    }

}

