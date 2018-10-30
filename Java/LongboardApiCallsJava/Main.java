package LongboardApiCallsJava;

import javax.swing.*;

import LongboardApiCallsJava.src.LongboardApiRequests;
import LongboardApiCallsJava.src.SpecificLongboardApiRequests;

import java.util.LinkedHashMap;


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
        
    }

}
