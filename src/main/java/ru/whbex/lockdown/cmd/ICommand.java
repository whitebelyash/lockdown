package ru.whbex.lockdown.cmd;

import org.bukkit.command.CommandSender;

import java.util.List;

interface ICommand {
    ExitStatus exec(CommandManager manager, CommandSender sender, List<String> args);
}
