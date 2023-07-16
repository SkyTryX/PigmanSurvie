package fr.skytryx.pigmansurvie.addons;

import org.bukkit.entity.Boat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Boat_antilag implements Listener {
    @EventHandler
    public void bbreak(){
        if (bbreak().getVehicle instanceof Boat){
            bbreak().setHealth(0);
            bbreak().getPlayer().sendmessage("Ton bateau a été cassé");

        }
    }
}
