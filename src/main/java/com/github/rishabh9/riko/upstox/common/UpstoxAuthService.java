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

import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;

/**
 * The implementations of this interface are responsible to <em>always return
 * the latest version</em> of the {@link ApiCredentials} and {@link AccessToken}.
 */
public interface UpstoxAuthService {

    /**
     * @return The latest API Key and API Secret of an Upstox account.
     */
    ApiCredentials getApiCredentials();

    /**
     * @return The latest access token
     */
    AccessToken getAccessToken();
}
