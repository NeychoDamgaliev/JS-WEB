package main.java.app.contracts;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Neycho Damgaliev on 1/20/2019.
 */
public interface HttpRequest {
    Map<String,String> getHeaders();

    Map<String,String> getBodyParameters();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    void addHeader(String header, String value);

    void addBodyParameter (String parameter, String value);

    boolean isResource();

}
