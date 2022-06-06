package ru.whbex.lockdown;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void on(PlayerLoginEvent event){
        if(!Lockdown.getInstance().isActive()) return;
        if(event.getPlayer().hasPermission("lockdown.bypass")) return;
        event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "Сервер закрыт.");

    }

}
