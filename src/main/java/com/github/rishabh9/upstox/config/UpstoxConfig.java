package com.github.rishabh9.upstox.config;

import org.aeonbits.owner.Config;

/**
 * All configuration related to Upstox API is managed here.
 */
public interface UpstoxConfig extends Config {

    /**
     * @return The value configured for the property key - <code>upstox.api.key</code>.
     * Empty string if none found.
     */
    @Key("upstox.api.key")
    @DefaultValue("")
    // prevent NPE
    String apiKeyProp();

    /**
     * @return The value configured for the environment variable - <code>UPSTOX_API_KEY</code>
     * Empty string if none found.
     */
    @Key("UPSTOX_API_KEY")
    @DefaultValue("")
    // prevent NPE
    String apiKeyEnv();

    /**
     * @return The value configured for the property key - <code>upstox.api.secret</code>.
     * Empty string if none found.
     */
    @Key("upstox.api.secret")
    @DefaultValue("")
    // prevent NPE
    String apiSecretProp();

    /**
     * @return The value configured for the environment variable - <code>UPSTOX_API_SECRET</code>
     * Empty string if none found.
     */
    @Key("UPSTOX_API_SECRET")
    @DefaultValue("")
    // prevent NPE
    String apiSecretEnv();

    /**
     * @return The value configured for the property key - <code>upstox.api.redirect.uri</code>.
     * Empty string if none found.
     */
    @Key("upstox.api.redirect.uri")
    @DefaultValue("")
    // prevent NPE
    String redirectUriProp();

    /**
     * @return The value configured for the environment variable - <code>UPSTOX_API_REDIRECT_URI</code>
     * Empty string if none found.
     */
    @Key("UPSTOX_API_REDIRECT_URI")
    @DefaultValue("")
    // prevent NPE
    String redirectUriEnv();

}
