package com.github.rishabh9.riko.upstox.common;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * A class for Async service calls.
 *
 * @param <T> The type of the response class expected from a Service.
 */
public interface CallMe<T> {

    void onSuccess(T data);

    /**
     * In an aysnc call, this method is called when an error occurs while making a request to Upstox.<br>
     * You either get the error string (maybe be Json string) or the Throwable. Never both.
     *
     * @param error
     * @param t
     */
    void onFailure(@Nonnull String error, @Nullable Throwable t);
}
