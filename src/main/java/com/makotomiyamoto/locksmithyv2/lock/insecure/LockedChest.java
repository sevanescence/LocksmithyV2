package com.makotomiyamoto.locksmithyv2.lock.insecure;

import com.makotomiyamoto.locksmithyv2.lock.InsecureLockable;
import com.makotomiyamoto.locksmithyv2.minimized.LockOwner;
import com.makotomiyamoto.locksmithyv2.minimized.LockLocation;
import com.makotomiyamoto.locksmithyv2.util.GsonManager;
import com.makotomiyamoto.locksmithyv2.util.JsonSerializable;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LockedChest implements InsecureLockable, JsonSerializable {
    private final LockOwner owner;
    private final LockLocation location;
    private final UUID uuid;
    boolean locked = true;
    boolean picked = false;

    public LockedChest(Player player, Block block) {
        this.owner = new LockOwner(player);
        this.location = new LockLocation(block.getLocation());
        this.uuid = UUID.randomUUID();
    }

    @Override
    public LockOwner getOwner() {
        return owner;
    }

    @Override
    public LockLocation getLockLocation() {
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
