package fr.skytryx.pigmansurvie.addons;

import org.bukkit.entity.Boat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class Boat_antilag implements Listener {
    @EventHandler
    public void bbreak(VehicleExitEvent shifted){
        if (shifted.getVehicle() instanceof Boat){

        }
    }
}
