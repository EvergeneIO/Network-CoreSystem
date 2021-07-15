package de.polygondev.coresystem.commands;

import de.polygondev.coresystem.CoreSystem;
import de.polygondev.coresystem.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TpaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (strings.length == 1){
                if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(strings[0]))){
                    if (Data.requestMap.containsKey(Bukkit.getPlayer(strings[0])) || Data.requestMap.containsValue(Bukkit.getPlayer(strings[0]))){
                        CoreSystem.messaging.sendMessage(null, "tpaRequestAlready", Bukkit.getPlayer(strings[0]));
                    } else {
                        Data.requestMap.put(p, Bukkit.getPlayer(strings[0]));
                        CoreSystem.messaging.sendMessage(new String[]{p.getName()}, "tpaDestination", Bukkit.getPlayer(strings[0]));
                        Data.timerMap.put(Bukkit.getPlayer(strings[0]), Bukkit.getScheduler().scheduleSyncDelayedTask(CoreSystem.instance, () -> {
                            Data.requestMap.remove(p);
                            CoreSystem.messaging.sendMessage(null, "tpaRequestExpired", p);
                            CoreSystem.messaging.sendMessage(null, "tpaRequestExpired", Bukkit.getPlayer(strings[0]));
                        }, 600));
                    }
                } else {
                    CoreSystem.messaging.sendMessage(null, "playerOffline", p);
                }
            } else {
                CoreSystem.messaging.sendMessage(null, "tpaNoArgs", p);
            }
        }
        return false;
    }
}
