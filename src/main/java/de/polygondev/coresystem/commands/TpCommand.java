package de.polygondev.coresystem.commands;

import de.polygondev.coresystem.CoreSystem;
import de.polygondev.coresystem.Data;
import de.polygondev.coresystem.utils.Messaging;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TpCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("coresystem.tp")){
                if (strings.length == 0){
                    CoreSystem.messaging.sendMessage(null, "tpNoArgs", p);
                } else if (strings.length == 1){
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))){
                        p.teleport(Bukkit.getPlayer(strings[0]).getLocation());
                        CoreSystem.messaging.sendMessage(null, "tpSuccess", p);
                    } else {
                        CoreSystem.messaging.sendMessage(null, "tpOffline", p);
                    }
                } else if (strings.length == 2){
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0])) && Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[1]))){
                        Bukkit.getPlayer(strings[0]).teleport(Bukkit.getPlayer(strings[1]).getLocation());
                        CoreSystem.messaging.sendMessage(null, "tpOtherMsg", Bukkit.getPlayer(strings[0]));
                        CoreSystem.messaging.sendMessage(null, "tpSuccessOther", p);
                    } else {
                        CoreSystem.messaging.sendMessage(null, "tpOffline", p);
                    }
                } else if (strings.length == 3){
                    p.teleport(new Location(p.getWorld(), Float.parseFloat(strings[0]), Float.parseFloat(strings[1]), Float.parseFloat(strings[2])));
                    CoreSystem.messaging.sendMessage(null, "tpSuccess", p);
                } else if (strings.length == 4){
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))){
                        Bukkit.getPlayer(strings[0]).teleport(new Location(p.getWorld(), Float.parseFloat(strings[1]), Float.parseFloat(strings[2]), Float.parseFloat(strings[3])));
                        CoreSystem.messaging.sendMessage(null, "tpOtherMsg", Bukkit.getPlayer(strings[0]));
                        CoreSystem.messaging.sendMessage(null, "tpSuccessOther", p);
                    } else {
                        CoreSystem.messaging.sendMessage(null, "tpOffline", p);
                    }
                }
            } else {
                CoreSystem.messaging.sendMessage(null, "noPermission", p);
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings)
    {
        if(strings[0].equalsIgnoreCase("")) {
            ArrayList<String> players = new ArrayList<>();
            for (Player x : Bukkit.getServer().getOnlinePlayers())
            {
                players.add(x.getName());
            }
            return players;
        }
        return null;
    }

}
