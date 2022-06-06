package ru.whbex.lockdown.cmd;

import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Set;

public interface ICommand {
    ExitStatus exec(CommandSender sender, List<String> args, Set<String> flags);
}
