package LongboardApiCallsJava.src;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.LinkedHashMap;


public class SpecificLongboardApiRequests {

    private static String specific_endpoint = "http://localhost:5000/longboard/%s";

    /**
     * A 'simple' longboard request, in this context, should be defined as a request that requires only the longboard
     * id. This method handles all the 'simple' requests.
     *
     * @param requestType the HTTP verb the request should use.
     * @return depending on the success of the request, either a helpful message signifying an invalid requestType, or
     * the body of the response from the api.
     */
    public static String simpleSpecificLongboardRequest(String requestType, String id) {

        if (id.isEmpty()) {
            return "Please provide a Longboard ID!";
        }

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

    /**
     * This method facilitates the use of the PUT and PATCH requests to the api. They require arguments and are
     * therefore are not 'simple' requests.
     *
     * @param args the data on the longboard to be put or patched.
     * @return the response body from the api or a helpful message signifying an invalid requestType.
     */
    public static String complexSpecificLongboardRequest(String requestType, String id, LinkedHashMap<String, String> args) {

        if (id.isEmpty()) {
            return "Please provide a Longboard ID!";
        }

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
