package smp.jello.jelloblock.commands.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ActivationTabCompleter implements TabCompleter {

    private final List<String> TRUE_FALSE;

    public ActivationTabCompleter() {
        TRUE_FALSE = new ArrayList<>();
        TRUE_FALSE.add("true");
        TRUE_FALSE.add("false");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) return null;
        return TRUE_FALSE;
    }
}
