package commands;

import events.GameStartEvent;
import me.kvlike.dreamhitman.DreamHitman;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class startCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if (sender.hasPermission("hitman.start")) {
                if(!hitmanCommand.hitmen_list.isEmpty() && !survivalistCommand.survivalists_list.isEmpty()) {

                    if (DreamHitman.game_started == false) {
                        Bukkit.broadcastMessage(ChatColor.RED + "Game started!");

                        GameStartEvent gameStartEvent = new GameStartEvent();
                        Bukkit.getServer().getPluginManager().callEvent(gameStartEvent);

                        DreamHitman.game_started = true;
                    } else {
                        sender.sendMessage(ChatColor.RED + "Game is already started!");
                    }
                }
                else{
                    sender.sendMessage(ChatColor.RED + "Not enough survivalists/hitmen.");
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
