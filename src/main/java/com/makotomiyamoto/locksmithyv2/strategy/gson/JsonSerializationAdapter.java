package com.makotomiyamoto.locksmithyv2.strategy.gson;

import com.google.gson.*;

import java.lang.reflect.Type;

public abstract class JsonSerializationAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
