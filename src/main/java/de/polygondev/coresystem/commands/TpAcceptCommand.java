package de.polygondev.coresystem.commands;

import de.polygondev.coresystem.CoreSystem;
import de.polygondev.coresystem.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TpAcceptCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (Data.requestMap.containsValue(p)){
                for (Player key : Data.requestMap.keySet()) {
                    if (Data.requestMap.get(key).equals(p)){
                        CoreSystem.messaging.sendMessage(null, "tpaRequestAccepted", key);
                        p.teleport(key.getLocation());
                        Data.requestMap.remove(key);
                        Bukkit.getScheduler().cancelTask(Data.timerMap.get(p));
                        Data.timerMap.remove(p);
                        break;
                    }
                }
            } else {
                CoreSystem.messaging.sendMessage(null, "tpaRequestNone", p);
            }
        }
        return false;
    }
}
