package de.polygondev.coresystem;

import de.polygondev.coresystem.commands.SetSpawnCommand;
import de.polygondev.coresystem.commands.SpawnCommand;
import de.polygondev.coresystem.utils.Messaging;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CoreSystem extends JavaPlugin {

    public static FileConfiguration config;
    public static FileConfiguration lang;
    // Since I don't really know how to make a config file anymore, I just used the example and then made this instance variable so I can access saveConfig() from different classes
    public static CoreSystem instance;
    public static Messaging messaging;

    @Override
    public void onEnable() {
        // Initialize the config variable
        messaging = new Messaging();
        config = this.getConfig();
        lang = YamlConfiguration.loadConfiguration(new File(getDataFolder(),"lang.yml"));
        instance = this;
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        // Save default config if the config doesnt exist yet
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }
}
