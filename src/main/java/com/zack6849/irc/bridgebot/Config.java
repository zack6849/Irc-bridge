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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Zack6849
 */
public class Config
{

    private Properties config;
    private File config_file;
    private String nickservAccount;
    private String nickservPassword;
    private String botNickname;
    private String botIdent;
    private String botPrefix;
    private String serverHostname;
    private String serverPassword;
    private List<String> channels;
    private List<String> admins;
    private int serverPort;
    private int messageDelay;
    private boolean debug;

    public void load()
    {
        try{
         config_file = new File(App.app.getDataFolder(), "bot.properties");
        App.app.getDataFolder().mkdir();
        System.out.println(config_file.exists());
        if (!config_file.exists())
        {
            System.out.println("No properties file found, creating one for you!");
            config_file.createNewFile();
            BufferedReader s = new BufferedReader(new InputStreamReader(Config.class.getResourceAsStream("/bot.properties")));
            String tmp = "";
            BufferedWriter out = new BufferedWriter(new FileWriter(getConfig_file()));
            while ((tmp = s.readLine()) != null)
            {
                out.write(tmp);
                out.flush();
                out.newLine();
            }
            out.flush();
            out.close();
        }
        System.out.println("[!!] Done! [!!]");
        config = new Properties();
        config.load(new FileInputStream(config_file));
        setServerPassword(getConfig().getProperty("SERVER-PASSWORD"));
        setServerHostname(getConfig().getProperty("SERVER-HOST"));
        setBotIdent(getConfig().getProperty("BOT-IDENT"));
        setBotNickname(getConfig().getProperty("BOT-NICKNAME"));
        setBotPrefix(getConfig().getProperty("BOT-PREFIX"));
        setNickservAccount(getConfig().getProperty("NICKSERV-ACCOUNT"));
        setNickservPassword(getConfig().getProperty("NICKSERV-PASSWORD"));
        setChannels(Arrays.asList(getConfig().getProperty("BOT-CHANNELS").split(" ")));
        setAdmins(Arrays.asList(getConfig().getProperty("BOT-ADMINS").split(" ")));
        setMessageDelay((int) Integer.valueOf(getConfig().getProperty("BOT-MESSAGE-DELAY")));
        setServerPort((int) Integer.valueOf(getConfig().getProperty("SERVER-PORT")));
        setDebug((boolean) Boolean.valueOf(getConfig().getProperty("DEBUG")));   
        }catch(Exception ex){
          ex.printStackTrace();
        }
    }
    /* 
     * 
     * +-----------------------------------------------------------------------------+
     * |    All code below this is auto-generated and fairly pointless to read,      |
     * |            but feel free to waste your time reading it.                     |
     * +-----------------------------------------------------------------------------+
     * 
     */
    
    /**
     * @return the config
     */
    public Properties getConfig()
    {
        return config;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(Properties config)
    {
        this.config = config;
    }

    /**
     * @return the config_file
     */
    public File getConfig_file()
    {
        return config_file;
    }

    /**
     * @param config_file the config_file to set
     */
    public void setConfig_file(File config_file)
    {
        this.config_file = config_file;
    }

    /**
     * @return the nickservAccount
     */
    public String getNickservAccount()
    {
        return nickservAccount;
    }

    /**
     * @param nickservAccount the nickservAccount to set
     */
    public void setNickservAccount(String nickservAccount)
    {
        this.nickservAccount = nickservAccount;
    }

    /**
     * @return the nickservPassword
     */
    public String getNickservPassword()
    {
        return nickservPassword;
    }

    /**
     * @param nickservPassword the nickservPassword to set
     */
    public void setNickservPassword(String nickservPassword)
    {
        this.nickservPassword = nickservPassword;
    }

    /**
     * @return the botNickname
     */
    public String getBotNickname()
    {
        return botNickname;
    }

    /**
     * @param botNickname the botNickname to set
     */
    public void setBotNickname(String botNickname)
    {
        this.botNickname = botNickname;
    }

    /**
     * @return the botIdent
     */
    public String getBotIdent()
    {
        return botIdent;
    }

    /**
     * @param botIdent the botIdent to set
     */
    public void setBotIdent(String botIdent)
    {
        this.botIdent = botIdent;
    }

    /**
     * @return the serverHostname
     */
    public String getServerHostname()
    {
        return serverHostname;
    }

    /**
     * @param serverHostname the serverHostname to set
     */
    public void setServerHostname(String serverHostname)
    {
        this.serverHostname = serverHostname;
    }

    /**
     * @return the serverPassword
     */
    public String getServerPassword()
    {
        return serverPassword;
    }

    /**
     * @param serverPassword the serverPassword to set
     */
    public void setServerPassword(String serverPassword)
    {
        this.serverPassword = serverPassword;
    }

    /**
     * @return the channels
     */
    public List<String> getChannels()
    {
        return channels;
    }

    /**
     * @param channels the channels to set
     */
    public void setChannels(List<String> channels)
    {
        this.channels = channels;
    }

    /**
     * @return the admins
     */
    public List<String> getAdmins()
    {
        return admins;
    }

    /**
     * @param admins the admins to set
     */
    public void setAdmins(List<String> admins)
    {
        this.admins = admins;
    }

    /**
     * @return the serverPort
     */
    public int getServerPort()
    {
        return serverPort;
    }

    /**
     * @param serverPort the serverPort to set
     */
    public void setServerPort(int serverPort)
    {
        this.serverPort = serverPort;
    }

    /**
     * @return the messageDelay
     */
    public int getMessageDelay()
    {
        return messageDelay;
    }

    /**
     * @param messageDelay the messageDelay to set
     */
    public void setMessageDelay(int messageDelay)
    {
        this.messageDelay = messageDelay;
    }

    /**
     * @return the debug
     */
    public boolean isDebug()
    {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug)
    {
        this.debug = debug;
    }

    /**
     * @return the botPrefix
     */
    public String getBotPrefix()
    {
        return botPrefix;
    }

    /**
     * @param botPrefix the botPrefix to set
     */
    public void setBotPrefix(String botPrefix)
    {
        this.botPrefix = botPrefix;
    }
    
     public boolean isAdmin(String username, String hostmask)
    {
        List<String> adminsl = Arrays.asList(getConfig().getProperty("BOT-ADMINS").split(" "));
        boolean hostmatch = false;
        boolean nickmatch = false;
        String nick;
        String hostname;
        for (String host : adminsl)
        {
            nick = host.split("\\@")[0];
            hostname = host.split("\\@")[1];
            Pattern p = Pattern.compile(hostname.replaceAll("\\.", "\\\\.").replaceAll("\\*", ".*"));
            Matcher m = p.matcher(hostmask);
            if (m.find())
            {
                hostmatch = true;
            }
            p = Pattern.compile(nick.replaceAll("\\*", ".*"));
            m = p.matcher(nick);
            if (m.find())
            {
                nickmatch = true;
            }
        }
        return nickmatch && hostmatch;
    }
}
