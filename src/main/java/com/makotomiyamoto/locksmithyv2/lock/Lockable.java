package com.makotomiyamoto.locksmithyv2.lock;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface Lockable {
    Player getOwner();
    Location getLockLocation();
    UUID getLockUUID();
    boolean isLocked();
    void setLocked(boolean lock);
}
