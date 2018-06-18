package com.github.rishabh9.riko.upstox.common.converters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class NumberStringDeserializer implements JsonDeserializer<NumberString> {

    @Override
    public NumberString deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
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
