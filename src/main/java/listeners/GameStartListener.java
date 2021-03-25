package listeners;

import events.GameStartEvent;
import me.kvlike.dreamhitman.DreamHitman;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import timer.Timer;

public class GameStartListener implements Listener {

    @EventHandler
    public void onGameStart(GameStartEvent event){

        World overworld = Bukkit.getServer().getWorlds().get(0);
        World nether = Bukkit.getServer().getWorlds().get(1);

        overworld.getWorldBorder().setCenter(0.0, 0.0);
        overworld.getWorldBorder().setSize(3000);

        nether.getWorldBorder().setCenter(0.0, 0.0);
        nether.getWorldBorder().setSize(375);

        DreamHitman.timer.startTimer();

    }

}
