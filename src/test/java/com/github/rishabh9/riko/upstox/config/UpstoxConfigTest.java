package com.github.rishabh9.riko.upstox.config;

import com.github.rishabh9.riko.config.UpstoxConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("A special test case")
public class UpstoxConfigTest {

    private UpstoxConfig upstoxConfig;

    @BeforeEach
    void init() {
        System.setProperty("redirect.url", "https://www.github.com/");
        upstoxConfig = ConfigFactory.create(UpstoxConfig.class, System.getenv(), System.getProperties());
    }

    @Test
    public void testReadUpstoxEnvironmentVariable() {
        assertEquals("https://www.google.com/", upstoxConfig.apiSecretEnv());
    }

    @Test
    public void testReadUpstoxSystemProperties() {
        assertEquals("https://www.github.com/", upstoxConfig.apiSecretProp());
    }
}
