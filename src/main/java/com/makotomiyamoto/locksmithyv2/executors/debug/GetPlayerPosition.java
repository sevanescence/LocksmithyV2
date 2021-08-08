package com.makotomiyamoto.locksmithyv2.executors.debug;

import com.makotomiyamoto.locksmithyv2.Locksmithyv2;
import com.makotomiyamoto.locksmithyv2.util.GsonManager;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GetPlayerPosition implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            System.out.println("Usage: Gets metadata of the current position of a player (must be called by player)");
            return true;
        }

        Player player = (Player) sender;
        Location loc = player.getLocation();
        player.sendMessage("Chunk: " + GsonManager.getGson().toJson(loc.getChunk(), Chunk.class));
        player.sendMessage("Location: " + GsonManager.getGson().toJson(loc));

        return true;
    }
}
