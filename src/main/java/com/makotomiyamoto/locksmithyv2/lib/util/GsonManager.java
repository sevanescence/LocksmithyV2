package com.makotomiyamoto.locksmithyv2.lib.util;

import com.google.gson.*;
import com.makotomiyamoto.locksmithyv2.lib.gson.adapter.JsonSerializationAdapter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A utility class which serves as a wrapper for the Gson library
 * to manage type adapter overrides for objects. The built-in
 * GsonBuilder has html escaping disabled by default.
 *
 * @author MakotoMiyamoto
 */
public abstract class GsonManager {
    private static Gson gson;
    private static final GsonBuilder gsonBuilder = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting();

    /**
     * Flush the built-in GsonBuilder to append all of its new changes
     * to the Gson instance. Calling this is required after configuring
     * the built-in GsonManager or calling
     * {@link #registerSerializationAdapter(JsonSerializationAdapter)} or
     * {@link #registerSerializationHierarchyAdapter(JsonSerializationAdapter)}
     */
    public static void flush() {
        gson = gsonBuilder.create();
    }

    /**
     * <p>
     * Register a type adapter for serialization and deserialization.
     * When serializing an object whose adapter strategy is passed through
     * registerSerializationAdapter, if the instance passed is not a base
     * class, pass its static class type to Gson#toJson to avoid serializing
     * the object with the strategy of its base class.
     * </p>
     * <br/>
     * <p>
     * It is recommended to initialize every adapter strategy in a library
     * or project at startup time, because the instance of Gson managed
     * by GsonManager is reinitialized every time an adapter is registered
     * by the manager's GsonBuilder instance.
     * </p>
     * @param adapter Gson adapter strategy
     * @param <T> type of object whose serialization strategy is overwritten
     */
    public static <T> void registerSerializationAdapter(JsonSerializationAdapter<T> adapter) {
        Type type = ((ParameterizedType) adapter.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        gsonBuilder.registerTypeAdapter(type, adapter);
    }

    /**
     * <p>
     * Register a type adapter for serialization and deserialization.
     * Use this method instead of {@link #registerSerializationAdapter(JsonSerializationAdapter)}
     * if the object whose strategy is being overwritten is the child of another class.
     * If you get really weird parsing errors that are a bit difficult to find on
     * the internet, then this is probably the method you want to use.
     * </p>
     * @param adapter Gson adapter strategy
     * @param <T> type of object whose serialization strategy is overwritten
     * @see #registerSerializationAdapter(JsonSerializationAdapter) 
     */
    public static <T> void registerSerializationHierarchyAdapter(JsonSerializationAdapter<T> adapter) {
        Type type = ((ParameterizedType) adapter.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        gsonBuilder.registerTypeHierarchyAdapter((Class<?>) type, adapter);
    }

    /**
     * A wrapper to fetch a statically-scoped Gson instance that is managed
     * by the library. This operation is unsafe as this Gson instance is
     * reinitialized every time {@link #registerSerializationAdapter(JsonSerializationAdapter)
     * GsonManager.registerSerializationAdapter} is called.
     * @return static gson instance
     */
    public static Gson getGson() {
        return gson;
    }

    /**
     * Gets the GsonBuilder instance used by GsonManager. This can be used
     * to add new properties to the GsonManager wrapper if they are not
     * already set by the static library. GsonManager#gsonBuilder is final
     * and thus cannot be reinitialized.
     * @return the GsonBuilder instance
     */
    public static GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }
}
