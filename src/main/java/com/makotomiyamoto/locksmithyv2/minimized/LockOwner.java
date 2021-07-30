package com.makotomiyamoto.locksmithyv2.minimized;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LockOwner {
    private final UUID ownerUUID;

    public LockOwner(Player player) {
        this.ownerUUID = player.getUniqueId();
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public Player resolveBukkitPlayer() {
        return Bukkit.getPlayer(this.ownerUUID);
    }
}
