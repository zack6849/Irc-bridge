/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zack6849.irc.bridgebot;

import java.util.Properties;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 *
 * @author Zack
 */
public class BukkitHandler implements Listener
{
    
    public static App plugin;
    public static Properties config;
    
    public BukkitHandler(App main)
    {
        config = Bot.getConfig().getConfig();
        plugin = main;
    }
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        String message = event.getMessage();
        String username = event.getPlayer().getName();
        String format = config.getProperty("MC-CHAT-FORMAT").replaceAll("%PLAYER%", username).replaceAll("%MESSAGE%", message);
        sendMessage(format);
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        String username = event.getPlayer().getName();
        String format = config.getProperty("MC-JOIN-FORMAT").replaceAll("%PLAYER%", username);
        sendMessage(format);
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        String username = event.getPlayer().getName();
        String format = config.getProperty("MC-LEAVE-FORMAT").replaceAll("%PLAYER%", username);
        sendMessage(format);
    }
    
    @EventHandler
    public void onKick(PlayerKickEvent event)
    {
        String username = event.getPlayer().getName();
        String reason = event.getReason();
        String format = config.getProperty("MC-KICK-FORMAT").replaceAll("%REASON%", reason).replaceAll("%PLAYER%", username);
        sendMessage(format);
    }
    
    @EventHandler
    public void onChatTabComplete(PlayerChatTabCompleteEvent event)
    {
        for (String s : event.getChatMessage().split(" "))
        {
            for (User u : Bot.bot.getUsers())
            {
                if (u.getNick().toLowerCase().startsWith(s.toLowerCase()) && !u.getNick().contains("."))
                {
                    event.getTabCompletions().add(u.getNick());
                }
            }
        }
    }
    
    @EventHandler
    public static void sendMessage(final String s)
    {
        Bukkit.getScheduler().runTaskAsynchronously(App.app, new Runnable()
        {
            public void run()
            {
                for (Channel c : Bot.bot.getChannels())
                {
                    Bot.bot.sendMessage(c, Utils.bukkitcolorize(s));
                }
            }
        });
    }
}
