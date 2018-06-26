package com.github.rishabh9.riko.upstox.common;

import com.github.rishabh9.riko.upstox.common.converters.NumberString;
import com.github.rishabh9.riko.upstox.common.converters.NumberStringDeserializer;
import com.github.rishabh9.riko.upstox.common.converters.NumberStringSerializer;
import com.github.rishabh9.riko.upstox.common.interceptors.AuthenticationInterceptor;
import com.github.rishabh9.riko.upstox.common.models.AuthHeaders;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ServiceGenerator {

    private static final Logger log = LogManager.getLogger(ServiceGenerator.class);

    private static final HttpUrl API_BASE_URL = getBaseUrl();

    private static HttpUrl getBaseUrl() {
        HttpUrl url = TestHelper.getInstance().getBaseUrl();
        if (null == url) {
            url = HttpUrl.parse("https://api.upstox.com/");
        }
        return url;
    }

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(NumberString.class, new NumberStringSerializer())
            .registerTypeAdapter(NumberString.class, new NumberStringDeserializer())
            .create();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(Java8CallAdapterFactory.create());

    private static Retrofit retrofit = builder.build();

    /**
     * Create service without authentication.
     *
     * @param serviceClass The Service
     * @param <S>          The type of Service
     * @return The retrofitted service
     */
    public static <S> S createService(@Nonnull final Class<S> serviceClass) {

        log.debug("Creating service without authentication");
        return createService(serviceClass, null, null);
    }

    /**
     * Create service with Basic Authentication
     *
     * @param serviceClass The Service
     * @param username     Username for basic authentication
     * @param password     Password for basic authentication
     * @param <S>          The type of Service
     * @return The retrofitted service
     */
    public static <S> S createService(@Nonnull final Class<S> serviceClass,
                                      @Nullable final String username,
                                      @Nullable final String password) {

        if (!Strings.isNullOrEmpty(username)
                && !Strings.isNullOrEmpty(password)) {
            final String authToken = Credentials.basic(username, password);
            log.debug("Creating service with Basic authentication");
            return createService(serviceClass, new AuthHeaders(authToken, username));
        }
        // Setup request headers without any auth
        return createService(serviceClass, null);
    }

    /**
     * Create service with Token Authorization
     *
     * @param serviceClass The Service
     * @param headers      The Authentication headers
     * @param <S>          The type of Service
     * @return The retrofitted service
     */
    public static <S> S createService(@Nonnull final Class<S> serviceClass,
                                      @Nullable final AuthHeaders headers) {

        enableAuthentication(headers);
        if (log.isDebugEnabled()) {
            enableHttpLogging();
        }
        log.debug("Creating service with authorization headers");
        return retrofit.create(serviceClass);
    }

    private static void enableAuthentication(final AuthHeaders headers) {

        if (null != headers
                && !Strings.isNullOrEmpty(headers.getToken())
                && !Strings.isNullOrEmpty(headers.getApiKey())) {

            log.debug("Adding auth headers interceptor");
            addInterceptor(
                    new AuthenticationInterceptor(
                            headers.getToken(), headers.getApiKey()));
        }
    }

    private static void enableHttpLogging() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // Set the desired log level
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        log.debug("Adding HTTP logging interceptor");
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
