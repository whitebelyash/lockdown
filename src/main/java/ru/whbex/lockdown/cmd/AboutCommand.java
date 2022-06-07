package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;

import java.util.List;
@CommandInfo(
        name = "about",
        internalname = "about",
        permission = "lockdown.dev.about",
        parent = "lddev",
        description = "Информация об плагине")
public class AboutCommand implements ICommand {

    @Override
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args) {
        String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        String line = ChatColor.DARK_GRAY + " > " + ChatColor.RESET + "%s \n";
        String version = String.format("Версия: %s", Lockdown.getInstance().getDescription().getVersion());
        String devs = String.format("Dev: %s", Lockdown.getInstance().getDescription().getAuthors());
        String kickMessage = String.format("Kick message: %s", Lockdown.getInstance().getKickMessage());
        sender.sendMessage(pref + "Информация об плагине");
        String msg = String.format(line, version) +
                String.format(line, devs) +
                " --- \n" +
                String.format(line, kickMessage);
        sender.sendMessage(msg);
        return ExitStatus.SUCCESS;
    }
}
