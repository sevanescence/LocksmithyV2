package com.makotomiyamoto.locksmithyv2.lock;

public interface InsecureLockable extends Lockable {
    boolean isPicked();
    void setPicked(boolean picked);
}
