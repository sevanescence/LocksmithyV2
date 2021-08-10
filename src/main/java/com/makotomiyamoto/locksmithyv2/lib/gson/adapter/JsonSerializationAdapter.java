package com.makotomiyamoto.locksmithyv2.lib.gson.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * A wrapper for {@link JsonSerializer JsonSerializer} and {@link JsonDeserializer JsonDeserializer}
 * which serves as a buffer for appending type adapter strategies to the
 * {@link com.makotomiyamoto.locksmithyv2.lib.util.GsonManager GsonManager} library.
 * @param <T> type of object whose strategy to overwrite
 *
 * @author MakotoMiyamoto
 */
public abstract class JsonSerializationAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    /**
     * {@link JsonSerializer#serialize(Object, Type, JsonSerializationContext)}
     */
    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }

    /**
     * {@link JsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)}
     */
    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }
}
