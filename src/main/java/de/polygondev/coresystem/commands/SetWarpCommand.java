package de.polygondev.coresystem.commands;

import de.polygondev.coresystem.CoreSystem;
import de.polygondev.coresystem.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class SetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (p.hasPermission("coresystem.setwarp")){
                if (strings.length > 0){
                    if (!CoreSystem.warps.contains(strings[0])){
                        CoreSystem.warps.set(strings[0] + ".world", p.getLocation().getWorld().getName());
                        CoreSystem.warps.set(strings[0] + ".x", p.getLocation().getX());
                        CoreSystem.warps.set(strings[0] + ".y", p.getLocation().getY());
                        CoreSystem.warps.set(strings[0] + ".z", p.getLocation().getZ());
                        CoreSystem.warps.set(strings[0] + ".yaw", p.getLocation().getYaw());
                        CoreSystem.warps.set(strings[0] + ".pitch", p.getLocation().getPitch());
                        try {
                            CoreSystem.warps.save(new File(CoreSystem.instance.getDataFolder(), "warps.yml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        p.sendMessage(Data.prefix + CoreSystem.lang.getString("setWarpSuccess") + strings[0]);
                    } else {
                        p.sendMessage(Data.prefix + CoreSystem.lang.getString("setWarpAlreadyExists"));
                    }
                } else {
                    p.sendMessage(Data.prefix + CoreSystem.lang.getString("setWarpNoName"));
                }
            }
        }
        return false;
    }
}
