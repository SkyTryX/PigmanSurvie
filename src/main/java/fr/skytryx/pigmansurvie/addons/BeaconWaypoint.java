package fr.skytryx.pigmansurvie.addons;

import fr.skytryx.pigmansurvie.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.Collections;


public class BeaconWaypoint implements Listener {

    @EventHandler
    public void OnBeaconClick(PlayerInteractEvent event){
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getClickedBlock().getType() != Material.BEACON) return;
        event.setCancelled(true);
        Inventory inventory = Bukkit.createInventory(null, 54, "§7Beacon Menu");
        Util.StainedGlass(36, 45, inventory);
        inventory.setItem(53, Util.CreateItem(Material.BARRIER, "§cClose", Collections.singletonList("§cClick here to close")));
        inventory.setItem(52, Util.CreateItem(Material.BEACON, "§bBeacon Menu", Arrays.asList("§bAccess to the beacon's", "§bmenu!")));
        event.getPlayer().openInventory(inventory);
    }
}
