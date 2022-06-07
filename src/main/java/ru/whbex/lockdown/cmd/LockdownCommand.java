package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

@CommandInfo(
        name = "lockdown",
        internalname = "lockdown",
        description = "Lockdown",
        permission = "lockdown.use",
        defaultCmd = "help",
        hasChildren = true,
        minArgs = 1)
class LockdownCommand implements ICommand {
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args){
        sender.sendMessage(ChatColor.RED + "/lockdown возможно использовать только в паре с подкомандой");
        return ExitStatus.ERROR_OTHER;

    }
}
