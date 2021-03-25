package commands;

import me.kvlike.dreamhitman.DreamHitman;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class reloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("hitman.reload")) {
            Plugin plugin = DreamHitman.getPlugin(DreamHitman.class);
            plugin.reloadConfig();

            sender.sendMessage(ChatColor.GREEN + "Config reloaded successfully");
        }
        else{
            sender.sendMessage(ChatColor.RED + "No permission");
        }

        return true;
    }
}
