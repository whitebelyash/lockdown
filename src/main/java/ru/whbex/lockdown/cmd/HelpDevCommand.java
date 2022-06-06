package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;

import java.util.List;
import java.util.Set;

@CommandInfo(
        name = "help",
        internalname = "helpdev",
        description = "Вывести список команд",
        permission = "lockdown.helpdev",
        parent = "lddev")
public class HelpDevCommand implements ICommand {
    @Override
    public ExitStatus exec(CommandSender sender, List<String> args, Set<String> flags){
        // build help for lockdown
        CommandManager manager = Lockdown.getInstance().getCommandManager();
        String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        sender.sendMessage(pref + "Помощь (команды плагина)");

        return ExitStatus.SUCCESS;
    }
}
