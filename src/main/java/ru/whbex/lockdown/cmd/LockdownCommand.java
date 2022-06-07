package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@CommandInfo(name = "lockdown",
        internalname = "lockdown",
        description = "Lockdown",
        permission = "lockdown.use",
        defaultCmd = "help",
        hasChildren = true,
        minArgs = 1)
public class LockdownCommand implements ICommand {
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args, Set<String> flags){
        sender.sendMessage(ChatColor.RED + "/lockdown возможно использовать только в паре с подкомандой");
        return ExitStatus.ERROR_OTHER;

    }
}
