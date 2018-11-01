package LongboardApiCallsJava;

import LongboardApiCallsJava.src.JPanelWithBackground;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This is a GUI to be used in conjunction with the Python Flask API.
 * It serves as a front end product for the API.
 *
 * @author Logan Czernel
 * @since 26-10-2018
 */


public class Main {

    public static void main(String[] args) {

        // Set up application, set background image, set icon, load button sound, load+start background music
        JFrame appScreen = new JFrame("Longboard Storage App");
        appScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            appScreen.setContentPane(new JPanelWithBackground("static/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image icon = Toolkit.getDefaultToolkit().getImage("static/icon.png");
        appScreen.setIconImage(icon);

        // TODO: background music

        /* For reference when creating specific api calls in the ActionLister(s).

         LinkedHashMap<String, String> new_args = new LinkedHashMap<>();
         new_args.put("id", "003");
         new_args.put("name", "Board from JAVA two");
         new_args.put("length", "1");
         new_args.put("width", "25");
         LongboardApiRequests.postLongboard(new_args);

         LinkedHashMap<String, String> new_args = new LinkedHashMap<>();
         new_args.put("name", "Board from JAVA");
         new_args.put("length", "1");
         new_args.put("width", "25");
         SpecificLongboardApiRequests.putLongboard("001", new_args);

         LinkedHashMap<String, String> new_args = new LinkedHashMap<>();
         new_args.put("length", "8");
         SpecificLongboardApiRequests.patchLongboard("001", new_args);

         System.out.println(LongboardApiRequests.simpleLongboardRequest("get"));
         */

        // Adding get panels
        addElements(appScreen, new ArrayList<>(Arrays.asList(40, 60, 80, 30)),
                new ArrayList<>(Arrays.asList(100, 60, 200, 30)), "Get Longboard",
                new ArrayList<>(Arrays.asList(100, 100, 200, 30)), e -> {
                    try {

                        playSoundAttempt();

                        // TODO: use api here

                        JOptionPane.showMessageDialog(appScreen, "");
                    } catch (Exception el) {
                        el.printStackTrace();
                    }
                }, "Get All Longboards", new ArrayList<>(Arrays.asList(100, 140, 200, 30)),
                e -> {
                    try {

                        playSoundAttempt();

                        // TODO: use api here

                        JOptionPane.showMessageDialog(appScreen, "");
                    } catch (Exception el) {
                        el.printStackTrace();
                    }
                });

        // Adding deletion panels
        addElements(appScreen, new ArrayList<>(Arrays.asList(40, 240, 80, 30)),
                new ArrayList<>(Arrays.asList(100, 240, 200, 30)), "Delete Longboard",
                new ArrayList<>(Arrays.asList(100, 280, 200, 30)), e -> {
                    try {

                        playSoundAttempt();

                        // TODO: use api here

                        JOptionPane.showMessageDialog(appScreen, "");
                    } catch (Exception el) {
                        el.printStackTrace();
                    }
                }, "Delete All Longboards", new ArrayList<>(Arrays.asList(100, 320, 200, 30)),
                e -> {
                    try {

                        playSoundAttempt();

                        // TODO: use api here

                        JOptionPane.showMessageDialog(appScreen, "");
                    } catch (Exception el) {
                        el.printStackTrace();
                    }
                });

        // Adding options panels
        addElements(appScreen, new ArrayList<>(Arrays.asList(40, 420, 80, 30)),
                new ArrayList<>(Arrays.asList(100, 420, 200, 30)), "Options Longboard",
                new ArrayList<>(Arrays.asList(100, 460, 200, 30)), e -> {
                    try {

                        playSoundAttempt();

                        // TODO: use api here

                        JOptionPane.showMessageDialog(appScreen, "");
                    } catch (Exception el) {
                        el.printStackTrace();
                    }
                }, "Options All Longboards", new ArrayList<>(Arrays.asList(100, 500, 200, 30)),
                e -> {
                    try {

                        playSoundAttempt();

                        // TODO: use api here

                        JOptionPane.showMessageDialog(appScreen, "");
                    } catch (Exception el) {
                        el.printStackTrace();
                    }
                });

        // Adding post panels
        // TODO: addElements call for post

        // Adding put/patch panels
        // TODO: addElements call for put/patch

        // Finish setting up application
        appScreen.setSize(935, 645);
        appScreen.setLayout(null);
        appScreen.setVisible(true);

    }


    // TODO: create method that takes JSON string and converts it to a pretty string to be displayed by the popup


    /**
     * Creates elements and adds them to the screen using the many arguments given.
     *
     * @param appScreen      the JFrame of the app that the elements will be added to.
     * @param posOne         the x, y, width and height of the JLabel.
     * @param posTwo         the x, y, width and height of the JTextField.
     * @param buttonOneLabel the text that will be put onto the first JButton.
     * @param posThree       the x, y, width and height of the JButton.
     * @param operatorOne    the ActionListener that will be applied to the first JButton.
     * @param buttonTwoLabel the text that will be put onto the second JButton.
     * @param posFour        the x, y, width and height of the JButton.
     * @param operatorTwo    the ActionListener that will be applied to the second JButton.
     */
    private static void addElements(JFrame appScreen, ArrayList<Integer> posOne, ArrayList<Integer> posTwo,
                                    String buttonOneLabel, ArrayList<Integer> posThree, ActionListener operatorOne,
                                    String buttonTwoLabel, ArrayList<Integer> posFour, ActionListener operatorTwo) {

        JLabel idLabel = new JLabel("ID: ");
        idLabel.setBounds(posOne.get(0), posOne.get(1), posOne.get(2), posOne.get(3));
        appScreen.add(idLabel);

        JTextField userInput = new JTextField();
        userInput.setBounds(posTwo.get(0), posTwo.get(1), posTwo.get(2), posTwo.get(3));
        appScreen.add(userInput);

        JButton specificLongboardButton = new JButton(buttonOneLabel);
        specificLongboardButton.setBounds(posThree.get(0), posThree.get(1), posThree.get(2), posThree.get(3));
        specificLongboardButton.addActionListener(operatorOne);
        appScreen.add(specificLongboardButton);

        JButton generalLongboardsButton = new JButton(buttonTwoLabel);
        generalLongboardsButton.setBounds(posFour.get(0), posFour.get(1), posFour.get(2), posFour.get(3));
        generalLongboardsButton.addActionListener(operatorTwo);
        appScreen.add(generalLongboardsButton);
    }

    /**
     * Attempts to load and play the clip and handles all exceptions.
     */
    private static void playSoundAttempt() {
        try {
            String soundName = "static/button-sound.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ignored) {
        }
    }

}
