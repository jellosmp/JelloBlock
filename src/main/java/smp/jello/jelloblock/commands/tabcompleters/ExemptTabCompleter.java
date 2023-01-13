package smp.jello.jelloblock.commands.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import smp.jello.jelloblock.JelloBlock;

import java.util.ArrayList;
import java.util.List;

public class ExemptTabCompleter implements TabCompleter {
    JelloBlock plugin = JelloBlock.getInstance();
    FileConfiguration config = plugin.getConfig();

    private final List<String> SUBCOMMANDS;

    public ExemptTabCompleter() {
        SUBCOMMANDS = new ArrayList<>();
        SUBCOMMANDS.add("add");
        SUBCOMMANDS.add("remove");
        SUBCOMMANDS.add("list");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return SUBCOMMANDS;
        }

        return switch (args[0]) {
            case "add" -> Bukkit
                    .getServer()
                    .getOnlinePlayers()
                    .stream()
                    .map(Player::getName)
                    .toList();
            case "remove" -> config.getStringList("exempt");
            default -> null;
        };
    }
}
