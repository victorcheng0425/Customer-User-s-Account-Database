package ui;

import model.Customer;
import model.CustomerDataBase;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MainPageGUI extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 760;
    private JLabel statusLabel1;
    private JLabel statusLabel2;
    private NorthGUI northgui;
    //private JPanel lightPanel;
    protected static JsonWriter jsonWriter;
    protected static JsonReader jsonReader;
    protected static CustomerDataBase database;
    protected static Customer currentUser;
    protected static final String JSON_STORE = "./data/customerDataBase.json";
    protected static List<String> input;

    public MainPageGUI() {
        super("OnlineStore");
        initalizeJsonAndDB();

        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setupNorthGui();
//        createContent();
        JButton button1 = new JButton("Right");
        add(button1, BorderLayout.LINE_END);
        JButton button2 = new JButton("Left");
        add(button2, BorderLayout.LINE_START);
        setVisible(true);
    }

    private void initalizeJsonAndDB() {
        database = new CustomerDataBase();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setUpDataBase();

    }

    private void setupNorthGui() {
        northgui = new NorthGUI(this);
        add(northgui, BorderLayout.PAGE_START);
    }

//    private void createContent() {
//
//    }

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

    //EFFECTS: Check user password from input, return true if it matches to the database, return false otherwise.
    private static boolean loginCheckUserPassword(Customer check) {
        if (check.verifyPassword(input.get(1))) {
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: login to user account if username exist and the password that user provided is correct.
    protected static boolean login(List<String> temp) {
        input = temp;
        //System.out.println(input.get(0));
        if (!database.containUserName(input.get(0))) {
            return false;
        } else {
            Customer tempAccess = database.findObject(input.get(0));
            if (loginCheckUserPassword(tempAccess)) {
                currentUser = tempAccess;
                return true;
            } else {
                return false;
            }
        }
    }

    // EFFECTS: saves the workroom to file
    protected static String saveDataBase() {
        try {
            jsonWriter.open();
            jsonWriter.write(database);
            jsonWriter.close();
            return ("Saved " + database.getDataBaseName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            return ("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads database from file
    protected static String loadDataBase() {
        try {
            database = jsonReader.read();
            return ("Loaded " + database.getDataBaseName() + " from " + JSON_STORE);
        } catch (IOException e) {
            return ("Unable to read from file: " + JSON_STORE);
        }
    }

    //REQUIRES: Valid input (User details/info)
    //MODIFIES: this
    //EFFECTS: create user account if username does not exist, otherwise do nothing
    protected static boolean createAccount(List<String> list) {
        if (database.containUserName(list.get(0))) {
            return false;
        } else {
            Customer temp = new Customer(list.get(2), list.get(0), list.get(1),
                    list.get(3), Integer.parseInt(list.get(5)), list.get(4));
            database.addCustomer(temp);
            //System.out.println("Account created!\n");
            return true;
        }
    }

    //EFFECTS: play sound
    protected static void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}
