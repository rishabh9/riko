package com.github.rishabh9.upstox.config;

import com.github.rishabh9.riko.exceptions.ConfigurationMissingException;
import com.google.common.base.Strings;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class UpstoxConfigUtility {

    private static final Logger logger = LogManager.getLogger(UpstoxConfigUtility.class);

    private static UpstoxConfigUtility ourInstance = new UpstoxConfigUtility();

    private UpstoxConfig upstoxConfig;

    public static UpstoxConfigUtility getInstance() {
        return ourInstance;
    }

    private UpstoxConfigUtility() {
        logger.debug("Loading Upstox configuration/properties");
        // Check if the Api Key and the redirect URL are configured
        Properties upstoxProps = new Properties();
        try {
            upstoxProps.load(this.getClass().getResourceAsStream("riko.properties"));
        } catch (IOException e) {
            logger.warn("'riko.properties' not found in classpath. Some properties may not be available.");
        }
        upstoxConfig = ConfigFactory.create(
                UpstoxConfig.class,
                System.getenv(), System.getProperties(), upstoxProps
        );
    }

    public String getApiKey() {
        if (!Strings.isNullOrEmpty(upstoxConfig.apiKeyEnv())) {
            return upstoxConfig.apiKeyEnv();
        }
        if (!Strings.isNullOrEmpty(upstoxConfig.apiKeyProp())) {
            return upstoxConfig.apiKeyProp();
        }
        throw new ConfigurationMissingException("Upstox API key is not configured.");
    }

    public String getApiSecret() {
        if (!Strings.isNullOrEmpty(upstoxConfig.apiSecretEnv())) {
            return upstoxConfig.apiSecretEnv();
        }
        if (!Strings.isNullOrEmpty(upstoxConfig.apiSecretProp())) {
            return upstoxConfig.apiSecretProp();
        }
        throw new ConfigurationMissingException("Upstox API secret is not configured.");
    }

    public String getRedirectUri() {
        if (!Strings.isNullOrEmpty(upstoxConfig.redirectUriEnv())) {
            return upstoxConfig.redirectUriEnv();
        }
        if (!Strings.isNullOrEmpty(upstoxConfig.redirectUriProp())) {
            return upstoxConfig.redirectUriProp();
        }
        throw new ConfigurationMissingException("Upstox API redirect URI is not configured.");
    }
}
