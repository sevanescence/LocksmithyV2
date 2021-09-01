package com.makotomiyamoto.locksmithyv2.lib.lock;

public interface ILockablePair<I extends Lockable> {
    I getPairLockable();
    void setPairLockable(I pairLockable);
}
