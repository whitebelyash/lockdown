package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;
import ru.whbex.lockdown.Utils;

import java.util.List;

@CommandInfo(
        name = "status",
        internalname = "status",
        description = "Просмотр статуса Lockdown",
        permission = "lockdown.use.status",
        parent = "lockdown")
class StatusCommand implements ICommand {

    @Override
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args) {
        final String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        sender.sendMessage(pref + "Сервер закрыт: " + Utils.parseBool(Lockdown.getInstance().isActive()));
        return ExitStatus.SUCCESS;
    }
}
