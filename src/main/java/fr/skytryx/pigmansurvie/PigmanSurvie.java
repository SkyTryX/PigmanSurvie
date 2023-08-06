package fr.skytryx.pigmansurvie;

import fr.skytryx.pigmansurvie.addons.*;
import fr.skytryx.pigmansurvie.commands.CommandMine;
import fr.skytryx.pigmansurvie.commands.CommandXPBottle;
import fr.skytryx.pigmansurvie.commands.staff.CommandInvsee;
import fr.skytryx.pigmansurvie.commands.staff.CommandStafftp;
import fr.skytryx.pigmansurvie.staff.InvseeCheck;
import fr.skytryx.pigmansurvie.staff.XrayAlerts;
import fr.skytryx.pigmansurvie.staff.VillagerAlerts;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PigmanSurvie extends JavaPlugin {


    Logger logger = Bukkit.getLogger();
    @Override
    public void onEnable() {
        logger.log(Level.FINE, "[PigmanSurvie] Plugin enabled!");
        Objects.requireNonNull(getCommand("stafftp")).setExecutor(new CommandStafftp());
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new CommandInvsee());
        Objects.requireNonNull(getCommand("mine")).setExecutor(new CommandMine());
        Objects.requireNonNull(getCommand("XPBottle")).setExecutor(new CommandXPBottle());

        //Parametres du monde de minage
        if(Bukkit.getWorld("mineworld") == null){
            new WorldCreator("mineworld").createWorld();
        }

        getServer().getPluginManager().registerEvents(new XrayAlerts(), this);
        getServer().getPluginManager().registerEvents(new VillagerAlerts(), this);
        getServer().getPluginManager().registerEvents(new InvseeCheck(), this);
        getServer().getPluginManager().registerEvents(new DeathChest(), this);
        getServer().getPluginManager().registerEvents(new Duraping(), this);
        getServer().getPluginManager().registerEvents(new BoatKill(), this);
        getServer().getPluginManager().registerEvents(new PhantomSize(), this);
        getServer().getPluginManager().registerEvents(new XPBottleListener(), this);
        getServer().getPluginManager().registerEvents(new FastLeavesDecay(), this);
        getServer().getPluginManager().registerEvents(new BeaconWaypoint(), this);
    }

    @Override
    public void onDisable() {
        logger.log(Level.FINE, "[PigmanSurvie] Plugin disabled!");
        ;
    }
}
