package smp.jello.jelloblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import smp.jello.jelloblock.JelloBlock;

import java.util.Arrays;
import java.util.List;

public class ExemptCommand implements CommandExecutor {
    JelloBlock plugin = JelloBlock.getInstance();
    FileConfiguration config = plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // I love this syntax, ty Intellij
        return switch (args[0]) {
            case "add" -> onAdd(sender, args);
            case "remove" -> onRemove(sender, args);
            case "list" -> onList(sender, args);
            default -> false;
        };
    }

    private boolean onAdd(CommandSender sender, String[] args) {
        List<String> exemptPlayers = config.getStringList("exempt");
        String[] playersToAdd = Arrays.copyOfRange(args, 1, args.length);
        exemptPlayers.addAll(Arrays.asList(playersToAdd));
        config.set("exempt", exemptPlayers);
        plugin.saveConfig();
        sender.sendMessage("Success, added (" + playersToAdd.length + ") player(s) to exempt list: " + String.join(", ", playersToAdd));
        return true;
    }

    private boolean onRemove(CommandSender sender, String[] args) {
        List<String> exemptPlayers = config.getStringList("exempt");
        String[] playersToAdd = Arrays.copyOfRange(args, 1, args.length);
        exemptPlayers.removeAll(Arrays.asList(playersToAdd));
        config.set("exempt", exemptPlayers);
        plugin.saveConfig();
        sender.sendMessage("Success, added (" + playersToAdd.length + ") player(s) to exempt list: " + String.join(", ", playersToAdd));
        return true;
    }

    private boolean onList(CommandSender sender, String[] args) {
        List<String> exemptPlayers = config.getStringList("exempt");
        sender.sendMessage("There are (" + exemptPlayers.size() + ") exempt players: " + String.join(", ", exemptPlayers));
        return true;
    }
}
