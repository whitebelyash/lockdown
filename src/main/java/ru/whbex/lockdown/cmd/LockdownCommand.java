package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class LockdownCommand extends AbstractCommand {
    LockdownCommand(){
        super(
                "lockdown",
                "lockdown.use",
                "Основная команда Lockdown",
                "<подкоманда> [аргументы..] | Для просмотра подкоманд введите /lockdown help",
                1,
                "help",
                false,
                true,
                Arrays.asList(new ToggleCommand(), new StatusCommand(), new HelpCommand()));
    }
    ExitStatus commandExec(CommandSender sender, List<String> args, Set<String> flags){
        sender.sendMessage(ChatColor.RED + "/lockdown возможно использовать только в паре с подкомандой");
        return ExitStatus.ERROR_OTHER;

    }
}
