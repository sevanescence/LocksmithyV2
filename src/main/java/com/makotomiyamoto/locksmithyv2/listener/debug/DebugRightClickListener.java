package com.makotomiyamoto.locksmithyv2.listener.debug;

import com.makotomiyamoto.locksmithyv2.Locksmithyv2;
import com.makotomiyamoto.locksmithyv2.util.GenericComparator;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@Deprecated
public class DebugRightClickListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (! GenericComparator.equalsAny(event.getAction(), Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        boolean isAirClicked = event.getAction().equals(Action.RIGHT_CLICK_AIR);
        @SuppressWarnings("ConstantConditions")
        Chunk chunk = isAirClicked
                        ? event.getPlayer().getLocation().getChunk()
                        : event.getClickedBlock().getChunk();

        String debug = Locksmithyv2.prefixString("Chunk x: §3" + chunk.getX() + "§r, z: §3" + chunk.getZ() + "§r at ");
        debug += isAirClicked ? "player." : "clicked block.";
        event.getPlayer().sendMessage(debug);
    }
}
