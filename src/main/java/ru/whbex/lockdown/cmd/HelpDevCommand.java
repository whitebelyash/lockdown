package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

@CommandInfo(
        name = "help",
        internalname = "helpdev",
        description = "Вывести список команд",
        permission = "lockdown.dev.help",
        parent = "lddev")
public class HelpDevCommand implements ICommand {
    @Override
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args){
        // Список команд
        final String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        final String line = ChatColor.DARK_GRAY + " > " + ChatColor.GRAY + "%s - " + ChatColor.RESET + "%s";
        sender.sendMessage(pref + "Помощь (команды плагина)");
        manager.getChildCommands(this.getClass().getAnnotation(CommandInfo.class).parent()).values().forEach(value -> {
            String description = value.getClass().getAnnotation(CommandInfo.class).description();
            String name = "/" + this.getClass().getAnnotation(CommandInfo.class).parent() + " " + value.getClass().getAnnotation(CommandInfo.class).name();
            sender.sendMessage(String.format(line, name, description));
        });
        return ExitStatus.SUCCESS;
    }
}
