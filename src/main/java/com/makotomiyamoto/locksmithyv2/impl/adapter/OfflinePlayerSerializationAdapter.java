package com.makotomiyamoto.locksmithyv2.impl.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.makotomiyamoto.locksmithyv2.lib.gson.adapter.JsonSerializationAdapter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.lang.reflect.Type;
import java.util.UUID;

public class OfflinePlayerSerializationAdapter extends JsonSerializationAdapter<OfflinePlayer> {
    @Override
    public JsonElement serialize(OfflinePlayer player, Type typeofSrc, JsonSerializationContext context) {
        JsonObject playerJsonObject = new JsonObject();

        playerJsonObject.addProperty("uuid", player.getUniqueId().toString());

        return playerJsonObject;
    }

    @Override
    public OfflinePlayer deserialize(JsonElement jsonElement, Type typeOfElement, JsonDeserializationContext context) {
        UUID playerUid = UUID.fromString(jsonElement.getAsString());

        return Bukkit.getOfflinePlayer(playerUid);
    }
}
