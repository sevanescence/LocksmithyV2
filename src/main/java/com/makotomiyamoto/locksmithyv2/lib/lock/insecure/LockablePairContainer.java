package com.makotomiyamoto.locksmithyv2.lib.lock.insecure;

import com.makotomiyamoto.locksmithyv2.lib.lock.ILockablePair;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class LockablePairContainer extends LockableContainer implements ILockablePair<LockablePairContainer> {
    private transient LockablePairContainer pair;
    private final UUID uuid;

    protected LockablePairContainer(OfflinePlayer player, Location location, LockablePairContainer pair, UUID uuid, boolean picked, boolean locked) {
        super(player, location);
        this.pair = pair;
        this.uuid = uuid;
        this.picked = picked;
        this.locked = locked;
    }

    protected LockablePairContainer(OfflinePlayer player, Location thisLocation, LockablePairContainer pair) {
        super(player, thisLocation);
        this.pair = pair;
        this.uuid = pair.getLockUUID();
    }

    public LockablePairContainer(OfflinePlayer player, Location location, Location pairLocation) {
        super(player, location);
        this.uuid = UUID.randomUUID();
        this.pair = new LockablePairContainer(player, pairLocation, this);
    }

    @Override
    public LockablePairContainer getPairLockable() {
        return pair;
    }

    @Override
    public void setPairLockable(LockablePairContainer pairLockable) {
        pair = pairLockable;
    }

    @Override
    public OfflinePlayer getOwner() {
        return super.getOwner();
    }

    @Override
    public Location getLockLocation() {
        return super.getLockLocation();
    }

    @Override
    public UUID getLockUUID() {
        return this.uuid;
    }

    @Override
    public boolean isLocked() {
        return super.isLocked();
    }

    @Override
    public void setLocked(boolean locked) {
        super.setLocked(locked);
    }

    @Override
    public boolean isPicked() {
        return super.isPicked();
    }

    @Override
    public void setPicked(boolean picked) {
        super.setPicked(picked);
    }
}
