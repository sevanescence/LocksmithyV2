package com.makotomiyamoto.locksmithyv2.lib.util;

import com.google.gson.reflect.TypeToken;
import com.makotomiyamoto.locksmithyv2.lib.lock.InsecureLockable;
import com.makotomiyamoto.locksmithyv2.lib.lock.Lockable;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Wrapper class which handles and maintains the {@link Lockable Lockable} container cache.
 * </p>
 * <br/>
 * <p>
 * Note: Do not confuse this with {@link com.makotomiyamoto.locksmithyv2.LocksmithyPlugin LocksmithyPlugin},
 * which is the plugin's runtime logic container.
 * </p>
 *
 * @author MakotoMiyamoto
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
     * @see com.makotomiyamoto.locksmithyv2.lib.lock.SecureLockable SecureLockable
     */
    public static boolean lockableIsSecure(Lockable lockable) {
        return !lockableIsInsecure(lockable);
    }

    /**
     * Checks whether a location is maintained by Locksmithy's lockable system.
     * To fetch a lockable container at a location, use {@link #get(Location)} instead.
     * @param location a location object
     * @return whether a location is lockable
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
     * the data into the Locksmithy lockable cache. Calling this method during runtime
     * at any point besides plugin enable is unsafe as it will overwrite the existing cache.
     *
     * @param folder the folder which contains every chunk folder
     * @throws IOException might be returned if either the chunks folder does not exist
     * or something else goes wrong during file reading.
     *
     * @implNote Not yet implemented. Don't use this (yet).
     */
    public static void loadLockableChunksFolder(File folder) throws IOException {
        // TODO implement this
        // x_y.chunk.json
        File[] chunks = folder.listFiles((dir, name) -> name.endsWith(".chunk.json"));
        if (chunks == null) throw new FileNotFoundException("chunks folder does not exist.");

        Arrays.asList(chunks).forEach(file -> {
            try(FileReader reader = new FileReader(file)) {
                Type mapType = new TypeToken<HashMap<Location, Lockable>>() {}.getType();
                HashMap<Location, Lockable> lockableMap = GsonManager.getGson().fromJson(reader, mapType);
                // TODO get chunk

                // chunk x, y: [-0-9]+?(?=[^-0-9]) # first match is x, second is y
                // world uuid: [0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}
                //      # first match is uuid
                Pattern chunk_x_y = Pattern.compile("[-0-9]+?(?=[^-0-9])");
                Pattern chunk_uuid
                        = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}");

                Matcher chunkXYMatcher = chunk_x_y.matcher(file.getName());
                Matcher worldUUIDMatcher = chunk_uuid.matcher(file.getName());

                int x, y;
                UUID uuid;
                x = Integer.parseInt(MatcherUtils.getNextFind(chunkXYMatcher, 0));
                y = Integer.parseInt(MatcherUtils.getNextFind(chunkXYMatcher, 0));
                uuid = UUID.fromString(MatcherUtils.getNextFind(worldUUIDMatcher, 0));

                Chunk chunk = Objects.requireNonNull(Bukkit.getWorld(uuid)).getChunkAt(x, y);
                Locksmithy.getLockableContainers().put(chunk, lockableMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Attempts to write the lockable cache into the chunks folder. Cache is not cleared
     * when this method is called. Calling this method during runtime is safe, but not
     * recommended on the main server thread. You could safely use this to save a
     * mid-runtime backup of the lockable cache.
     *
     * @param folder the folder which contains every chunk folder
     * @throws IOException might be returned if either the chunks folder does not exist
     * or something else goes wrong during file writing.
     *
     * @implNote Not yet implemented. Don't use this (yet).
     */
    public static void saveLockableChunksCache(File folder) throws IOException {
        // TODO implement this
    }
}
