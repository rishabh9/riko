/*
 * MIT License
 *
 * Copyright (c) 2018 Rishabh Joshi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.rishabh9.riko.upstox.common;

import com.github.rishabh9.riko.upstox.common.converters.AlwaysListTypeAdapterFactory;
import com.github.rishabh9.riko.upstox.common.converters.NumberString;
import com.github.rishabh9.riko.upstox.common.converters.NumberStringDeserializer;
import com.github.rishabh9.riko.upstox.common.converters.NumberStringSerializer;
import com.github.rishabh9.riko.upstox.common.interceptors.AuthenticationInterceptor;
import com.github.rishabh9.riko.upstox.common.interceptors.HttpErrorLoggingInterceptor;
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
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.github.rishabh9.riko.upstox.common.constants.PropertyKeys.*;

public class ServiceGenerator {

    private static final Logger log = LogManager.getLogger(ServiceGenerator.class);

    private ServiceGenerator() {
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(NumberString.class, new NumberStringSerializer())
                .registerTypeAdapter(NumberString.class, new NumberStringDeserializer())
                .registerTypeAdapterFactory(new AlwaysListTypeAdapterFactory())
                .create();
        final String readTimeout = System.getProperty(RIKO_READ_TIMEOUT);
        final String writeTimeout = System.getProperty(RIKO_WRITE_TIMEOUT);
        final String connectTimeout = System.getProperty(RIKO_CONNECT_TIMEOUT);
        this.httpClient = new OkHttpClient.Builder();
        if (!Strings.isNullOrEmpty(readTimeout)) {
            this.httpClient.readTimeout(
                    Long.parseLong(readTimeout), TimeUnit.SECONDS);
        }
        if (!Strings.isNullOrEmpty(connectTimeout)) {
            this.httpClient.connectTimeout(
                    Long.parseLong(connectTimeout), TimeUnit.SECONDS);
        }
        if (!Strings.isNullOrEmpty(writeTimeout)) {
            this.httpClient.writeTimeout(
                    Long.parseLong(writeTimeout), TimeUnit.SECONDS);
        }
        final HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme(System.getProperty(RIKO_SERVER_SCHEME, RIKO_SERVER_SCHEME_DEFAULT))
                .host(System.getProperty(RIKO_SERVER_URL, RIKO_SERVER_URL_DEFAULT));
        final String port = System.getProperty(RIKO_SERVER_PORT);
        if (!Strings.isNullOrEmpty(port)) {
            urlBuilder.port(Integer.parseInt(port));
        }
        this.builder =
                new Retrofit.Builder()
                        .baseUrl(Objects.requireNonNull(urlBuilder.build()))
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(Java8CallAdapterFactory.create())
                        .client(httpClient.build());
        this.retrofit = builder.build();
    }

    private static final ServiceGenerator instance = new ServiceGenerator();

    public static ServiceGenerator getInstance() {
        return ServiceGenerator.instance;
    }

    private OkHttpClient.Builder httpClient;

    private Retrofit.Builder builder;

    private Retrofit retrofit;

    /**
     * A helper method for unit testing, allowing for random base URLs to be used per test.
     * <em>SHOULD NOT BE USED ON OUTSIDE OF UNIT TESTS</em>
     *
     * @param url The base URL to use.
     */
    public void rebuildWithUrl(HttpUrl url) {
        builder.client(httpClient.build());
        builder.baseUrl(url);
        retrofit = builder.build();
    }

    /**
     * Create service without authentication.
     *
     * @param serviceClass The Service
     * @param <S>          The type of Service
     * @return The retrofitted service
     */
    public <S> S createService(@Nonnull final Class<S> serviceClass) {

        log.debug("Creating service without authentication");
        return createService(Objects.requireNonNull(serviceClass), null);
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
    public <S> S createService(@Nonnull final Class<S> serviceClass,
                               @Nonnull final String username,
                               @Nonnull final String password) {

            final String authToken = Credentials.basic(username, password);
            log.debug("Creating service with Basic authentication");
            return createService(
                    Objects.requireNonNull(serviceClass),
                    new AuthHeaders(Objects.requireNonNull(authToken), Objects.requireNonNull(username)));
    }

    /**
     * Create service with Token Authorization
     *
     * @param serviceClass The Service
     * @param headers      The Authentication headers
     * @param <S>          The type of Service
     * @return The retrofitted service
     */
    public <S> S createService(@Nonnull final Class<S> serviceClass,
                               @Nullable final AuthHeaders headers) {

        httpClient.interceptors().clear(); // Fix StackOverFlowError

        enableAuthentication(headers);
        enableHttpErrorLogging();
        if (log.isDebugEnabled()) {
            enableHttpLogging();
        }
        log.debug("Creating service with authorization headers");
        return retrofit.create(Objects.requireNonNull(serviceClass));
    }

    private void enableHttpErrorLogging() {
        addInterceptor(new HttpErrorLoggingInterceptor());
    }

    private void enableAuthentication(final AuthHeaders headers) {

        if (null != headers
                && !Strings.isNullOrEmpty(headers.getToken())
                && !Strings.isNullOrEmpty(headers.getApiKey())) {

            log.debug("Adding auth headers interceptor");
            addInterceptor(
                    new AuthenticationInterceptor(
                            headers.getToken(), headers.getApiKey()));
        }
    }

    private void enableHttpLogging() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // Set the desired log level
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        log.debug("Adding HTTP logging interceptor");
        addInterceptor(interceptor);
    }

    private void addInterceptor(Interceptor interceptor) {

        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor);

            builder.client(httpClient.build());
            retrofit = builder.build();
        }
    }

}
