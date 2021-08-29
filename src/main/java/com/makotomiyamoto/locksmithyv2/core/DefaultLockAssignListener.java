package com.makotomiyamoto.locksmithyv2.core;

import com.makotomiyamoto.locksmithyv2.lib.lock.ILockablePair;
import com.makotomiyamoto.locksmithyv2.lib.lock.Lockable;
import com.makotomiyamoto.locksmithyv2.lib.lock.event.LockAssignEvent;
import com.makotomiyamoto.locksmithyv2.lib.util.Locksmithy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DefaultLockAssignListener implements Listener {
    @EventHandler
    public void onLockAssign(LockAssignEvent event) {
        if (Locksmithy.locationIsLockable(event.getBlock().getLocation())) {
            event.setCancelled(true);
            return;
        } else if (event.getLockableList().get(0) instanceof ILockablePair) {
            if (Locksmithy.locationIsLockable(((ILockablePair<?>) event.getLockableList().get(0)).getPairLockable().getLockLocation())) {
                event.setCancelled(true);
                return;
            }
        }

        // TODO check if key has ID
        
    }
}
