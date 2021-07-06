package de.polygondev.coresystem;

import de.polygondev.coresystem.commands.*;
import de.polygondev.coresystem.utils.Messaging;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CoreSystem extends JavaPlugin {

    public static FileConfiguration config;
    public static FileConfiguration lang;
    public static FileConfiguration warps;
    public static CoreSystem instance;
    public static Messaging messaging;

    @Override
    public void onEnable() {
        // Initialize the config variable
        messaging = new Messaging();
        // Save default config and language file if they dont exist yet
        if (!new File(this.getDataFolder(), "lang.yml").exists())
        {
            this.saveResource("lang.yml", false);
        }
        this.saveDefaultConfig();
        config = this.getConfig();
        lang = YamlConfiguration.loadConfiguration(new File(getDataFolder(),"lang.yml"));
        warps = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "warps.yml"));
        instance = this;
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("setwarp").setExecutor(new SetWarpCommand());

        GamemodeCommand cmd_gm = new GamemodeCommand();
        getCommand("gm").setExecutor(cmd_gm);
        getCommand("gm").setTabCompleter(cmd_gm);

    }

    @Override
    public void onDisable() {
        //Alles entladen damit man das pl reloaden kann (ist immer ein tolles feature)
        messaging = null;
        config = null;
    }
}
