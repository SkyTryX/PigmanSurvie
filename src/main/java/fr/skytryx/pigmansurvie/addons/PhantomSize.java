package fr.skytryx.pigmansurvie.addons;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class PhantomSize implements Listener {

    @EventHandler
    public void onPhantomSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.PHANTOM) {
            Phantom phantom = (Phantom) event.getEntity();
            // Modifier la taille du Phantom
            phantom.setSize(64);
        }
    }
}
