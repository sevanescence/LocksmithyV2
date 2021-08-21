package com.makotomiyamoto.locksmithyv2.lib.util;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDTagType implements PersistentDataType<byte[], UUID> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<UUID> getComplexType() {
        return UUID.class;
    }

    @Override
    public byte[] toPrimitive(UUID complex, PersistentDataAdapterContext context) {
        ByteBuffer buf = ByteBuffer.wrap(new byte[16]);
        buf.putLong(complex.getMostSignificantBits());
        buf.putLong(complex.getLeastSignificantBits());
        return buf.array();
    }

    @Override
    public UUID fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        ByteBuffer buf = ByteBuffer.wrap(primitive);
        long firstLong = buf.getLong();
        long secondLong = buf.getLong();
        return new UUID(firstLong, secondLong);
    }
}
