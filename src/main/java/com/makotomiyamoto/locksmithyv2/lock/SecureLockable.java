package com.makotomiyamoto.locksmithyv2.lock;

/**
 * Represents a lockable container that cannot be
 * forced upon by means not reminiscent of a key
 * unlock event. These types of lockable containers
 * might only be for admin-locked containers or
 * players who try a little too hard, who knows.
 *
 * @author MakotoMiyamoto
 *
 * @apiNote Whether or not SecureLockable containers will
 * default to Lockable containers that simply do not
 * implement InsecureLockable is still uncertain, and is
 * dependent on future implementations of Locksmithy's
 * hierarchy system. This interface is not deprecated.
 */
public interface SecureLockable extends Lockable {

}
