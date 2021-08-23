package com.makotomiyamoto.locksmithyv2.lib.util;

import net.md_5.bungee.api.ChatColor;

import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public abstract class ChatUtils {
    protected static final Random rand = new Random();
    protected static final BinaryOperator<String> concatenate = (s, s2) -> s + s2;

    public static ChatColor fromRGB(int r, int g, int b) {
        return ChatColor.of(Stream.of(r, g, b).map(Integer::toHexString).reduce("", concatenate));
    }

    public static ChatColor randColor() {
        return ChatColor.of(rand.ints(3).mapToObj(Integer::toHexString).reduce("", concatenate));
    }

    public static ChatColor randColorRange(int origin, int bound) {
        return ChatColor.of("#" +
                rand.ints(3, origin, bound).mapToObj(Integer::toHexString).reduce("", concatenate));
    }

    public static ChatColor randBrightColor() {
        return randColorRange(192, 256);
    }
}
