package com.github.rishabh9.riko.upstox.common.converters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.math.BigDecimal;

/**
 * Deserializer for Json, to support the custom data type {@link NumberString}.
 */
public class NumberStringDeserializer implements JsonDeserializer<NumberString> {

    @Override
    public NumberString deserialize(final JsonElement json,
                                    final Type typeOfT,
                                    final JsonDeserializationContext context)
            throws JsonParseException {

        BigDecimal value;
        try {
            // Is it a number?
            value = json.getAsJsonPrimitive().getAsBigDecimal();
        } catch (NumberFormatException | ClassCastException e) {
            // No it is a stupid empty string!
            value = null;
        }
        return new NumberString(value);
    }
}
