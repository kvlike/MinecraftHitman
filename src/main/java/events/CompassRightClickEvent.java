package events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CompassRightClickEvent extends Event {

    private Player clicker;
    private Player target;

    public CompassRightClickEvent(Player clicker, Player target){

        this.clicker = clicker;
        this.target = target;

    }

    public Player getClicker() {
        return clicker;
    }

    public Player getTarget() {
        return target;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
