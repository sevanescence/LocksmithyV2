package com.makotomiyamoto.locksmithyv2.lib.lock.insecure;

import com.makotomiyamoto.locksmithyv2.lib.lock.InsecureLockable;
import com.makotomiyamoto.locksmithyv2.lib.lock.Lockable;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

/**
 * Represents an insecure lockable container that (currently) encapsulates all
 * inventory-interactable blocks. This implementation might change overtime.
 *
 * @author MakotoMiyamoto
 *
 * @apiNote In future versions of Locksmithy, different containers may be
 * maintained by different structures, however it isn't 100% certain.
 */
public class LockableContainer implements InsecureLockable {
    private final OfflinePlayer owner;
    private final Location location;
    private final UUID uuid;
    boolean locked = true;
    boolean picked = false;

    public LockableContainer(OfflinePlayer player, Location location) {
        this.owner = player;
        this.location = location;
        this.uuid = UUID.randomUUID();
    }

    /**
     * {@link Lockable#getOwner()}
     */
    @Override
    public OfflinePlayer getOwner() {
        return owner;
    }

    /**
     * {@link Lockable#getLockLocation()}
     */
    @Override
    public Location getLockLocation() {
        return location;
    }

    /**
     * {@link Lockable#getLockUUID()}
     */
    @Override
    public UUID getLockUUID() {
        return uuid;
    }

    /**
     * {@link Lockable#isLocked()}
     */
    @Override
    public boolean isLocked() {
        return locked;
    }

    /**
     * {@link Lockable#setLocked(boolean)}
     */
    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * {@link InsecureLockable#isPicked()}
     */
    @Override
    public boolean isPicked() {
        return picked;
    }

    /**
     * {@link InsecureLockable#setPicked(boolean)}
     */
    @Override
    public void setPicked(boolean picked) {
        this.picked = picked;
    }

}
