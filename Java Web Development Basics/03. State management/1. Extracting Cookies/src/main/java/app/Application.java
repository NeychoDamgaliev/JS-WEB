package main.java.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Neycho Damgaliev on 1/20/2019.
 */
public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
//        List<String> validURLs = getValidURLs();
        List<String> request = getRequest();

        String method = getMethod(request.get(0));
        String URL = getURL(request.get(0));

        Map<String, String> headers = getHeaders(request);
        Map<String, String> cookies = getCookies(request);
        Map<String, String> bodyParams = getBodyParams(request);

        cookies.entrySet().forEach(c-> {
            System.out.printf("%s <-> %s%s",c.getKey(),c.getValue(),System.lineSeparator());
        });
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
                .filter(h -> h.contains(": ") && !h.startsWith("Cookie"))
                .map(h -> h.split(": "))
                .forEach(h -> {
                    headers.putIfAbsent(h[0], h[1]);
                });
        return headers;
    }

    private static Map<String, String> getCookies(List<String> request) {
        Map<String, String> cookies = new LinkedHashMap<>();
        request.stream().skip(1)
                .filter(h -> h.contains(": ") && h.startsWith("Cookie"))
                .map(c -> c.replaceFirst("Cookie: ", ""))
                .map(c -> c.split("; "))
                .forEach(c -> {
                    Arrays.stream(c)
                            .map(cookie -> cookie.split("="))
                            .forEach(cookie -> cookies.putIfAbsent(cookie[0], cookie[1]));
                });
        return cookies;
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
