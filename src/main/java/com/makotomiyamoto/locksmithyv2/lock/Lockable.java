package com.makotomiyamoto.locksmithyv2.lock;

import com.makotomiyamoto.locksmithyv2.minimized.LockLocation;
import com.makotomiyamoto.locksmithyv2.minimized.LockOwner;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface Lockable {
    LockOwner getOwner();
    LockLocation getLockLocation();
    UUID getLockUUID();
    boolean isLocked();
    void setLocked(boolean lock);
}
