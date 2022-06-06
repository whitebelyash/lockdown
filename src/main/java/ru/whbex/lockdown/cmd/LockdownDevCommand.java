package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class LockdownDevCommand extends AbstractCommand {
    LockdownDevCommand(){
        super(
                "lddev",
                "lockdown.dev",
                "Команды плагина",
                "<подкоманда> [args]",
                1,
                "helpdev",
                false,
                true,
                Arrays.asList(new AbstractCommand[]{new HelpDevCommand()})
        );
    }

    @Override
    ExitStatus commandExec(CommandSender sender, List<String> args, Set<String> flags) {
        sender.sendMessage(ChatColor.RED + "/lddev возможно использовать только в паре с подкомандой");
        return ExitStatus.SUCCESS;
    }
}
