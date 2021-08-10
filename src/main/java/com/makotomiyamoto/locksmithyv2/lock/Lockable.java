package com.makotomiyamoto.locksmithyv2.lock;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Represents a container that is able to be locked or is
 * already locked. A lockable container must have an owner,
 * location, uuid, and lock state.
 *
 * @see com.makotomiyamoto.locksmithyv2.lock.insecure.LockableContainer LockableContainer
 * @author MakotoMiyamoto
 */
public interface Lockable {
    /**
     * Gets the owner of a lockable container. In possible future implementations,
     * if the owner of a lock is changed, then the container will be re-initialized
     * with a new UUID.
     * @return a reference to an OfflinePlayer
     */
    OfflinePlayer getOwner();

    /**
     * Gets the location of a lockable container.
     * @return a new copy of Location containing the position of this Lockable
     */
    Location getLockLocation();

    /**
     * Gets the UUID of a lockable container. In possible future implementations,
     * if the owner of a lock is changed, then the container will be re-initialized
     * with a new UUID.
     * @return a unique id which identifies a lockable container
     */
    UUID getLockUUID();

    /**
     * Gets the locked state of a lockable container.
     * @return whether or not a lockable container is locked
     */
    boolean isLocked();

    /**
     * Sets the locked state of a lockable container.
     * @param lock whether to lock a container
     */
    void setLocked(boolean lock);
}
