package events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HitmanAddEvent extends Event {

    private Player player;

    public HitmanAddEvent(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
