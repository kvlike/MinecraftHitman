package timer;

import events.GameStopEvent;
import me.kvlike.dreamhitman.DreamHitman;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;


public class Timer {

    private int timecount;
    private int taskID;
    private final Plugin plugin = DreamHitman.getPlugin(DreamHitman.class);
    private int time;

    public int getTimecount() {
        return timecount;
    }

    public void setTimecount(int timecount) {
        this.timecount = timecount;
    }

    public Timer(int timecount){
        this.timecount = timecount;
    }

    public void startTimer(){
        time = timecount;
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        this.taskID = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(time == 0){
                    Bukkit.broadcastMessage(ChatColor.RED + "Time is up!");
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Survivalist won the game!");
                    Bukkit.getServer().getPluginManager().callEvent(new GameStopEvent());
                    return;
                }
                if(time == 3600){
                    Bukkit.broadcastMessage(ChatColor.RED + "1 hour remains");
                }
                if(time == 1800){
                    Bukkit.broadcastMessage(ChatColor.RED + "30 minutes remain");
                }
                else if(time == 900){
                    Bukkit.broadcastMessage(ChatColor.RED + "15 minutes remain");
                }
                else if(time == 600){
                    Bukkit.broadcastMessage(ChatColor.RED + "10 minutes remain");
                }
                else if(time == 300){
                    Bukkit.broadcastMessage(ChatColor.RED + "5 minutes remain");
                }
                else if(time == 60){
                    Bukkit.broadcastMessage(ChatColor.RED + "1 minute remains");
                }
                else if(time == 10){
                    Bukkit.broadcastMessage(ChatColor.RED + "10 seconds remain");
                }
                else if(time == 5){
                    Bukkit.broadcastMessage(ChatColor.RED + "5 seconds remain");
                }
                else if(time == 4){
                    Bukkit.broadcastMessage(ChatColor.RED + "4 seconds remain");
                }
                else if(time == 3){
                    Bukkit.broadcastMessage(ChatColor.RED + "3 seconds remain");
                }
                else if(time == 2){
                    Bukkit.broadcastMessage(ChatColor.RED + "2 seconds remain");
                }
                else if(time == 1){
                    Bukkit.broadcastMessage(ChatColor.RED + "1 second remains");
                }
                String minutes = String.valueOf(time / 60);
                String seconds = String.valueOf(time % 60);
                String abmsg = minutes + ":" + seconds;
                if(time % 60 < 10){
                    abmsg = minutes + ":0" + seconds;
                }
                if(time / 60 < 10){
                    abmsg = "0" + abmsg;
                }
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.YELLOW + abmsg));
                }
                time--;
            }
        }, 0L, 20L);
        return;

    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
        DreamHitman.game_started = false;
    }
}
