package com.makotomiyamoto.locksmithyv2.impl.adapter;

import com.google.gson.*;
import com.makotomiyamoto.locksmithyv2.lib.gson.adapter.JsonSerializationAdapter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.lang.reflect.Type;
import java.util.UUID;

public class LocationSerializationAdapter extends JsonSerializationAdapter<Location> {
    @Override
    public JsonElement serialize(Location src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject locationJsonObject = new JsonObject();

        locationJsonObject.addProperty("x", src.getX());
        locationJsonObject.addProperty("y", src.getY());
        locationJsonObject.addProperty("z", src.getZ());
        locationJsonObject.addProperty("world", src.getWorld().getUID().toString());

        return locationJsonObject;
    }

    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject locationJsonObject = (JsonObject) json;

        int x = locationJsonObject.get("x").getAsInt();
        int y = locationJsonObject.get("y").getAsInt();
        int z = locationJsonObject.get("z").getAsInt();
        UUID world = UUID.fromString(locationJsonObject.get("world").getAsString());

        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}
