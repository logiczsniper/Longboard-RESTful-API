package LongboardApiCallsJava.src;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.LinkedHashMap;


public class LongboardApiRequests {

    private static String general_endpoint = "http://localhost:5000/longboards";

    public static void getLongboards() {
        try {
            HttpResponse<String> response = Unirest.get(general_endpoint)
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }

    public static void clearLongboards() {
        try {
            HttpResponse<String> response = Unirest.delete(general_endpoint)
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }

    public static void optionsLongboards() {
        try {
            HttpResponse<String> response = Unirest.options(general_endpoint)
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }

    public static void postLongboard(LinkedHashMap<String, String> args) {
        try {
            HttpResponse<String> response = Unirest.post(general_endpoint)
                    .queryString("id", args.get("id"))
                    .queryString("name", args.get("name"))
                    .queryString("length", args.get("length"))
                    .queryString("width", args.get("width"))
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {
        }
    }

}
