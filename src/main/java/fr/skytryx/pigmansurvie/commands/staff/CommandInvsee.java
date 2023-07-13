package fr.skytryx.pigmansurvie.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandInvsee implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player) || strings.length == 0) return false;
        if(Bukkit.getPlayer(strings[0]) == null) return false;
        Player player = (Player) commandSender;
        PlayerInventory playerinv = Objects.requireNonNull(Bukkit.getPlayer(strings[0])).getInventory();
        Inventory inv = Bukkit.createInventory(null, 54, "§7Invsee");
        for(int i = 0; i < 36; i++){
            inv.setItem(i, playerinv.getItem(i));
        }
        for(int i = 36; i < 45; i++) inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        inv.setItem(45, playerinv.getHelmet());
        inv.setItem(46, playerinv.getChestplate());
        inv.setItem(47, playerinv.getLeggings());
        inv.setItem(48, playerinv.getBoots());
        inv.setItem(53, playerinv.getItemInOffHand());
        player.openInventory(inv);
        return true;
    }
}
