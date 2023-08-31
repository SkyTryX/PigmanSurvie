package fr.skytryx.pigmansurvie.staff;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvseeCheck implements Listener {
    @EventHandler
    public void InvseeUnDupe(InventoryClickEvent event){
        if(event.getView().getTitle().equals("§8Leaderboard")) event.setCancelled(true);
        if(event.getView().getTitle().equals("§7Skill Menu")) event.setCancelled(true);
    }
}
