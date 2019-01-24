package main.java.app;

import main.java.app.impl.Request;
import main.java.app.impl.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Neycho Damgaliev on 1/20/2019.
 */
public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> validURLs = getValidURLs();
        Request request = new Request(getRequestString());

        Response response = generateResponse(request, validURLs);

        System.out.println(response);
    }

    private static Response generateResponse(Request request, List<String> validURLs) {
        Response response = new Response();
        if (!validURLs.contains(request.getRequestUrl())) {
            response.setStatusCode(404);
            response.setContent("The requested functionality was not found.".getBytes());
        } else {
            if (!request.getHeaders().containsKey("Authorization")) {
                response.setStatusCode(401);
                response.setContent("You are not authorized to access the requested functionality.".getBytes());
            } else if ("POST".equals(request.getMethod()) && request.getBodyParameters().isEmpty()) {
                response.setStatusCode(400);
                response.setContent("There was an error with the requested functionality due to malformed request.".getBytes());
            } else {
                response.setStatusCode(200);
                List<String> responseBodyData = new ArrayList<>();
                int index = 0;
                for (Map.Entry<String, String> kvp : request.getBodyParameters().entrySet()) {
                    if (index == 0) {
                        responseBodyData.add(kvp.getValue());
                    } else {
                        responseBodyData.add(kvp.getKey() + " - " + kvp.getValue());
                    }
                    index++;
                }

                String responseContent =
                        String.format("Greetings %s! You have successfully created %s with %s, %s.",
                                new String(Base64.getDecoder().decode(request.getHeaders().get("Authorization")
                                        .split("\\s+")[1])),
                                responseBodyData.get(0),
                                responseBodyData.get(1),
                                responseBodyData.get(2));

                response.setContent(responseContent.getBytes());
            }
        }
        request
                .getHeaders()
                .entrySet()
                .stream()
                .filter(kvp -> !kvp.getKey().equals("Authorization"))
                .forEach(kvp -> response.addHeader(kvp.getKey(), kvp.getValue()));

        return response;
    }

    private static List<String> getValidURLs() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    private static String getRequestString() throws IOException {
        StringBuilder request = new StringBuilder();
        String line = null;

        // Adds request headers
        while ((line = reader.readLine()) != null && line.length() > 0) {
            request.append(line).append(System.lineSeparator());
        }

        // Add an empty line before request body
        request.append(System.lineSeparator());

        // Adds the request body params
        if ((line = reader.readLine()) != null && line.length() > 0) {
            request.append(line);
        }

        return request.toString().trim();
    }
}
