package main.java.app.impl;

import main.java.app.contracts.HttpCookie;

/**
 * Created by Neycho Damgaliev on 1/26/2019.
 */
public class HttpCookieImpl implements HttpCookie {
    private String key;
    private String value;

    public HttpCookieImpl(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s <-> %s",this.getKey(),this.getValue());
    }
}
