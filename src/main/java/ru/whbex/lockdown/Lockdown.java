package ru.whbex.lockdown;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ru.whbex.lockdown.cmd.CommandManager;

public final class Lockdown extends JavaPlugin {
    private static Lockdown instance;
    private boolean active = false;
    private String kickMessage = "Denied";

    @Override
    public void onEnable() {
        new CommandManager(this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        this.saveDefaultConfig();
        this.setupConfig();
        instance = this;
        getLogger().info(String.format("Enabled %s %s", getDescription().getName(), getDescription().getVersion()));



    }

    @Override
    public void onDisable() {
        this.saveFieldsToConfig();
        this.saveConfig();
        instance = null;
        getLogger().info("Goodbye");
    }
    public void onReload(){
        this.saveFieldsToConfig();
        this.reloadConfig();
        this.setupConfig();
        instance = this;
    }

    public static Lockdown getInstance(){ return instance; }
    public void setActive(boolean set) { active = set; }
    public boolean isActive(){ return active; }
    public String getKickMessage(){ return kickMessage; }
    private void setupConfig(){
        active = this.getConfig().getBoolean("lockdown-active");
        String kickMessage = this.getConfig().getString("kick-message");
        if(kickMessage == null) kickMessage = "&cServer is closed";
        kickMessage = ChatColor.translateAlternateColorCodes('&', kickMessage);
        this.kickMessage = kickMessage;
    }
    private void saveFieldsToConfig(){
        this.getConfig().set("lockdown-active", active);
    }
}
