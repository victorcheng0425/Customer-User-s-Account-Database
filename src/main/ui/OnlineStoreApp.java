package ui;

import model.Customer;
import model.CustomerDataBase;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

//Online Store Application user interface,
//This interface contain a main page called menu, and lots of subpage depends on user request.
//This App will interact with Customer/CustomerDataBase/Password Class to achieve user's goal
//The application will keep looping in one page unless user want to logout/quit
//DataBase is created by calling setup method,
//two temporary customer object will add to the database for testing purpose
//One of the testing account: username:vicche01, password: 123456
// Citation:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class OnlineStoreApp {

    private static final String JSON_STORE = "./data/customerDataBase.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final int AGECONSTRAINT = 16;
    private String exit = "-> Exit : 'Q'";
    private Scanner input;
    private CustomerDataBase database;
    private Customer currentUser;

    OnlineStoreApp() {
        input = new Scanner(System.in);
        database = new CustomerDataBase();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runOnlineStoreApp();
    }

    //MODIFIES: this
    //EFFECTS: keep looping the online store application until user quit
    //EFFECTS: Save Customer Database into file (JSON) before the application terminated.
    private void runOnlineStoreApp() {
        boolean running = true;
        String command;
        setUpDataBase();

        while (running) {
            menu();
            command = readInput();
            if (command.equals("q")) {
                running = false;
            }
            processCommandMainPage(command);
            currentUser = null;
        }
        System.out.println("See you soon! Thanks for shopping!\n");
        System.out.println("*********************");
        System.out.println("*********************");
    }

    // EFFECTS: displays menu of options to user
    private void menu() {
        System.out.println("Hi, Welcome to Fat Tree Online Store");
        System.out.println("Please select any command below.\n");
        System.out.println("-> Load data from the file: 'D'");
        System.out.println("-> Customer Login: 'L'.");
        System.out.println("-> Sign up: 'N'");
        System.out.println("-> Admin Login: 'A'(Not available)");
        System.out.println(exit);
    }

    //EFFECTS: read command from Main Page, and call certain function to process request
    private void processCommandMainPage(String command) {
        switch (command) {
            case "l":
                login();
                break;
            case "n":
                createAccount();
                break;
            case "d":
                loadAccountToFile();
                break;
            default:
                break;
        }
    }

    //REQUIRES: Valid input (User details/info)
    //MODIFIES: this
    //EFFECTS: create user account if username does not exist, otherwise do nothing
    private void createAccount() {
        String name;
        String username;
        String password;
        String address;
        int age;
        String phoneNumber;
        System.out.println("Please enter your username!");
        String read = readInput();
        if (database.containUserName(read)) {
            System.out.println("Username exist, please try again.");
        } else {
            username = read;
            System.out.println("Please enter your password, Name, address, PhoneNumber, age in order (Caution!)");
            password = readInputNoLowerCase();
            name = readInputLine();
            address = readInputLine();
            phoneNumber = readInput();
            age = input.nextInt();
            Customer temp = new Customer(name, username, password, address, age, phoneNumber);
            database.addCustomer(temp);
            System.out.println("Account created!\n");
            saveAccountToFile();
        }
    }

    //EFFECTS: Store user data to file if he/she permitted.
    private void saveAccountToFile() {
        System.out.println("\n**************************************");
        System.out.println("Your temporary account has created successfully!");
        System.out.println("Do you want to store the account info into the File/Database?");
        System.out.println("You must store it if you want to login next time.");
        System.out.println("->Yes: 'Y', No: 'Any Key'");
        String command = readInput();
        switch (command) {
            case "y":
                saveDataBase();
                break;
            default:
                break;
        }
    }

    //EFFECTS: load user data to file.
    private void loadAccountToFile() {
        System.out.println("\n**************************************");
        System.out.println("Do you want to load the account info from the file that you previously stored?");
        System.out.println("->Yes: 'Y', No: 'Any Key'");
        String command = readInput();
        switch (command) {
            case "y":
                loadDataBase();
                break;
            default:
                break;
        }
    }

    //EFFECTS: Check user password from input, return true if it matches to the database, return false otherwise.
    private boolean loginCheckUserPassword(Customer check) {
        System.out.println("Please enter your password!");
        String read = readInputNoLowerCase();
        if (check.verifyPassword(read)) {
            System.out.println("Login Successful!");
            System.out.println("Transferring you to shopping Mall right now!");
            System.out.println("------------------------------------------");
            System.out.println("------------------------------------------");
            System.out.println("------------------------------------------");
            return true;
        } else {
            System.out.println("Wrong Password!!!!!!!!");
            return false;
        }
    }

    //EFFECTS: login to user account if username exist and the password that user provided is correct.
    private void login() {
        boolean looping = true;
        while (looping) {
            System.out.println("Welcome to Login Portal");
            System.out.println("Please enter your username");
            String read = readInput();

            if (read.equals("q")) {
                looping = false;
            } else if (!database.containUserName(read)) {
                System.out.println("Username does not exist! Please try again or press 'Q' to exit!");
                System.out.println("New User can register a new account at the main page!\n");
            } else {
                Customer tempAccess = database.findObject(read);
                if (loginCheckUserPassword(tempAccess)) {
                    looping = false;
                    currentUser = tempAccess;
                    shoppingMainPage();
                } else {
                    System.out.println("Going back to Login Portal.\n");
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: set up the database in the beginning of the program
    private void setUpDataBase() {
        Customer c1 = new Customer("Victor1", "vicche01", "123456", "4792 Mars", 90,
                "2501901213");
        Customer c2 = new Customer("Victor2", "vicche02", "654321", "Addr2", 50,
                "2501212212");
        database.addCustomer(c1);
        database.addCustomer(c2);
    }

    //EFFECTS: Display the option in Shopping Main Page, read input from user,
    // keep looping until user quit/logout
    private void shoppingMainPage() {
        boolean looping = true;
        while (looping) {
            if (currentUser == null) {
                break;
            }
            System.out.println("Hello!  " + currentUser.getName() + "\nWelcome to the store!");
            System.out.println("Please select the following option: ");
            System.out.println("->Account Setting: 'A'");
            System.out.println("->Shopping: 'S' (Currently Unavailable!)");
            System.out.println("->Logout: 'Q' ");
            String read = readInput();
            switch (read) {
                case "a":
                    accountSetting();
                    break;
                case "q":
                    System.out.println("Logout Automatically!");
                    System.out.println("*********************");
                    looping = false;
                default:
                    break;
            }
        }
    }

    //EFFECTS: Read input(command), then call nextline to move the cursor to next line.
    // Finally return it as lowercase string
    private String readInput() {
        String read = input.next();
        input.nextLine();
        read = read.toLowerCase();
        return read;
    }

    //EFFECTS: Read input(command), then call nextline to move the cursor to next line.
    private String readInputNoLowerCase() {
        String read = input.next();
        input.nextLine();
        return read;
    }

    //EFFECTS: Read input(line) and return it as lowercase string
    private String readInputLine() {
        return input.nextLine();
    }

    //EFFECTS: Process the command in account setting, and call appropriate method
    private void processCommandAccountSetting(String command) {
        switch (command) {
            case "1":
                userNamePasswordSetting();
                break;
            case "2":
                addressSetting();
                break;
            case "3":
                phoneNumberSetting();
                break;
            case "4":
                creditCardSetting();
                break;
            case "5":
                removeUserAccount();
            default:
                break;
        }
    }

    //EFFECTS: Account Setting menu print the option for users to view/modify their account
    //         keep looping if user input if user does not quit
    //         otherwise the program will call processCommandAccountSetting
    private void accountSetting() {
        boolean looping = true;
        while (looping) {
            System.out.println("Welcome to the Account Setting!");
            System.out.println("Please select the following option: ");
            System.out.println("->Username and Password Setting: '1'");
            System.out.println("->Address Setting: '2'");
            System.out.println("->Phone Number Setting: '3'");
            System.out.println("->Credit Card Setting: '4'");
            System.out.println("->Delete Account! (Caution!): '5'");
            System.out.println(exit);
            String read = readInput();
            if (read.equals("q") || read.equals("5")) {
                looping = false;
            }
            processCommandAccountSetting(read);
            if (read.equals("5")) {
                currentUser = null;
            }
        }
    }

    //EFFECTS: verify user password, print correct result if the password is right, print wrong password otherwise
    private void verifyPW(String command) {
        if (currentUser.verifyPassword(command)) {
            System.out.println("Your Password is correct!\n");
        } else {
            System.out.println("Wrong Password!\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: Change user password
    private void changePw(String command) {
        currentUser.customerResetPassword(command);
        System.out.println("Password has changed!");
        System.out.println("*********************");
    }

    //EFFECTS: Process the command in username and password setting
    //Verify Password: '1', Change password: '2', Check Username: '3'
    //call verifyPW if token is 1, call changePw if token is 2, print current username if token is 3
    //do nothing otherwise
    private void processCommandUserAndPw(String command) {
        String read;
        switch (command) {
            case "1":
                System.out.println("Please Enter Your Password!");
                read = readInputNoLowerCase();
                verifyPW(read);
                break;
            case "2":
                System.out.println("Please Enter Your Password!");
                read = readInputNoLowerCase();
                changePw(read);
                break;
            case "3":
                System.out.println("Username: " + currentUser.getUserName() + "\n");
                break;
            default:
                break;
        }
        System.out.println("Going back to Username and Password Setting!");
        System.out.println("*********************************************");
    }

    //EFFECTS: Print the menu of username and password setting. Call processCommandUserAndPw to process the command.
    //Keep looping if user does not quit
    private void userNamePasswordSetting() {
        boolean looping = true;
        while (looping) {
            System.out.println("Welcome to the Password Setting!");
            System.out.println("Please select the following option: \n");
            System.out.println("->Verify Password: '1'");
            System.out.println("->Change password: '2'");
            System.out.println("->Check Username: '3'");
            System.out.println(exit);
            String read = readInput();
            if (read.equals("q")) {
                looping = false;
            }
            processCommandUserAndPw(read);
        }
    }


    //MODIFIES: this
    //EFFECTS: Change user address
    private void changeAddr(String command) {
        currentUser.changeAddress(command);
        System.out.println("Address has changed!");
        System.out.println("********************");
    }

    //EFFECTS: Process the command in username and password setting
    //Check Address: '1', Change Address: '2'
    //print address if token is 1, call changeAddr if 2, do nothing otherwise
    private void processCommandAddressSetting(String command) {
        String read;
        switch (command) {
            case "1":
                System.out.println("Your current address: " + currentUser.getAddress() + "\n");
                break;
            case "2":
                System.out.println("Please Enter Your New Address!");
                read = readInputLine();
                changeAddr(read);
                break;
            default:
                break;
        }
        System.out.println("Going back to Address Setting!");
        System.out.println("*********************************************");
    }

    //EFFECTS: Print the menu of address setting. Call processCommandAddressSetting to process the command.
    //Keep looping if user does not quit
    private void addressSetting() {
        boolean looping = true;
        while (looping) {
            System.out.println("->Check Address: '1'");
            System.out.println("->Change Address: '2'");
            System.out.println(exit);
            String read = readInput();
            if (read.equals("q")) {
                System.out.println("Going back to Account Setting!");
                looping = false;
            }
            processCommandAddressSetting(read);
        }

    }

    //MODIFIES: this
    //EFFECTS: Change user phone number
    private void changePhoneNumber(String command) {
        currentUser.changePhoneNumber(command);
        System.out.println("Phone Number has changed!");
        System.out.println("********************");
    }

    //EFFECTS: Process the command in username and password setting
    //Check Phone Number: '1', Change Phone Number: '2', print current phone number if token is 1,
    //call changePhoneNumber if 2, do nothing otherwise
    private void processCommandPhoneNumberSetting(String command) {
        String read;
        switch (command) {
            case "1":
                System.out.println("Your Current Phone Number: " + currentUser.getPhoneNumber() + "\n");
                break;
            case "2":
                System.out.println("Please Enter Your New Phone Number!");
                read = readInputLine();
                changePhoneNumber(read);
                break;
            default:
                break;
        }
        System.out.println("Going back to Phone Number Setting!");
        System.out.println("*********************************************");
    }

    //EFFECTS: Print the menu of phone number setting. Call processCommandPhoneNumberSetting to process the command.
    private void phoneNumberSetting() {
        boolean looping = true;
        while (looping) {
            System.out.println("->Check Phone Number: '1'");
            System.out.println("->Change Phone Number: '2'");
            System.out.println(exit);
            String read = readInput();
            if (read.equals("q")) {
                System.out.println("Going back to Account Setting!");
                looping = false;
            }
            processCommandPhoneNumberSetting(read);
        }
    }

    //EFFECTS: Check Customer Current Credit Card info
    private void checkCreditCard() {
        if (!currentUser.containMasterCard() && !currentUser.containVisaCard()) {
            System.out.println("No Credit Card found!");
            System.out.println("Please add one to your account. (More details on Credit Card Setting)");
            System.out.println("Note: you can save only one credit card info into our system");
        } else if (currentUser.containMasterCard()) {
            System.out.println("Your Master Card Number is: " + currentUser.getCardNumber() + "\n");
        } else {
            System.out.println("Your Visa Card Number is: " + currentUser.getCardNumber() + "\n");
        }
        System.out.println("*********************************************");
    }

    //MODIFIES: this
    //EFFECTS: If credit card exists, no action. If no credit card exist, add one visa/master card based on user input
    private void updateCreditCard(String number) {
        if (currentUser.containVisaCard() || currentUser.containMasterCard()) {
            System.out.println("You have one Credit Card added. Please remove it before adding the new one!");
        } else {
            System.out.println("If your card is VISA CARD---> '1'");
            System.out.println("If your card is MASTER CARD---> '2'");
            String read = readInput();
            if (read.equals("1")) {
                currentUser.addVisaCard(number);
                System.out.println("Added a new VISA CARD, card number is: " + currentUser.getCardNumber() + "\n");
            } else if (read.equals("2")) {
                currentUser.addMasterCard(number);
                System.out.println("Added a new MASTER CARD, card number is: " + currentUser.getCardNumber() + "\n");
            } else {
                System.out.println("Not a valid input!");
            }
        }
        System.out.println("*********************************************");
    }

    //MODIFIES: this
    //EFFECTS: Remove credit card from user account
    private void removeCreditCard() {
        currentUser.removeCard();
        System.out.println("Removed Credit Card");
        System.out.println("*********************************************");

    }

    //EFFECTS: Process the command in username and password setting
    //Check Card Number '1', Add Credit Card: '2', Remove Card: '3'
    //Call checkCreditCard if token is 1, call updateCreditCard if token is 2, call removeCreditCard if token is 3,
    // do nothing otherwise
    private void processCommandCreditCardSetting(String command) {
        String read;
        switch (command) {
            case "1":
                checkCreditCard();
                break;
            case "2":
                System.out.println("Please enter your Credit Card Number.");
                read = readInput();
                updateCreditCard(read);
                break;
            case "3":
                removeCreditCard();
            default:
                break;
        }
        System.out.println("Going back to Credit Card Setting!");
        System.out.println("*********************************************");
    }

    //EFFECTS: Print the menu of Credit Card setting. Call processCommandCreditCardSetting to process the command.
    //Keep looping until user quit.
    private void creditCardSetting() {
        boolean looping = true;
        while (looping) {
            System.out.println("->Check Card Number: '1'");
            System.out.println("->Add Credit Card: '2'");
            System.out.println("->Remove Card: '3'");
            System.out.println(exit);

            String read = readInput();
            if (read.equals("q")) {
                System.out.println("Going back to Account Setting!");
                looping = false;
            }
            processCommandCreditCardSetting(read);
        }
    }

    //MODIFIES: this
    //EFFECTS: Remove user account, (User request)
    private void removeUserAccount() {
        database.deleteCustomer(currentUser.getUserName());
        System.out.println("Account Removed! Thanks for using FatTree Online Store.\n");
        saveDataBase();
    }

    // EFFECTS: saves the workroom to file
    private void saveDataBase() {
        try {
            jsonWriter.open();
            jsonWriter.write(database);
            jsonWriter.close();
            System.out.println("Saved " + database.getDataBaseName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads database from file
    private void loadDataBase() {
        try {
            database = jsonReader.read();
            System.out.println("Loaded " + database.getDataBaseName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
