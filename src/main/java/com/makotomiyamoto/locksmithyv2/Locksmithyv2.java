package com.makotomiyamoto.locksmithyv2;

import com.makotomiyamoto.locksmithyv2.executors.debug.GetPlayerPosition;
import com.makotomiyamoto.locksmithyv2.lock.Lockable;
import com.makotomiyamoto.locksmithyv2.strategy.gson.impl.ChunkSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.strategy.gson.impl.LocationSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.strategy.gson.impl.OfflinePlayerSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.util.GsonManager;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

public final class Locksmithyv2 extends JavaPlugin {
    private static final HashMap<Chunk, HashMap<Location, Lockable>> lockedChests = new HashMap<>();
    public static String prefixString(String string) {
        return "[LocksmithyV2] " + string;
    }
    // TODO location references should be sorted by folder and location
    // e.g. /locksmithyv2/locks/chunk_x_z/Lockable#getUUID().json
    // resolve all the chunks and locations from storage at startup
    // TODO asynchronously save cache every 15 minutes (as a bukkit task)

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Starting LocksmithyV2...");
        GsonManager.registerSerializationAdapter(new OfflinePlayerSerializationAdapter());
        GsonManager.registerSerializationAdapter(new LocationSerializationAdapter());
        GsonManager.registerSerializationAdapter(new ChunkSerializationAdapter());

        Objects.requireNonNull(this.getCommand("ploc")).setExecutor(new GetPlayerPosition());;

        System.out.println("LocksmithyV2 enabled without any problems.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static HashMap<Chunk, HashMap<Location, Lockable>> getLockedChests() {
        return lockedChests;
    }
}
