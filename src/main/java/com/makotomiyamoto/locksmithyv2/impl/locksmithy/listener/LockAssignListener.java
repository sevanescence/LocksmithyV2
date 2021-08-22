package com.makotomiyamoto.locksmithyv2.impl.locksmithy.listener;

import com.makotomiyamoto.locksmithyv2.lib.lock.event.LockAssignEvent;
import com.makotomiyamoto.locksmithyv2.lib.util.Locksmithy;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class LockAssignListener implements Listener {
    @EventHandler
    public void onLockAssign(LockAssignEvent event) {
        if (Locksmithy.locationIsLockable(event.getBlock().getLocation())) {
            event.setCancelled(true);
            return;
        }
    }
}
