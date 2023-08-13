package fr.skytryx.pigmansurvie.addons;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.metadata.FixedMetadataValue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class SkillListener implements Listener {

    public void GetXP(String skill, YamlConfiguration config, Player player, Float xp, File file){
        config.set(player.getUniqueId() + "."+skill+".xp", config.getInt(player.getUniqueId() + "."+skill+".xp") + xp);
        BossBar xp_bar = BossBar.bossBar(Component.text("Gained "+ xp + " XP in "+skill), 0f, BossBar.Color.GREEN, BossBar.Overlay.PROGRESS);
        player.showBossBar(xp_bar);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")), () -> player.hideBossBar(xp_bar), 40L);
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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


    Map<Material, Float> Miner = new HashMap<Material, Float>() {{
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
        put(Material.REDSTONE_ORE, 10f);
        put(Material.DEEPSLATE_REDSTONE_ORE, 12f);
        put(Material.LAPIS_ORE, 20f);
        put(Material.DEEPSLATE_LAPIS_ORE, 25f);
        put(Material.ANCIENT_DEBRIS, 300f);
        put(Material.COPPER_ORE, 8f);
        put(Material.DEEPSLATE_COPPER_ORE, 9f);
        put(Material.NETHER_QUARTZ_ORE, 10f);
        put(Material.NETHER_GOLD_ORE, 7f);
        put(Material.EMERALD_ORE, 500f);
        put(Material.DEEPSLATE_EMERALD_ORE, 700f);
    }};
    Map<Material, Float> Farmer = new HashMap<Material, Float>() {{
        put(Material.POTATOES, 10f);
        put(Material.CARROTS, 10f);
        put(Material.BEETROOTS, 15f);
        put(Material.SUGAR_CANE, 10f);
    }};
    Map<Material, Float> WoodCutter = new HashMap<Material, Float>() {{
       put(Material.ACACIA_LOG, 10f);
       put(Material.BIRCH_LOG, 10f);
       put(Material.CHERRY_LOG, 10f);
       put(Material.JUNGLE_LOG, 10f);
       put(Material.DARK_OAK_LOG, 10f);
       put(Material.MANGROVE_LOG, 10f);
       put(Material.SPRUCE_LOG, 10f);
       put(Material.OAK_LOG, 10f);
       put(Material.AZALEA, 20f);
    }};
    Map<Material, Float> Excavater = new HashMap<Material, Float>() {{
        put(Material.DIRT, 1f);
        put(Material.GRASS_BLOCK, 2f);
        put(Material.MYCELIUM, 10f);
        put(Material.PODZOL, 8f);
        put(Material.GRAVEL, 3f);
        put(Material.SAND, 3f);
        put(Material.SUSPICIOUS_GRAVEL, 100f);
        put(Material.SUSPICIOUS_SAND, 100f);
    }};
    @EventHandler
    public void XPGain(BlockBreakEvent event){
        if(event.getPlayer().getGameMode() != GameMode.SURVIVAL) return;
        final File skillfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "skills.yml");
        final YamlConfiguration skillconfig = YamlConfiguration.loadConfiguration(skillfile);
        if(event.getBlock().getMetadata("PLACED").isEmpty()) {
            if (Miner.containsKey(event.getBlock().getType())) {
                GetXP("mining", skillconfig, event.getPlayer(), Miner.get(event.getBlock().getType()), skillfile);
            } else if (Farmer.containsKey(event.getBlock().getType())) {
                GetXP("farming", skillconfig, event.getPlayer(), Farmer.get(event.getBlock().getType()), skillfile);
            } else if (WoodCutter.containsKey(event.getBlock().getType())) {
                GetXP("woodcutting", skillconfig, event.getPlayer(), WoodCutter.get(event.getBlock().getType()), skillfile);
            } else if (Excavater.containsKey(event.getBlock().getType())){
                GetXP("excavating", skillconfig, event.getPlayer(), Excavater.get(event.getBlock().getType()), skillfile);
            }
        }
    }

    @EventHandler
    public void AntiDupe(BlockPlaceEvent event){
        event.getBlock().setMetadata("PLACED", new FixedMetadataValue(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("PigmanSurvie")), "PLACED"));
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent event){
        final File skillfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "skills.yml");
        final YamlConfiguration skillconfig = YamlConfiguration.loadConfiguration(skillfile);
        AtomicInteger lvl_total = new AtomicInteger();
        event.getEnchantsToAdd().forEach((ench, lvl) -> lvl_total.addAndGet(lvl));
        GetXP("enchanting", skillconfig, event.getEnchanter(), (float) (event.getExpLevelCost()+(lvl_total.get()*10)), skillfile);
    }

    @EventHandler
    public void onInvSkill(InventoryClickEvent event){
        final File skillfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "skills.yml");
        final YamlConfiguration skillconfig = YamlConfiguration.loadConfiguration(skillfile);
        if(Objects.requireNonNull(event.getClickedInventory()).getType() == InventoryType.ANVIL){
            event.getWhoClicked().sendMessage(event.getSlot() + " slot");
            AnvilInventory inv = (AnvilInventory) event.getClickedInventory();
            GetXP("forging", skillconfig, (Player)event.getWhoClicked(), (float) (inv.getRepairCost()*10), skillfile);
        } else if (event.getClickedInventory().getType() == InventoryType.GRINDSTONE) {
            GrindstoneInventory inv = (GrindstoneInventory) event.getClickedInventory();
            AtomicInteger lvl_total = new AtomicInteger();
            Arrays.stream(inv.getStorageContents()).filter(Objects::nonNull).forEach(item -> item.getEnchantments().forEach((ench, lvl) -> lvl_total.addAndGet(lvl)));
            GetXP("forging", skillconfig, (Player)event.getWhoClicked(), (float) lvl_total.get()*10, skillfile);
        } else if (event.getClickedInventory().getType() == InventoryType.BREWING) {
            BrewerInventory inv = (BrewerInventory) event.getClickedInventory();
            Arrays.stream(inv.getStorageContents()).forEach(potion ->{
                if(Arrays.asList(Material.POTION, Material.LINGERING_POTION, Material.SPLASH_POTION).contains(Objects.requireNonNull(event.getCurrentItem()).getType())){
                    event.getWhoClicked().sendMessage("idk");
                }
            });
        }
    }
}
