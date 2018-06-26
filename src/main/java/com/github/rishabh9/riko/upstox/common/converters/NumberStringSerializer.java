package com.github.rishabh9.riko.upstox.common.converters;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Serializer for Json, to support the custom data type {@link NumberString}.
 */
public class NumberStringSerializer implements JsonSerializer<NumberString> {

    @Override
    public JsonElement serialize(final NumberString src,
                                 final Type typeOfSrc,
                                 final JsonSerializationContext context) {
        if (null == src) {
            return new JsonPrimitive("");
        }
        return new JsonPrimitive(src.value());
    }
}
