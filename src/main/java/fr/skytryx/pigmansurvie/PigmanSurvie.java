package fr.skytryx.pigmansurvie;

import fr.skytryx.pigmansurvie.addons.Boat_antilag;
import fr.skytryx.pigmansurvie.addons.DeathChest;
import fr.skytryx.pigmansurvie.addons.Duraping;
import fr.skytryx.pigmansurvie.commands.staff.CommandInvsee;
import fr.skytryx.pigmansurvie.commands.staff.CommandStafftp;
import fr.skytryx.pigmansurvie.staff.InvseeCheck;
import fr.skytryx.pigmansurvie.staff.XrayAlerts;
import fr.skytryx.pigmansurvie.staff.VillagerAlerts;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PigmanSurvie extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[PigmanSurvie] Plugin enabled!");
        Objects.requireNonNull(getCommand("stafftp")).setExecutor(new CommandStafftp());
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new CommandInvsee());

        getServer().getPluginManager().registerEvents(new XrayAlerts(), this);
        getServer().getPluginManager().registerEvents(new VillagerAlerts(), this);
        getServer().getPluginManager().registerEvents(new InvseeCheck(), this);
        getServer().getPluginManager().registerEvents(new DeathChest(), this);
        getServer().getPluginManager().registerEvents(new Duraping(), this);
        getServer().getPluginManager().registerEvents(new Boat_antilag(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[PigmanSurvie] Plugin disabled!");
    }
}
