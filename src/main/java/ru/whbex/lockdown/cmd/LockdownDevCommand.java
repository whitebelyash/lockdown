package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@CommandInfo(name = "lddev",
        internalname = "lddev",
        description = "Lockdown",
        permission = "lockdown.usedev",
        defaultCmd = "helpdev",
        minArgs = 1)
public class LockdownDevCommand implements ICommand {


    @Override
   public ExitStatus exec(CommandSender sender, List<String> args, Set<String> flags) {
        sender.sendMessage(ChatColor.RED + "/lddev возможно использовать только в паре с подкомандой");
        return ExitStatus.SUCCESS;
    }
}
