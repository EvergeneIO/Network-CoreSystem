package de.polygondev.coresystem.commands;

import de.polygondev.coresystem.CoreSystem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

public class GamemodeCommand implements CommandExecutor, TabCompleter
{

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings)
    {
        if (!commandSender.hasPermission("coresystem.gamemode"))
        {
            CoreSystem.messaging.sendMessage(null, "noPermission", commandSender);
            return true;
        }

        Player sender = null;
        Player otherPlayer = null;

        if (commandSender instanceof Player)
        {
            sender = ((Player) commandSender).getPlayer();
        }
        else
        {
            CoreSystem.messaging.sendMessage(null, "terminalNoGamemode", commandSender);
            return true;
        }

        if (strings.length > 0)
        {
            Bukkit.getServer().getConsoleSender().sendMessage("changing is not null");
            if (strings.length > 1)
            {
                otherPlayer = Bukkit.getPlayer(strings[1]);
            }
            Bukkit.getServer().getConsoleSender().sendMessage("This is start of changing");

            Player changing = null;
            if (otherPlayer != null && otherPlayer != sender)
            {
                changing = otherPlayer;
            }
            else
            {
                changing = sender;
            }

            Bukkit.getServer().getConsoleSender().sendMessage("This is end of changing");

            if (changing != null)
            {
                Bukkit.getServer().getConsoleSender().sendMessage("strings length is more than 2 and start switch");
                switch (strings[0])
                {
                    case "0":
                    case "s":
                    case "survival":
                        changing.setGameMode(GameMode.SURVIVAL);
                        break;
                    case "1":
                    case "c":
                    case "creative":
                        changing.setGameMode(GameMode.CREATIVE);
                        break;
                    case "2":
                    case "a":
                    case "adventure":
                        changing.setGameMode(GameMode.ADVENTURE);
                        break;
                    case "3":
                    case "o":
                    case "spectator":
                        changing.setGameMode(GameMode.SPECTATOR);
                        break;
                    default:
                        CoreSystem.messaging.sendMessage(null, "gamemodeNoArgs", changing);
                        return true;
                }
                Bukkit.getServer().getConsoleSender().sendMessage("end switch");
                if (changing != commandSender)
                {
                    CoreSystem.messaging.sendMessage(new String[]{changing.getName()}, "changedGamemodeOther", commandSender);
                }
                CoreSystem.messaging.sendMessage(null, "changedGamemode", changing);
            }
        }
        return true;
    }

    private static final String[] ARG0 = { "0", "s", "survival", "1", "c", "creative", "2", "a", "adventure", "3", "o", "spectator" };

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings)
    {
        if(strings[0].equalsIgnoreCase("")) {
            return Arrays.asList(ARG0);
        }

        if (strings.length == 1 && !strings[0].equalsIgnoreCase(""))
        {
            //create new array
            final List<String> completions = new ArrayList<>();
            //copy matches of first argument from list (ex: if first arg is 'm' will return just 'minecraft')
            StringUtil.copyPartialMatches(strings[0], Arrays.asList(ARG0), completions);
            //sort the list
            Collections.sort(completions);
            return completions;
        }
        if (strings.length < 2)
        {
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
