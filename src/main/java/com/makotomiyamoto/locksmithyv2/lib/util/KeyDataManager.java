package com.makotomiyamoto.locksmithyv2.lib.util;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public abstract class KeyDataManager {
    public static NamespacedKey keyBoundId;
    public static PersistentDataType<String, UUID> keyBoundIdTagType = new UUIDStringTagType();

    public static void initialize(JavaPlugin plugin) {
        keyBoundId = new NamespacedKey(plugin, "key_bound_id");
    }

}
