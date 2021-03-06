package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;

import java.util.List;
@CommandInfo(
        name = "reload",
        internalname = "reload",
        description = "Перезагрузить конфигурацию",
        permission = "lockdown.dev.reload",
        parent = "lddev")
class ReloadCommand implements ICommand {
    @Override
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args) {
        Lockdown.getInstance().onReload();
        Lockdown.getInstance().getLogger().info("Reloaded config, invoked by: " + sender.getName());
        sender.sendMessage(ChatColor.GREEN + "Конфигурация перезагружена");
        return ExitStatus.SUCCESS;
    }
}
