package fr.skytryx.pigmansurvie.commands;

import fr.skytryx.pigmansurvie.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class CommandSkill implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;
        final File skillfile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("PigmanSurvie")).getDataFolder(), "skills.yml");
        final YamlConfiguration skillconfig = YamlConfiguration.loadConfiguration(skillfile);
        Inventory skill_inv = Bukkit.createInventory(null, 36, "§7Skill Menu");
        Util.StainedGlass(0,9, skill_inv);
        skill_inv.setItem(11, Util.CreateItem(Material.DIAMOND_HOE, "§6Farming", Arrays.asList("§bLevel: §6"+ skillconfig.getString(player.getUniqueId()+".farming.xp")+"§b/20", "§8...")));
        skill_inv.setItem(12, Util.CreateItem(Material.DIAMOND_PICKAXE, "§6Mining", Arrays.asList("§bLevel: §6"+ skillconfig.getString(player.getUniqueId()+".mining.xp")+"§b/20", "§8...")));
        skill_inv.setItem(13, Util.CreateItem(Material.DIAMOND_SHOVEL, "§6Excavating", Arrays.asList("§bLevel: §6"+ skillconfig.getString(player.getUniqueId()+".excavating.xp")+"§b/20", "§8...")));
        skill_inv.setItem(14, Util.CreateItem(Material.DIAMOND_AXE, "§6WoodCutting", Arrays.asList("§bLevel: §6"+skillconfig.getString(player.getUniqueId()+".woodcutting.xp")+"§b/20", "§8...")));
        skill_inv.setItem(15, Util.CreateItem(Material.FISHING_ROD, "§6Fishing", Arrays.asList("§bLevel: §6"+skillconfig.getString(player.getUniqueId()+".woodcutting.xp")+"§b/20", "§8...")));

        skill_inv.setItem(20, Util.CreateItem(Material.DIAMOND_SWORD, "§6Fighting", Arrays.asList("§bLevel: §6"+skillconfig.getString(player.getUniqueId()+".fighting.xp")+"§b/20", "§8...")));
        skill_inv.setItem(21, Util.CreateItem(Material.BOW, "§6Bow-ing", Arrays.asList("§bLevel: §6"+skillconfig.getString(player.getUniqueId()+".bow-ing.xp")+"§b/20", "§8...")));
        skill_inv.setItem(22, Util.CreateItem(Material.ENCHANTING_TABLE, "§6Enchanting", Arrays.asList("§bLevel: §6"+ skillconfig.getString(player.getUniqueId()+".enchanting.xp")+"§b/20", "§8...")));
        skill_inv.setItem(23, Util.CreateItem(Material.ANVIL, "§6Forging", Arrays.asList("§bLevel: §6"+ skillconfig.getString(player.getUniqueId()+".forging.xp")+"§b/20", "§8...")));
        skill_inv.setItem(24, Util.CreateItem(Material.BREWING_STAND, "§6Brewing", Arrays.asList("§bLevel: §6"+ skillconfig.getString(player.getUniqueId()+".brewing.xp")+"§b/20", "§8...")));
        Util.StainedGlass(27, 36, skill_inv);
        player.openInventory(skill_inv);
        return true;
    }
}
