package listeners;

import commands.hitmanCommand;
import commands.survivalistCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

public class RightClickListener implements Listener {

    private Player getNearest(Player p){
        Player result = null;
        double lastDistance = Double.MAX_VALUE;
        for(Player pl : p.getWorld().getPlayers()) {
            if(p == pl || !survivalistCommand.survivalists_list.contains(pl))
                continue;

            double distance = p.getLocation().distance(pl.getLocation());
            if(distance < lastDistance) {
                lastDistance = distance;
                result = pl;
            }
        }
        return result;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){

        Player clicker = event.getPlayer();
        if(hitmanCommand.hitmen_list.contains(clicker)){
            if(clicker.getInventory().getItemInMainHand().getType().equals(Material.COMPASS)){
                World.Environment world = clicker.getWorld().getEnvironment();
                Player target = getNearest(clicker);
                ItemStack compass = clicker.getInventory().getItemInMainHand();
                CompassMeta meta = (CompassMeta) compass.getItemMeta();
                if(world.equals(World.Environment.NORMAL)){
                    if(target == null){
                        clicker.sendMessage(ChatColor.RED + "No survivalists found!");
                    }
                    else {
                        meta.setLodestone(null);
                        meta.setLodestoneTracked(false);
                        clicker.getInventory().getItemInMainHand().setItemMeta(meta);
                        clicker.setCompassTarget(target.getLocation());
                        clicker.sendMessage(ChatColor.YELLOW + "Now tracking " + ChatColor.RED + target.getDisplayName());
                    }
                }
                else if(world.equals(World.Environment.NETHER)){
                    if(target == null){
                        clicker.sendMessage(ChatColor.RED + "No survivalists found!");
                    }
                    else {
                        meta.setLodestone(target.getLocation());
                        meta.setLodestoneTracked(false);
                        clicker.getInventory().getItemInMainHand().setItemMeta(meta);
                        clicker.sendMessage(ChatColor.YELLOW + "Now tracking " + ChatColor.RED + target.getDisplayName());
                    }
                }
                else if(world.equals(World.Environment.THE_END)){
                    if(target == null){
                        clicker.sendMessage(ChatColor.RED + "No survivalists found!");
                    }
                    else {
                        meta.setLodestone(target.getLocation());
                        meta.setLodestoneTracked(false);
                        clicker.getInventory().getItemInMainHand().setItemMeta(meta);
                        clicker.sendMessage(ChatColor.YELLOW + "Now tracking " + ChatColor.RED + target.getDisplayName());
                    }
                }
            }
        }

    }

}
