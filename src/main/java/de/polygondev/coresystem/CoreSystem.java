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
        // Set the default config values
        config.addDefault("messages.prefix", "§7[§9Evergene§7]§r ");
        config.addDefault("spawn.location.x", 0.0);
        config.addDefault("spawn.location.y", 0.0);
        config.addDefault("spawn.location.z", 0.0);
        config.addDefault("spawn.location.yaw", 0.0);
        config.addDefault("spawn.location.pitch", 0.0);
        config.addDefault("database.mysql.host", "localhost:3001");
        config.addDefault("database.mysql.database", "coreplugin");
        config.addDefault("database.mysql.username", "root");
        config.addDefault("database.mysql.password", "password");
        // Copy these defaults into the file if the config file doesnt exist
        config.options().copyDefaults(true);
        // Save the config
        saveConfig();
    }

    @Override
    public void onDisable() {

    }
}
