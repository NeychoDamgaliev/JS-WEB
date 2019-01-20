package main.java.app.api;

import java.util.HashMap;

/**
 * Created by Neycho Damgaliev on 1/20/2019.
 */
public interface HttpRequest {
    HashMap<String,String> getHeaders();

    HashMap<String,String> getBodyParameters();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    void addHeader(String header, String value);

    void addBodyParameter (String parameter, String value);

    boolean isResource();

}
