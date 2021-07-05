package de.polygondev.coresystem.utils;

import de.polygondev.coresystem.CoreSystem;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Messaging
{
    public void sendMessage(String[] formatString, String configNodePath, CommandSender player)
    {
        //Get Prefix beforehand
        String prefix = CoreSystem.lang.getString("prefix.pref");
        //Get String list from lang file
        ArrayList<String> messages = (ArrayList<String>) CoreSystem.lang.getStringList(configNodePath);
        //iterate through the list and send message to player
        for (String x : messages)
        {
            //checks if the showPrefix is enabled or disabled
            Matcher match = Pattern.compile("\\?.").matcher(x);
            if (formatString != null)
            {
                while (match.find())
                {
                    String x1 = match.group();
                    int x2 = Integer.parseInt(x1.replace("?", ""));
                    x = x.replace(x1, formatString[x2]);
                }
            }

            if (CoreSystem.lang.getBoolean("prefix.showPrefix"))
            {
                player.sendMessage(prefix + x);
            }
            else
            {
                player.sendMessage(x);
            }
        }
    }

}
