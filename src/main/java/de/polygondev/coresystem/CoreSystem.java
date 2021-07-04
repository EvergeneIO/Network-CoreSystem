package de.polygondev.coresystem;

import de.polygondev.coresystem.commands.SetSpawnCommand;
import de.polygondev.coresystem.commands.SpawnCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoreSystem extends JavaPlugin {

    public static FileConfiguration config;
    // Since I don't really know how to make a config file anymore, I just used the example and then made this instance variable so I can access saveConfig() from different classes
    public static CoreSystem instance;

    @Override
    public void onEnable() {
        // Initialize the config variable
        config = this.getConfig();
        instance = this;
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        // Save default config if the config doesnt exist yet
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }
}
