package fr.skytryx.pigmansurvie;

import fr.skytryx.pigmansurvie.addons.BoatKill;
import fr.skytryx.pigmansurvie.addons.DeathChest;
import fr.skytryx.pigmansurvie.addons.Duraping;
import fr.skytryx.pigmansurvie.addons.Mineworld;
import fr.skytryx.pigmansurvie.commands.staff.CommandInvsee;
import fr.skytryx.pigmansurvie.commands.staff.CommandStafftp;
import fr.skytryx.pigmansurvie.staff.InvseeCheck;
import fr.skytryx.pigmansurvie.staff.XrayAlerts;
import fr.skytryx.pigmansurvie.staff.VillagerAlerts;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PigmanSurvie extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[PigmanSurvie] Plugin enabled!");
        Objects.requireNonNull(getCommand("stafftp")).setExecutor(new CommandStafftp());
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new CommandInvsee());
        WorldCreator wc = new WorldCreator("mineworld");

        getServer().getPluginManager().registerEvents(new XrayAlerts(), this);
        getServer().getPluginManager().registerEvents(new VillagerAlerts(), this);
        getServer().getPluginManager().registerEvents(new InvseeCheck(), this);
        getServer().getPluginManager().registerEvents(new DeathChest(), this);
        getServer().getPluginManager().registerEvents(new Duraping(), this);
        getServer().getPluginManager().registerEvents(new BoatKill(), this);
        getServer().getPluginManager().registerEvents(new Mineworld(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[PigmanSurvie] Plugin disabled!");
    }
}
