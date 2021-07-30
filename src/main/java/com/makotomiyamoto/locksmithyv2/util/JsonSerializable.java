package com.makotomiyamoto.locksmithyv2.util;

public interface JsonSerializable {
    String toJSON();
    static <T> T fromJSON(String json, Class<T> type) {
        return GsonManager.GSON.fromJson(json, type);
    }
}
