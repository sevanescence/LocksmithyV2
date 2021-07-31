package com.makotomiyamoto.locksmithyv2.util;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public abstract class GsonManager {
    public static final Gson GSON;
    private static final JsonSerializer<Player> playerJsonSerializer;
    private static final JsonDeserializer<Player> playerJsonDeserializer;
    private static final JsonSerializer<Location> locationJsonSerializer;
    private static final JsonDeserializer<Location> locationJsonDeserializer;
    static {
        GsonBuilder staticGsonConfiguration = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping();

        playerJsonSerializer = (src, typeOfSrc, context) -> {
            JsonObject jsonBukkitPlayer = new JsonObject();

            jsonBukkitPlayer.addProperty("uuid", src.getUniqueId().toString());

            return jsonBukkitPlayer;
        };
        playerJsonDeserializer = (json, typeOfT, context) -> {
            JsonObject jsonBukkitPlayerContainer = json.getAsJsonObject();

            UUID playerUniqueId = UUID.fromString(jsonBukkitPlayerContainer.get("uuid").getAsString());
            Player player = Bukkit.getPlayer(playerUniqueId);
            assert player != null;

            return player;
        };
        staticGsonConfiguration.registerTypeAdapter(Player.class, playerJsonSerializer);
        staticGsonConfiguration.registerTypeAdapter(Player.class, playerJsonDeserializer);

        locationJsonSerializer = (src, typeOfSrc, context) -> {
            JsonObject jsonBukkitLocation = new JsonObject();

            jsonBukkitLocation.addProperty("x", src.getBlockX());
            jsonBukkitLocation.addProperty("y", src.getBlockY());
            jsonBukkitLocation.addProperty("z", src.getBlockZ());
            jsonBukkitLocation.addProperty("world", Objects.requireNonNull(src.getWorld()).getUID().toString());

            return jsonBukkitLocation;
        };
        locationJsonDeserializer = (json, typeOfT, context) -> {
            JsonObject jsonBukkitLocationContainer = json.getAsJsonObject();

            double x = jsonBukkitLocationContainer.get("x").getAsDouble(),
                    y = jsonBukkitLocationContainer.get("y").getAsDouble(),
                    z = jsonBukkitLocationContainer.get("z").getAsDouble();
            World world = Bukkit.getWorld(UUID.fromString(jsonBukkitLocationContainer.get("world").getAsString()));

            return new Location(world, x, y, z);
        };
        staticGsonConfiguration.registerTypeAdapter(Location.class, locationJsonSerializer);
        staticGsonConfiguration.registerTypeAdapter(Location.class, locationJsonDeserializer);

        GSON = staticGsonConfiguration.create();
    }
}
