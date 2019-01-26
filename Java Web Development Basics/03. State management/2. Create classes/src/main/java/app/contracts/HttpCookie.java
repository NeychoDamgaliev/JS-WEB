package main.java.app.contracts;

/**
 * Created by Neycho Damgaliev on 1/26/2019.
 */
public interface HttpCookie {
    String getKey();

    String getValue();

    void setKey(String key);

    void setValue(String value);

}
