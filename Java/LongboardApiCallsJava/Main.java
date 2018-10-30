package LongboardApiCallsJava;

import LongboardApiCallsJava.src.JPanelWithBackground;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;


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

        Clip sound = loadClipAttempt();

        // TODO: background music

        /**
         *         LinkedHashMap<String, String> new_args = new LinkedHashMap<>();
         *         new_args.put("id", "003");
         *         new_args.put("name", "Board from JAVA two");
         *         new_args.put("length", "1");
         *         new_args.put("width", "25");
         *
         *         LongboardApiRequests.postLongboard(new_args);

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

        // Get a longboard from a id
        JLabel idLabel = new JLabel("ID: ");
        idLabel.setBounds(20, 20, 80, 30);
        appScreen.add(idLabel);

        final JTextField userInput = new JTextField();
        userInput.setBounds(100, 20, 200, 30);
        appScreen.add(userInput);

        JButton getLongboardButton = new JButton("Get Longboard");
        getLongboardButton.setBounds(100, 60, 200, 30);
        getLongboardButton.addActionListener(e -> {
            try {
                assert sound != null;
                // TODO: button sound only plays once-- FIX
                sound.start();

                // TODO: use api here

                JOptionPane.showMessageDialog(appScreen, "");
            } catch (Exception el) {
                el.printStackTrace();
            }
        });
        appScreen.add(getLongboardButton);

        // Finish setting up application
        appScreen.setSize(935, 645);
        appScreen.setLayout(null);
        appScreen.setVisible(true);

    }

    /**
     * Attempts to load the button sound and handles all exceptions.
     */
    private static Clip loadClipAttempt() {
        try {
            String soundName = "static/button-sound.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ignored) {
        }
        return null;
    }

}
