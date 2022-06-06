package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;

import java.util.List;
import java.util.Set;

public class HelpDevCommand extends AbstractCommand {
    HelpDevCommand(){
        super(
                "help",
                "lockdown.dev.help",
                "Помощь по командам плагина",
                "",
                "lddev",
                0,
                false
        );
    }
    ExitStatus commandExec(CommandSender sender, List<String> args, Set<String> flags){
        // build help for lockdown
        CommandManager manager = Lockdown.getInstance().getCommandManager();
        String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        sender.sendMessage(pref + "Помощь (команды плагина)");
        manager.getCommand(parent).children.forEach(cmd -> {
            sender.sendMessage(ChatColor.DARK_GRAY + " > " + ChatColor.GRAY + cmd.name + " - " + ChatColor.RESET + cmd.description);
        });

        return ExitStatus.SUCCESS;
    }
}
