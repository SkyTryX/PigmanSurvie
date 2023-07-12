package fr.skytryx.pigmansurvie;

import fr.skytryx.pigmansurvie.staff.XrayAlerts;
import fr.skytryx.pigmansurvie.staff.VillagerAlerts;
import org.bukkit.plugin.java.JavaPlugin;

public final class PigmanSurvie extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[PigmanSurvie] Plugin enabled!");
        getServer().getPluginManager().registerEvents(new XrayAlerts(), this);
        getServer().getPluginManager().registerEvents(new VillagerAlerts(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("[PigmanSurvie] Plugin disabled!");
    }
}
