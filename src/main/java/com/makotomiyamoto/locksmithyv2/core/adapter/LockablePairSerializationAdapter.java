package com.makotomiyamoto.locksmithyv2.core.adapter;

import com.google.gson.*;
import com.makotomiyamoto.locksmithyv2.lib.gson.adapter.JsonSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.lib.lock.insecure.LockablePairContainer;
import com.makotomiyamoto.locksmithyv2.lib.lock.insecure.LockablePairContainerFactory;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.lang.reflect.Type;
import java.util.UUID;

public class LockablePairSerializationAdapter extends JsonSerializationAdapter<LockablePairContainer> {
    @Override
    public JsonElement serialize(LockablePairContainer src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("owner", src.getOwner().getUniqueId().toString());
        object.add("location", context.serialize(src.getLockLocation()));
        //object.add("pair", context.serialize(src.getPairLockable()));
        object.addProperty("uuid", src.getLockUUID().toString());
        object.addProperty("locked", src.isLocked());
        object.addProperty("picked", src.isPicked());

        // serialize pair
        LockablePairContainer pair = src.getPairLockable();
        JsonObject pairObject = new JsonObject();
        pairObject.addProperty("owner", pair.getOwner().getUniqueId().toString());
        pairObject.add("location", context.serialize(pair.getLockLocation()));
        pairObject.addProperty("uuid", pair.getLockUUID().toString());
        pairObject.addProperty("locked", pair.isLocked());
        pairObject.addProperty("picked", pair.isPicked());

        object.add("pair", pairObject);

        return object;
    }

    @Override
    public LockablePairContainer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = (JsonObject) json;

        OfflinePlayer owner = context.deserialize(object.get("owner"), OfflinePlayer.class);
        Location location = context.deserialize(object.get("location"), Location.class);
        //LockablePairContainer pair = context.deserialize(object.get("pair"), LockablePairContainer.class);
        UUID uuid = UUID.fromString(object.get("uuid").getAsString());
        boolean locked = object.get("locked").getAsBoolean();
        boolean picked = object.get("picked").getAsBoolean();

        // TODO couple the two pairs, they should have the same state anyway
        // deserialize pair
        JsonObject pairObject = (JsonObject) object.get("pair");
        OfflinePlayer pairOwner = context.deserialize(pairObject.get("owner"), OfflinePlayer.class);
        Location pairLocation = context.deserialize(pairObject.get("location"), Location.class);
        UUID pairUuid = UUID.fromString(pairObject.get("owner").getAsString());
        boolean pairLocked = pairObject.get("locked").getAsBoolean();
        boolean pairPicked = pairObject.get("picked").getAsBoolean();

        LockablePairContainer pair = LockablePairContainerFactory.getLockablePairContainer(pairOwner, pairLocation, pairUuid, pairLocked, pairPicked);
        var lockable = LockablePairContainerFactory.getLockablePairContainer(owner, location, uuid, locked, picked);
        lockable.setPairLockable(pair);
        lockable.getPairLockable().setPairLockable(lockable);

        return lockable;
    }
}
