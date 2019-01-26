package main.java.app.impl;

import main.java.app.contracts.HttpRequest;
import main.java.app.contracts.HttpResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Neycho Damgaliev on 1/24/2019.
 */
public class HttpResponseImpl implements HttpResponse {
    private Map<String, String> headers;
    private int statusCode;
    private byte[] content;

    public HttpResponseImpl() {
        this.headers = new LinkedHashMap<>();

    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    private String getStatusMessage() {
        switch (getStatusCode()) {
            case 200:
                return "OK";
            case 400:
                return "Bad HttpRequestImpl";
            case 401:
                return "Unauthorized";
            case 404:
                return "Not Found";
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();

        // RESPONSE LINE
        response.append("HTTP/1.1 ")
                .append(this.getStatusCode())
                .append(" ")
                .append(getStatusMessage())
                .append(System.lineSeparator());

        // RESPONSE HEADERS
        this.getHeaders()
                .entrySet()
                .forEach(kvp ->
                        response.append(kvp.getKey())
                                .append(": ")
                                .append(kvp.getValue())
                                .append(System.lineSeparator())
                );

        response.append(System.lineSeparator());

        // RESPONSE CONTENT
        if(this.getContent().length!=0) {
            response.append(new String(this.getContent()));
        }

        return response.toString();
    }
}
