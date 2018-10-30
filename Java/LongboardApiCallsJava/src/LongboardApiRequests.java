package LongboardApiCallsJava.src;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.LinkedHashMap;


public class LongboardApiRequests {

    private static String general_endpoint = "http://localhost:5000/longboards";

    /**
     * A 'simple' longboard request, in this context, should be defined as a request that requires no arguments. This
     * method handles all the 'simple' requests.
     *
     * @param requestType the HTTP verb the request should use.
     * @return depending on the success of the request, either a helpful message signifying an invalid requestType, or
     * the body of the response from the api.
     */
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

    /**
     * This method facilitates the use of the POST request to the api. It requires arguments and is therefore not a
     * 'simple' request.
     *
     * @param args the data on the longboard to be posted.
     * @return the response body from the api.
     */
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
