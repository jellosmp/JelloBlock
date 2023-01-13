package smp.jello.jelloblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import smp.jello.jelloblock.JelloBlock;

import java.util.Objects;

public class ActivationCommand implements CommandExecutor {
    JelloBlock plugin = JelloBlock.getInstance();
    FileConfiguration config = plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String arg0 = args[0];
        if (!Objects.equals(arg0, "true") && !Objects.equals(arg0, "false"))
            return false;

        config.set("active", arg0.equals("true"));
        plugin.saveConfig();

        return true;
    }
}
