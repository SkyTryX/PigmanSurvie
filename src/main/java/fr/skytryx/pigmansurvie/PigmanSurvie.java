package fr.skytryx.pigmansurvie;

import fr.skytryx.pigmansurvie.addons.*;
import fr.skytryx.pigmansurvie.commands.*;
import fr.skytryx.pigmansurvie.commands.staff.CommandInvsee;
import fr.skytryx.pigmansurvie.commands.staff.CommandStafftp;
import fr.skytryx.pigmansurvie.staff.InvseeCheck;
import fr.skytryx.pigmansurvie.staff.XrayAlerts;
import fr.skytryx.pigmansurvie.staff.VillagerAlerts;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PigmanSurvie extends JavaPlugin {

    Logger logger = Bukkit.getLogger();
    @Override
    public void onEnable() {
        logger.log(Level.ALL, "[PigmanSurvie] Plugin enabled!");
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Objects.requireNonNull(getCommand("stafftp")).setExecutor(new CommandStafftp());
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new CommandInvsee());
        Objects.requireNonNull(getCommand("mine")).setExecutor(new CommandMine());
        Objects.requireNonNull(getCommand("XPBottle")).setExecutor(new CommandXPBottle());
        Objects.requireNonNull(getCommand("skill")).setExecutor(new CommandSkill());
        Objects.requireNonNull(getCommand("leaderboard")).setExecutor(new CommandLeaderboard());
        Objects.requireNonNull(getCommand("luckyblock")).setExecutor(new CommandLuckyBlock());
        Objects.requireNonNull(getCommand("lobby")).setExecutor(new CommandLobby());

        //Parametres du monde de minage
        if(Bukkit.getWorld("mineworld") == null){
            new WorldCreator("mineworld").createWorld();
        }

        Util.CreateRecipe(new ItemStack(Material.BEDROCK), Arrays.asList("CSC", "SCS", "CSC"), new HashMap<Character, Material>(){{put('S', Material.STONE); put('C', Material.COBBLESTONE);}}, "bedrock_lol");

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
        getServer().getPluginManager().registerEvents(new SkillListener(), this);
        getServer().getPluginManager().registerEvents(new LuckyBlockBreak(), this);
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        logger.log(Level.ALL, "[PigmanSurvie] Plugin disabled!");
    }
}
