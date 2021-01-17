package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class SignUpGUI extends JPanel implements ActionListener {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    private MainPageGUI mainpage;
//    private static final String STATUS1 = "Please enter your username!";
//    private static final String STATUS2 = "Username exist, please try again.";
//    private static final String STATUS3 = "Please enter your password, "
//            + "Name, address, PhoneNumber, age in order (Caution!)";
//    private static final String STATUS4 = "Account created!";
//    private static final String STATUS5 = "Your temporary account has created successfully!";
//    private static final String STATUS6 = "Do you want to store the account info into the File/Database?";
//    private static final String STATUS7 = "You must store it if you want to login next time.";
//    private static final String STATUS8 = "Your temporary account has created successfully!";
//    private static final String STATUS9 = "Do you want to load the account info from the file "
//            + "that you previously stored?";
    private JLabel statusLabel1;
    private JLabel statusLabel2;

    //private NorthGUI northGUI;
    private String[] labels = {"UserName: ", "Password: ", "Name: ", "Address: ", "PhoneNumber: ", "age: "};
    private int numPairs = labels.length;
    private JTextField[] textFields = new JTextField[numPairs];
    private List<String> input;

    public SignUpGUI(MainPageGUI mainpage) {
        this.mainpage = mainpage;
        input = new ArrayList<>();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, WIDTH, 10);
        setLayout(layout);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setUp();
        addButton();

    }

    //MODIFIES: this
    //EFFECTS: Add multiple label and textfields for user input
    private void setUp() {
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            add(l);
            textFields[i] = new JTextField(10);
            //l.setLabelFor(textFields[i]);
            add(textFields[i]);
        }

        //SpringUtilities.makeCompactGrid(labels, textFields, 5, 5, 5, 5);
    }

    //MODIFIES: this
    //EFFECTS: Add button to Signup page
    private void addButton() {
        JButton button1 = new JButton("Enter!");
        button1.setPreferredSize(new Dimension(250, 20));
        button1.setActionCommand("EnterInfo");
        button1.addActionListener(this);
        add(button1);

        JButton button2 = new JButton("Reset!");
        button2.setPreferredSize(new Dimension(250, 20));
        button2.setActionCommand("Reset");
        button2.addActionListener(this);
        add(button2);
    }

    //MODIFIES: this
    //EFFECTS: Read user input and save it to array if user click "Enter",
    // Reset the array and textfields if user click reset
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("EnterInfo")) {
            for (int i = 0; i < numPairs; i++) {
                input.add(textFields[i].getText());
            }
            validateSignUpAccount();
        } else if (e.getActionCommand().equals("Reset")) {
            for (int i = 0; i < numPairs; i++) {
                textFields[i].setText("");
                input = new ArrayList<>();
            }
        } else if (e.getActionCommand().equals("Yes")) {
            //System.out.println("Yes");
            removeAll();
            statusLabel1.setText(MainPageGUI.saveDataBase());
            add(statusLabel1);
        } else if (e.getActionCommand().equals("No")) {
            removeAll();
            statusLabel1.setText("No data saved!");
            //System.out.println("No");
            add(statusLabel1);
        }

        validate();
        repaint();

    }

    //MODIFIES: this
    //EFFECTS: Check user account
    private void validateSignUpAccount() {
        if (!MainPageGUI.createAccount(input)) {
            if (statusLabel2 == null) {
                statusLabel2 = new JLabel("Username used! Please try again");
                add(statusLabel2);
                input = new ArrayList<>();
            }
        } else {
            if (statusLabel2 == null) {
                statusLabel2 = new JLabel("Account created!");
                add(statusLabel2);
            } else {
                statusLabel2.setText("Account created!");
            }
            saveToFile();
        }
        validate();
        repaint();

    }

    //MODIFIES: this
    //EFFECTS: save data to JSON
    private void saveToFile() {
        removeAll();

        statusLabel1 = new JLabel("Want to save account info to file?");
        add(statusLabel1);

        JButton button = new JButton("Yes");
        button.setPreferredSize(new Dimension(250, 20));
        button.setActionCommand("Yes");
        button.addActionListener(this);
        add(button);

        JButton button1 = new JButton("No");
        button1.setPreferredSize(new Dimension(250, 20));
        button1.setActionCommand("No");
        button1.addActionListener(this);
        add(button1);

        validate();
        repaint();
    }

}
