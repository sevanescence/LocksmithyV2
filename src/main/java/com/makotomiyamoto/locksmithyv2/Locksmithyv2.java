package com.makotomiyamoto.locksmithyv2;

import com.makotomiyamoto.locksmithyv2.lock.Lockable;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Locksmithyv2 extends JavaPlugin {
    private final HashMap<Chunk, HashMap<Location, Lockable>> lockedChests = new HashMap<>();
    // TODO location references should be sorted by folder and location
    // e.g. /locksmithyv2/locks/chunk_x_z/Lockable#getUUID().json
    // resolve all the chunks and locations from storage at startup
    // TODO asynchronously save cache every 15 minutes (as a bukkit task)

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Starting LocksmithyV2...");
        Player player = Bukkit.getPlayer("MiyamotoMakoto");
        System.out.println("LocksmithyV2 enabled without any problems.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
