package ru.whbex.lockdown;

import org.bukkit.ChatColor;

public class Utils {
    public static String parseBool(Boolean bool){
        if(bool) return ChatColor.GREEN + "Да";
        return ChatColor.RED + "Нет";

    }
}
