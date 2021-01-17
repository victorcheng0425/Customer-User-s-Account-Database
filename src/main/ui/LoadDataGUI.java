package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LoadDataGUI extends JPanel {

    public static final int LAYOUTWIDTH = 700;
    public static final int LAYOUTHEIGHT = 500;
    public static final int IMAGEWIDTH = 600;
    public static final int IMAGEHEIGHT = 400;
    private JLabel statusLabel1;
    //    private JLabel statusLabel2;
    private MainPageGUI mainpage;
    private ImageIcon loaddataicon;
    private JPanel lightPanel;
    private JLabel imageLabel;


    public LoadDataGUI(MainPageGUI mainpage) {
        this.mainpage = mainpage;
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, LAYOUTWIDTH, 10);
        setLayout(layout);
        setPreferredSize(new Dimension(LAYOUTWIDTH, LAYOUTHEIGHT));
        lightPanel = new JPanel();
        lightPanel.setPreferredSize(new Dimension(IMAGEWIDTH, IMAGEHEIGHT));
        add(lightPanel);
        setUpImageLabel();
    }

    //MODIFIES: this
    //EFFECTS: create print message and call function to add image
    private void setUpImageLabel() {
        statusLabel1 = new JLabel(MainPageGUI.loadDataBase());
        add(statusLabel1);
        addImage();
        addSound();
    }

    //MODIFIES: this
    //EFFECTS: Get image from file, and add it to lightPanel
    private void addImage() {
        String sep = System.getProperty("file.separator");
        loaddataicon = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "loaddata.jpg");
        imageLabel = new JLabel(loaddataicon);
        lightPanel.add(imageLabel);

    }

    //EFFECTS: Get sound file, and play it
    private void addSound() {
        String sep = System.getProperty("file.separator");
        MainPageGUI.playSound(System.getProperty("user.dir") + sep
                + "sounds" + sep + "happy.wav");
    }

}
