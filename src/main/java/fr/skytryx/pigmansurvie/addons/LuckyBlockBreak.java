package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.bukkit.World;


public class LuckyBlockBreak implements Listener {

    @EventHandler
    public void LBPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType() != Material.PLAYER_HEAD && event.getItemInHand().getItemMeta().getDisplayName().startsWith("§"))
            return;
        Player player = event.getPlayer();
        if (event.getItemInHand().getItemMeta().getDisplayName().contains("Coal")) {
            switch ((int) (Math.random() * (10 - 1 + 1) + 1)) {
                case 1:
                    event.getPlayer().kick();
                    break;
                case 2:
                    event.getPlayer().sendMessage("§c[LuckyBlock] §bTu vas être clear de ton stuff dans 10 secondes!!!");
                    break;
                    // dorian la t un batard :)
                case 3:
                    Location playerLocation = event.getPlayer().getLocation();
                    int diamondsToGive = 3;

                    for (int i = 0; i < diamondsToGive; i++) {
                        spawnDiamond(playerLocation);
                    }
                    break;
                case 4:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT, 9));
                    break;
                case 5:
                    Location player_loc = new Location(event.getPlayer().getWorld(), event.getPlayer().getLocation().getBlockX(), event.getPlayer().getLocation().getBlockY() + 100, event.getPlayer().getLocation().getBlockZ());
                    if (player_loc.getBlockY() >= 320) {
                        player_loc.setY(319);
                    }
                    event.getPlayer().getWorld().getBlockAt(player_loc).setType(Material.DAMAGED_ANVIL);
                    break;
                case 6:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.COAL_ORE, 64));
                    break;
                case 7:
                    event.getPlayer().setExp(event.getPlayer().getExp() * 2);
                    break;
                case 8:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT, 32));
                    break;
                case 9:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.COAL, 32));
                    break;
                case 10:
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 60, 5));
                    break;

            }
        } else if (event.getItemInHand().getItemMeta().getDisplayName().contains("Iron")) {
            switch ((int) (Math.random() * (10 - 1 + 1) + 1)) {
                case 1:
                    Location playerLocation = event.getPlayer().getLocation();
                    generateIronStructure(playerLocation);
                    break;
                case 2:
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 5));
                    break;
                case 3:
                    event.getPlayer().setHealth(1);
                    event.getPlayer().setFoodLevel(0);
                    event.getPlayer().setSaturation(0);
                    Location locationmob = player.getLocation().add(2,0,2);
                    spawnMobnearPlayer(player, locationmob, EntityType.ENDERMITE);
                    break;
                case 4:
                    event.getPlayer().sendMessage("Fait 10 pompe ou il y aura une explosion dans ta base!!");
                    new BukkitRunnable() {
                        @Override
                        public void run(){
                            player.sendMessage("Allez plus vite que ca, Tu cherche les problemes la !!!");
                        }
                    }.runTaskLater((Plugin) this, 60L);
                    break;
                case 5:
                player.kickPlayer("Vous allez etre banni");
                case 6:
                Location locationmob2 = player.getLocation().add(2,0,2);
                    for (int i = 0; i < 4; i++) {
                        spawnMobnearPlayer(player, locationmob2, EntityType.IRON_GOLEM);
                    }
                case 7:
                    Location playerLocation2 = event.getPlayer().getLocation();
                    int ironToGive = 32;

                    for (int i = 0; i < ironToGive; i++) {
                        spawnIron(playerLocation2);
                    }
                case 8:
                    Location playerLoc2 = player.getLocation().add(0,0,100);
                    Location playertp = playerLoc2.add(0,0,3);
                    generateIronStructure(playerLoc2);
                    player.teleport(playertp);
                    player.sendMessage("J'espere que tu a un seau d'eau ;)");
                case 9:
                    Location playerLocation4 = player.getLocation();
                    World world = player.getWorld();
                    world.strikeLightning(playerLocation4);
                case 10:
                    player.sendMessage("Wait a second");
                    new BukkitRunnable() {
                        @Override
                        public void run(){
                            player.sendMessage("Still waiting ?");
                        }
                    }.runTaskLater((Plugin) this, 60L);

            }
        } else if (event.getItemInHand().getItemMeta().getDisplayName().contains("Gold")) {
            switch ((int) (Math.random() * (10 - 1 + 1) + 1)) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
            }
        } else if (event.getItemInHand().getItemMeta().getDisplayName().contains("Diamond")) {
            switch ((int) (Math.random() * (10 - 1 + 1) + 1)) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
            }
        } else if (event.getItemInHand().getItemMeta().getDisplayName().contains("Netherite")) {
            switch ((int) (Math.random() * (10 - 1 + 1) + 1)) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
            }
        } else if (event.getItemInHand().getItemMeta().getDisplayName().contains("Redstone")) {
            switch ((int) (Math.random() * (10 - 1 + 1) + 1)) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
            }
        } else if (event.getItemInHand().getItemMeta().getDisplayName().contains("Emerald")) {
            switch ((int) (Math.random() * (10 - 1 + 1) + 1)) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
            }
        }
        event.setCancelled(true);
        event.getItemInHand().setAmount(event.getItemInHand().getAmount() - 1);
    }
//Spawn d'un mob
    private void spawnMobnearPlayer(Player player, Location locationmob, EntityType entityType) {
        locationmob.getWorld().spawnEntity(locationmob, entityType);
    }

// Pluie de diamand
    public void spawnDiamond(Location location) {
        Item diamond = location.getWorld().dropItem(location, new ItemStack(Material.DIAMOND));
        diamond.setVelocity(new Vector(Math.random() - 0.5, 1, Math.random() - 0.5));
    }
    public void spawnIron(Location location) {
        Item iron = location.getWorld().dropItem(location, new ItemStack(Material.IRON_INGOT));
        iron.setVelocity(new Vector(Math.random() - 0.5, 1, Math.random() - 0.5));
    }
   // cube metal creux
    public void generateIronStructure(Location location) {
        int size = 3; // Taille du cube

        for (int x = -size; x <= size; x++) {
            for (int y = -size; y <= size; y++) {
                for (int z = -size; z <= size; z++) {
                    if (Math.abs(x) == size || Math.abs(y) == size || Math.abs(z) == size) {
                        Location blockLocation = location.clone().add(x, y, z);
                        Block block = blockLocation.getBlock();

                        if (block.getType() != Material.AIR) {
                            continue; // Ne remplacez pas les blocs existants
                        }

                        block.setType(Material.IRON_BLOCK);
                    }
                }
            }
        }
    }
}

