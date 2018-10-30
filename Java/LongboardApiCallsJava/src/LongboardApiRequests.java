package LongboardApiCallsJava.src;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.LinkedHashMap;


public class LongboardApiRequests {

    private static String general_endpoint = "http://localhost:5000/longboards";

    public static String simpleLongboardRequest(String requestType) {
        try {
            HttpResponse<String> response;

            switch (requestType) {
                case "get":
                    response = Unirest.get(general_endpoint)
                            .asString();
                    break;

                case "clear":
                    response = Unirest.delete(general_endpoint)
                            .asString();
                    break;

                case "options":
                    response = Unirest.options(general_endpoint)
                            .asString();
                    break;

                default:
                    return String.format("Invalid requestType: %s", requestType);
            }

            return response.getBody();
        } catch (UnirestException ignore) {
        }
        return "";
    }

    public static String postLongboard(LinkedHashMap<String, String> args) {
        try {
            HttpResponse<String> response = Unirest.post(general_endpoint)
                    .queryString("id", args.get("id"))
                    .queryString("name", args.get("name"))
                    .queryString("length", args.get("length"))
                    .queryString("width", args.get("width"))
                    .asString();
            return response.getBody();
        } catch (UnirestException ignore) {
        }
        return "";
    }

}
