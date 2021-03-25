package commands;

import events.HitmanRemoveEvent;
import events.SurvivalistAddEvent;
import events.SurvivalistRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class survivalistCommand implements CommandExecutor {

    public static ArrayList<Player> survivalists_list = new ArrayList<>();

    private String usage = ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/survivalist <add/remove> <player>";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("hitman.survivalist")) {

            if (args.length > 1) {
                if (args[0].equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target instanceof Player) {
                        if (survivalists_list.contains(target)) {
                            sender.sendMessage(ChatColor.RED + "This player is already a survivalist.");
                        } else {
                            if(hitmanCommand.hitmen_list.contains(target)){
                                hitmanCommand.hitmen_list.remove(target);
                                sender.sendMessage(ChatColor.RED + target.getDisplayName() + ChatColor.YELLOW + " is no longer a hitman.");
                                HitmanRemoveEvent hitmanRemoveEvent = new HitmanRemoveEvent(target);
                                Bukkit.getServer().getPluginManager().callEvent(hitmanRemoveEvent);
                            }
                            survivalists_list.add(target);
                            sender.sendMessage(ChatColor.RED + target.getDisplayName() + ChatColor.YELLOW + " is now a survivalist.");
                            SurvivalistAddEvent survivalistAddEvent = new SurvivalistAddEvent(target);
                            Bukkit.getServer().getPluginManager().callEvent(survivalistAddEvent);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "This player does not exist.");
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target instanceof Player) {
                        if (!survivalists_list.contains(target)) {
                            sender.sendMessage(ChatColor.RED + "This player is not a survivalist.");
                        } else {
                            survivalists_list.remove(target);
                            sender.sendMessage(ChatColor.RED + target.getDisplayName() + ChatColor.YELLOW + " is no longer a survivalist.");
                            SurvivalistRemoveEvent survivalistRemoveEvent = new SurvivalistRemoveEvent(target);
                            Bukkit.getServer().getPluginManager().callEvent(survivalistRemoveEvent);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "This player does not exist.");
                    }
                } else {
                    sender.sendMessage(usage);
                }
            } else {
                sender.sendMessage(usage);
            }
        }
        else{
            sender.sendMessage(ChatColor.RED + "No permission");
        }

        return true;
    }
}
