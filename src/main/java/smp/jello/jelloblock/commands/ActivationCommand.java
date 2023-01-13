package smp.jello.jelloblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ActivationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
        if (!Objects.equals(arg0, "true") && !Objects.equals(arg0, "false"))
    }
}
