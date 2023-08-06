package fr.skytryx.pigmansurvie.addons;

import fr.skytryx.pigmansurvie.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class BeaconWaypoint implements Listener {

    @EventHandler
    public void OnBeaconClick(PlayerInteractEvent event){
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK || Objects.requireNonNull(event.getClickedBlock()).getType() != Material.BEACON) return;
        if(!event.getPlayer().isSneaking()) return;
        event.setCancelled(true);
        Inventory inventory = Bukkit.createInventory(null, 54, "§7Beacon Menu");
        final File beaconfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "beacon.yml");
        final YamlConfiguration beaconconfig = YamlConfiguration.loadConfiguration(beaconfile);
        if(!Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).isEmpty()){
            AtomicInteger i = new AtomicInteger();
            Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).forEach((path, waypoint) ->{
                inventory.setItem(i.get(), Util.CreateItem(Material.BEACON, (String) beaconconfig.get(path+".name"), Arrays.asList("§b"+ Objects.requireNonNull(beaconconfig.get(path + ".x")), "§b"+ Objects.requireNonNull(beaconconfig.get(path + ".y")), "§b"+ Objects.requireNonNull(beaconconfig.get(path + ".z")), "§b"+Objects.requireNonNull(beaconconfig.get(path + ".world")))));
                i.getAndIncrement();
            });
        }
        Util.StainedGlass(36, 45, inventory);
        inventory.setItem(45, Util.CreateItem(Material.GREEN_WOOL, "§bManage Waypoint", Collections.singletonList("§bCreate/manage beacon's waypoint")));
        inventory.setItem(53, Util.CreateItem(Material.BARRIER, "§cClose", Collections.singletonList("§cClick here to close")));
        event.getPlayer().openInventory(inventory);
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){
        if(event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;
        if(event.getView().getTitle().equals("§7Beacon Menu")){
            event.setCancelled(true);
            if(Objects.requireNonNull(event.getCurrentItem()).getItemMeta().getDisplayName().equals("§cClose")){
                event.getWhoClicked().closeInventory();
            } else if(Objects.requireNonNull(event.getCurrentItem()).getItemMeta().getDisplayName().equals("§bManage Waypoint")){
                Inventory invmanager = Bukkit.createInventory(null, 9, "§7Waypoint Manager");
                invmanager.setItem(0, Util.CreateItem(Material.ITEM_FRAME, "§bChange Logo", Collections.singletonList("§bChange the waypoint's logo")));
                invmanager.setItem(1, Util.CreateItem(Material.NAME_TAG, "§bRename Waypoint", Collections.singletonList("§bChange the waypoint's name")));
                invmanager.setItem(2, Util.CreateItem(Material.CRAFTING_TABLE, "§bCreate Waypoint", Collections.singletonList("§bCreate a new waypoint")));
                invmanager.setItem(3, Util.CreateItem(Material.TNT, "§cDelete Waypoint", Collections.singletonList("§cWill delete the waypoint, FOREVER!")));
                event.getWhoClicked().openInventory(invmanager);
            } else if(event.getSlot() >= 0 && event.getSlot() < 35){
                event.getWhoClicked().teleport(new Location(Bukkit.getWorld(Objects.requireNonNull(event.getCurrentItem().getLore()).get(3).substring(2)), Integer.parseInt(Objects.requireNonNull(event.getCurrentItem().getLore()).get(0).substring(2))+0.5, Integer.parseInt(Objects.requireNonNull(event.getCurrentItem().getLore()).get(1).substring(2))+1, Integer.parseInt(Objects.requireNonNull(event.getCurrentItem().getLore()).get(2).substring(2))+0.5));
                event.getWhoClicked().sendMessage("§c[Waypoint] §bTeleported to "+ event.getCurrentItem().getItemMeta().getDisplayName());
            }

        }else if(event.getView().getTitle().equals("§7Waypoint Manager")){
            final File beaconfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "beacon.yml");
            final YamlConfiguration beaconconfig = YamlConfiguration.loadConfiguration(beaconfile);
            event.setCancelled(true);
            AtomicBoolean exists = new AtomicBoolean(false);
            Block block = event.getWhoClicked().getTargetBlockExact(5);
            if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§bCreate Waypoint")){
                String uuid = UUID.randomUUID().toString();
                if(block != null){
                    Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).forEach((path, waypoint) ->{
                        if(Objects.requireNonNull(beaconconfig.get(path + ".x")).toString().equals(String.valueOf(block.getX())) &&
                        Objects.requireNonNull(beaconconfig.get(path + ".y")).toString().equals(String.valueOf(block.getY())) &&
                        Objects.requireNonNull(beaconconfig.get(path + ".z")).toString().equals(String.valueOf(block.getZ())) &&
                        Objects.requireNonNull(beaconconfig.get(path + ".world")).toString().equals(block.getWorld().getName())){
                            event.getWhoClicked().sendMessage("§c[Waypoint] This waypoint already exists!");
                            exists.set(true);
                        }
                    });
                    if(exists.get() == new AtomicBoolean(false).get()){
                        beaconconfig.set(uuid + ".x", block.getX());
                        beaconconfig.set(uuid + ".y", block.getY());
                        beaconconfig.set(uuid + ".z", block.getZ());
                        beaconconfig.set(uuid + ".world", block.getWorld().getName());
                        beaconconfig.set(uuid + ".name", "§bWaypoint de " + event.getWhoClicked().getName());
                        beaconconfig.set(uuid + ".owner", event.getWhoClicked().getUniqueId().toString());
                        event.getWhoClicked().sendMessage("§c[Waypoint] §bYour waypoint has been added!");
                    }
                }

            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§cDelete Waypoint")){
                if(block != null) {
                    Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).forEach((path, waypoint) -> {
                        if (Objects.requireNonNull(beaconconfig.get(path + ".x")).toString().equals(String.valueOf(block.getX())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".y")).toString().equals(String.valueOf(block.getY())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".z")).toString().equals(String.valueOf(block.getZ())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".world")).toString().equals(block.getWorld().getName())) {
                            beaconconfig.set(path, null);
                            event.getWhoClicked().sendMessage("§c[Waypoint] §bYour waypoint has been deleted!");
                            exists.set(true);
                        }
                    });
                    if(exists.get() == new AtomicBoolean(false).get()) event.getWhoClicked().sendMessage("§c[Waypoint] §cYou can't delete a waypoint that doesn't exist!");
                }
            }try {
                beaconconfig.save(beaconfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

