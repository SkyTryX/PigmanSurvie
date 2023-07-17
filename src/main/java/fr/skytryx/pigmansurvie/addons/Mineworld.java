package fr.skytryx.pigmansurvie.addons;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Mineworld implements CommandExecutor {

    @Override
    //Verification de l'auteur de la commande
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return false;
        ((Player)commandSender).teleport(Objects.requireNonNull(Bukkit.getWorld("mineworld")).getSpawnLocation());
        return true;
    }
}

