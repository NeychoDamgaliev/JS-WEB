package main.java.app.impl;

import main.java.app.api.HttpRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Neycho Damgaliev on 1/20/2019.
 */
public class Request implements HttpRequest {

    private static final String HTTP_DOT_SEPARATOR = ".";

    private String method;
    private String requestUrl;
    private HashMap<String,String> headers;
    private HashMap<String, String> bodyParameters;

    public Request(String request) {
        List<String> requestLines = Arrays.asList(request.split(System.lineSeparator()));


        this.setMethod(requestLines.get(0).split("\\s+")[0]);
        this.setRequestUrl(requestLines.get(0).split("\\s+")[1]);

        this.headers = new HashMap<>();
        this.bodyParameters = new HashMap<>();

        this.setHeaders(requestLines);
        this.setBodyParameters(requestLines);

    }

    private void setHeaders (List<String> requestLines) {
        requestLines.stream()
                .skip(1)
                .filter(rl->rl.contains(": "))
                .map(rl->rl.split(": "))
                .forEach(rlKvp -> this.addHeader(rlKvp[0],rlKvp[1]));
    }

    private void setBodyParameters (List<String> requestLines) {
        if (!requestLines.get(requestLines.size() - 1).equals(System.lineSeparator())) {
            Arrays.stream(requestLines.get(requestLines.size() - 1)
                    .split("&"))
                    .map(rlBodyParams -> rlBodyParams.split("="))
                    .forEach(rlKVPbodyParam -> this.addBodyParameter(rlKVPbodyParam[0],rlKVPbodyParam[1]));
        }
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public HashMap<String, String> getBodyParameters() {
        return this.bodyParameters;
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
        this.headers.put(header,value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter,value);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(HTTP_DOT_SEPARATOR);
    }
}
