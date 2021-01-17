package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthGUI extends JPanel implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 220;
    public MainPageGUI mainpage;
    private LoginGUI logingui;
    private SignUpGUI signupgui;
    private LoadDataGUI loaddatagui;
    private ShoppingMainPageGUI shopgui;
    private DisplayAllUserGUI displayallusergui;
    private static final String STATUS1 = "Hi, Welcome to Fat Tree Online Store";
    private static final String STATUS2 = "Please select any option below";
    private JLabel statusLabel1;
    private JLabel statusLabel2;
    private int index = 0;

    private enum ButtonOption {
        load, login, signup, home, displayall
    }

    public NorthGUI(MainPageGUI mainpage) {
        this.mainpage = mainpage;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 1000, 10);
        setLayout(layout);
        setUpPrintingMessage();
        setUpButton();

    }

    //MODIFIES: this
    //EFFECTS: Load print message to the object
    private void setUpPrintingMessage() {
        statusLabel1 = new JLabel(STATUS1);
        statusLabel2 = new JLabel(STATUS2);
        add(statusLabel1);
        add(statusLabel2);
        index += 2;
    }

    //MODIFIES: this
    //EFFECTS: add button to the object
    private void setUpButton() {
        JButton button1 = new JButton("Load data from the file");
        button1.setPreferredSize(new Dimension(250, 20));
        button1.setActionCommand("Load data");
        button1.addActionListener(this);

        JButton button2 = new JButton("Customer Login");
        button2.setPreferredSize(new Dimension(250, 20));
        button2.setActionCommand("Login");
        button2.addActionListener(this);

        JButton button3 = new JButton("Sign up");
        button3.setPreferredSize(new Dimension(250, 20));
        button3.setActionCommand("Sign up");
        button3.addActionListener(this);

        JButton button4 = new JButton("Display All User");
        button4.setPreferredSize(new Dimension(250, 20));
        button4.setActionCommand("DisplayAll");
        button4.addActionListener(this);

        add(button1);
        add(button2);
        add(button3);
        add(button4);
        index += 4;
    }

//    private void loginButton(JButton button) {
//
//
//    }


    //EFFECTS: Call handler when different button pressed
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            loginbuttonHandle();
        } else if (e.getActionCommand().equals("Sign up")) {
            signupbuttonHandle();
        } else if (e.getActionCommand().equals("Load data")) {
            //JLabel statusLabel2 = new JLabel(MainPageGUI.loadDataBase());
            loadDataButtonHandle();
        } else if (e.getActionCommand().equals("Home")) {
            homeButtonHandle();
        } else if (e.getActionCommand().equals("DisplayAll")) {
            displayAllHandle();
        }
        revalidate();
        repaint();
    }

    //MODIFIES: this, MainPageGUI mainpage
    //EFFECTS: Handle DisplayAll User button
    private void displayAllHandle() {
        removeAllExceptionItself(ButtonOption.displayall);
        statusLabel1.setText("Display All User");
        displayallusergui = new DisplayAllUserGUI(mainpage);
        mainpage.add(displayallusergui, BorderLayout.CENTER);
    }


    //MODIFIES: this, MainPageGUI mainpage
    //EFFECTS: Handle load data button
    private void homeButtonHandle() {
        removeAllExceptionItself(ButtonOption.home);
        statusLabel1.setText("Home Page");
        shopgui = new ShoppingMainPageGUI(this);
        mainpage.add(shopgui, BorderLayout.CENTER);
    }

    //MODIFIES: this, MainPageGUI mainpage
    //EFFECTS: Handle load data button
    private void loadDataButtonHandle() {
        removeAllExceptionItself(ButtonOption.load);
        statusLabel1.setText("Load Data");
        loaddatagui = new LoadDataGUI(mainpage);
        mainpage.add(loaddatagui, BorderLayout.CENTER);
    }

    //MODIFIES: this, MainPageGUI mainpage
    //EFFECTS: Handle login button
    private void loginbuttonHandle() {
//        if (signupgui != null) {
//            signupgui.removeAll();
//        }
        removeAllExceptionItself(ButtonOption.login);
        logingui = new LoginGUI(this);
        mainpage.add(logingui, BorderLayout.CENTER);
        statusLabel1.setText("Login Page");
    }

    //MODIFIES: this, MainPageGUI mainpage
    //EFFECTS: Handle login button
    private void signupbuttonHandle() {
//        if (logingui != null) {
//            logingui.removeAll();
//        }
        removeAllExceptionItself(ButtonOption.signup);
        signupgui = new SignUpGUI(mainpage);
        mainpage.add(signupgui, BorderLayout.CENTER);
        statusLabel1.setText("Sign Up Page");
    }

    //MODIFIES: this
    //EFFECTS: Remove all previous center border except the one who pass the parameter
    private void removeAllExceptionItself(ButtonOption opt) {
        if (logingui != null && opt != ButtonOption.login) {
            logingui.removeAll();
        }
        if (signupgui != null && opt != ButtonOption.signup) {
            signupgui.removeAll();
        }
        if (shopgui != null && opt != ButtonOption.home) {
            shopgui.removeAll();
        }
        if (loaddatagui != null && opt != ButtonOption.load) {
            loaddatagui.removeAll();
        }

        if (displayallusergui != null && opt != ButtonOption.displayall) {
            displayallusergui.removeAll();
        }

    }


    //MODIFIES: this
    //EFFECTS: add button to the object
    protected void addHomePageButton() {
        if (index >= 7) {
            return;
        }
        JButton button1 = new JButton("Home Page");
        button1.setPreferredSize(new Dimension(250, 20));
        button1.setActionCommand("Home");
        button1.addActionListener(this);
        add(button1);
        revalidate();
        repaint();
        index += 1;
    }
}
