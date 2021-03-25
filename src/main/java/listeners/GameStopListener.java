package listeners;

import commands.hitmanCommand;
import commands.survivalistCommand;
import events.GameStopEvent;
import events.HitmanRemoveEvent;
import events.SurvivalistRemoveEvent;
import me.kvlike.dreamhitman.DreamHitman;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStopListener implements Listener {

    @EventHandler
    public void onGameStop(GameStopEvent event){

        World overworld = Bukkit.getServer().getWorlds().get(0);
        World nether = Bukkit.getServer().getWorlds().get(1);

        overworld.getWorldBorder().reset();

        nether.getWorldBorder().reset();

        DreamHitman.timer.stopTimer();

        DreamHitman.game_started = false;

        for(int i = 0; i < hitmanCommand.hitmen_list.size(); i++){
            HitmanRemoveEvent hitmanRemoveEvent = new HitmanRemoveEvent(hitmanCommand.hitmen_list.get(i));
            Bukkit.getServer().getPluginManager().callEvent(hitmanRemoveEvent);
        }

        for(int i = 0; i < survivalistCommand.survivalists_list.size(); i++){
            SurvivalistRemoveEvent survivalistRemoveEvent = new SurvivalistRemoveEvent(survivalistCommand.survivalists_list.get(i));
            Bukkit.getServer().getPluginManager().callEvent(survivalistRemoveEvent);
        }

        hitmanCommand.hitmen_list.clear();
        survivalistCommand.survivalists_list.clear();

    }

}
