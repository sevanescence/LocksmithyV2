package com.makotomiyamoto.locksmithyv2.core;

import com.makotomiyamoto.locksmithyv2.lib.lock.ILockablePair;
import com.makotomiyamoto.locksmithyv2.lib.lock.Lockable;
import com.makotomiyamoto.locksmithyv2.lib.lock.event.LockAssignEvent;
import com.makotomiyamoto.locksmithyv2.lib.util.KeyDataManager;
import com.makotomiyamoto.locksmithyv2.lib.util.Locksmithy;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class DefaultLockAssignListener implements Listener {
    @EventHandler
    public void onLockAssign(LockAssignEvent event) {
        if (event.getLockableList().isEmpty()) {
            event.setCancelled(true);
            return;
        }

        if (Locksmithy.locationIsLockable(event.getBlock().getLocation())) {
            event.getPlayer().sendMessage(ChatColor.RED + "This block is already locked!");
            event.setCancelled(true);
            return;
        } else if (event.getLockableList().get(0) instanceof ILockablePair) {
            if (Locksmithy.locationIsLockable(((ILockablePair<?>) event.getLockableList().get(0)).getPairLockable().getLockLocation())) {
                event.getPlayer().sendMessage(ChatColor.RED + "A block connected to this one is locked!");
                event.setCancelled(true);
                return;
            }
        }

        // TODO check for padlock when feature is implemented
        // TODO also put messages in a container or something, make it writable too (you know, for config stuff)
        ItemStack key = event.getPlayer().getInventory().getItemInMainHand();
        ItemMeta keyMeta = key.getItemMeta(); assert keyMeta != null;

        if (keyMeta.getPersistentDataContainer().has(KeyDataManager.keyBoundId, KeyDataManager.keyBoundIdTagType)) {
            event.getPlayer().sendMessage(ChatColor.RED + "This key already belongs to a lock! A padlock is required.");
            event.setCancelled(true);
        }
    }
}
