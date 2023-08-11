package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SkillListener implements Listener {

    @EventHandler
    public void ProfileCreation(PlayerJoinEvent event){
        final File skillfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "skills.yml");
        final YamlConfiguration skillconfig = YamlConfiguration.loadConfiguration(skillfile);
        if(skillconfig.get(event.getPlayer().getUniqueId().toString()) == null){
            Arrays.asList("farming", "mining", "excavating", "woodcutting", "fishing", "fighting", "bow-ing", "enchanting", "forging", "brewing").forEach(sk ->{
                skillconfig.set(event.getPlayer().getUniqueId()+"."+sk+".xp", 0);
                skillconfig.set(event.getPlayer().getUniqueId()+"."+sk+".level", 0);
            });
            try {
                skillconfig.save(skillfile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    Map<Material, Float> Miner  = new HashMap<Material, Float>() {{
        put(Material.STONE , 1f);
        put(Material.COAL_ORE, 5f);
        put(Material.DEEPSLATE, 1.5f);
        put(Material.DEEPSLATE_COAL_ORE, 6f);
        put(Material.IRON_ORE, 9f);
        put(Material.DEEPSLATE_IRON_ORE, 10f);
        put(Material.GOLD_ORE, 15f);
        put(Material.DEEPSLATE_GOLD_ORE, 17.5f);
        put(Material.DIAMOND_ORE, 30f);
        put(Material.DEEPSLATE_DIAMOND_ORE, 35f);
    }};
    @EventHandler
    public void XPGain(BlockBreakEvent event){
        final File skillfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "skills.yml");
        final YamlConfiguration skillconfig = YamlConfiguration.loadConfiguration(skillfile);
        if(event.getBlock().getMetadata("PLACED").isEmpty()) {
            if (Miner.containsKey(event.getBlock().getType())) {
                skillconfig.set(event.getPlayer().getUniqueId() + ".mining.xp", skillconfig.getInt(event.getPlayer().getUniqueId() + ".mining.xp") + Miner.get(event.getBlock().getType()));
            }
        }
        try {
            skillconfig.save(skillfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void AntiDupe(BlockPlaceEvent event){
        event.getBlock().setMetadata("PLACED", new FixedMetadataValue(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("PigmanSurvie")), "PLACED"));
    }
}
