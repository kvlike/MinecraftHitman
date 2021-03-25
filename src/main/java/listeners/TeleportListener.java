package listeners;

import me.kvlike.dreamhitman.DreamHitman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;

public class TeleportListener implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e){

        Plugin plugin = DreamHitman.getPlugin(DreamHitman.class);

        if(e.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL){
            if(!plugin.getConfig().getBoolean("nether-enabled")){
                if(DreamHitman.game_started) {
                    e.setCancelled(true);
                }
            }
        }

    }

}
