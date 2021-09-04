package com.makotomiyamoto.locksmithyv2.impl.locksmithy.listener;

import com.makotomiyamoto.locksmithyv2.lib.util.Locksmithy;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Door;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {
    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        event.blockList().removeIf(e -> Locksmithy.locationIsLockable(e.getLocation()));
        event.blockList().removeIf(e -> {
            Block blockAbove = e.getLocation().add(0, 1, 0).getBlock();
            return blockAbove.getBlockData() instanceof Door && Locksmithy.locationIsLockable(blockAbove.getLocation());
        });
    }
}
