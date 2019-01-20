package main.java.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Neycho Damgaliev on 1/20/2019.
 */
public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> validURLs = getValidURLs();
        List<String> request = getRequest();

        String method = getMethod(request.get(0));
        String URL = getURL(request.get(0));

        Map<String, String> headers = getHeaders(request);
        Map<String, String> bodyParams = getBodyParams(request);


        StringBuilder response = new StringBuilder();
        if (!validURLs.contains(URL)) {
            // DO SOMETHING
            response.append("HTTP/1.1 404 Not Found").append(System.lineSeparator());
            response.append(getResponseHeaders(headers));

            response.append(System.lineSeparator());
            response.append("The requested functionality was not found.");
        } else {
            if (!headers.containsKey("Authorization")) {
                response.append("HTTP/1.1 401 Unauthorized").append(System.lineSeparator());
                response.append(getResponseHeaders(headers));

                response.append(System.lineSeparator());
                response.append("You are not authorized to access the requested functionality.");
            } else if ("POST".equals(method) && bodyParams.isEmpty()) {
                response.append("HTTP/1.1 400 Bad Request").append(System.lineSeparator());
                response.append(getResponseHeaders(headers));

                response.append(System.lineSeparator());
                response.append("There was an error with the requested functionality due to malformed request.");
            } else {
                response.append("HTTP/1.1 200 OK").append(System.lineSeparator());
                response.append(getResponseHeaders(headers));

                response.append(System.lineSeparator());
                List<String> responseBodyData = new ArrayList<>();
                int index = 0;
                for (Map.Entry<String, String> kvp : bodyParams.entrySet()) {
                    if (index == 0) {
                        responseBodyData.add(kvp.getValue());
                    } else {
                        responseBodyData.add(kvp.getKey() + " - " + kvp.getValue());
                    }
                    index++;
                }

                String responseBody =
                        String.format("Greetings %s! You have successfully created %s with %s, %s.",
                                new String(Base64.getDecoder().decode(headers.get("Authorization").split("\\s+")[1])),
                                responseBodyData.get(0),
                                responseBodyData.get(1),
                                responseBodyData.get(2));

                response.append(responseBody);
            }
        }

        System.out.println(response.toString());
    }


    private static List<String> getValidURLs() throws IOException {
        List<String> urls = Arrays.asList(reader.readLine().split("\\s+"));
        return urls;
    }

    private static List<String> getRequest() throws IOException {
        List<String> request = new ArrayList<>();
        String line = null;

        // Adds request headers
        while ((line = reader.readLine()) != null && line.length() > 0) {
            request.add(line);
        }

        // Add an empty line before request body
        request.add(System.lineSeparator());

        // Adds the request body params
        if ((line = reader.readLine()) != null && line.length() > 0) {
            request.add(line);
        }

        return request;
    }

    private static String getMethod(String requestLine) {
        return requestLine.split("\\s+")[0];
    }

    private static String getURL(String requestLine) {
        return requestLine.split("\\s+")[1];
    }

    private static Map<String, String> getHeaders(List<String> request) {
        Map<String, String> headers = new LinkedHashMap<>();
        request.stream().skip(1)
                .filter(h -> h.contains(": "))
                .map(h -> h.split(": "))
                .forEach(h -> headers.putIfAbsent(h[0], h[1]));
        return headers;
    }

    private static Map<String, String> getBodyParams(List<String> request) {
        Map<String, String> bodyParams = new LinkedHashMap<>();
        if (!request.get(request.size() - 1).equals(System.lineSeparator())) {
            Arrays.stream(request.get(request.size() - 1)
                    .split("&"))
                    .map(bp -> bp.split("="))
                    .forEach(bp -> bodyParams.putIfAbsent(bp[0], bp[1]));
        }
        return bodyParams;
    }


    private static String getResponseHeaders(Map<String, String> headers) {
        StringBuilder responseHeaders = new StringBuilder();
        if (headers.containsKey("Date")) {
            responseHeaders.append("Date: ").append(headers.get("Date")).append(System.lineSeparator());
        } else {
            // SERVER SHOULD RETURN CORRECT DATE...
            // EVEN IF DATE IS NOT PROVIDED BY REQUEST...
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            responseHeaders.append("Date: ").append(simpleDateFormat.format(new Date())).append(System.lineSeparator());
        }
        if (headers.containsKey("Host")) {
            responseHeaders.append("Host: ").append(headers.get("Host")).append(System.lineSeparator());
        }
        if (headers.containsKey("Content-Type")) {
            responseHeaders.append("Content-Type: ").append(headers.get("Content-Type")).append(System.lineSeparator());
        }
        return responseHeaders.toString();
    }

}
