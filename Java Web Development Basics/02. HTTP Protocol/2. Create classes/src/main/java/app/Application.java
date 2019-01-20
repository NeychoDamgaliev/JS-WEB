package main.java.app;

import main.java.app.impl.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Neycho Damgaliev on 1/20/2019.
 */
public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        Request request = new Request(getRequestString());


        System.out.println("");
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

        return request.toString();
    }
}
