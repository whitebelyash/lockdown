package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

@CommandInfo(
        name = "lddev",
        internalname = "lddev",
        description = "Lockdown",
        permission = "lockdown.dev",
        defaultCmd = "helpdev",
        hasChildren = true,
        minArgs = 1)
public class LockdownDevCommand implements ICommand {


    @Override
   public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args) {
        sender.sendMessage(ChatColor.RED + "/lddev возможно использовать только в паре с подкомандой");
        return ExitStatus.SUCCESS;
    }
}
