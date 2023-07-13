package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.meta.Damageable;


public class Duraping implements Listener {
    @EventHandler
    public void ping(PlayerItemDamageEvent event){
        Damageable im = (Damageable) event.getItem().getItemMeta();
        if( (double) im.getDamage() / event.getItem().getType().getMaxDurability()  == 0.1){
            event.getPlayer().sendMessage("§c[Outils] §4ATTENTION! §bTon outils va bientôt §6casser");
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_BREAK,50.0f,1.0f);
        }

    }
}
