package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;

import java.util.List;
import java.util.Set;

public class HelpCommand extends AbstractCommand {
    HelpCommand(){
        super(
                "help",
                "lockdown.help",
                "Помощь по командам",
                "",
                "lockdown",
                0,
                false);
    }
    ExitStatus commandExec(CommandSender sender, List<String> args, Set<String> flags){
        CommandManager manager = Lockdown.getInstance().getCommandManager();
        // build help for lockdown
        String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        sender.sendMessage(pref + "Помощь");
        manager.getCommand(parent).children.forEach(cmd -> {
            sender.sendMessage(ChatColor.DARK_GRAY + " > " + ChatColor.GRAY + cmd.name + " - " + ChatColor.RESET + cmd.description);
        });

        return ExitStatus.SUCCESS;
    }
}
