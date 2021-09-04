package com.makotomiyamoto.locksmithyv2.impl.locksmithy.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // TODO cancel if block is locked
    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {
        // TODO cancel event if block is locked
    }
}
