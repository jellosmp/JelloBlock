package smp.jello.jelloblock.commands.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExemptTabCompleter implements TabCompleter {

    private final List<String> SUBCOMMANDS;

    public ExemptTabCompleter() {
        SUBCOMMANDS = new ArrayList<>();
        SUBCOMMANDS.add("add");
        SUBCOMMANDS.add("remove");
        SUBCOMMANDS.add("list");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.getLogger().warning("hi");
        if (args.length == 0) {
            return SUBCOMMANDS;
        }

        if (Objects.equals(args[0], "list")) return null;

        return Bukkit
                .getServer()
                .getOnlinePlayers()
                .stream()
                .map(Player::getName)
                .toList();
    }
}
