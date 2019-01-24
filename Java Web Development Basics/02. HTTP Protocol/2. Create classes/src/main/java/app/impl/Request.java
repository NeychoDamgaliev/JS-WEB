package main.java.app.impl;

import main.java.app.contracts.HttpRequest;

import java.util.*;

/**
 * Created by Neycho Damgaliev on 1/20/2019.
 */
public class Request implements HttpRequest {

    private static final String HTTP_DOT_SEPARATOR = ".";

    private String method;
    private String requestUrl;
    private Map<String, String> headers;
    private Map<String, String> bodyParameters;

    public Request(String request) {
        this.init(request);
    }

    private void init(String request) {
//        boolean newLine = false;
        String[] split = request.split(System.lineSeparator() + System.lineSeparator());
        List<String> requestLines = Arrays.asList(split[0].split(System.lineSeparator()));


        this.setMethod(requestLines.get(0).split("\\s+")[0]);
        this.setRequestUrl(requestLines.get(0).split("\\s+")[1]);

        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();


        this.setHeaders(requestLines);
        if (split.length > 1) {
            this.setBodyParameters(Arrays.asList(split[1].split(System.lineSeparator())));
        }
    }

    private void setHeaders(List<String> requestLines) {
        requestLines.stream()
                .skip(1)
                .filter(rl -> rl.contains(": "))
                .map(rl -> rl.split(": "))
                .forEach(rlKvp -> this.addHeader(rlKvp[0], rlKvp[1]));
    }

    // HANDLES MULTILINE BODY PARAMETES
    private void setBodyParameters(List<String> requestLines) {
        requestLines.forEach(line -> {
            Arrays.stream(line
                    .split("&"))
                    .map(kvpEl->kvpEl.split("="))
                    .forEach(kvp->this.addBodyParameter(kvp[0],kvp[1]));
        });
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(HTTP_DOT_SEPARATOR);
    }
}
