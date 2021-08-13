package com.makotomiyamoto.locksmithyv2.lib.util;

import java.util.Arrays;

public abstract class GenericComparator {
    @SafeVarargs
    public static <T> boolean equalsAny(T t, T ... ta) {
        return Arrays.asList(ta).contains(t);
    }
}
