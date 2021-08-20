package com.makotomiyamoto.locksmithyv2;

import com.makotomiyamoto.locksmithyv2.impl.adapter.LockableListSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.impl.bukkit.executor.GetPlayerPosition;
import com.makotomiyamoto.locksmithyv2.impl.bukkit.listener.BlockBreakListener;
import com.makotomiyamoto.locksmithyv2.impl.bukkit.listener.ExplosionListener;
import com.makotomiyamoto.locksmithyv2.impl.adapter.ChunkSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.impl.adapter.LocationSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.impl.adapter.OfflinePlayerSerializationAdapter;
import com.makotomiyamoto.locksmithyv2.lib.util.CustomItemRecipeManager;
import com.makotomiyamoto.locksmithyv2.lib.util.GsonManager;
import com.makotomiyamoto.locksmithyv2.lib.util.Locksmithy;
import com.makotomiyamoto.locksmithyv2.test.CreateFakeLockableLol;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

/**
 * Runtime logic class for LocksmithyV2.
 *
 * @author MakotoMiyamoto
 * @version 0.1-ALPHA
 */
public final class LocksmithyPlugin extends JavaPlugin {
    /**
     * Probably to be the only ever utility method in LocksmithyPlugin, this method is used
     * to prefix debug messages with the Locksmithy tag if necessary. Will probably never
     * be used in a production setting, who knows. For writing to a file, just use Bukkit's
     * built-in logger.
     *
     * @param string the string to be formatted
     * @return a formatted string with the [LocksmithyV2] prefix
     */
    public static String prefixString(String string) {
        return "[LocksmithyV2] " + string;
    }

    // TODO location references should be sorted by folder and location
    // e.g. /locksmithyv2/locks/chunk_x_z/Lockable#getUUID().json
    // resolve all the chunks and locations from storage at startup
    // TODO asynchronously save cache every 15 minutes (as a bukkit task)
    // TODO craftable blank keys (don't do advanced keys yet)
    // TODO add key mold command for admins
    private static File chunksFolder;

    @Override
    public void onEnable() {
        // Plugin startup logic
        GsonManager.registerSerializationHierarchyAdapter(new OfflinePlayerSerializationAdapter());
        GsonManager.registerSerializationAdapter(new LocationSerializationAdapter());
        GsonManager.registerSerializationHierarchyAdapter(new ChunkSerializationAdapter());
        GsonManager.registerSerializationAdapter(new LockableListSerializationAdapter());
        GsonManager.flush();

        Objects.requireNonNull(this.getCommand("ploc")).setExecutor(new GetPlayerPosition());

        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new ExplosionListener(), this);

        // test
        this.getServer().getPluginManager().registerEvents(new CreateFakeLockableLol(), this);

        try {
            chunksFolder = new File(this.getDataFolder().getAbsolutePath() + File.separator + "chunks");
            Locksmithy.loadLockableChunksFolder(chunksFolder);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                if (chunksFolder.mkdirs()) System.out.println(chunksFolder.getAbsolutePath() + " created!");
            } else {
                e.printStackTrace();
            }
        }

        CustomItemRecipeManager.initialize(this);

        this.getLogger().info("LocksmithyV2 enabled without any problems.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            Locksmithy.saveLockableChunksCache(chunksFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
