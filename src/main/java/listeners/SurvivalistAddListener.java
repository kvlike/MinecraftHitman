package listeners;

import events.SurvivalistAddEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SurvivalistAddListener implements Listener {


    @EventHandler
    public void onSurvivalistAdd(SurvivalistAddEvent event){

        event.getPlayer().setGlowing(true);

    }

}
