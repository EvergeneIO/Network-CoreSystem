package de.polygondev.coresystem.utils;

import de.polygondev.coresystem.CoreSystem;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Messaging
{
    public void sendMessage(String configNodePath, Player player) {
        //Get Prefix beforehand
        String prefix = CoreSystem.lang.getString("prefix.pref");
        //Get String list from lang file
        ArrayList<String> messages = (ArrayList<String>) CoreSystem.lang.getStringList(configNodePath);
        //iterate through the list and send message to player
        for (String x : messages) {
            //checks if the showPrefix is enabled or disabled
            if (CoreSystem.lang.getBoolean("prefix.showPrefix")) {
                player.sendMessage(prefix + x);
            } else {
                player.sendMessage(x);
            }
        }
    }

}
