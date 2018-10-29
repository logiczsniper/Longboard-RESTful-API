package LongboardApiCallsJava.src;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.LinkedHashMap;


public class SpecificLongboardApiRequests {

    private static String specific_endpoint = "http://localhost:5000/longboard/%s";

    public static void getLongboard(String id) {
        try {
            HttpResponse<String> response = Unirest.get(String.format(specific_endpoint, id))
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }

    public static void putLongboard(String id, LinkedHashMap<String, String> args) {
        try {
            HttpResponse<String> response = Unirest.put(String.format(specific_endpoint, id))
                    .queryString("name", args.get("name"))
                    .queryString("length", args.get("length"))
                    .queryString("width", args.get("width"))
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }

    public static void deleteLongboard(String id) {
        try {
            HttpResponse<String> response = Unirest.delete(String.format(specific_endpoint, id))
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }

    public static void optionsLongboard(String id) {
        try {
            HttpResponse<String> response = Unirest.options(String.format(specific_endpoint, id))
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }

    public static void headLongboard(String id) {
        try {
            HttpResponse<String> response = Unirest.head(String.format(specific_endpoint, id))
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }

    public static void patchLongboard(String id, LinkedHashMap<String, String> args) {
        try {
            HttpResponse<String> response = Unirest.patch(String.format(specific_endpoint, id))
                    .queryString("name", args.get("name"))
                    .queryString("length", args.get("length"))
                    .queryString("width", args.get("width"))
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }
}
