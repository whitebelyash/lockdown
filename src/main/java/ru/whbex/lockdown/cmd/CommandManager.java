package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import ru.whbex.lockdown.Lockdown;

import java.util.*;

public class CommandManager implements CommandExecutor {
    private final Set<ICommand> commands = new HashSet<>();
    private final Map<String, ICommand> registeredCommands = new HashMap<>();
    // пришлось тут костылить, чтобы конфликтов не было
    // мапа вида ICommand: help: ICommand(help), cmd1, cmd2, etc.. (используется name для сравнения с args[0]
    // если использовать везде только name - будут конфликты между одинаковыми подкомандами разных команд
    private final Map<ICommand, Map<String, ICommand>> parentsMap = new HashMap<>();
    // я хз тут мб можно и по-другому реализовать, но пока что так
    public CommandManager(Lockdown instance){
        // команды добавлять сюда
        commands.add(new LockdownCommand());
        commands.add(new LockdownDevCommand());
        commands.add(new HelpCommand());
        commands.add(new HelpDevCommand());
        commands.add(new StatusCommand());
        commands.add(new ToggleCommand());
        commands.add(new AboutCommand());
        // регистрация команд
        commands.forEach(cmd -> {
            String cmdName = cmd.getClass().getAnnotation(CommandInfo.class).internalname();
            String cmdParent = cmd.getClass().getAnnotation(CommandInfo.class).parent();
            registeredCommands.put(cmdName, cmd);
            if(cmdParent.isEmpty()){
                PluginCommand command = instance.getCommand(cmdName);
                if(command == null){
                    instance.getLogger().severe(String.format("Couldn't register command %s. Is it exists?", cmdName));
                    return;
                }
                command.setExecutor(this);
            }
        });
        for(ICommand value : registeredCommands.values()){
            CommandInfo valueInfo = value.getClass().getAnnotation(CommandInfo.class);
            if(valueInfo.parent().isEmpty()) continue;
            ICommand parent = registeredCommands.get(valueInfo.parent());
            if(parent == null) continue;
            addSetIntoMap(parent, valueInfo.name(), value);
        }
        instance.getLogger().info("Command registration finished");
    }

    @SuppressWarnings("NullableProblems")
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        // Stage pre1: preparing
        List<String> argsl = new ArrayList<>(Arrays.asList(args));
        ICommand toExec = getCommand(cmd.getName());
        CommandInfo toExecInfo = toExec.getClass().getAnnotation(CommandInfo.class);
        final String commandUsage = ChatColor.RED + "Использование: /" + toExecInfo.name() + " " + toExecInfo.usage();
        final String onlyPlayer = ChatColor.RED + "Данную команду можно выполнить только будучи игроком";
        final String onlyConsole = ChatColor.RED + "Данную команду можно выполнить только из консоли";
        // Checks
        if(args.length < toExecInfo.minArgs()){
            sender.sendMessage(commandUsage);
            return true;
        }
        if(!(sender instanceof Player) && toExecInfo.requirePlayer()){
            sender.sendMessage(onlyPlayer);
            return true;
        }
        if(sender instanceof Player && toExecInfo.onlyConsole()){
            sender.sendMessage(onlyConsole);
            return true;
        }
        // Stage 1: execute simple command
        if(!toExecInfo.hasChildren()) return executeCmd(toExec, sender, argsl);

        // Stage 2: electric boogaloo (execute subcommand)
        String subCmd = argsl.get(0);
        argsl.remove(0);
        if(parentsMap.get(toExec).containsKey(subCmd)){
            // Checks 2
            CommandInfo subCmdInfo = parentsMap.get(toExec).get(subCmd).getClass().getAnnotation(CommandInfo.class);
            if(!(sender instanceof Player) && subCmdInfo.requirePlayer()){
                sender.sendMessage(onlyPlayer);
                return true;
            }
            if(sender instanceof Player && subCmdInfo.onlyConsole()){
                sender.sendMessage(onlyConsole);
                return true;
            }
            return executeCmd(parentsMap.get(toExec).get(subCmd), sender, argsl);
        }
        if(toExecInfo.allowExecuteSingle()) return executeCmd(toExec, sender, argsl);
        if(toExecInfo.defaultCmd().isEmpty()){
            sender.sendMessage(commandUsage);
            return true;
        }
        return executeCmd(getCommand(toExecInfo.defaultCmd()), sender, argsl);
    }
    private boolean executeCmd(ICommand cmd, CommandSender sender, List<String> args){
        switch(cmd.exec(this, sender, args, null)){
            case ERROR_USAGE:
                sender.sendMessage(ChatColor.RED + "Неверное использование команды");
                break;
            case ERROR_OTHER:
                sender.sendMessage(ChatColor.RED + "Произошла ошибка во время выполнения команды");
                break;
            case ERROR_PERM:
                sender.sendMessage(ChatColor.RED + "Недостаточно прав для выполнения этого действия");
                break;
            case SUCCESS:
                break;
        }
        return true;
    }
    public Set<ICommand> getCommandSet(){
        return commands;
    }
    public ICommand getCommand(String name){
        return registeredCommands.get(name);
    }
    public Map<String, ICommand> getChildCommands(ICommand parent){
        return parentsMap.get(parent);
    }
    public Map<String, ICommand> getChildCommands(String parent){
        return parentsMap.get(getCommand(parent));
    }
    private void addSetIntoMap(ICommand key, String toAddKey, ICommand toAddValue){
        Map<String, ICommand> map = new HashMap<>();
        if(parentsMap.get(key) != null) map = parentsMap.get(key);
        map.put(toAddKey, toAddValue);
        parentsMap.put(key, map);
    }
}