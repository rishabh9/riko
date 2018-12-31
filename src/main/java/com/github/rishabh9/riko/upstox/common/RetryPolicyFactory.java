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

import net.jodah.failsafe.RetryPolicy;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;

/**
 * A simple factory to create policies for retrying when an API call fails.
 * Riko uses the open source <a href="https://github.com/jhalterman/failsafe">Failsafe</a>
 * library for implementing retries.
 *
 * @see RikoRetryPolicyFactory
 */
public interface RetryPolicyFactory {

    /**
     * @return A {@link RetryPolicy} that expresses when retries should be performed.
     * An {@code empty} {@link RetryPolicy} indicates retries should be disabled.
     */
    @Nonnull
    Optional<RetryPolicy> createRetryPolicy();

    /**
     * @return A {@link ScheduledExecutorService} to allow for asynchronous executions.
     * Cannot be {@code null}.
     */
    @Nonnull
    ScheduledExecutorService createExecutorService();
}
