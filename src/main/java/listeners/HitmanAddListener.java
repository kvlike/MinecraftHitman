package listeners;

import events.HitmanAddEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HitmanAddListener implements Listener {

    @EventHandler
    public void onHitmanAdd(HitmanAddEvent event){

        ItemStack compass = new ItemStack(Material.COMPASS);

        ItemMeta compass_meta = compass.getItemMeta();
        compass_meta.setDisplayName(ChatColor.YELLOW + "Player tracker");
        ArrayList<String> compass_lore = new ArrayList<>();
        compass_lore.add(ChatColor.DARK_PURPLE + "Right click to track the nearest survivalist");
        compass_meta.setLore(compass_lore);
        compass.setItemMeta(compass_meta);

        event.getPlayer().getInventory().addItem(compass);

    }

}
