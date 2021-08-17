package com.makotomiyamoto.locksmithyv2.lib.util;

import net.md_5.bungee.api.ChatColor;

public class ChatUtils {
    public static ChatColor randBrightColor() {
        String color = Integer.toHexString((int) (Math.random() * (16777216 - 6777216) + 6777216));
        return ChatColor.of("#" + color);
    }
}
