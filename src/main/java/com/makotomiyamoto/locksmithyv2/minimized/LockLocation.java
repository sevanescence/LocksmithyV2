package com.makotomiyamoto.locksmithyv2.minimized;

import com.google.gson.stream.JsonReader;
import com.makotomiyamoto.locksmithyv2.util.GsonManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;

public class LockLocation {
    private final int x, y, z;
    private final String world;

    public LockLocation(Location location) {
        x = location.getBlockX();
        y = location.getBlockY();
        z = location.getBlockZ();
        world = Objects.requireNonNull(location.getWorld()).getName();
    }

    public LockLocation(int x, int y, int z, String world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Location resolveBukkitLocation() {
        return Objects.requireNonNull(Bukkit.getWorld(world)).getBlockAt(x, y, z).getLocation();
    }

}
