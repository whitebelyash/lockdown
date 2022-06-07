package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;

import java.util.List;
import java.util.Set;
@CommandInfo(name = "about", internalname = "about", permission = "lockdown.dev.about", parent = "lddev", description = "Информация об плагине")
public class AboutCommand implements ICommand {

    @Override
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args, Set<String> flags) {
        String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        String line = ChatColor.DARK_GRAY + " > " + ChatColor.RESET + "%s";
        sender.sendMessage(pref + "Информация об плагине");
        sender.sendMessage(String.format(line, String.format("Версия: %s", Lockdown.getInstance().getDescription().getVersion())));
        sender.sendMessage(String.format(line, String.format("Dev: %s", Lockdown.getInstance().getDescription().getAuthors())));
        return ExitStatus.SUCCESS;
    }
}
