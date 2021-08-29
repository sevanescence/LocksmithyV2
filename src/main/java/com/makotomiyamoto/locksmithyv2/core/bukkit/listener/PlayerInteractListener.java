package com.makotomiyamoto.locksmithyv2.core.bukkit.listener;

import com.makotomiyamoto.locksmithyv2.lib.lock.event.LockAssignEvent;
import com.makotomiyamoto.locksmithyv2.lib.lock.insecure.LockableContainer;
import com.makotomiyamoto.locksmithyv2.lib.lock.insecure.LockablePairContainer;
import com.makotomiyamoto.locksmithyv2.lib.util.BlockPairUtils;
import com.makotomiyamoto.locksmithyv2.lib.util.CustomItemRecipeManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryHolder;

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
            BlockState state = event.getClickedBlock().getState();
            if (state instanceof InventoryHolder && ((InventoryHolder) state).getInventory().getSize() == 54) {
                player.sendMessage("instanceof DoubleChest");
                Block chest1 = event.getClickedBlock();
                Block chest2 = BlockPairUtils.getConnectedChest((Chest) chest1.getState());
                var lockable = new LockablePairContainer(player, chest1.getLocation(), chest2.getLocation());
                var otherLockable = lockable.getPairLockable();
                lockAssignEvent.getLockableList().add(lockable);
                lockAssignEvent.getLockableList().add(otherLockable);
            } else if (event.getClickedBlock().getBlockData() instanceof Door) {
                player.sendMessage("instanceof Door");
                Block door1 = event.getClickedBlock();
                Block door2 = BlockPairUtils.getConnectedDoorHalf(door1);
                var lockable = new LockablePairContainer(player, door1.getLocation(), door2.getLocation());
                var otherLockable = lockable.getPairLockable();
                lockAssignEvent.getLockableList().add(lockable);
                lockAssignEvent.getLockableList().add(otherLockable);
            } else {
                player.sendMessage("instanceof neither DoubleChest nor Door");
                LockableContainer lockable = new LockableContainer(player, event.getClickedBlock().getLocation());
                lockAssignEvent.getLockableList().add(lockable);
            }

            Bukkit.getPluginManager().callEvent(lockAssignEvent);

            if (lockAssignEvent.isCancelled()) {
                player.sendMessage("Lock could not be assigned.");
            } else {
                player.sendMessage(lockAssignEvent.getLockableList().toString());
            }
        } else {
            player.sendMessage("Failed");
        }
    }
}
