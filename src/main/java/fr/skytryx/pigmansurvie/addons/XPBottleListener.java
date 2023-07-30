package fr.skytryx.pigmansurvie.addons;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class XPBottleListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player= event.getPlayer();
        Action action=event.getAction();
        ItemStack item=event.getItem();
        if (item==null || item.getItemMeta()==null)return;
        if (item.getItemMeta().getDisplayName().equals("§aXP Flask") && action==Action.RIGHT_CLICK_AIR){
            event.setCancelled(true);
            player.giveExp(50);
            item.setAmount(item.getAmount()-1);
            player.sendMessage("Vous absorbez 50 points d'xp");

        }
    }
}