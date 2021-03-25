package listeners;

import commands.hitmanCommand;
import events.HitmanAddEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class onRespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        if(hitmanCommand.hitmen_list.contains(event.getPlayer())){
            HitmanAddEvent hitmanAddEvent = new HitmanAddEvent(event.getPlayer());
            Bukkit.getServer().getPluginManager().callEvent(hitmanAddEvent);
        }
    }

}
