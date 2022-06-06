package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;

import java.util.List;
import java.util.Set;

@CommandInfo(
        name = "help",
        internalname = "help",
        description = "Вывести список команд",
        permission = "lockdown.help",
        parent = "lockdown")
public class HelpCommand implements ICommand {
    @Override
    public ExitStatus exec(CommandSender sender, List<String> args, Set<String> flags){
        CommandManager manager = Lockdown.getInstance().getCommandManager();
        // build help for lockdown
        String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        sender.sendMessage(pref + "Помощь");
        return ExitStatus.SUCCESS;
    }
}
