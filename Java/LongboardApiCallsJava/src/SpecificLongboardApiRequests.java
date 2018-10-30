package LongboardApiCallsJava.src;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.LinkedHashMap;


public class SpecificLongboardApiRequests {

    private static String specific_endpoint = "http://localhost:5000/longboard/%s";

    public static String simpleSpecificLongboardRequest(String requestType, String id) {
        try {
            HttpResponse<String> response;
            String id_endpoint = String.format(specific_endpoint, id);

            switch (requestType) {
                case "get":
                    response = Unirest.get(id_endpoint)
                            .asString();
                    break;

                case "delete":
                    response = Unirest.delete(id_endpoint)
                            .asString();
                    break;

                case "options":
                    response = Unirest.options(id_endpoint)
                            .asString();
                    break;

                case "head":
                    response = Unirest.head(id_endpoint)
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

    public static String complexSpecificLongboardRequest(String requestType, String id, LinkedHashMap<String, String> args) {
        try {
            HttpResponse<String> response;
            String id_endpoint = String.format(specific_endpoint, id);

            switch (requestType) {
                case "put":
                    response = Unirest.put(id_endpoint)
                            .queryString("name", args.get("name"))
                            .queryString("length", args.get("length"))
                            .queryString("width", args.get("width"))
                            .asString();
                    break;

                case "patch":
                    response = Unirest.patch(id_endpoint)
                            .queryString("name", args.get("name"))
                            .queryString("length", args.get("length"))
                            .queryString("width", args.get("width"))
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
}
