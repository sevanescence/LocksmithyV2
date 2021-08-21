package com.makotomiyamoto.locksmithyv2.lib.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class CustomItemRecipeManager {
    public static NamespacedKey insecureKeyRecipeKey;
    public static ShapedRecipe insecureKeyRecipe;
    public static ItemStack insecureKeyItem;

    public static NamespacedKey keyBoundId;
    static {
        insecureKeyItem = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta = insecureKeyItem.getItemMeta();
        assert meta != null;

        meta.setDisplayName(ChatColor.YELLOW + "Simple Key");
        String[] lore = {
                ChatColor.GRAY + "Shift right-click on an unlocked container",
                ChatColor.GRAY + "to lock the container and assign the key."
        };
        meta.setLore(List.of(lore));

        insecureKeyItem.setItemMeta(meta);
    }

    public static void initialize(JavaPlugin plugin) {
        insecureKeyRecipeKey = new NamespacedKey(plugin, "insecure_key");
        insecureKeyRecipe = new ShapedRecipe(insecureKeyRecipeKey, insecureKeyItem);

        insecureKeyRecipe.shape("iiB", "nn ");
        insecureKeyRecipe.setIngredient('i', Material.IRON_INGOT);
        insecureKeyRecipe.setIngredient('B', Material.IRON_BLOCK);
        insecureKeyRecipe.setIngredient('n', Material.IRON_NUGGET);

        plugin.getServer().addRecipe(insecureKeyRecipe);
    }
}
