package com.makotomiyamoto.locksmithyv2.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class GsonManager {
    public static final Gson GSON;
    static {
        GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls().create();
    }
}
