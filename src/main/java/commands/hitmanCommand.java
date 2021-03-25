package commands;

import events.HitmanAddEvent;
import events.HitmanRemoveEvent;
import events.SurvivalistRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class hitmanCommand implements CommandExecutor {

    public static ArrayList<Player> hitmen_list = new ArrayList<>();

    private String usage = ChatColor.RED + "Usage: " + ChatColor.YELLOW + "/hitman <add/remove> <player>";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("hitman.hitman")) {

            if (args.length > 1) {
                if (args[0].equalsIgnoreCase("add")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target instanceof Player) {
                        if (hitmen_list.contains(target)) {
                            sender.sendMessage(ChatColor.RED + "This player is already a hitman.");
                        } else {
                            if(survivalistCommand.survivalists_list.contains(target)){
                                survivalistCommand.survivalists_list.remove(target);
                                sender.sendMessage(ChatColor.RED + target.getDisplayName() + ChatColor.YELLOW + " is no longer a survivalist.");
                                SurvivalistRemoveEvent survivalistRemoveEvent = new SurvivalistRemoveEvent(target);
                                Bukkit.getServer().getPluginManager().callEvent(survivalistRemoveEvent);
                            }
                            hitmen_list.add(target);
                            sender.sendMessage(ChatColor.RED + target.getDisplayName() + ChatColor.YELLOW + " is now a hitman.");
                            HitmanAddEvent hitmanAddEvent = new HitmanAddEvent(target);
                            Bukkit.getServer().getPluginManager().callEvent(hitmanAddEvent);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "This player does not exist.");
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target instanceof Player) {
                        if (!hitmen_list.contains(target)) {
                            sender.sendMessage(ChatColor.RED + "This player is not a hitman.");
                        } else {
                            hitmen_list.remove(target);
                            sender.sendMessage(ChatColor.RED + target.getDisplayName() + ChatColor.YELLOW + " is no longer a hitman.");
                            HitmanRemoveEvent hitmanRemoveEvent = new HitmanRemoveEvent(target);
                            Bukkit.getServer().getPluginManager().callEvent(hitmanRemoveEvent);
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
