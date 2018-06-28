package com.github.rishabh9.riko.upstox.common;

import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.AuthHeaders;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Parent class for every Service class. Holds common methods.
 */
public abstract class Service {

    private static final Logger log = LogManager.getLogger(Service.class);

    protected final AccessToken accessToken;
    protected final ApiCredentials credentials;

    /**
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     */
    public Service(@Nonnull final AccessToken accessToken,
                   @Nonnull final ApiCredentials credentials) {

        this.accessToken = Objects.requireNonNull(accessToken);
        this.credentials = Objects.requireNonNull(credentials);
    }

    protected <T> T prepareServiceApi(@Nonnull final Class<T> type) {

        log.debug("Preparing service API: {}", type.getName());
        final String token = accessToken.getType() + " " + accessToken.getToken();
        return ServiceGenerator.getInstance()
                .createService(type, new AuthHeaders(token, credentials.getApiKey()));
    }
}
