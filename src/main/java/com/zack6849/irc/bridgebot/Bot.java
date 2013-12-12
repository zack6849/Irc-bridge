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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.slf4j.impl.SimpleLogger;

/**
 *
 * @author Zack6849
 */
public class Bot
{

    public static Config config;
    public static PircBotX bot;
    public Bot(){
            config = getConfig();
            App.app.getServer().getPluginManager().registerEvents(new BukkitHandler(new App(), config), App.app);
            start();       
    }
    public static Config getConfig()
    {
        if(config == null){
            App.app.getLogger().info("CONFIG WAS NULL! LOADING!");
            config.load();
            App.app.getLogger().info("CONFIG LOADED.");
            return config;
        }
        if(config == null){
            App.app.getLogger().info("CONFIG WAS STILL NULL! WAT?!");
            return config;   
        }
       return null; 
    }

    public static void start()
    {
        try
        {
            Bot.config = new Config();
            config.load();
            System.setProperty(SimpleLogger.SHOW_DATE_TIME_KEY, "true");
            System.setProperty(SimpleLogger.DATE_TIME_FORMAT_KEY, "[HH:mm:ss]");
            System.setProperty(SimpleLogger.SHOW_THREAD_NAME_KEY, "false");
            System.setProperty(SimpleLogger.LEVEL_IN_BRACKETS_KEY, "true");
            System.setProperty(SimpleLogger.SHOW_LOG_NAME_KEY, "false");
            System.out.println("Starting");
            Configuration.Builder<PircBotX> builder = new Configuration.Builder<PircBotX>();
            builder.getListenerManager().addListener(new IrcHandler());
            builder.setName(config.getBotNickname());
            builder.setLogin(config.getBotIdent());
            builder.setAutoNickChange(true);  
            builder.setVersion("BridgeBot v1.0");
            builder.setEncoding(Charset.forName("UTF-8"));
            builder.setServer(config.getServerHostname(), config.getServerPort(), config.getServerPassword());
            builder.setNickservPassword(config.getNickservPassword());
            for (String channel : config.getChannels())
            {
                builder.addAutoJoinChannel(channel);
            }            
            bot = new PircBotX(builder.buildConfiguration());
            bot.startBot();
        } catch (Exception ex)
        {
            bot.sendRaw().rawLineNow("MSG NickServ GHOST " + config.getBotNickname() + " " + config.getNickservPassword());
            bot.sendIRC().changeNick(config.getBotNickname());
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void stop()
    {
        try{
        bot.stopBotReconnect();
        bot.sendIRC().quitServer("Shutting down...");
        }catch(Exception e){
            
        }
    }
}
