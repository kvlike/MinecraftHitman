package commands;

import me.kvlike.dreamhitman.DreamHitman;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class netherCommand implements CommandExecutor {

    String usage = ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/nether <on/off>";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            if(sender.hasPermission("hitman.nether")){
                if(!DreamHitman.game_started) {
                    Plugin plugin = DreamHitman.getPlugin(DreamHitman.class);
                    if (args.length > 0) {
                        if (args[0].equalsIgnoreCase("on")) {
                            plugin.getConfig().set("nether-enabled", true);
                            plugin.reloadConfig();
                            sender.sendMessage(ChatColor.YELLOW + "Enabled nether");

                        } else if (args[0].equalsIgnoreCase("off")) {
                            plugin.getConfig().set("nether-enabled", false);
                            plugin.reloadConfig();
                            sender.sendMessage(ChatColor.YELLOW + "Disabled nether");

                        } else {
                            sender.sendMessage(usage);
                        }
                    } else {
                        sender.sendMessage(usage);
                    }
                }
                else{
                    sender.sendMessage(ChatColor.RED + "You can't use that when the game is started");
                }
            }
            else{
                sender.sendMessage(ChatColor.RED + "No permission");
            }
        }
        else{
            sender.sendMessage(ChatColor.RED + "This command can be only executed by player");
        }

        return true;
    }
}
