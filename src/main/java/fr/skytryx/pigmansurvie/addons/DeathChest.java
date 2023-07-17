package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class DeathChest implements Listener {

    @EventHandler
    public void DeathChestSpawn(PlayerDeathEvent event){
        Location deathloc = event.getPlayer().getLocation();
        event.getPlayer().getWorld().getBlockAt(deathloc).setType(Material.CHEST);
        BlockData block = event.getPlayer().getWorld().getBlockAt(deathloc).getBlockData();

        for(int i = 0; i < 36; i++){
            ((Chest)block).getBlockInventory().addItem(Objects.requireNonNull(event.getPlayer().getInventory().getItem(i)));
            if(Objects.requireNonNull(((Chest)block).getBlockInventory().getItem(((Chest)block).getBlockInventory().getSize())).getType() != Material.AIR){
                Location newchest = new Location(deathloc.getWorld(), deathloc.getBlockX()+1, deathloc.getBlockY(), deathloc.getBlockZ());
                newchest.getBlock().setType(Material.CHEST);
                block = newchest.getBlock().getBlockData();
            }
        }
        ((Chest)block).getBlockInventory().addItem(Objects.requireNonNull(event.getPlayer().getInventory().getHelmet()));
        ((Chest)block).getBlockInventory().addItem(Objects.requireNonNull(event.getPlayer().getInventory().getChestplate()));
        ((Chest)block).getBlockInventory().addItem(Objects.requireNonNull(event.getPlayer().getInventory().getLeggings()));
        ((Chest)block).getBlockInventory().addItem(Objects.requireNonNull(event.getPlayer().getInventory().getBoots()));
        ((Chest)block).getBlockInventory().addItem(event.getPlayer().getInventory().getItemInOffHand());
    }
}
