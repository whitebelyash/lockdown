package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;
import ru.whbex.lockdown.Utils;

import java.util.List;
import java.util.Set;

@CommandInfo(name = "status",
        internalname = "status",
        description = "Lockdown status",
        permission = "lockdown.status",
        parent = "lockdown")
public class StatusCommand implements ICommand {

    @Override
    public ExitStatus exec(CommandSender sender, List<String> args, Set<String> flags) {
        String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        sender.sendMessage(pref + "Сервер закрыт: " + Utils.parseBool(Lockdown.getInstance().isActive()));
        return ExitStatus.SUCCESS;
    }
}
