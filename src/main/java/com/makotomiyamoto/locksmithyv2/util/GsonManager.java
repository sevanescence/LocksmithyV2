package com.makotomiyamoto.locksmithyv2.util;

import com.google.gson.*;
import com.makotomiyamoto.locksmithyv2.strategy.gson.JsonSerializationAdapter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class GsonManager {
    private static Gson gson;
    private static final GsonBuilder gsonBuilder = new GsonBuilder();

    public static <T> void registerSerializationAdapter(JsonSerializationAdapter<T> adapter) {
        Type type = ((ParameterizedType) adapter.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        gsonBuilder.registerTypeAdapter(type, adapter);
        gson = gsonBuilder.create();
    }

    public static Gson getGson() {
        return gson;
    }

//    public static final Gson GSON;
//
//    private static final JsonSerializer<Player> playerJsonSerializer;
//    private static final JsonDeserializer<Player> playerJsonDeserializer;
//    private static final JsonSerializer<Location> locationJsonSerializer;
//    private static final JsonDeserializer<Location> locationJsonDeserializer;
//    private static final JsonSerializer<Chunk> chunkJsonSerializer;
//    private static final JsonDeserializer<Chunk> chunkJsonDeserializer;
//    static {
//        GsonBuilder staticGsonConfiguration = new GsonBuilder();
//
//        playerJsonSerializer = (src, typeOfSrc, context) -> {
//            JsonObject jsonBukkitPlayer = new JsonObject();
//
//            jsonBukkitPlayer.addProperty("uuid", src.getUniqueId().toString());
//
//            return jsonBukkitPlayer;
//        };
//        playerJsonDeserializer = (json, typeOfT, context) -> {
//            JsonObject jsonBukkitPlayerContainer = json.getAsJsonObject();
//
//            UUID playerUniqueId = UUID.fromString(jsonBukkitPlayerContainer.get("uuid").getAsString());
//            Player player = Bukkit.getPlayer(playerUniqueId);
//            assert player != null;
//
//            return player;
//        };
//        staticGsonConfiguration.registerTypeAdapter(Player.class, playerJsonSerializer);
//        staticGsonConfiguration.registerTypeAdapter(Player.class, playerJsonDeserializer);
//
//        locationJsonSerializer = (src, typeOfSrc, context) -> {
//            JsonObject jsonBukkitLocation = new JsonObject();
//
//            jsonBukkitLocation.addProperty("x", src.getBlockX());
//            jsonBukkitLocation.addProperty("y", src.getBlockY());
//            jsonBukkitLocation.addProperty("z", src.getBlockZ());
//            jsonBukkitLocation.addProperty("world", Objects.requireNonNull(src.getWorld()).getUID().toString());
//
//            return jsonBukkitLocation;
//        };
//        locationJsonDeserializer = (json, typeOfT, context) -> {
//            JsonObject jsonBukkitLocationContainer = json.getAsJsonObject();
//
//            double x = jsonBukkitLocationContainer.get("x").getAsDouble(),
//                    y = jsonBukkitLocationContainer.get("y").getAsDouble(),
//                    z = jsonBukkitLocationContainer.get("z").getAsDouble();
//            World world = Bukkit.getWorld(UUID.fromString(jsonBukkitLocationContainer.get("world").getAsString()));
//
//            return new Location(world, x, y, z);
//        };
//        staticGsonConfiguration.registerTypeAdapter(Location.class, locationJsonSerializer);
//        staticGsonConfiguration.registerTypeAdapter(Location.class, locationJsonDeserializer);
//
//        chunkJsonSerializer = (src, typeOfSrc, context) -> {
//            JsonObject jsonBukkitChunk = new JsonObject();
//
//            jsonBukkitChunk.addProperty("x", src.getX());
//            jsonBukkitChunk.addProperty("z", src.getZ());
//            jsonBukkitChunk.addProperty("world", src.getWorld().getUID().toString());
//
//            return jsonBukkitChunk;
//        };
//        chunkJsonDeserializer = (json, typeOfT, context) -> {
//            JsonObject jsonBukkitChunkContainer = json.getAsJsonObject();
//
//            int x = jsonBukkitChunkContainer.get("x").getAsInt();
//            int z = jsonBukkitChunkContainer.get("z").getAsInt();
//            UUID worldUUID = UUID.fromString(jsonBukkitChunkContainer.get("world").getAsString());
//
//            return Objects.requireNonNull(Bukkit.getWorld(worldUUID)).getChunkAt(x, z);
//        };
//        staticGsonConfiguration.registerTypeAdapter(Chunk.class, chunkJsonSerializer);
//        staticGsonConfiguration.registerTypeAdapter(Chunk.class, chunkJsonDeserializer);
//
//        GSON = staticGsonConfiguration.create();
//    }
}
