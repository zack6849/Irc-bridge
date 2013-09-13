package com.zack6849.irc.bridgebot;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.pircbotx.Channel;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin
{

    public static Plugin app;

    @Override
    public void onEnable()
    {
        Bot.start();
        App.app = this;
        getServer().getPluginManager().registerEvents(new BukkitHandler(this), this);
    }

    @Override
    public void onDisable()
    {
        Bot.stop();
    }
}