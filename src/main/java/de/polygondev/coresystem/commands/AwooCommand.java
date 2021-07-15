package de.polygondev.coresystem.commands;

import de.polygondev.coresystem.CoreSystem;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


// awoo command
// very important
public class AwooCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("coresystem.awoo")){
                for (Player all : Bukkit.getOnlinePlayers()){
                    all.sendTitle("§9AWOOOOOOOOOOOOOOOOOOOOOO", "§9AWOOOOOOOOOOOOOOOOOOOOOO");
                    all.playSound(all.getLocation(), Sound.ENTITY_WOLF_HOWL, 1, 1);
                }
            } else {
                CoreSystem.messaging.sendMessage(null, "noPermission", p);
            }
        }
        return false;
    }
}
