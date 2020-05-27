package de.polygondev;

import de.polygondev.coresystem.CoreSystem;
import org.bukkit.entity.Player;

import java.util.List;

public class Messages {

    public static void coreSystemInfo(Player player) {
        List<String> liste = (List<String>) CoreSystem.langConfig.getList("coreSystemInfo");

        for (String x : liste) {
            player.sendMessage(x);
        }
    }

}
