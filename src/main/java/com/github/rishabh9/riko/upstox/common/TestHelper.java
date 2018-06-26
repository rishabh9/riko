package com.github.rishabh9.riko.upstox.common;

import okhttp3.HttpUrl;

public class TestHelper {

    private static TestHelper instance = new TestHelper();

    public static TestHelper getInstance() {
        return instance;
    }

    private TestHelper() {}

    private HttpUrl baseUrl;

    public void setBaseUrl(HttpUrl url) {
        baseUrl = url;
    }

    public HttpUrl getBaseUrl() {
        return baseUrl;
    }
}
