package main.java.app.contracts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Neycho Damgaliev on 1/24/2019.
 */
public interface HttpResponse {
    Map<String,String > getHeaders();

    int getStatusCode();

    byte[] getContent();

    byte[] getBytes();

    void setStatusCode(int statusCode);

    void setContent( byte[] content);

    void addHeader(String header, String value);

}
