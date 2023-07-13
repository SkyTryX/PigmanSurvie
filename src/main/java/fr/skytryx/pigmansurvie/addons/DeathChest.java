package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathChest implements Listener {

    @EventHandler
    public void DeathChestSpawn(PlayerDeathEvent event){
        Location deathloc = event.getPlayer().getLocation();
        event.getPlayer().getWorld().getBlockAt(deathloc).setType(Material.CHEST);
    }
}
