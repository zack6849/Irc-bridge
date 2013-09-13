/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zack6849.irc.bridgebot;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

/**
 *
 * @author Zack
 */
public class Bot
{

    public static Config config;
    public static PircBotX bot;

    public static Config getConfig()
    {
        return Bot.config;
    }

    public static void start()
    {
        try
        {
            bot = new PircBotX();
            Bot.config = new Config();
            config.load();
            bot.getListenerManager().addListener(new IrcHandler());
            bot.setName(config.getBotNickname());
            bot.setLogin(config.getBotIdent());
            bot.setAutoNickChange(true);
            bot.setVerbose(true);
            bot.setVersion("BridgeBot v1.0");
            bot.setEncoding(Charset.forName("UTF-8"));
            bot.connect(config.getServerHostname(), config.getServerPort(), config.getServerPassword());
            bot.identify(config.getNickservAccount() + " " + config.getNickservPassword());
            for (String s : config.getChannels())
            {
                bot.joinChannel(s);
            }
        } catch (Exception ex)
        {
            bot.sendRawLineNow("MSG NickServ GHOST " + config.getBotNickname() + " " + config.getNickservPassword());
            bot.setName(config.getBotNickname());
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void stop()
    {
        bot.shutdown(true);
    }
}
