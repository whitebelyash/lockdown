package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.whbex.lockdown.Lockdown;

import java.util.*;

public class CommandManager implements CommandExecutor {
    private final Set<AbstractCommand> commands = new HashSet<>();
    private final Map<String, AbstractCommand> registeredCommands = new HashMap<>();
    // я хз тут мб можно и по-другому реализовать, но пока что так
    public CommandManager(Lockdown instance){
        // команды добавлять сюда
        commands.add(new LockdownCommand());
        commands.add(new LockdownDevCommand());
        // регистрация команд
        commands.forEach(cmd -> {
            registeredCommands.put(cmd.name, cmd);
            instance.getCommand(cmd.name).setExecutor(this);

        });
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        AbstractCommand toExecute = registeredCommands.get(cmd.getName());
        List<String > argsl = new ArrayList<>(Arrays.asList(args));
        if(args.length < toExecute.minArgs){
            sender.sendMessage(ChatColor.RED + "Использование: /" + toExecute.name + " " + toExecute.usage);
            return true;
        }
        if((!sender.hasPermission(toExecute.permission)) && sender instanceof Player) {
            sender.sendMessage("Недостаточно прав");
            return true;
        }

        if(toExecute.hasChildren){
            AbstractCommand toExecuteSub = null;
            for(AbstractCommand record : toExecute.children){
                if(record.name.equals(args[0])){
                    toExecuteSub = record;
                    break;
                }
            }
            if(toExecuteSub == null){
                sender.sendMessage(ChatColor.RED + "Использование: /" + toExecute.name + " " + toExecute.usage);
                return true;
            }
            argsl.remove(0);
            if(argsl.size() < toExecuteSub.minArgs){
                sender.sendMessage(ChatColor.RED + "Использование: /" + toExecute.name + " " + toExecuteSub.name + " " + toExecuteSub.usage);
            }
            return this.executeCmd(toExecuteSub, sender, argsl, null);
        }
        return this.executeCmd(toExecute, sender, argsl, null);

    }
    private boolean executeCmd(AbstractCommand cmd, CommandSender sender, List<String> args, Set<String> flags){
        switch(cmd.commandExec(sender, args, flags)){
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
    public Set<AbstractCommand> getCommandSet(){
        return commands;
    }
    public AbstractCommand getCommand(String name){
        return registeredCommands.get(name);
    }
}