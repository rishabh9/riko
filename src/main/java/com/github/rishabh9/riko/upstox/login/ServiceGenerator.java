package com.github.rishabh9.riko.upstox.login;

import com.github.rishabh9.riko.upstox.login.interceptors.AuthenticationInterceptor;
import com.google.common.base.Strings;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String API_BASE_URL = "https://api.upstox.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (!Strings.isNullOrEmpty(username)
                && !Strings.isNullOrEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        enableAuthentication(authToken);
        enableLogging();
        return retrofit.create(serviceClass);
    }

    private static void enableAuthentication(String authToken) {
        if (!Strings.isNullOrEmpty(authToken)) {
            AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(authenticationInterceptor)) {
                httpClient.addInterceptor(authenticationInterceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
    }

    private static void enableLogging() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // Set your desired log level
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (!httpClient.interceptors().contains(loggingInterceptor)) {
            httpClient.addInterceptor(loggingInterceptor);

            builder.client(httpClient.build());
            retrofit = builder.build();
        }
    }

}
