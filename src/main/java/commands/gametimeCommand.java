package commands;

import me.kvlike.dreamhitman.DreamHitman;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class gametimeCommand implements CommandExecutor {

    private String usage = ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/gametime <set/reset> <game time in seconds>";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("hitman.gametime")){
            if(args.length >= 1){
                Plugin plugin = DreamHitman.getPlugin(DreamHitman.class);
                if(args[0].equalsIgnoreCase("reset")){
                    plugin.getConfig().set("game-time-in-seconds", 3600);
                    plugin.saveConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&eGame time set back to &c&l1 &ehour"));
                }
                else if(args[0].equalsIgnoreCase("set")){
                    if(args.length > 1){
                        boolean check = true;
                        for(int i = 0; i < args[1].length(); i++){
                            if(!Character.isDigit(args[1].charAt(i))){
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            int newtime = Integer.parseInt(args[1]);
                            plugin.getConfig().set("game-time-in-seconds", newtime);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eGame time set to &c&l%time% &eseconds".replace("%time%", args[1])));
                        }
                        else{
                            sender.sendMessage(ChatColor.RED + "Game time must be an integer!");
                        }
                    }
                    else {
                        sender.sendMessage(usage);
                    }
                }
                else{
                    sender.sendMessage(usage);
                }
                plugin.reloadConfig();
                DreamHitman.timer.setTimecount(plugin.getConfig().getInt("game-time-in-seconds"));
            }
            else{
                sender.sendMessage(usage);
            }
        }
        else{
            sender.sendMessage(ChatColor.RED + "No permission");
        }

        return true;
    }
}
