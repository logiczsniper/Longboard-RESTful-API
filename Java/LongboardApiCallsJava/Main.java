package LongboardApiCallsJava;

import javax.swing.*;
import LongboardApiCallsJava.src.LongboardApiRequests;

import java.util.HashMap;
import java.util.Map;


/**
 * This is a gui to be used in conjunction with the Python Flask api.
 * It serves as a front end product for the api.
 *
 * @author Logan Czernel
 * @since 26-10-2018
 */


public class Main {

    public static void main(String[] args) {
        JFrame appScreen = new JFrame("Longboard Storage App");
        appScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: aesthetics - icon image and sounds

        // TODO: actual methods so that this app can interact with my api

        Map<String, String> new_args = new HashMap<>();
        new_args.put("name", "Board from JAVA BABY");
        new_args.put("length", "1");
        new_args.put("width", "25");
        new_args.put("id", "001");

        LongboardApiRequests.postLongboard(new_args);
    }

}
