package listeners;

import commands.survivalistCommand;
import events.GameStopEvent;
import me.kvlike.dreamhitman.DreamHitman;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){

        Player player = event.getEntity();
        if(DreamHitman.game_started && survivalistCommand.survivalists_list.contains(player)){
            GameStopEvent gameStopEvent = new GameStopEvent();
            Bukkit.getServer().getPluginManager().callEvent(gameStopEvent);
            Bukkit.broadcastMessage(ChatColor.RED + "Hitmen won the game!");
        }

    }

}
