package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class LoginGUI extends JPanel implements ActionListener {
    public static final int LAYOUTWIDTH = 700;
    public static final int LAYOUTHEIGHT = 500;
    public static final int IMAGEWIDTH = 600;
    public static final int IMAGEHEIGHT = 400;
    private NorthGUI northgui;
    private static final String STATUS1 = "Please enter your username and password:";
//    private static final String STATUS2 = "Username exist, please try again.";
//    private static final String STATUS4 = "Account created!";
//    private static final String STATUS5 = "Your temporary account has created successfully!";
//    private static final String STATUS6 = "Do you want to store the account info into the File/Database?";
//    private static final String STATUS7 = "You must store it if you want to login next time.";
//    private static final String STATUS8 = "Your temporary account has created successfully!";
////    private static final String STATUS9 = "Do you want to load the account info from the file "
//            + "that you previously stored?";
    private JLabel statusLabel1;
    private JLabel statusLabel2;
    private JLabel statusLabel3;
    private NorthGUI northGUI;
    private String[] labels = {"UserName: ", "Password: "};
    private List<String> input;
    private int numPairs = labels.length;
    private JTextField[] textFields = new JTextField[numPairs];
    private ImageIcon loginicon;
    private JPanel lightPanel;
    private JLabel imageLabel;
    //private ShoppingMainPageGUI shopgui;


    public LoginGUI(NorthGUI northgui) {
        this.northgui = northgui;
        input = new ArrayList<>();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, LAYOUTWIDTH, 10);
        printIntroMessage();
        setLayout(layout);
        setPreferredSize(new Dimension(LAYOUTWIDTH, LAYOUTHEIGHT));
        //setUpImageLabel();
        setUpInputField();
        addButton();
    }

    //MODIFIES: this
    //EFFECTS: create print message and call function to add image and sound
    private void setUpImageLabel() {
        lightPanel = new JPanel();
        lightPanel.setPreferredSize(new Dimension(IMAGEWIDTH,IMAGEHEIGHT));
        add(lightPanel);
        addImage();
        addSound();
        statusLabel3 = new JLabel("Login Successful!");
        add(statusLabel3);
    }

    //MODIFIES: this
    //EFFECTS: Get image from file, rescale it and add it to lightPanel
    private void addImage() {
        String sep = System.getProperty("file.separator");
        loginicon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "login.jpg").getImage().getScaledInstance(IMAGEWIDTH,
                IMAGEHEIGHT, Image.SCALE_DEFAULT));
        imageLabel = new JLabel(loginicon);
        lightPanel.add(imageLabel);
    }

    //EFFECTS: Get sound file, and play it
    private void addSound() {
        String sep = System.getProperty("file.separator");
        MainPageGUI.playSound(System.getProperty("user.dir") + sep
                + "sounds" + sep + "happy.wav");
    }

    //MODIFIES: this
    //EFFECTS: add print intro message
    private void printIntroMessage() {
        statusLabel1 = new JLabel(STATUS1);
        add(statusLabel1);
    }

    //MODIFIES: this
    //EFFECTS: Add multiple label and textfields for user input
    private void setUpInputField() {
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
        JButton button1 = new JButton("Login!");
        button1.setPreferredSize(new Dimension(250, 20));
        button1.setActionCommand("Login");
        button1.addActionListener(this);
        add(button1);

        JButton button2 = new JButton("Reset!");
        button2.setPreferredSize(new Dimension(250, 20));
        button2.setActionCommand("Reset");
        button2.addActionListener(this);
        add(button2);
    }

    //MODIFIES: this
    //EFFECTS: Read user input and save it to array if user click "Login",
    // Reset the array and textfields if user click reset
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            for (int i = 0; i < numPairs; i++) {
                input.add(textFields[i].getText());
            }
            validateLoginAccount();
            //statusLabel1.setText(input.get(0));
        } else if (e.getActionCommand().equals("Reset")) {
            for (int i = 0; i < numPairs; i++) {
                textFields[i].setText("");
                input = new ArrayList<>();
            }
        } else if (e.getActionCommand().equals("Continue")) {
            northgui.addHomePageButton();
            removeAll();
            //add(shopgui)
            //BorderLayout layout = (BorderLayout)mainpage.getLayout();
            //mainpage.remove(layout.getLayoutComponent(BorderLayout.CENTER));
            //removeAll();
            //mainpage.add(shopgui, BorderLayout.CENTER);
            validate();
            repaint();
        }
    }

    //EFFECTS: Check user login account and add/modifies print message(statusLabel)
    private void validateLoginAccount() {
        if (!MainPageGUI.login(input)) {
            if (statusLabel2 == null) {
                statusLabel2 = new JLabel("Username/password incorrect! Please try again");
                add(statusLabel2);
                input = new ArrayList<>();
            }
        } else {
            removeAll();
            setUpImageLabel();
            JButton button1 = new JButton("Continue");
            button1.setPreferredSize(new Dimension(250, 20));
            button1.setActionCommand("Continue");
            button1.addActionListener(this);
            add(button1);

//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                statusLabel3.setText("Timer Error");
//            }
            //removeAll();
           // shopgui = new ShoppingMainPageGUI(mainpage);
           // add(shopgui);

        }
        validate();
        repaint();
    }



}
