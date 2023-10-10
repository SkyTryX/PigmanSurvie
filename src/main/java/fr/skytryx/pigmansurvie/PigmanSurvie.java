package fr.skytryx.pigmansurvie;

import fr.skytryx.pigmansurvie.addons.*;
import fr.skytryx.pigmansurvie.commands.*;
import fr.skytryx.pigmansurvie.staff.InvseeCheck;
import fr.skytryx.pigmansurvie.staff.XrayAlerts;
import fr.skytryx.pigmansurvie.staff.VillagerAlerts;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class PigmanSurvie extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Objects.requireNonNull(getCommand("mine")).setExecutor(new CommandMine());
        Objects.requireNonNull(getCommand("XPBottle")).setExecutor(new CommandXPBottle());
        Objects.requireNonNull(getCommand("skill")).setExecutor(new CommandSkill());
        Objects.requireNonNull(getCommand("leaderboard")).setExecutor(new CommandLeaderboard());
        Objects.requireNonNull(getCommand("luckyblock")).setExecutor(new CommandLuckyBlock());

        if(Bukkit.getWorld("mineworld") == null){
            new WorldCreator("mineworld").createWorld();
        }
        //Util.CreateRecipe(Util.CreateHead("Coal", CommandLuckyBlock.LB_Texture.get("Coal")), Arrays.asList(" C ", "C C", " C "), new HashMap<Character, Material>(){{put('C', Material.COAL_BLOCK);}}, "Coal_LB");
        //Util.CreateRecipe(Util.CreateHead("Iron", CommandLuckyBlock.LB_Texture.get("Iron")), Arrays.asList(" C ", "C C", " C "), new HashMap<Character, Material>(){{put('C', Material.IRON_BLOCK);}}, "Iron_LB");
        //Util.CreateRecipe(Util.CreateHead("Gold", CommandLuckyBlock.LB_Texture.get("Gold")), Arrays.asList(" C ", "C C", " C "), new HashMap<Character, Material>(){{put('C', Material.GOLD_BLOCK);}}, "Gold_LB");
        //Util.CreateRecipe(Util.CreateHead("Diamond", CommandLuckyBlock.LB_Texture.get("Diamond")), Arrays.asList("   ", "C C", "   "), new HashMap<Character, Material>(){{put('C', Material.DIAMOND_BLOCK);}}, "Diamond_LB");
        //Util.CreateRecipe(Util.CreateHead("Netherite", CommandLuckyBlock.LB_Texture.get("Netherite")), Arrays.asList("C C", "C C", "C C"), new HashMap<Character, Material>(){{put('C', Material.NETHERITE_INGOT);}}, "Netherite_LB");
        //Util.CreateRecipe(Util.CreateHead("Redstone", CommandLuckyBlock.LB_Texture.get("Redstone")), Arrays.asList("SSS", "SCS", "SSS"), new HashMap<Character, Material>(){{put('C', Material.REDSTONE_BLOCK); put('S', Material.SCULK);}}, "Redstone_LB");
        //Util.CreateRecipe(Util.CreateHead("Emerald", CommandLuckyBlock.LB_Texture.get("Emerald")), Arrays.asList("LBL", "EDE", "LBL"), new HashMap<Character, Material>(){{put('L', Material.LAPIS_LAZULI); put('B', Material.LAPIS_BLOCK); put('E', Material.EMERALD_BLOCK); put('D', Material.DIAMOND);}}, "Emerald_LB");

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
        getServer().getPluginManager().registerEvents(new BetaMSGs(), this);

        Bukkit.getLogger().info("[PigmanSurvie] Le plugin a été activé");
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        Bukkit.getLogger().info("[PigmanSurvie] Le plugin a été desactivé");
    }
}
