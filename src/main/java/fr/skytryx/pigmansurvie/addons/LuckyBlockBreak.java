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
        if(event.getBlock().getType() != Material.PLAYER_HEAD) return;
        if(event.getItemInHand().getItemMeta().getDisplayName().contains("Coal")){
            int chance = (int) (Math.random() * (10 - 1 + 1) + 1);
            if(chance == 1) event.getPlayer().kick();
            else if(chance == 2) event.getPlayer().sendMessage("§c[LuckyBlock] §bTu vas être clear de ton stuff dans 10 secondes!!!");
            else if(chance == 3) event.getPlayer().getInventory().addItem(new ItemStack(Material.DIAMOND));
            else if(chance == 4) event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT, 9));
            else if(chance == 5) {
                Location player_loc = new Location(event.getPlayer().getWorld(), event.getPlayer().getLocation().getBlockX(), event.getPlayer().getLocation().getBlockY() + 100, event.getPlayer().getLocation().getBlockZ());
                if (player_loc.getBlockY() >= 320) {
                    player_loc.setY(319);
                }
                event.getPlayer().getWorld().getBlockAt(player_loc).setType(Material.DAMAGED_ANVIL);
            }
            else if(chance == 6) event.getPlayer().getInventory().addItem(new ItemStack(Material.COAL_ORE, 64));
            else if(chance == 7) event.getPlayer().setExp(event.getPlayer().getExp()*2);
            else if(chance == 8) event.getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT, 32));
            else if(chance == 9) event.getPlayer().getInventory().addItem(new ItemStack(Material.COAL, 32));
            else if(chance == 10) event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 60, 5));
            event.setCancelled(true);
            event.getItemInHand().setAmount(event.getItemInHand().getAmount()-1);
        }
    }
}
