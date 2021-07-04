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

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            p.teleport(new Location(Bukkit.getWorld(CoreSystem.config.getString("spawn.location.world")), CoreSystem.config.getDouble("spawn.location.x"), CoreSystem.config.getDouble("spawn.location.y"), CoreSystem.config.getDouble("spawn.location.z"), (float)CoreSystem.config.getDouble("spawn.location.yaw"), (float)CoreSystem.config.getDouble("spawn.location.pitch")));
            p.sendMessage(Data.prefix + CoreSystem.lang.getString("spawnTeleport"));
        } else {
            commandSender.sendMessage(Data.prefix + CoreSystem.lang.getString("terminalNoTeleport"));
        }
        return false;
    }
}
