package smp.jello.jelloblock;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class JelloBlock extends JavaPlugin {
    FileConfiguration config = getConfig();

    private static JelloBlock _instance;

    @Override
    public void onEnable() {
        _instance = this;

        List<String> exemptListDefault = new ArrayList<>();
        exemptListDefault.add("TheDevvy");
        config.addDefault("exempt", exemptListDefault);
        config.options().copyDefaults(true);
        saveConfig();

        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
    }

    public static JelloBlock getInstance() {
        return _instance;
    }
}
