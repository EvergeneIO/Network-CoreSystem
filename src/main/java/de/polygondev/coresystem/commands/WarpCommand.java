package de.polygondev.coresystem.commands;

import de.polygondev.coresystem.CoreSystem;
import de.polygondev.coresystem.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Set;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (strings.length > 0){
                if (CoreSystem.warps.contains("" + strings[0])){
                    p.teleport(new Location(Bukkit.getWorld(CoreSystem.warps.getString("" + strings[0] + ".world")), CoreSystem.warps.getDouble("" + strings[0]+ ".x"), CoreSystem.warps.getDouble("" + strings[0]+ ".y"), CoreSystem.warps.getDouble("" + strings[0]+ ".z"), (float)CoreSystem.warps.getDouble("" + strings[0]+ ".yaw"), (float)CoreSystem.warps.getDouble("" + strings[0]+ ".pitch")));
                    p.sendMessage(Data.prefix + CoreSystem.lang.getString("warpSuccess") + strings[0]);
                } else {
                    p.sendMessage(Data.prefix + CoreSystem.lang.getString("warpNotExist"));
                }
            } else {
                String warpList = "";
                for (String warp : CoreSystem.warps.getKeys(false)){
                    if (warpList.equals("")){
                        warpList = warp;
                    } else {
                        warpList = warpList + ", " + warp;
                    }
                }
                p.sendMessage(Data.prefix + CoreSystem.lang.getString("warpListPrefix") + warpList);
            }
        } else {
            commandSender.sendMessage(Data.prefix + CoreSystem.lang.getString("terminalNoTeleport"));
        }
        return false;
    }
}
