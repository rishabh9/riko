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

package com.github.rishabh9.riko.upstox.common.constants;

public class PropertyKeys {
    public static final String RIKO_READ_TIMEOUT = "riko.read.timeout";
    public static final int RIKO_READ_TIMEOUT_DEFAULT = 10;

    public static final String RIKO_WRITE_TIMEOUT = "riko.write.timeout";
    public static final int RIKO_WRITE_TIMEOUT_DEFAULT = 10;

    public static final String RIKO_CONNECT_TIMEOUT = "riko.connect.timeout";
    public static final int RIKO_CONNECT_TIMEOUT_DEFAULT = 10;

    public static final String RIKO_SERVER_SCHEME = "riko.server.scheme";
    public static final String RIKO_SERVER_SCHEME_DEFAULT = "https";

    public static final String RIKO_SERVER_URL = "riko.server.url";
    public static final String RIKO_SERVER_URL_DEFAULT = "api.upstox.com";

    public static final String RIKO_SERVER_PORT = "riko.server.port";
    public static final int RIKO_SERVER_PORT_DEFAULT = 443;

    public static final String RIKO_WS_SERVER_SCHEME = "riko.ws.server.scheme";
    public static final String RIKO_WS_SERVER_SCHEME_DEFAULT = "https";

    public static final String RIKO_WS_SERVER_URL = "riko.ws.server.url";
    public static final String RIKO_WS_SERVER_URL_DEFAULT = "ws-api.upstox.com";

    public static final String RIKO_WS_SERVER_PORT = "riko.ws.server.port";
    public static final int RIKO_WS_SERVER_PORT_DEFAULT = 443;

    public static final String RIKO_WS_RECONNECT = "riko.ws.reconnect";
    public static final String RIKO_WS_RECONNECT_DEFAULT = "false";
}
