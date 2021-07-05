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
            if (!p.hasPermission("coresystem.setwarp"))
            {
                //p.sendMessage(Data.prefix + CoreSystem.lang.getString("setWarpNoName"));
                CoreSystem.messaging.sendMessage(null, "noPermission", p);
                return true;
            }
            if (strings.length > 0){
                if (CoreSystem.warps.contains(strings[0]))
                {
                    //p.sendMessage(Data.prefix + CoreSystem.lang.getString("setWarpAlreadyExists"));
                    CoreSystem.messaging.sendMessage(null, "setWarpAlreadyExists", p);
                    return true;
                }

                //Create warp
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
                //Data.prefix + CoreSystem.lang.getString("setWarpSuccess") + strings[0]
                CoreSystem.messaging.sendMessage(new String[] {strings[0]},"setWarpSuccess", p);
                return true;
            }
            CoreSystem.messaging.sendMessage(null, "setWarpNoName", p);
        }

        return false;
    }
}
