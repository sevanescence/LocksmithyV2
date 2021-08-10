package com.makotomiyamoto.locksmithyv2.impl.bukkit.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {
    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        // debug, will change to locksmithy location lookup later
        event.blockList().removeIf(e -> e.getType().equals(Material.ACACIA_PLANKS));
    }
}
