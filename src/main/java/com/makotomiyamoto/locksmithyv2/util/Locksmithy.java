package com.makotomiyamoto.locksmithyv2.util;

import com.makotomiyamoto.locksmithyv2.lock.InsecureLockable;
import com.makotomiyamoto.locksmithyv2.lock.Lockable;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Wrapper class which handles and maintains the {@link Lockable Lockable} container cache.
 *
 * @author MakotoMiyamoto
 * @apiNote Do not confuse this with {@link com.makotomiyamoto.locksmithyv2.LocksmithyPlugin LocksmithyPlugin},
 * which is the plugin's runtime logic container.
 */
public abstract class Locksmithy {
    private static final HashMap<Chunk, HashMap<Location, Lockable>> lockableContainers = new HashMap<>();

    /**
     * Gets the lockable container cache. Common boolean evaluation for this data structure
     * is implemented by its encapsulating utility class.
     * @return a hashmap of lockable containers
     */
    public static HashMap<Chunk, HashMap<Location, Lockable>> getLockableContainers() {
        return lockableContainers;
    }

    /**
     * Checks whether a lockable container is an insecure container.
     * @param lockable the lockable container
     * @return whether a lockable is insecure
     * @see InsecureLockable
     */
    public static boolean lockableIsInsecure(Lockable lockable) {
        return lockable instanceof InsecureLockable;
    }

    /**
     * Checks whether a lockable container is a secure container.
     * @param lockable the lockable container
     * @return whether a lockable is secure
     * @see com.makotomiyamoto.locksmithyv2.lock.SecureLockable SecureLockable
     */
    public static boolean lockableIsSecure(Lockable lockable) {
        return !lockableIsInsecure(lockable);
    }

    /**
     * Checks whether a location is maintained by Locksmithy's lockable system.
     * @param location a location object
     * @return whether a location is lockable
     * @apiNote To fetch a lockable container at a location, use . instead
     */
    public static boolean locationIsLockable(Location location) {
        HashMap<Location, Lockable> lockablesInChunk = lockableContainers.get(location.getChunk());
        return lockablesInChunk != null && lockablesInChunk.get(location) != null;
    }

    /**
     * <p>
     * Get a lockable container at a location. This implementation searches through
     * a chunk map before searching through the location map to decrease time spent
     * searching for lockable containers.
     * </p>
     * <p>
     *  Returns <code>null</code> if no lockable container exists at this location.
     * </p>
     * @param location a location object
     * @return A lockable container, or null
     */
    public static Lockable get(Location location) {
        HashMap<Location, Lockable> lockablesInChunk = lockableContainers.get(location.getChunk());
        if (lockablesInChunk == null) {
            return null;
        }
        return lockablesInChunk.get(location);
    }

    /**
     * Attempts to read the chunks folder in the LocksmithyV2 data folder and parse
     * the data into the Locksmithy lockable cache.
     * @param folder the folder which contains every chunk folder
     * @throws IOException might be returned if either the chunks folder does not exist
     * or something else goes wrong during file reading.
     * @apiNote Calling this method during runtime at any point besides plugin enable
     * is unsafe as it will overwrite the existing cache.
     *
     * @implNote Not yet implemented. Don't use this (yet).
     */
    public static void loadLockableChunksFolder(File folder) throws IOException {
        // TODO implement this
    }

    /**
     * Attempts to write the lockable cache into the chunks folder. Cache is not cleared
     * when this method is called.
     * @param folder the folder which contains every chunk folder
     * @throws IOException might be returned if either the chunks folder does not exist
     * or something else goes wrong during file writing.
     * @apiNote Calling this method during runtime is safe, but is not recommended on the
     * main server thread. You could safely use this to save a mid-runtime backup of the
     * lockable cache.
     *
     * @implNote Not yet implemented. Don't use this (yet).
     */
    public static void saveLockableChunksCache(File folder) throws IOException {
        // TODO implement this
    }
}
