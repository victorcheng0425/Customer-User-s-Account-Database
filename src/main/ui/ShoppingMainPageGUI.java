package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShoppingMainPageGUI extends JPanel implements ActionListener {
    public static final int LAYOUTWIDTH = 700;
    public static final int LAYOUTHEIGHT = 500;
    private JLabel statusLabel1;
    private JLabel statusLabel2;
    private NorthGUI northgui;
    private static final String STATUS1 = "Please select the following option";
    private int index = 0;
    private String[] labels = {"Password: ", "Address: ", "PhoneNumber: "};
    private int numPairs = labels.length;
    private JTextField[] textFields = new JTextField[numPairs];
    private List<String> input;

    public ShoppingMainPageGUI(NorthGUI northgui) {
        this.northgui = northgui;
        input = new ArrayList<>();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, LAYOUTWIDTH, 10);
        setLayout(layout);
        setPreferredSize(new Dimension(LAYOUTWIDTH, LAYOUTHEIGHT));
        printIntroMessage();
        shoppingPageBasicButton();
        //mainpage.add(this, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: add print intro message
    private void printIntroMessage() {
        statusLabel1 = new JLabel("Hello!  " + MainPageGUI.currentUser.getName() + ". Welcome to the store!");
        //statusLabel1 = new JLabel("Hello!");
        statusLabel2 = new JLabel(STATUS1);
        add(statusLabel1);
        add(statusLabel2);
        index += 2;
    }

    //MODIFIES: this
    //EFFECTS: Add button to Signup page
    private void shoppingPageBasicButton() {
        JButton button1 = new JButton("Account Setting!");
        button1.setPreferredSize(new Dimension(250, 20));
        button1.setActionCommand("accountsetting");
        button1.addActionListener(this);
        add(button1);

        JButton button2 = new JButton("Shopping(Currently Unavailable!)!");
        button2.setPreferredSize(new Dimension(250, 20));
        button2.setActionCommand("shopping");
        button2.addActionListener(this);
        add(button2);
        index += 2;
    }

    //MODIFIES: this
    //EFFECTS: Read user input and save it to array if user click "Enter",
    // Reset the array and textfields if user click reset
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("accountsetting")) {
            displayAllOptionsButton();
        } else if (e.getActionCommand().equals("shopping")) {
            return;
        } else if (e.getActionCommand().equals("displayall")) {
            displayUserInfo();
        } else if (e.getActionCommand().equals("changeAccountInfo")) {
            setUpFields();
            setUpEnterButton();
        } else if (e.getActionCommand().equals("Change")) {
            removeAll();
            changeAccountInfo();
        }
        validate();
        repaint();
    }

    //MODIFIES: MainPageGUI
    //EFFECTS: Change user address by calling static function in MainPageGUI
    private void changeAccountInfo() {
        for (int i = 0; i < numPairs; i++) {
            input.add(textFields[i].getText());
        }
        MainPageGUI.currentUser.customerResetPassword(input.get(0));
        MainPageGUI.currentUser.changeAddress(input.get(1));
        MainPageGUI.currentUser.changePhoneNumber(input.get(2));
        JLabel label = new JLabel("Updated: " + MainPageGUI.saveDataBase());
        add(label);
    }

    //MODIFIES: this
    //EFFECTS: Set up textfields for input
    private void setUpFields() {
        removeAll();
        JLabel statusLabel1 = new JLabel("Change your info now!");
        add(statusLabel1);
        index = 1;
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            add(l);
            textFields[i] = new JTextField(10);
            //l.setLabelFor(textFields[i]);
            add(textFields[i]);
        }
        index += numPairs;
    }

    private void setUpEnterButton() {
        JButton button = new JButton("Change");
        button.setPreferredSize(new Dimension(250, 20));
        button.setActionCommand("Change");
        button.addActionListener(this);
        add(button);
    }

    //MODIFIES: this
    //EFFECTS: Display User Info
    private void displayUserInfo() {
        removeAll();
        index = 0;
        JLabel statusLabel1 = new JLabel("UserName: " + MainPageGUI.currentUser.getUserName());
        JLabel statusLabel2 = new JLabel("First and Last Name: " + MainPageGUI.currentUser.getName());
        JLabel statusLabel3 = new JLabel("Age: " + MainPageGUI.currentUser.getAge());
        JLabel statusLabel4 = new JLabel("Phone Number: " + MainPageGUI.currentUser.getPhoneNumber());
        JLabel statusLabel5 = new JLabel("Address: " + MainPageGUI.currentUser.getAddress());
        add(statusLabel1);
        add(statusLabel2);
        add(statusLabel3);
        add(statusLabel4);
        add(statusLabel5);
        index += 5;
        validate();
        repaint();
    }

    //MODIFIES: this
    //EFFECTS: display all the options(buttons) in account setting
    private void displayAllOptionsButton() {
        JButton button1 = new JButton("Display Account Info");
        button1.setPreferredSize(new Dimension(250, 20));
        button1.setActionCommand("displayall");
        button1.addActionListener(this);
        add(button1);

        JButton button2 = new JButton("Change Account Info");
        button2.setPreferredSize(new Dimension(250, 20));
        button2.setActionCommand("changeAccountInfo");
        button2.addActionListener(this);
        add(button2);
        index += 3;
    }

}
