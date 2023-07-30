package fr.skytryx.pigmansurvie.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.Math;

import static java.lang.Math.floorDiv;

public class CommandXPBottle implements CommandExecutor {

    public ItemStack createxpbottle(int n){
        ItemStack xpcontainer=new ItemStack(Material.FLOWER_POT, n);
        xpcontainer.addUnsafeEnchantment(Enchantment.LURE, 1);
        ItemMeta xpcontainer_meta=xpcontainer.getItemMeta();
        xpcontainer_meta.setDisplayName("§aXP Flask");
        xpcontainer_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        xpcontainer.setItemMeta(xpcontainer_meta);
        return xpcontainer;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;
        Player player=(Player) commandSender;
        int nb_flask;
        if (args.length == 0){
            nb_flask = floorDiv((Math.toIntExact(Math.round(player.getTotalExperience()*0.8))), 50);
            player.setLevel(0);
            player.setExp(0);
        } else {
            try {
                int exp = player.getTotalExperience()*(Integer.parseInt(args[0])/100);
                nb_flask = floorDiv((Math.toIntExact(Math.round(exp*0.8))), 50);
            } catch(NumberFormatException e) {
                return false;
            }
        }
            player.getInventory().addItem(createxpbottle(nb_flask));
            player.sendMessage("§c[XPBottle] §bVous recevez " + nb_flask + "flask d'xp.");
            return true;
    }
}
