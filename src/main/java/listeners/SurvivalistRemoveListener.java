package listeners;

import events.SurvivalistRemoveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SurvivalistRemoveListener implements Listener {


    @EventHandler
    public void onSurvivalistRemove(SurvivalistRemoveEvent event){

        event.getPlayer().setGlowing(false);

    }

}
