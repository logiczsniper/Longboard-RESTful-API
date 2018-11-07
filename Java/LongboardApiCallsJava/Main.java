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
import java.util.LinkedHashMap;


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

        // Add user input for simple requests
        JTextField simpleUserInput = addLabeledUserInput(appScreen, new ArrayList<>(Arrays.asList(100, 60, 200, 30)), "ID: ");

        // Add Longboard specific buttons
        addSimpleJButton(appScreen, new ArrayList<>(Arrays.asList(100, 100, 200, 30)), "get", simpleUserInput, true);
        addSimpleJButton(appScreen, new ArrayList<>(Arrays.asList(100, 140, 200, 30)), "delete", simpleUserInput, true);
        addSimpleJButton(appScreen, new ArrayList<>(Arrays.asList(100, 180, 200, 30)), "options", simpleUserInput, true);

        // Add General Longboard buttons
        addSimpleJButton(appScreen, new ArrayList<>(Arrays.asList(100, 300, 200, 30)), "get", simpleUserInput, false);
        addSimpleJButton(appScreen, new ArrayList<>(Arrays.asList(100, 340, 200, 30)), "delete", simpleUserInput, false);
        addSimpleJButton(appScreen, new ArrayList<>(Arrays.asList(100, 380, 200, 30)), "options", simpleUserInput, false);

        // Adding post, put, patch panels
        JTextField idUserInput = addLabeledUserInput(appScreen, new ArrayList<>(Arrays.asList(650, 60, 200, 30)), "ID: ");
        JTextField nameUserInput = addLabeledUserInput(appScreen, new ArrayList<>(Arrays.asList(650, 100, 200, 30)), "NAME: ");
        JTextField widthUserInput = addLabeledUserInput(appScreen, new ArrayList<>(Arrays.asList(650, 140, 200, 30)), "LENGTH: ");
        JTextField lengthUserInput = addLabeledUserInput(appScreen, new ArrayList<>(Arrays.asList(650, 180, 200, 30)), "WIDTH: ");

        addComplexJButton(appScreen, new ArrayList<>(Arrays.asList(650, 220, 200, 30)), "post", idUserInput, nameUserInput, lengthUserInput, widthUserInput, false);
        addComplexJButton(appScreen, new ArrayList<>(Arrays.asList(650, 260, 200, 30)), "put", idUserInput, nameUserInput, lengthUserInput, widthUserInput, true);
        addComplexJButton(appScreen, new ArrayList<>(Arrays.asList(650, 300, 200, 30)), "patch", idUserInput, nameUserInput, lengthUserInput, widthUserInput, true);

        // Finish setting up application
        appScreen.setSize(935, 645);
        appScreen.setLayout(null);
        appScreen.setVisible(true);

    }

    /**
     * Creates a user input and a label that corresponds to the input in terms of position and what should be provided.
     *
     * @param appScreen the app screen to add the new elements.
     * @param position the x, y, width and height of the elements.
     * @param label the text to become a JLabel object.
     * @return the JTextField object created. This is done so that the content provided can be accessed.
     */
    private static JTextField addLabeledUserInput(JFrame appScreen, ArrayList<Integer> position, String label) {
        JLabel idLabel = new JLabel(label);
        idLabel.setBounds(position.get(0) - 60, position.get(1), 80, 30);
        appScreen.add(idLabel);

        JTextField userInput = new JTextField();
        userInput.setBounds(position.get(0), position.get(1), position.get(2), position.get(3));
        appScreen.add(userInput);

        return userInput;
    }

    /**
     * Creates a button with a set up Action Listener.
     *
     * @param appScreen the app screen to add the new elements.
     * @param position the x, y, width and height of the elements.
     * @param method the HTTP request method that will be used as both a parameter to the Longboard Api request functions and also as
     *               text for a label of the button.
     * @param simpleUserInput the user input for longboard id. It is the only required parameter for some of the methods.
     * @param isSpecific signals whether or not a Specific or regular request should be made. If specific, the id is passed to the
     *                   specific longboard request function rather than no id being passed to the regular simple longboard request
     *                   function.
     */
    private static void addSimpleJButton(JFrame appScreen, ArrayList<Integer> position, String method, JTextField simpleUserInput, Boolean isSpecific) {
        JButton button = new JButton(method.substring(0, 1).toUpperCase() + method.substring(1));
        button.setBounds(position.get(0), position.get(1), position.get(2), position.get(3));
        ActionListener baseListener = e -> {
            try {

                playSoundAttempt();
                String response;

                if (isSpecific) {
                    response = SpecificLongboardApiRequests.simpleSpecificLongboardRequest(method, simpleUserInput.getText());
                    JOptionPane.showMessageDialog(appScreen, response);
                } else {
                    response = LongboardApiRequests.simpleLongboardRequest(method);
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
     * Creates a button with a set up Action Listener. These Action Listeners, unlike those created by addSimpleJButton,
     * make requests that are deemed 'complex' as they require more arguments than the 'simple' requests.
     *
     * @param appScreen the app screen to add the new elements.
     * @param position the x, y, width and height of the elements.
     * @param method the HTTP request method that will be used as both a parameter to the Longboard Api request functions and also as
     *               text for a label of the button.
     * @param idUserInput the user input that should contain the id to be used for the new longboard.
     * @param nameUserInput the user input that should contain the name to be used for the new longboard.
     * @param lengthUserInput the user input that should contain the length to be used for the new longboard.
     * @param widthUserInput the user input that should contain the width to be used for the new longboard.
     * @apiNote making a put request should only be made if all three of the optional parameters are provided. If they
     * are not provided, a patch request should be used.
     * @param isSpecific signals whether or not a Specific or regular request should be made. If specific, the id is passed to the
     *                   specific longboard request function rather than no id being passed to the regular simple longboard request
     *                   function.
     */
    private static void addComplexJButton(JFrame appScreen, ArrayList<Integer> position, String method,
                                          JTextField idUserInput, JTextField nameUserInput, JTextField lengthUserInput,
                                          JTextField widthUserInput, Boolean isSpecific) {
        JButton button = new JButton(method.substring(0, 1).toUpperCase() + method.substring(1));
        button.setBounds(position.get(0), position.get(1), position.get(2), position.get(3));
        ActionListener baseListener = e -> {
            try {

                playSoundAttempt();
                String response;

                LinkedHashMap<String, String> new_args = new LinkedHashMap<>();
                new_args.put("id", idUserInput.getText());
                new_args.put("name", nameUserInput.getText());
                new_args.put("length", lengthUserInput.getText());
                new_args.put("width", widthUserInput.getText());

                if (isSpecific) {

                    response = SpecificLongboardApiRequests.complexSpecificLongboardRequest(method, idUserInput.getText(), new_args);
                    JOptionPane.showMessageDialog(appScreen, response);

                } else {

                    response = LongboardApiRequests.postLongboard(new_args);
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
            final String soundName = "static/button-sound.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ignored) {
        }
    }

}
