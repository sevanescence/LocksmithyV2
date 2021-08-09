package com.makotomiyamoto.locksmithyv2.lock;

/**
 * Represents a lockable container that cannot be
 * forced upon by means not reminiscent of a key
 * unlock event. These types of lockable containers
 * might only be for admin-locked containers or
 * players who try a little too hard, who knows.
 */
public interface SecureLockable extends Lockable {

}
