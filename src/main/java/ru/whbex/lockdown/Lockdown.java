package ru.whbex.lockdown;

import org.bukkit.plugin.java.JavaPlugin;
import ru.whbex.lockdown.cmd.CommandManager;

public final class Lockdown extends JavaPlugin {
    private static Lockdown instance;
    private static CommandManager cmdmgr;
    private boolean active = false;

    @Override
    public void onEnable() {
        instance = this;
        cmdmgr = new CommandManager(this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        instance = this;



    }

    @Override
    public void onDisable() {
        instance = null;
    }
    public CommandManager getCommandManager(){return cmdmgr;}
    public static Lockdown getInstance(){ return instance; }
    public void setActive(boolean set) { active = set; }
    public boolean isActive(){ return active; }
}
