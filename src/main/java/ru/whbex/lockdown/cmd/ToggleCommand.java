package ru.whbex.lockdown.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.whbex.lockdown.Lockdown;

import java.util.List;
import java.util.Set;

@CommandInfo(name = "toggle",
        internalname = "toggle",
        description = "Lockdown toggle",
        permission = "lockdown.toggle",
        parent = "lockdown")
public class ToggleCommand implements ICommand{

    @Override
    public ExitStatus exec(CommandSender sender, List<String> args, Set<String> flags) {
        Lockdown instance = Lockdown.getInstance();
        boolean toggle = true;
        String pref = ChatColor.GOLD + "Lockdown " + ChatColor.DARK_GRAY + "> " + ChatColor.RESET;
        String open =  pref + "Сервер успешно " + ChatColor.GREEN + "открыт";
        String close = pref + "Сервер успешно " + ChatColor.RED + "закрыт";
        String aopen = ChatColor.RED + "Ничего не изменилось. Сервер уже открыт";
        String aclose = ChatColor.RED + "Ничего не изменилось. Сервер уже закрыт";
        if(!(args.size() > 0)) {
            toggle = !instance.isActive();
            if(toggle) sender.sendMessage(close);
            if(!toggle) sender.sendMessage(open);
            instance.setActive(toggle);
            return ExitStatus.SUCCESS;
        }
        if(args.contains("on")) {
            if(instance.isActive()){
                sender.sendMessage(aclose);
                return ExitStatus.SUCCESS;
            }
            sender.sendMessage(open);
        }
        if(args.contains("off")){
            if(!instance.isActive()){
                sender.sendMessage(aopen);
                return ExitStatus.SUCCESS;
            }
            sender.sendMessage(close);
            toggle = false;

        }
        instance.setActive(toggle);
        return ExitStatus.SUCCESS;
    }
}
