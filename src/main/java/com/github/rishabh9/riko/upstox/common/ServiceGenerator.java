package com.github.rishabh9.riko.upstox.common;

import com.github.rishabh9.riko.upstox.common.interceptors.AuthenticationInterceptor;
import com.github.rishabh9.riko.upstox.common.models.AuthHeaders;
import com.github.rishabh9.riko.upstox.common.models.AuthHeadersBuilder;
import com.google.common.base.Strings;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Nonnull;

public class ServiceGenerator {

    private static final Logger log = LogManager.getLogger(ServiceGenerator.class);

    private static final String API_BASE_URL = "https://api.upstox.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(final Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    /**
     * Create service with Basic Authentication
     *
     * @param serviceClass The Service
     * @param username Username for basic authentication
     * @param password Password for basic authentication
     * @param <S> The Service
     * @return The retrofitted service
     */
    public static <S> S createService(final Class<S> serviceClass, final String username, final String password) {
        AuthHeadersBuilder builder = new AuthHeadersBuilder();
        if (!Strings.isNullOrEmpty(username)
                && !Strings.isNullOrEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(
                    serviceClass,
                    builder
                            .withToken(authToken)
                            .withApiKey(username)
                            .build());
        }
        return createService(serviceClass, builder.build());
    }

    /**
     * Create service with Token Authorization
     *
     * @param serviceClass The Service
     * @param headers The Authentication headers
     * @param <S> The Service
     * @return The retrofitted service
     */
    public static <S> S createService(final Class<S> serviceClass, @Nonnull final AuthHeaders headers) {
        enableAuthentication(headers);
        if (log.isTraceEnabled()) {
            enableHttpLogging();
        }
        return retrofit.create(serviceClass);
    }

    private static void enableAuthentication(final AuthHeaders headers) {
        if (!Strings.isNullOrEmpty(headers.getToken())
                && !Strings.isNullOrEmpty(headers.getApiKey())) {

            addInterceptor(
                    new AuthenticationInterceptor(
                            headers.getToken(),
                            headers.getApiKey()));
        }
    }

    private static void enableHttpLogging() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // Set your desired log level
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        addInterceptor(interceptor);
    }

    private static void addInterceptor(Interceptor interceptor) {
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor);

            builder.client(httpClient.build());
            retrofit = builder.build();
        }
    }

}
