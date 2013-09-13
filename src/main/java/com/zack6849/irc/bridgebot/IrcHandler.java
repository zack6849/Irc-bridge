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
 */

package com.zack6849.irc.bridgebot;

import java.util.Properties;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.KickEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PartEvent;

/**
 *
 * @author Zack6849
 */
public class IrcHandler extends ListenerAdapter
{

    Properties config = Bot.config.getConfig();

    @Override
    public void onMessage(MessageEvent event)
    {
        String username = event.getUser().getNick();
        String channel = event.getChannel().getName();
        String message = event.getMessage();
        String format = config.getProperty("IRC-CHAT-FORMAT").replaceAll("%USER%", username).replaceAll("%CHANNEL%", channel).replaceAll("%MESSAGE%", message);
        Bukkit.broadcastMessage(Utils.irccolorize(ChatColor.translateAlternateColorCodes('&', format)));
    }

    @Override
    public void onPart(PartEvent event)
    {
        String channel = event.getChannel().getName();
        String username = event.getUser().getNick();
        String reason = event.getReason().isEmpty() ? "No Reason specified" : event.getReason();
        String format = config.getProperty("IRC-PART-FORMAT").replaceAll("%USER%", username).replaceAll("%CHANNEL%", channel).replaceAll("%REASON%", reason);
        Bukkit.broadcastMessage(Utils.irccolorize(ChatColor.translateAlternateColorCodes('&', format)));
    }

    @Override
    public void onJoin(JoinEvent event)
    {
        String channel = event.getChannel().getName();
        String username = event.getUser().getNick();
        String format = config.getProperty("IRC-JOIN-FORMAT").replaceAll("%USER%", username).replaceAll("%CHANNEL%", channel);
        Bukkit.broadcastMessage(Utils.irccolorize(ChatColor.translateAlternateColorCodes('&', format)));
    }

    @Override
    public void onAction(ActionEvent event)
    {
        String channel = event.getChannel().getName();
        String username = event.getUser().getNick();
        String action = event.getMessage();
        String format = config.getProperty("IRC-ACTION-FORMAT").replaceAll("%USER%", username).replaceAll("%CHANNEL%", channel).replaceAll("%ACTION%", action);
        Bukkit.broadcastMessage(Utils.irccolorize(ChatColor.translateAlternateColorCodes('&', format)));
    }

    @Override
    public void onKick(KickEvent event)
    {
        String channel = event.getChannel().getName();
        String kicked = event.getRecipient().getNick();
        String kicker = event.getSource().getNick();
        String reason = event.getReason().isEmpty() ? "No Reason specified" : event.getReason();
        String format = config.getProperty("IRC-KICK-FORMAT").replaceAll("%KICKED%", kicked).replaceAll("%KICKER%", kicker).replaceAll("%REASON%", reason).replaceAll("%CHANNEL%", channel);
        Bukkit.broadcastMessage(Utils.irccolorize(ChatColor.translateAlternateColorCodes('&', format)));
    }
}
