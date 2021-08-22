package com.makotomiyamoto.locksmithyv2.lib.lock.event;

import com.makotomiyamoto.locksmithyv2.lib.lock.Lockable;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Chest;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

public class LockAssignEvent extends Event implements Cancellable {
    private final ArrayList<Lockable> lockableList = new ArrayList<>();
    private final Player player;
    private final Block block;
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean cancelled;

    public LockAssignEvent(Player player, Block block) {
        this.player = player;
        this.block = block;
        if (block.getBlockData() instanceof Chest || block.getBlockData() instanceof Door) {
            // lockable pair
            player.sendMessage("Lockable pair block");
        } else {
            // normal lockable
            player.sendMessage("Normal lockable block");
        }
    }

    public ArrayList<Lockable> getLockableList() {
        return lockableList;
    }

    @Override
    public String getEventName() {
        return super.getEventName();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
