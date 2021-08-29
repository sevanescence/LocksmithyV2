package com.makotomiyamoto.locksmithyv2.lib.util;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Chest;
import org.bukkit.util.Vector;

import java.util.HashMap;

public abstract class BlockPairUtils {
    private static final HashMap<BlockFace, Vector> cartesianVectorMap = new HashMap<>();
    static {
        // north = +Z
        // south = -Z
        // east = +X
        // west = -X

        // north & left = Vec(1, 0, 0)
        // south & left = Vec(-1, 0, 0)
        // east & left = Vec(0, 0, -1)
        // west & left = Vec(0, 0, 1)

        // north & right = Vec(-1, 0, 0)
        // south & right = Vec(1, 0, 0)
        // east & right = Vec(0, 0, 1)
        // west & right = Vec(0, 0, -1)

        cartesianVectorMap.put(BlockFace.NORTH, new Vector(1, 0, 0));
        cartesianVectorMap.put(BlockFace.SOUTH, new Vector(-1, 0, 0));
        cartesianVectorMap.put(BlockFace.EAST, new Vector(0, 0, -1));
        cartesianVectorMap.put(BlockFace.WEST, new Vector(0, 0, 1));
    }

    public static Block getConnectedChest(org.bukkit.block.Chest chestBlock) {
        Chest chestBlockData = (Chest) chestBlock.getBlockData();

        Chest.Type type = chestBlockData.getType();
        if (type.equals(Chest.Type.SINGLE))
            throw new IllegalArgumentException("Chest type is a single chest.");

        BlockFace facing = chestBlockData.getFacing();
        int vectorMultiplier = type.equals(Chest.Type.LEFT) ? 1 : -1;

        Vector vec = cartesianVectorMap.get(facing).multiply(vectorMultiplier);
        Location loc = chestBlock.getLocation().add(vec);

        return loc.getBlock();
    }

    public static Block getConnectedDoorHalf(Block doorBlock) {
        try {
            Bisected door = (Bisected) doorBlock.getBlockData();
            if (door.getHalf().equals(Bisected.Half.TOP)) {
                return doorBlock.getLocation().add(0, -1, 0).getBlock();
            } else {
                return doorBlock.getLocation().add(0, 1, 0).getBlock();
            }
        } catch (ClassCastException ignored) {
            throw new ClassCastException("Parameter block must be a door. If you're getting " +
                    "this error then, you guessed it, it probably isn't.");
        }
    }
}
