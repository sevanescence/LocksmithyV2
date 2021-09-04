package com.makotomiyamoto.locksmithyv2.core.adapter;

import com.google.gson.*;
import com.makotomiyamoto.locksmithyv2.lib.gson.adapter.JsonSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.lib.lock.Lockable;
import com.makotomiyamoto.locksmithyv2.lib.lock.insecure.LockableContainer;
import com.makotomiyamoto.locksmithyv2.lib.lock.insecure.LockablePairContainer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LockableListSerializationAdapter extends JsonSerializationAdapter<List<Lockable>> {
    @Override
    public JsonElement serialize(List<Lockable> src, Type typeOfSrc, JsonSerializationContext context) {
        return super.serialize(src, typeOfSrc, context);
    }

    @Override
    public List<Lockable> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Lockable> lockables = new ArrayList<>();
        JsonArray array = json.getAsJsonArray();

        array.forEach(object -> {
            if (((JsonObject) object).get("pair") != null) {
                lockables.add(context.deserialize(object, LockablePairContainer.class));
            } else if (((JsonObject) object).get("picked") != null) {
                lockables.add(context.deserialize(object, LockableContainer.class));
            }
        });

        return lockables;
    }
}
