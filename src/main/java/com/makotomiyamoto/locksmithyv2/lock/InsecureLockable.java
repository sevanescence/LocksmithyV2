package com.makotomiyamoto.locksmithyv2.lock;

/**
 * Represents a lockable container that is able to be
 * forcibly unlocked by a non-operator player. In the
 * base implementation of Locksmithy, InsecureLockable
 * containers are able to be opened via lock picking.
 *
 * @see com.makotomiyamoto.locksmithyv2.lock.insecure.LockableContainer LockableContainer
 * @author MakotoMiyamoto
 */
public interface InsecureLockable extends Lockable {
    /**
     * Gets the picked state of a lock. This determines whether
     * a lockable was unlocked through means that are not
     * reminiscent of a key unlock event.
     * @return whether a lockable container has been picked
     */
    boolean isPicked();

    /**
     * Sets the picked state of a lockable container.
     * @param picked whether the lockable container has been picked
     */
    void setPicked(boolean picked);
}
