package com.makotomiyamoto.locksmithyv2.test;

import com.makotomiyamoto.locksmithyv2.lib.lock.Lockable;
import com.makotomiyamoto.locksmithyv2.lib.lock.insecure.LockableContainer;
import com.makotomiyamoto.locksmithyv2.lib.util.GsonManager;
import com.makotomiyamoto.locksmithyv2.lib.util.Locksmithy;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;
import java.util.Objects;

public class CreateFakeLockableLol implements Listener {
    @EventHandler
    public void onStickBonkOnBlock(PlayerInteractEvent event) {
        if (! event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (Objects.equals(event.getHand(), EquipmentSlot.OFF_HAND)) return;
        assert event.getClickedBlock() != null;

        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
            if (player.isSneaking()) {
                // create lockable block if it isnt there
                if (Locksmithy.locationIsLockable(event.getClickedBlock().getLocation())) {
                    player.sendMessage("Location is already lockable.");
                } else {
                    LockableContainer lock = new LockableContainer(player, event.getClickedBlock().getLocation());
                    Chunk chunk = event.getClickedBlock().getChunk();
                    Location loc = event.getClickedBlock().getLocation();
                    if (Locksmithy.getLockableContainers().containsKey(chunk)) {
                        player.sendMessage("Chunk already exists as key.");
                    } else {
                        player.sendMessage("Adding chunk " + chunk.getX() + " " + chunk.getZ() + " to cache.");
                        Locksmithy.getLockableContainers().put(chunk, new HashMap<>());
                    }
                    Locksmithy.getLockableContainers().get(chunk).put(loc, lock);
                    player.sendMessage("New lockable created.");
                    player.sendMessage(GsonManager.getGson().toJson(loc));
                    player.sendMessage(GsonManager.getGson().toJson(chunk));
                    player.sendMessage(GsonManager.getGson().toJson(lock));
                }
            } else {
                // get location data if lockable
                Lockable lockable;
                if ((lockable = Locksmithy.get(event.getClickedBlock().getLocation())) == null) {
                    player.sendMessage("Location is not a lockable block.");
                } else {
                    player.sendMessage(GsonManager.getGson().toJson(lockable));
                }
            }
        }
    }
}
