package com.makotomiyamoto.locksmithyv2.impl.adapter;

import com.google.gson.*;
import com.makotomiyamoto.locksmithyv2.lib.gson.adapter.JsonSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.lib.lock.insecure.LockableContainer;

import java.lang.reflect.Type;

public class LockableContainerSerializationAdapter extends JsonSerializationAdapter<LockableContainer> {
    @Override
    public JsonElement serialize(LockableContainer src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();


        return object;
    }

    @Override
    public LockableContainer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return super.deserialize(json, typeOfT, context);
    }
}
