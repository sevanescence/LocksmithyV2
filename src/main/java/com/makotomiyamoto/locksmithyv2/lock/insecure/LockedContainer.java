package com.makotomiyamoto.locksmithyv2.lock.insecure;

import com.makotomiyamoto.locksmithyv2.lock.InsecureLockable;
import com.makotomiyamoto.locksmithyv2.util.GsonManager;
import com.makotomiyamoto.locksmithyv2.util.JsonSerialized;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LockedContainer implements InsecureLockable, JsonSerialized {
    private final Player owner;
    private final Location location;
    private final UUID uuid;
    boolean locked = true;
    boolean picked = false;

    public LockedContainer(Player player, Location location) {
        this.owner = player;
        this.location = location;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public Player getOwner() {
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

    @Override
    public String toJSON() {
        return GsonManager.GSON.toJson(this);
    }
}
