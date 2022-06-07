package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;

import java.util.List;
import java.util.Set;
@CommandInfo(name = "reload", internalname = "reload", description = "Перезагрузить конфигурацию", permission = "lockdown.dev.reload", parent = "lddev")
public class ReloadCommand implements ICommand {
    @Override
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args, Set<String> flags) {
        Lockdown.getInstance().onReload();
        Lockdown.getInstance().getLogger().info("Reloaded config, invoked by: " + sender.getName());
        sender.sendMessage(ChatColor.GREEN + "Конфигурация перезагружена");
        return ExitStatus.SUCCESS;
    }
}
