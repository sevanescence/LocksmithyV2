package com.makotomiyamoto.locksmithyv2.impl.adapter;

import com.google.gson.*;
import com.makotomiyamoto.locksmithyv2.lib.gson.adapter.JsonSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.lib.lock.insecure.LockableContainer;
import com.makotomiyamoto.locksmithyv2.lib.util.GsonManager;
import org.bukkit.OfflinePlayer;

import java.lang.reflect.Type;

public class LockableContainerSerializationAdapter extends JsonSerializationAdapter<LockableContainer> {
    @Override
    public JsonElement serialize(LockableContainer src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        // TODO remove static reference to OfflinePlayer class and see if an issue occurs
        object.add("owner", GsonManager.getGson().toJsonTree(src.getOwner(), OfflinePlayer.class));
        object.add("location", GsonManager.getGson().toJsonTree(src.getLockLocation()));
        object.addProperty("uuid", src.getLockUUID().toString());
        // TODO this

        return object;
    }

    @Override
    public LockableContainer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return super.deserialize(json, typeOfT, context);
    }
}
