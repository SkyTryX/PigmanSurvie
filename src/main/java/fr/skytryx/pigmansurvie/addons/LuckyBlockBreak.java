package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LuckyBlockBreak implements Listener {

    @EventHandler
    public void LBPlace(BlockPlaceEvent event){
        if(event.getBlock().getType() != Material.PLAYER_HEAD && event.getItemInHand().getItemMeta().getDisplayName().startsWith("§")) return;
        if(event.getItemInHand().getItemMeta().getDisplayName().contains("Coal")){
            switch((int) (Math.random() * (10 - 1 + 1) + 1)){
                case 1:
                    event.getPlayer().kick();
                case 2:
                    event.getPlayer().sendMessage("§c[LuckyBlock] §bTu vas être clear de ton stuff dans 10 secondes!!!");
                case 3:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.DIAMOND));
                case 4:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT, 9));
                case 5:
                    Location player_loc = new Location(event.getPlayer().getWorld(), event.getPlayer().getLocation().getBlockX(), event.getPlayer().getLocation().getBlockY() + 100, event.getPlayer().getLocation().getBlockZ());
                    if (player_loc.getBlockY() >= 320) {
                        player_loc.setY(319);
                    }
                    event.getPlayer().getWorld().getBlockAt(player_loc).setType(Material.DAMAGED_ANVIL);
                case 6:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.COAL_ORE, 64));
                case 7:
                    event.getPlayer().setExp(event.getPlayer().getExp()*2);
                case 8:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT, 32));
                case 9:
                    event.getPlayer().getInventory().addItem(new ItemStack(Material.COAL, 32));
                case 10:
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 60, 5));
            }
        } else if(event.getItemInHand().getItemMeta().getDisplayName().contains("Iron")){
            switch((int) (Math.random() * (10 - 1 + 1) + 1)){
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
        } else if(event.getItemInHand().getItemMeta().getDisplayName().contains("Gold")) {
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
        }else if(event.getItemInHand().getItemMeta().getDisplayName().contains("Diamond")) {
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
        }else if(event.getItemInHand().getItemMeta().getDisplayName().contains("Netherite")) {
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
        } else if(event.getItemInHand().getItemMeta().getDisplayName().contains("Redstone")) {
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
        } else if(event.getItemInHand().getItemMeta().getDisplayName().contains("Emerald")) {
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
        event.getItemInHand().setAmount(event.getItemInHand().getAmount()-1);
    }
}
