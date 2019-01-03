/*
 * MIT License
 *
 * Copyright (c) 2019 Rishabh Joshi
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

package com.github.rishabh9.riko.upstox.login;

import com.github.rishabh9.riko.upstox.common.RetryPolicyFactory;
import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.UpstoxAuthService;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.login.models.TokenRequest;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.RateLimiter;
import net.jodah.failsafe.Failsafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.github.rishabh9.riko.upstox.common.constants.RateLimits.LOGIN_RATE_LIMIT;

@SuppressWarnings("UnstableApiUsage")
public class LoginService extends Service {

    private static final Logger log = LogManager.getLogger(LoginService.class);

    private static final RateLimiter loginRateLimiter = RateLimiter.create(LOGIN_RATE_LIMIT);

    /**
     * @param upstoxAuthService The service to retrieve authentication details.
     */
    public LoginService(@Nonnull final UpstoxAuthService upstoxAuthService,
                        @Nonnull final RetryPolicyFactory retryPolicyFactory) {

        super(upstoxAuthService, retryPolicyFactory);
    }

    /**
     * Retrieves the access code from Upstox Authorization URL through a synchronous call.<br>
     *
     * @param request The TokenRequest object containing all fields,
     *                including the access code received after authenticating the user on Upstox site.
     * @return An 'optional' AccessToken. Return object is empty in case of error.
     */
    public CompletableFuture<AccessToken> getAccessToken(@Nonnull final TokenRequest request) {

        if (Strings.isNullOrEmpty(Objects.requireNonNull(request).getCode())) {
            throw new IllegalArgumentException(
                    "Missing value for authorization code. Code: " + request.getCode());
        }

        // Create a very simple REST adapter which points the Upstox API endpoint.
        final LoginApi loginApi =
                ServiceGenerator.getInstance().createService(
                        LoginApi.class,
                        upstoxAuthService.getApiCredentials().getApiKey(),
                        upstoxAuthService.getApiCredentials().getApiSecret());

        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET AccessToken.", failure))
                .onSuccess(response -> log.debug("GET AccessToken successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET AccessToken, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    loginRateLimiter.acquire(1);
                    return loginApi.getAccessToken(request);
                });
    }
}
