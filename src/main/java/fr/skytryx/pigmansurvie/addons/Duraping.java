package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.meta.Damageable;


public class Duraping implements Listener {
    @EventHandler
    public void ping(PlayerItemDamageEvent event){
        Damageable im = (Damageable) event.getItem().getItemMeta();
        if(im.getDamage() == 10){
            event.getPlayer().sendMessage("alert");
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_BREAK,1,1);
        }

    }
}
