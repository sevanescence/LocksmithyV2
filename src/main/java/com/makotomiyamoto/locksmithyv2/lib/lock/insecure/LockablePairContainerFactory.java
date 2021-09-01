package com.makotomiyamoto.locksmithyv2.lib.lock.insecure;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public abstract class LockablePairContainerFactory {
    public static LockablePairContainer getLockablePairContainer(OfflinePlayer player, Location location, LockablePairContainer pair, UUID uuid, boolean locked, boolean picked) {
        return new LockablePairContainer(player, location, pair, uuid, locked, picked);
    }

    public static LockablePairContainer getLockablePairContainer(OfflinePlayer player, Location location, UUID uuid, boolean locked, boolean picked) {
        return getLockablePairContainer(player, location, null, uuid, locked, picked);
    }
}
