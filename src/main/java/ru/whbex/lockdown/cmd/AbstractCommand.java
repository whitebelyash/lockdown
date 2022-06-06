package ru.whbex.lockdown.cmd;

import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract class AbstractCommand {
    protected final String name;
    protected final String permission;
    protected final String description;
    protected final String usage;
    protected final int minArgs;
    protected final boolean requirePlayer;
    protected final String parent;
    protected final String defaultCmd;
    protected final boolean hasChildren;
    protected final List<AbstractCommand> children;
    protected final boolean subcommand;
    AbstractCommand(String name, String permission, String description, String usage, String parent, int minArgs, boolean requirePlayer){
        this.description = description;
        this.permission = permission;
        this.usage = usage;
        this.name = name;
        this.requirePlayer = requirePlayer;
        this.parent = parent;
        this.minArgs = minArgs;
        if(parent != null) minArgs++;
        this.defaultCmd = null;
        this.hasChildren = false;
        this.children = null;
        this.subcommand = true;
    }
    AbstractCommand(String name, String permission, String description, String usage, int minArgs,
                    String defaultCmd, boolean requirePlayer, boolean hasChildren, List<AbstractCommand> children){
        this.description = description;
        this.permission = permission;
        this.usage = usage;
        this.name = name;
        this.requirePlayer = requirePlayer;
        this.minArgs = minArgs;
        this.parent = null;
        this.defaultCmd = defaultCmd;
        this.hasChildren = hasChildren;
        this.children = children;
        this.subcommand = false;
    }
    abstract ExitStatus commandExec(CommandSender sender, List<String> args, Set<String> flags);

}
