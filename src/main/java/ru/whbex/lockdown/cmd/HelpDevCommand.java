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
    public ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args, Set<String> flags){
        // build help for lockdown
        final String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        final String line = ChatColor.DARK_GRAY + " > " + ChatColor.GRAY + "%s - " + ChatColor.RESET + "%s";
        sender.sendMessage(pref + "Помощь (команды плагина)");
        manager.getChildCommands(this.getClass().getAnnotation(CommandInfo.class).parent()).values().forEach(value -> {
            String description = value.getClass().getAnnotation(CommandInfo.class).description();
            String name = value.getClass().getAnnotation(CommandInfo.class).name();
            sender.sendMessage(String.format(line, name, description));
        });
        return ExitStatus.SUCCESS;
    }
}
