package commands;

import events.GameStopEvent;
import me.kvlike.dreamhitman.DreamHitman;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class stopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            if (sender.hasPermission("hitman.stop")) {
                if (DreamHitman.game_started) {
                    GameStopEvent gameStopEvent = new GameStopEvent();
                    Bukkit.getServer().getPluginManager().callEvent(gameStopEvent);

                    DreamHitman.game_started = false;

                    Bukkit.broadcastMessage(ChatColor.YELLOW + "Game has been stopped.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Game is not even started!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "No permission");
            }

        }
        else{
            sender.sendMessage(ChatColor.RED + "This command can be only executed by player");
        }

        return true;
    }
}
