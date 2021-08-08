package com.makotomiyamoto.locksmithyv2.lock.insecure;

import com.makotomiyamoto.locksmithyv2.lock.InsecureLockable;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class LockedContainer implements InsecureLockable {
    private final OfflinePlayer owner;
    private final Location location;
    private final UUID uuid;
    boolean locked = true;
    boolean picked = false;

    public LockedContainer(OfflinePlayer player, Location location) {
        this.owner = player;
        this.location = location;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public OfflinePlayer getOwner() {
        return owner;
    }

    @Override
    public Location getLockLocation() {
        return location;
    }

    @Override
    public UUID getLockUUID() {
        return uuid;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean isPicked() {
        return picked;
    }

    @Override
    public void setPicked(boolean picked) {
        this.picked = picked;
    }

}
