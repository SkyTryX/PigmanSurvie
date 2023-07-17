package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DeathChest implements Listener {

    @EventHandler
    public void DeathChestSpawn(PlayerDeathEvent event){
        Location deathloc = event.getPlayer().getLocation();
        event.getPlayer().getWorld().getBlockAt(deathloc).setType(Material.CHEST);
        final File file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager()
                .getPlugin("PigmanSurvie")).getDataFolder(), "deathchest.yml");
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        List<ItemStack> player_item = new ArrayList<>();
        for(int i = 0; i < 36; i++){
            player_item.add(event.getPlayer().getInventory().getItem(i));
        }
        player_item.add(event.getPlayer().getInventory().getHelmet());
        player_item.add(event.getPlayer().getInventory().getChestplate());
        player_item.add(event.getPlayer().getInventory().getLeggings());
        player_item.add(event.getPlayer().getInventory().getBoots());
        player_item.add(event.getPlayer().getInventory().getItemInOffHand());
        config.set(String.valueOf(deathloc), player_item.toString());
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void OnDeathChestOpen(PlayerInteractEvent event){
        //if(Objects.requireNonNull(event.getClickedBlock()).getType() != Material.CHEST) return;
        //final File file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager()
        //        .getPlugin("PigmanSurvie")).getDataFolder(), "deathchest.yml");
        //final YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        //if(config.contains(event.getClickedBlock().getLocation().toString())){
            //String list_string= (String) config.get(event.getClickedBlock().getLocation().toString());
            //assert list_string != null;
            //list_string = list_string.replace("[","");
            //list_string= list_string.replace("]","");
            //List<String> player_inv = new ArrayList<>(Arrays.asList(list_string.split(",")));
            //for(int i = 0; i < 36; i++){
                //event.getPlayer().getInventory().setItem(i, (ItemStack) player_inv.get(i));
            //}
        //}
    }
}
