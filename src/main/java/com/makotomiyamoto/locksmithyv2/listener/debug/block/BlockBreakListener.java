package com.makotomiyamoto.locksmithyv2.listener.debug.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockExplodeEvent;

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
