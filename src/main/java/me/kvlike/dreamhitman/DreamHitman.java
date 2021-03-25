package me.kvlike.dreamhitman;

import commands.*;
import events.GameStopEvent;
import listeners.*;
import org.bukkit.plugin.java.JavaPlugin;
import timer.Timer;


public final class DreamHitman extends JavaPlugin {

    public static boolean game_started;
    public static Timer timer;

    @Override
    public void onEnable() {
        System.out.println("Enabled Minecraft Hitman Plugin");
        game_started = false;

        // config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // timer
        timer = new Timer(getConfig().getInt("game-time-in-seconds"));

        // register listeners
        getServer().getPluginManager().registerEvents(new HitmanAddListener(), this);
        getServer().getPluginManager().registerEvents(new HitmanRemoveListener(), this);
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
        getServer().getPluginManager().registerEvents(new SurvivalistAddListener(), this);
        getServer().getPluginManager().registerEvents(new SurvivalistRemoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new GameStartListener(), this);
        getServer().getPluginManager().registerEvents(new GameStopListener(), this);
        getServer().getPluginManager().registerEvents(new onDeathListener(), this);
        getServer().getPluginManager().registerEvents(new onRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new TeleportListener(), this);

        // register command
        getCommand("hitman").setExecutor(new hitmanCommand());
        getCommand("survivalist").setExecutor(new survivalistCommand());
        getCommand("startgame").setExecutor(new startCommand());
        getCommand("stopgame").setExecutor(new stopCommand());
        getCommand("hitmanreload").setExecutor(new reloadCommand());
        getCommand("gametime").setExecutor(new gametimeCommand());
        getCommand("nether").setExecutor(new netherCommand());

    }

    @Override
    public void onDisable() {

        getServer().getPluginManager().callEvent(new GameStopEvent());

    }


}
