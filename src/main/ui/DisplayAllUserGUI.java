package ui;

import model.CustomerDataBase;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class DisplayAllUserGUI extends JPanel {
    public static final int LAYOUTWIDTH = 700;
    public static final int LAYOUTHEIGHT = 500;
    private JLabel statusLabel1;
    private MainPageGUI mainpage;


    public DisplayAllUserGUI(MainPageGUI mainpage) {
        this.mainpage = mainpage;
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, LAYOUTWIDTH, 10);
        setLayout(layout);
        setPreferredSize(new Dimension(LAYOUTWIDTH, LAYOUTHEIGHT));
        displayAll();
    }

    private void displayAll() {
        Set<String> keyset = MainPageGUI.database.getAllKey();
        for (String key : keyset) {
            JLabel statusLabel = new JLabel(key);
            add(statusLabel);
        }
    }

}
