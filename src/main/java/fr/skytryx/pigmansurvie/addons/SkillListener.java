package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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


}
