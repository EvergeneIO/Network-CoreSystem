package de.polygondev.coresystem.commands;

import de.polygondev.coresystem.CoreSystem;
import de.polygondev.coresystem.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // Check if the executor is a player, since you (obviously) cant get the location of the console
        if (commandSender instanceof Player){
            // Convert CommandSender to Player
            Player p = (Player) commandSender;
            // Check if the player has the permission coresystem.setspawn
            if (p.hasPermission("coresystem.setspawn")){
                // Get the coordinates (x, y, z)and the facing values (yaw and pitch)
                String worldName = p.getLocation().getWorld().getName();
                double x = p.getLocation().getX();
                double y = p.getLocation().getY();
                double z = p.getLocation().getZ();
                double pitch = p.getLocation().getPitch();
                double yaw = p.getLocation().getYaw();
                // Save them in the config file
                CoreSystem.config.set("spawn.location.world", worldName);
                CoreSystem.config.set("spawn.location.x", x);
                CoreSystem.config.set("spawn.location.y", y);
                CoreSystem.config.set("spawn.location.z", z);
                CoreSystem.config.set("spawn.location.yaw", yaw);
                CoreSystem.config.set("spawn.location.pitch", pitch);
                CoreSystem.instance.saveConfig();
                // Notify the player
                p.sendMessage(Data.prefix + "§aThe spawn location has been successfully set.");
            } else {
                p.sendMessage(Data.prefix + "§cYou don't have the required permission to execute this command!");
            }
        } else {
            commandSender.sendMessage(Data.prefix + "§cYou cant get the location of the server console. Its always in the terminal.");
        }
        return false;
    }
}
