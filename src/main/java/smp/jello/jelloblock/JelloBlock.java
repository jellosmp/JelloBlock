package smp.jello.jelloblock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import smp.jello.jelloblock.commands.ActivationCommand;
import smp.jello.jelloblock.commands.ExemptCommand;
import smp.jello.jelloblock.commands.tabcompleters.ActivationTabCompleter;
import smp.jello.jelloblock.commands.tabcompleters.ExemptTabCompleter;

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
        List<String> bookPagesDefault = new ArrayList<>();
        bookPagesDefault.add(
            ChatColor.DARK_GREEN.toString() +
            ChatColor.BOLD +
            "Welcome!\n" +
            ChatColor.RESET +
            "This server is not public yet! It will be released early next month." +
            " The server will be" +
            ChatColor.BOLD +
            " Whitelist Only!" +
            ChatColor.RESET +
            " See the" +
            ChatColor.BLUE +
            ChatColor.BOLD +
            " Discord" +
            ChatColor.RESET +
            " to join.\n\n" +
            "https://dsc.gg/jelsmp"
        );
        config.addDefault("bookpages", bookPagesDefault);
        config.addDefault("active", true);
        config.options().copyDefaults(true);
        saveConfig();

        Bukkit.getPluginManager().registerEvents(new Listeners(), this);

        getCommand("activation").setExecutor(new ActivationCommand());
        getCommand("activation").setTabCompleter(new ActivationTabCompleter());

        getCommand("exempt").setExecutor(new ExemptCommand());
        getCommand("exempt").setTabCompleter(new ExemptTabCompleter());
    }

    public static JelloBlock getInstance() {
        return _instance;
    }
}
