package com.makotomiyamoto.locksmithyv2.lib.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public abstract class KeyDataManager {
    public static NamespacedKey keyBoundId;
    public static PersistentDataType<String, UUID> keyBoundIdTagType = new UUIDStringTagType();

    public static void initialize(JavaPlugin plugin) {
        keyBoundId = new NamespacedKey(plugin, "key_bound_id");
    }

    public static boolean itemIsKey(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return false;
        return meta.getPersistentDataContainer().has(keyBoundId, keyBoundIdTagType);
    }

}
