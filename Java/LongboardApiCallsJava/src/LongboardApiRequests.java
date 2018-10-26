package LongboardApiCallsJava.src;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Map;

public class LongboardApiRequests {

    public static void getLongboards() {
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:5000/longboards")
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {}
    }

    public static void clearLongboards() {
        try {
            HttpResponse<String> response = Unirest.delete("http://localhost:5000/longboards")
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {}
    }

    public static void optionsLongboards() {
        try {
            HttpResponse<String> response = Unirest.options("http://localhost:5000/longboards")
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {}
    }

    public static void postLongboard(Map<String, String> args) {
        System.out.println(args);
        try {
            HttpResponse<String> response = Unirest.post("http://localhost:5000/longboards")
                    .body(new JSONObject(args))
                    .asString();
            System.out.println(response.getBody());
        } catch (UnirestException ignore) {}
    }

}
