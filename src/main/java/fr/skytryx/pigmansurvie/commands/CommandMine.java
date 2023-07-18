package fr.skytryx.pigmansurvie.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandMine implements CommandExecutor {

    @Override
    //Verification de l'auteur de la commande
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;
        if (strings.length == 1 && strings[0].equals("reload")){
            if (player.isOp()) {
                player.sendMessage("ok mon gars");
                Bukkit.getOnlinePlayers().forEach(p -> {
                    p.sendMessage("Le serveur va se reinitialiser");
                    p.kick();
                });
            }
            else{
                player.sendMessage("Vous n'avez pas la permission pour faire ca.");
            }
        };

        if (player.getWorld() == Bukkit.getWorld("mineworld")) {
            player.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
            player.sendMessage("§c[MineWorld] §bTu as été téléporté dans l'§6Overworld");

        }
        else{
            player.teleport(Objects.requireNonNull(Bukkit.getWorld("mineworld")).getSpawnLocation());
            player.sendMessage("§c[MineWorld] §bTu as été téléporté dans le monde §6Minage");
        }
        return true;
    }
}

