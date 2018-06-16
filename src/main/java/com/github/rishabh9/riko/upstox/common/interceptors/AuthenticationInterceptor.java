package com.github.rishabh9.riko.upstox.common.interceptors;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;
    private String apiKey;

    public AuthenticationInterceptor(String token, String apiKey) {
        this.authToken = token;
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("X-API-KEY", apiKey)
                .header("Authorization", authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}