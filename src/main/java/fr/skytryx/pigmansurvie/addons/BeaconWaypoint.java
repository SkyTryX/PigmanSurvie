package fr.skytryx.pigmansurvie.addons;

import fr.skytryx.pigmansurvie.Util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BeaconWaypoint implements Listener {

    public Map<String, String> queue_rename = new HashMap<>();

    @EventHandler
    public void OnBeaconClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK || Objects.requireNonNull(event.getClickedBlock()).getType() != Material.BEACON)
            return;
        if (!event.getPlayer().isSneaking()) return;
        event.setCancelled(true);
        Inventory inventory = Bukkit.createInventory(null, 54, "§7Beacon Menu");
        final File beaconfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "beacon.yml");
        final YamlConfiguration beaconconfig = YamlConfiguration.loadConfiguration(beaconfile);
        if (!Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).isEmpty()) {
            AtomicInteger i = new AtomicInteger();
            Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).forEach((path, waypoint) -> {
                inventory.setItem(i.get(), Util.CreateItem(Material.valueOf(beaconconfig.getString(path + ".logo")), (String) beaconconfig.get(path + ".name"), Arrays.asList("§b" + Objects.requireNonNull(beaconconfig.get(path + ".x")), "§b" + Objects.requireNonNull(beaconconfig.get(path + ".y")), "§b" + Objects.requireNonNull(beaconconfig.get(path + ".z")), "§b" + Objects.requireNonNull(beaconconfig.get(path + ".world")))));
                i.getAndIncrement();
            });
        }
        Util.StainedGlass(36, 45, inventory);
        inventory.setItem(45, Util.CreateItem(Material.GREEN_WOOL, "§bManage Waypoint", Collections.singletonList("§bCreate/manage beacon's waypoint")));
        inventory.setItem(53, Util.CreateItem(Material.BARRIER, "§cClose", Collections.singletonList("§cClick here to close")));
        event.getPlayer().openInventory(inventory);
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null) return;
        if (event.getView().getTitle().equals("§7Beacon Menu")) {
            event.setCancelled(true);
            if (Objects.requireNonNull(event.getCurrentItem()).getItemMeta().getDisplayName().equals("§cClose")) {
                event.getWhoClicked().closeInventory();
            } else if (Objects.requireNonNull(event.getCurrentItem()).getItemMeta().getDisplayName().equals("§bManage Waypoint")) {
                Inventory invmanager = Bukkit.createInventory(null, 9, "§7Waypoint Manager");
                invmanager.setItem(0, Util.CreateItem(Material.ITEM_FRAME, "§bChange Logo", Collections.singletonList("§bChange the waypoint's logo")));
                invmanager.setItem(1, Util.CreateItem(Material.NAME_TAG, "§bRename Waypoint", Collections.singletonList("§bChange the waypoint's name")));
                invmanager.setItem(2, Util.CreateItem(Material.CRAFTING_TABLE, "§bCreate Waypoint", Collections.singletonList("§bCreate a new waypoint")));
                invmanager.setItem(3, Util.CreateItem(Material.TNT, "§cDelete Waypoint", Collections.singletonList("§cWill delete the waypoint, FOREVER!")));
                event.getWhoClicked().openInventory(invmanager);
            } else if (event.getSlot() >= 0 && event.getSlot() < 35) {
                event.getWhoClicked().teleport(new Location(Bukkit.getWorld(Objects.requireNonNull(event.getCurrentItem().getLore()).get(3).substring(2)), Integer.parseInt(Objects.requireNonNull(event.getCurrentItem().getLore()).get(0).substring(2)) + 0.5, Integer.parseInt(Objects.requireNonNull(event.getCurrentItem().getLore()).get(1).substring(2)) + 1, Integer.parseInt(Objects.requireNonNull(event.getCurrentItem().getLore()).get(2).substring(2)) + 0.5));
                event.getWhoClicked().sendMessage("§c[Waypoint] §bTeleported to " + event.getCurrentItem().getItemMeta().getDisplayName());
            }
        } else if (event.getView().getTitle().equals("§7Waypoint Manager")) {
            final File beaconfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "beacon.yml");
            final YamlConfiguration beaconconfig = YamlConfiguration.loadConfiguration(beaconfile);
            event.setCancelled(true);
            AtomicBoolean exists = new AtomicBoolean(false);
            Block block = event.getWhoClicked().getTargetBlockExact(5);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bCreate Waypoint")) {
                String uuid = UUID.randomUUID().toString();
                if (block != null && block.getType() == Material.BEACON) {
                    if (((Beacon) block.getState()).getTier() != 0) {
                        event.getWhoClicked().sendMessage(String.valueOf((((Beacon) block.getState()).getTier())));
                        Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).forEach((path, waypoint) -> {
                            if (Objects.requireNonNull(beaconconfig.get(path + ".x")).toString().equals(String.valueOf(block.getX())) &&
                                    Objects.requireNonNull(beaconconfig.get(path + ".y")).toString().equals(String.valueOf(block.getY())) &&
                                    Objects.requireNonNull(beaconconfig.get(path + ".z")).toString().equals(String.valueOf(block.getZ())) &&
                                    Objects.requireNonNull(beaconconfig.get(path + ".world")).toString().equals(block.getWorld().getName())) {
                                event.getWhoClicked().sendMessage("§c[Waypoint] This waypoint already exists!");
                                exists.set(true);
                            }
                        });
                        if (exists.get() == new AtomicBoolean(false).get()) {
                            beaconconfig.set(uuid + ".x", block.getX());
                            beaconconfig.set(uuid + ".y", block.getY());
                            beaconconfig.set(uuid + ".z", block.getZ());
                            beaconconfig.set(uuid + ".world", block.getWorld().getName());
                            beaconconfig.set(uuid + ".name", "§bWaypoint de " + event.getWhoClicked().getName());
                            beaconconfig.set(uuid + ".logo", "BEACON");
                            beaconconfig.set(uuid + ".owner", event.getWhoClicked().getUniqueId().toString());
                            event.getWhoClicked().sendMessage("§c[Waypoint] §bYour waypoint has been added!");
                        }
                    } else
                        event.getWhoClicked().sendMessage("§c[Waypoint] §cYou have to activate the beacon to create a waypoint!");
                } else
                    event.getWhoClicked().sendMessage("§c[Waypoint] §cYou have to look at the waypoint in order to do that!");

            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cDelete Waypoint")) {
                if (block != null) {
                    Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).forEach((path, waypoint) -> {
                        if (Objects.requireNonNull(beaconconfig.get(path + ".x")).toString().equals(String.valueOf(block.getX())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".y")).toString().equals(String.valueOf(block.getY())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".z")).toString().equals(String.valueOf(block.getZ())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".world")).toString().equals(block.getWorld().getName()) &&
                                Objects.equals(beaconconfig.getString(path + ".owner"), event.getWhoClicked().getUniqueId().toString())) {
                            beaconconfig.set(path, null);
                            event.getWhoClicked().sendMessage("§c[Waypoint] §bYour waypoint has been deleted!");
                            exists.set(true);
                        }
                    });
                    if (exists.get() == new AtomicBoolean(false).get())
                        event.getWhoClicked().sendMessage("§c[Waypoint] §cYou can't delete a waypoint that doesn't exist/isn't yours!");
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bRename Waypoint")) {
                if (block != null) {
                    Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).forEach((path, waypoint) -> {
                        if (Objects.requireNonNull(beaconconfig.get(path + ".x")).toString().equals(String.valueOf(block.getX())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".y")).toString().equals(String.valueOf(block.getY())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".z")).toString().equals(String.valueOf(block.getZ())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".world")).toString().equals(block.getWorld().getName()) &&
                                Objects.equals(beaconconfig.getString(path + ".owner"), event.getWhoClicked().getUniqueId().toString())) {
                            queue_rename.put(event.getWhoClicked().getUniqueId().toString(), path);
                            event.getWhoClicked().closeInventory();
                            event.getWhoClicked().sendMessage("§c[Waypoint] §bSend in chat the name of your waypoint (use & to make colors)");
                        } else
                            event.getWhoClicked().sendMessage("§c[Waypoint] §cYou can't rename a waypoint that isn't yours/doesn't exist");
                    });
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bChange Logo")) {
                Material holdeditem = event.getWhoClicked().getInventory().getItemInMainHand().getType();
                if (block != null && holdeditem != Material.AIR) {
                    Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).forEach((path, waypoint) -> {
                        if (Objects.requireNonNull(beaconconfig.get(path + ".x")).toString().equals(String.valueOf(block.getX())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".y")).toString().equals(String.valueOf(block.getY())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".z")).toString().equals(String.valueOf(block.getZ())) &&
                                Objects.requireNonNull(beaconconfig.get(path + ".world")).toString().equals(block.getWorld().getName()) &&
                                Objects.equals(beaconconfig.getString(path + ".owner"), event.getWhoClicked().getUniqueId().toString())) {
                            beaconconfig.set(path+".logo", holdeditem.toString());
                            event.getWhoClicked().sendMessage("§c[Waypoint] §bSet logo to "+ holdeditem.toString());
                        }
                    });
                }
            }try {
                beaconconfig.save(beaconfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}

    @EventHandler
    public void ChatInput(AsyncPlayerChatEvent event){
        if(!queue_rename.isEmpty()){
            final File beaconfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "beacon.yml");
            final YamlConfiguration beaconconfig = YamlConfiguration.loadConfiguration(beaconfile);
            if(queue_rename.containsKey(event.getPlayer().getUniqueId().toString())){
                String msg = event.getMessage().replace("&", "§");
                event.setCancelled(true);
                beaconconfig.set(queue_rename.get(event.getPlayer().getUniqueId().toString())+".name", msg);
                try {
                    beaconconfig.save(beaconfile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                event.getPlayer().sendMessage("§c[Waypoint] §bSet your waypoint name to §f"+msg);
                queue_rename.remove(event.getPlayer().getUniqueId().toString());
            }
        }
    }

    @EventHandler
    public void OnBeaconBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.BEACON) return;
        Block block = event.getBlock();
        final File beaconfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "beacon.yml");
        final YamlConfiguration beaconconfig = YamlConfiguration.loadConfiguration(beaconfile);
        Objects.requireNonNull(beaconconfig.getConfigurationSection("")).getValues(false).forEach((path, waypoint) -> {
            if (Objects.requireNonNull(beaconconfig.get(path + ".x")).toString().equals(String.valueOf(block.getX())) &&
                    Objects.requireNonNull(beaconconfig.get(path + ".y")).toString().equals(String.valueOf(block.getY())) &&
                    Objects.requireNonNull(beaconconfig.get(path + ".z")).toString().equals(String.valueOf(block.getZ())) &&
                    Objects.requireNonNull(beaconconfig.get(path + ".world")).toString().equals(block.getWorld().getName())) {
                beaconconfig.set(path, null);
            }
        });
        try {
            beaconconfig.save(beaconfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


