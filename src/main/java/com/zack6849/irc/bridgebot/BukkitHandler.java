/*
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 */
package com.zack6849.irc.bridgebot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 *
 * @author Zack6849
 */
public class BukkitHandler implements Listener
{

    public static App plugin;
    public static Properties config;
    public static Config conf;
    public BukkitHandler(App main, Config conf)
    {
        App.app.getLogger().info("loading config file!");
        config = Bot.getConfig().getConfig();
        plugin = main;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        App.app.getLogger().info("Message sent, sending to irc!");
        String message = event.getMessage();
        String username = event.getPlayer().getDisplayName();
        String playername = event.getPlayer().getName();
        String format = config.getProperty("MC-CHAT-FORMAT").replace("%PLAYER%", username).replace("%MESSAGE%", message).replace("%PLAYERNAME%", playername);
        sendMessage(Utils.bukkitcolorize(format));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        String username = event.getPlayer().getDisplayName();
        String playername = event.getPlayer().getName();
        String format = config.getProperty("MC-JOIN-FORMAT").replace("%PLAYER%", username).replace("%PLAYERNAME%", playername);
        sendMessage(Utils.bukkitcolorize(format));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        String username = event.getPlayer().getDisplayName();
        String playername = event.getPlayer().getName();
        String format = config.getProperty("MC-LEAVE-FORMAT").replace("%PLAYER%", username).replace("%PLAYERNAME%", playername);
        sendMessage(Utils.bukkitcolorize(format));
    }

    @EventHandler
    public void onKick(PlayerKickEvent event)
    {
        String username = event.getPlayer().getDisplayName();
        String playername = event.getPlayer().getName();
        String reason = event.getReason();
        String format = config.getProperty("MC-KICK-FORMAT").replace("%REASON%", reason).replace("%PLAYER%", username).replace("%PLAYERNAME%", playername);
        sendMessage(Utils.bukkitcolorize(format));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
        String username = event.getEntity().getDisplayName();
        String playername = event.getEntity().getName();
        String message = event.getDeathMessage();
        String format = config.getProperty("MC-DEATH-FORMAT").replace("%MESSAGE%", message).replace("%PLAYER%", username).replace("%PLAYERNAME%", playername);
        sendMessage(Utils.bukkitcolorize(format));
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event)
    {
        String command = event.getMessage().substring(1);
        String[] args = event.getMessage().split(" ");
        if (command.startsWith("me") && args.length > 1)
        {
            String username = event.getPlayer().getDisplayName();
            String playername = event.getPlayer().getName();
            String message = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
            String format = config.getProperty("MC-ACTION-FORMAT").replace("%MESSAGE%", message).replace("%PLAYER%", username).replace("%PLAYERNAME%", playername);
            sendMessage(Utils.bukkitcolorize(format));
        }
    }

    @EventHandler
    public void onChatTabComplete(PlayerChatTabCompleteEvent event)
    {
        HashSet<String> autocomplete = new HashSet<String>();
        for (String s : event.getChatMessage().split(" "))
        {
            for (Channel c : Bot.bot.getUserChannelDao().getAllChannels())
            {
                for(User u : Bot.bot.getUserChannelDao().getUsers(c)){
                     if (u.getNick().toLowerCase().startsWith(s.toLowerCase()) && !u.getNick().contains(".")){
                        autocomplete.add(u.getNick());   
                     }
                }
            }
        }
        for(String s : autocomplete){
            event.getTabCompletions().add(s);
        }
    }

    @EventHandler
    public static void sendMessage(final String s)
    {
        App.app.getLogger().info("Sending message " + s);
        for (Channel c : Bot.bot.getUserChannelDao().getAllChannels())
        {
            App.app.getLogger().info("to " + c.getName());
            c.send().message(Utils.bukkitcolorize(s));
        }
    }

    public static String getPlayerList()
    {
        StringBuilder sb = new StringBuilder();
        if (Bukkit.getOnlinePlayers().length == 0)
        {
            return "No players currently online! D:";
        }
        for (Player p : Bukkit.getOnlinePlayers())
        {
            sb.append(p.getDisplayName()).append("(").append(p.getName()).append(")").append(" ");
        }
        return sb.toString().trim();
    }
}
