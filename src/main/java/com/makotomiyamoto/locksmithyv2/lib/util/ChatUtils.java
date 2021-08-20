package com.makotomiyamoto.locksmithyv2.lib.util;

import net.md_5.bungee.api.ChatColor;

import java.util.stream.Stream;

public abstract class ChatUtils {
    public static ChatColor randBrightColor() {
        Integer[] rgb = {
                (int) Math.floor(Math.random() * (256 - 192) + 192),
                (int) Math.floor(Math.random() * (256 - 192) + 192),
                (int) Math.floor(Math.random() * (256 - 192) + 192)
        };
        Integer product = Stream.of(rgb).reduce(1, (a, b) -> a * b);
        String color = Integer.toHexString(product);
        return ChatColor.of("#" + color);
    }
}
