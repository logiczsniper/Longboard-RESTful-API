package LongboardApiCallsJava;

import LongboardApiCallsJava.src.JPanelWithBackground;
import LongboardApiCallsJava.src.LongboardApiRequests;
import LongboardApiCallsJava.src.SpecificLongboardApiRequests;

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

        // Set up application, set background image, set icon, load button sound.
        JFrame appScreen = new JFrame("Longboard Storage App");
        appScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            appScreen.setContentPane(new JPanelWithBackground("static/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image icon = Toolkit.getDefaultToolkit().getImage("static/icon.png");
        appScreen.setIconImage(icon);

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

        // Add user input for simple requests
        JTextField simpleUserInput = addLabeledUserInput(appScreen, new ArrayList<>(Arrays.asList(100, 60, 200, 30)), "ID: ");

        // Add Longboard specific buttons
        addJButton(appScreen, new ArrayList<>(Arrays.asList(100, 100, 200, 30)), "get", simpleUserInput, true);
        addJButton(appScreen, new ArrayList<>(Arrays.asList(100, 140, 200, 30)), "delete", simpleUserInput, true);
        addJButton(appScreen, new ArrayList<>(Arrays.asList(100, 180, 200, 30)), "options", simpleUserInput, true);

        // Add General Longboard buttons
        addJButton(appScreen, new ArrayList<>(Arrays.asList(100, 300, 200, 30)), "get", simpleUserInput, false);
        addJButton(appScreen, new ArrayList<>(Arrays.asList(100, 340, 200, 30)), "delete", simpleUserInput, false);
        addJButton(appScreen, new ArrayList<>(Arrays.asList(100, 380, 200, 30)), "options", simpleUserInput, false);

        // Adding post panels
        // TODO: call for post

        // Adding put/patch panels
        // TODO: call for put/patch

        // Finish setting up application
        appScreen.setSize(935, 645);
        appScreen.setLayout(null);
        appScreen.setVisible(true);

    }

    // TODO: create method that takes JSON string and converts it to a pretty string to be displayed by the popup

    // TODO: documentation on new methods

    private static JTextField addLabeledUserInput(JFrame appScreen, ArrayList<Integer> position, String label) {
        JLabel idLabel = new JLabel(label);
        idLabel.setBounds(position.get(0) - 60, position.get(1), 80, 30);
        appScreen.add(idLabel);

        JTextField userInput = new JTextField();
        userInput.setBounds(position.get(0), position.get(1), position.get(2), position.get(3));
        appScreen.add(userInput);

        return userInput;
    }

    private static void addJButton(JFrame appScreen, ArrayList<Integer> position, String method, JTextField simpleUserInput, Boolean isSpecific) {
        JButton button = new JButton(method.substring(0, 1).toUpperCase() + method.substring(1));
        button.setBounds(position.get(0), position.get(1), position.get(2), position.get(3));
        ActionListener baseListener = e -> {
            try {

                playSoundAttempt();

                if (isSpecific) {
                    String response = SpecificLongboardApiRequests.simpleSpecificLongboardRequest(method, simpleUserInput.getText());
                    JOptionPane.showMessageDialog(appScreen, response);
                } else {
                    String response = LongboardApiRequests.simpleLongboardRequest(method);
                    JOptionPane.showMessageDialog(appScreen, response);
                }

            } catch (Exception el) {
                el.printStackTrace();
            }
        };

        button.addActionListener(baseListener);
        appScreen.add(button);
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
