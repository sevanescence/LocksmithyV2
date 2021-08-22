package com.makotomiyamoto.locksmithyv2.impl.bukkit.listener;

import com.makotomiyamoto.locksmithyv2.lib.lock.event.LockAssignEvent;
import com.makotomiyamoto.locksmithyv2.lib.util.CustomItemRecipeManager;
import com.makotomiyamoto.locksmithyv2.lib.util.Locksmithy;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (! event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        // stop listener from firing twice
        if (Objects.equals(event.getHand(), EquipmentSlot.OFF_HAND)) return;
        Block block = event.getClickedBlock();
        assert block != null;

        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().isSimilar(CustomItemRecipeManager.insecureKeyItem)) {
            // check if player has inventory space for new key
            // for now, just handle creating the lock
            player.sendMessage("Pass");

            LockAssignEvent lockAssignEvent = new LockAssignEvent(player, block);
            Bukkit.getPluginManager().callEvent(lockAssignEvent);

            if (lockAssignEvent.isCancelled()) {
                player.sendMessage("Lock could not be assigned.");
            } else {
                player.sendMessage("New lock created!");
            }
        } else {
            player.sendMessage("Failed");
        }
    }
}
