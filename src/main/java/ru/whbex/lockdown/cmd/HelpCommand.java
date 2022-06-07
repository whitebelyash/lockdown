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
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args, Set<String> flags){
        // build help for lockdown
        final String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        final String line = ChatColor.DARK_GRAY + " > " + ChatColor.GRAY + "%s - " + ChatColor.RESET + "%s";
        sender.sendMessage(pref + "Помощь");
        manager.getChildCommands(this.getClass().getAnnotation(CommandInfo.class).parent()).values().forEach(value -> {
            String description = value.getClass().getAnnotation(CommandInfo.class).description();
            String name = "/" + this.getClass().getAnnotation(CommandInfo.class).parent() + " " + value.getClass().getAnnotation(CommandInfo.class).name();
            sender.sendMessage(String.format(line, name, description));
        });
        return ExitStatus.SUCCESS;
    }
}
