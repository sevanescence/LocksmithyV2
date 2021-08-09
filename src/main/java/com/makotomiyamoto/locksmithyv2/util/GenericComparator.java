package com.makotomiyamoto.locksmithyv2.util;

public abstract class GenericComparator {
    @SafeVarargs
    public static <T> boolean equalsAny(T t, T ... ta) {
        for (T it : ta) {
            if (t.equals(it))
                return true;
        }
        return false;
    }
}
