package fr.skytryx.pigmansurvie.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.codehaus.plexus.util.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
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
                    p.kickPlayer("Le server minage se reinitialise");

                });
            }
            else{
                player.sendMessage("Vous n'avez pas la permission pour faire ca.");
            }
            Bukkit.unloadWorld("mineworld", false);
            try { FileUtils.deleteDirectory(new File("mineworld"));} catch (IOException e) {System.out.println("ca existe pas");}
            new WorldCreator("mineworld").createWorld();
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

